package cc.tianbin.data_struct.ds1_堆;

import cc.tianbin.common.model.Person;
import cc.tianbin.common.util.SysOut;
import cc.tianbin.common.util.SysRandom;
import cc.tianbin.data_struct.Node;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 增强堆，新增了 反向索引功能
 */
public class HeapGreater {

    @Test
    public void testCase() {
        int[] arr = SysRandom.generateArr(10);
        SysOut.printArray(arr);

        int limit = 10;
        MaxHeapGreater maxHeap = new MaxHeapGreater(new MyComparator());
        for (int i : arr) {
            maxHeap.push(new Node(Person.buildByAge(i)));
        }
        for (int i = 0; i < limit; i++) {
            Person p = (Person) maxHeap.pop().value;
            System.out.println(p.getAge());
        }
    }

    private class MyComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            Person p1 = (Person) o1.value;
            Person p2 = (Person) o2.value;
            return p1.getAge() - p2.getAge();
        }
    }

    public class MaxHeapGreater implements MyHeap<Node> {

        private ArrayList<Node> heap;
        private HashMap<Node, Integer> indexMap;
        private int heapSize;
        private Comparator<? super Node> comparator;

        public MaxHeapGreater(Comparator<? super Node> comparator) {
            this.heap = new ArrayList<>();
            this.indexMap = new HashMap<>();
            this.heapSize = 0;
            this.comparator = comparator;
        }

        @Override
        public boolean isEmpty() {
            return heapSize == 0;
        }

        @Override
        public boolean isFull() {
            return false;
        }

        @Override
        public boolean contains(Node obj) {
            return indexMap.containsKey(obj);
        }

        @Override
        public void push(Node obj) {
            heap.add(obj);

            heapInsert(heapSize++);
        }

        /**
         * 当前在在下面，看怎么网上调一调
         */
        private void heapInsert(int curIndex) {
            int fatherIndex = (curIndex - 1) / 2;
            while (comparator.compare(heap.get(curIndex), heap.get(fatherIndex)) > 0) {
                swap(curIndex, fatherIndex);
                curIndex = fatherIndex;
                fatherIndex = (curIndex - 1) / 2;
            }
        }

        private void swap(int i, int j) {
            Node o1 = heap.get(i);
            Node o2 = heap.get(j);
            heap.set(i, o2);
            heap.set(j, o1);
            indexMap.put(o2, i);
            indexMap.put(o1, j);
        }

        @Override
        public Node peek() {
            return heap.get(0);
        }

        @Override
        public Node pop() {
            Node heap0 = heap.get(0);
            swap(0, heapSize - 1);
            heap.remove(--heapSize);
            indexMap.remove(heap0);

            heapify(0);
            return heap0;
        }

        private void heapify(int curIndex) {
            int leftChildIndex = curIndex * 2 + 1;
            while (leftChildIndex < heapSize) {
                int largerChildIndex = leftChildIndex + 1 < heapSize && comparator.compare(heap.get(leftChildIndex + 1), heap.get(leftChildIndex)) > 0
                        ? leftChildIndex + 1
                        : leftChildIndex;

                if (comparator.compare(heap.get(largerChildIndex), heap.get(curIndex)) < 0) {
                    // ok了，当前位置刚刚好，无需再往下沉
                    break;
                }

                swap(curIndex, largerChildIndex);
                curIndex = largerChildIndex;
                leftChildIndex = curIndex * 2 + 1;
            }
        }

        @Override
        public void remove(Node obj) {
            Integer index = indexMap.get(obj);
            indexMap.remove(obj);

            Node replace = heap.get(heapSize - 1);
            heap.remove(--heapSize);

            if (comparator.compare(obj, replace) == 0) {
                // 最后一个元素 就是要删除的
                return;
            }

            heap.set(index, replace);
            indexMap.put(replace, index);
            resign(replace);
        }

        @Override
        public void resign(Node obj) {
            Integer index = indexMap.get(obj);

            heapInsert(index);
            heapify(index);
        }

        @Override
        public List<Node> getAllElements() {
            List<Node> allElements = new ArrayList<>();
            for (Node e : heap) {
                allElements.add(e);
            }
            return allElements;
        }
    }
}
