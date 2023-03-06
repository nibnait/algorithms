package cc.tianbin.data_struct.ds1_堆;

import cc.tianbin.common.util.SwapUtil;
import cc.tianbin.common.util.SysOut;
import cc.tianbin.common.util.SysRandom;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 大根堆：可以理解为在一个"完全二叉树"中，每一颗子树的最大值都是头节点
 * 大根堆：可以理解为在一个"完全二叉树"中，每一颗子树的最小值都是头节点
 * <p>
 * 因此堆可以用数组表示
 * heap[i] 的父节点: heap[(i - 1) / 2]
 * heap[i] 的子节点: heap[i * 2 + 1], heap[i * 2 + 2]
 * <p>
 * 可见，大小根堆的区别近在于建堆时的比较方法不同而已。
 * <p>
 * 当前的类 为手写的一个大根堆
 * Created by nibnait on 2020-01-07
 */
public class Heap {

    @Test
    public void testCase() {
        int limit = 10;
        int[] arr = SysRandom.generateArrNaturalNum(limit);
        SysOut.printArray(arr);

        MapHeap maxHeap = new MapHeap(limit);
        for (int i : arr) {
            maxHeap.push(i);
        }
        for (int i = 0; i < limit; i++) {
            System.out.println(maxHeap.pop());
        }
    }

    public class MapHeap implements MyHeap<Integer> {

        private int[] heap;
        private final int limit;
        private int heapSize;

        public MapHeap(int limit) {
            this.heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        @Override
        public boolean isEmpty() {
            return heapSize == 0;
        }

        @Override
        public boolean isFull() {
            return heapSize == limit;
        }

        @Override
        public void push(Integer obj) {
            if (isFull()) {
                throw new UnsupportedOperationException("heap is full");
            }
            heap[heapSize] = obj;
            heapInsert(heapSize++);
        }

        /**
         * 先把 obj 加到数组末尾
         * 看他能不能在往上调一调
         */
        private void heapInsert(int curIndex) {
            int fatherIndex = (curIndex - 1) / 2;
            while (heap[curIndex] > heap[fatherIndex]) {
                SwapUtil.swap(heap, curIndex, fatherIndex);
                curIndex = fatherIndex;
                fatherIndex = (curIndex - 1) / 2;
            }
        }

        @Override
        public Integer peek() {
            return heap[0];
        }

        @Override
        public Integer pop() {
            int heap0 = heap[0];
            SwapUtil.swap(heap, 0, --heapSize);
            heapify(0);
            return heap0;
        }

        /**
         * 当前 heap[0] 为原来的 heap[heapSize-1]
         * 所以要把 heap[0] 往下调一调
         */
        private void heapify(int curIndex) {
            int leftChildIndex = curIndex * 2 + 1;
            while (leftChildIndex < heapSize) {
                int largerChildIndex = leftChildIndex + 1 < heapSize && heap[leftChildIndex] < heap[leftChildIndex + 1]
                        ? leftChildIndex + 1
                        : leftChildIndex;

                if (heap[largerChildIndex] < heap[curIndex]) {
                    // ok了，当前位置刚刚好，无需再往下沉
                    break;
                }

                SwapUtil.swap(heap, curIndex, largerChildIndex);
                curIndex = largerChildIndex;
                leftChildIndex = curIndex * 2 + 1;
            }
        }

        @Override
        public List<Integer> getAllElements() {
            List<Integer> allElements = new ArrayList<>();
            for (int e : heap) {
                allElements.add(e);
            }
            return allElements;
        }
    }
}