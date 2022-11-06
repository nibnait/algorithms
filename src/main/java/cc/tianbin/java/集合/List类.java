package cc.tianbin.java.集合;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.BlockingQueue;

/**
 * @author nibnait
 * @version $Id: List.java, v 0.1 2019-08-15 上午10:58 nibnait Exp $$
 */
public class List类 {
    public static void main(String[] args) {

        Queue queue;
        Deque linkedList = new LinkedList();
        linkedList.push(1);

        BlockingQueue blockingQueue;

        AbstractQueue abstractQueue;
        PriorityQueue priorityQueue;

        ArrayList arrayList;


    }

    @Test
    public void test01() {
        // grow 了几次？
        List<Integer> list = new ArrayList<>(0);
        // grow 了几次
//        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
    }

    @Test
    public void test02() {
        // 代码里的一些固定长度的list，可以用。
        // 无法对都不能对这份儿list进行 add remove 操作，只能修改单个索引下的值
        List<String> list1 = Arrays.asList();
        list1.add("");

        // 使用场景：一个 if/else 里面需要返回 空list 的时候。
        // 使用它，不需要再进行创建对象
        List<String> list2 = Collections.emptyList();
        list2.add("a");
    }
}