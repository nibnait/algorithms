package cc.tianbin.demo.java.并发.JUC.JUC_demo;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.*;
import junit.framework.TestCase;
import lombok.Data;
import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Disruptor
 * Created by nibnait on 2019-09-01
 */
public class DisruptorDemo extends TestCase {

    // 队列中的元素
    @Data
    class Event {
        private long value;
    }

    @Test
    public void testMain() throws InterruptedException {

        // RingBuffer生产工厂,初始化RingBuffer的时候使用
        EventFactory<Event> factory = new EventFactory<Event>() {
            @Override
            public Event newInstance() {
                return new Event();
            }
        };

        // 指定RingBuffer的大小
        int bufferSize = 16;

        // 创建disruptor，采用单生产者模式
        /**
         * @see RingBuffer#createMultiProducer(EventFactory, int, WaitStrategy)
         */
        Disruptor<Event> disruptor = new Disruptor(factory, bufferSize,
                Executors.newSingleThreadExecutor(), ProducerType.SINGLE,
                new BlockingWaitStrategy());

        //注册事件处理器
        /**
         * 创建 SequenceBarrier 对象，用于接收 ringBuffer 中的可消费事件
         * 创建 BatchEventProcessor，负责消费事件
         * 绑定 BatchEventProcessor对象的异常处理类
         * 调用 ringBuffer.addGatingSequences，将消费者的 Sequence 传给 ringBuffer
         */
        disruptor.handleEventsWith(
                (event, sequence, endOfBatch) -> {
                    System.out.println("Event: " + event.getValue());
                });

        //启动Disruptor
        /**
         * @see ConsumerRepository#add(EventProcessor, EventHandler, SequenceBarrier)
         * @see EventProcessorInfo#start(Executor)
         */
        disruptor.start();

        //获取RingBuffer
        RingBuffer<Event> ringBuffer = disruptor.getRingBuffer();

        //生产Event
        for (long l = 0; true; l++) {
            // 获取下一个可用位置的下标
            long sequence = ringBuffer.next();
            try {
                // 返回可用位置的元素
                Event event = ringBuffer.get(sequence);
                // 设置该位置元素的值
                event.setValue(l);
            } finally {
                ringBuffer.publish(sequence);
            }
            Thread.sleep(1000);
        }

    }
}
