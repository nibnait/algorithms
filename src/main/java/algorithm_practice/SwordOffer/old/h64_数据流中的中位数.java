package algorithm_practice.SwordOffer.old;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 题目：如何得到一个数据流中的中位数？
 *    如果从数据流中读出奇数个数值，那么中位数就是所有值排序之后位于中间的数值。
 *      如果数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 *
 *【法1】：用数组来装这个数据流，
 *     1. 直接往数组里放数字 O（1），然后基于"e29_数组中出现次数超过一半的数字"的Partition的思想，来寻找数据流中的中位数O（N）
 *     2. 在插入的时候，一边排序一边插 O（N），(每插入一个数字，其后面的数字向后移动的格数不确定，最坏情况：向后移动N次)
 *        这样，寻找中位数就可以直接在O（1）的时间拿到
 *【法2】：用排序的链表来装
 *      时间复杂度与用排序的数组相同
 *      （为了得到中位数，我们可以定义两个指针 指向链表的中间结点，那么我们就可以在O（1）的时间得出中位数了）
 *【法3】：用二叉搜索树来装这个数据流
 *      插入的时间复杂度：平均O（logN），但是最差情况有可能跟排序的链表相同：O（N）
 *      为了得到中位数，可以在二叉树结点中添加一个表示子树结点数目的字段，
 *      有了这个字段 我们就可以在平均O（logN）的时间内得到中位数，但最差情况仍然需要O（N）
 *【法4】：AVL树：平衡二叉搜索树 √
 *      将平衡因子改为左右子树结点数目之差，
 *      这样我们就可以用O（logN）时间往AVL树中插入一个新结点
 *      在O（1）时间得到所有结点的中位数
 *【法5】：建立一个大根堆和一个小根堆 √
 *      小根堆的堆顶(最小元素) 大于 大根堆的堆顶(最大元素)
 *      当数据总数目是偶数时，把新数据插入到小根堆中，否则插入大根堆中（保证两个堆中数据的数目之差不超过1）
 *      这样我们往堆中插入的时间效率为O（logN），
 *      由于只需O（1）的时间即可得到堆顶元素，因此得到中位数的时间效率就是O（1）
 *
 * Created by nibnait on 2016/10/3.
 */
public class h64_数据流中的中位数 {

    private static class Heap<T> {
        // 堆中元素存放的集合
        private List<T> data;
        // 比较器
        private Comparator<T> cmp;
        /**
         * 构造函数
         *
         * @param cmp 比较器对象
         */
        public Heap(Comparator<T> cmp) {
            this.cmp = cmp;
            this.data = new ArrayList<>(64);
        }
        /**
         * 向上调整堆
         *
         * @param idx 被上移元素的起始位置
         */
        public void shiftUp(int idx) {
            // 检查是位置是否正确
            if (idx < 0 || idx >= data.size()) {
                throw new IllegalArgumentException(idx + "");
            }
            // 获取开始调整的元素对象
            T intent = data.get(idx);
            // 如果不是根元素，则需要上移
            while (idx > 0) {
                // 找父元素对象的位置
                int parentIdx = (idx - 1) / 2;
                // 获取父元素对象
                T parent = data.get(parentIdx);
                //上移的条件，子结点比父结点大，此处定义的大是以比较器返回值为准
                if (cmp.compare(intent, parent) > 0) {
                    // 将父结点向下放
                    data.set(idx, parent);
                    idx = parentIdx;
                    // 记录父结点下放的位置
                }
                // 子结点不比父结点大，说明父子路径已经按从大到小排好顺序了，不需要调整了
                else {
                    break;
                }
            }
            // index此时记录是的最后一个被下放的父结点的位置（也可能是自身），
            // 所以将最开始的调整的元素值放入index位置即可
            data.set(idx, intent);
        }
        /**
         * 向下调整堆
         *
         * @param idx 被下移的元素的起始位置
         */
        public void shiftDown(int idx) {
            // 检查是位置是否正确
            if (idx < 0 || idx >= data.size()) {
                throw new IllegalArgumentException(idx + "");
            }
            // 获取开始调整的元素对象
            T intent = data.get(idx);
            // 获取开始调整的元素对象的左子结点的元素位置
            int leftIdx = idx * 2 + 1;
            // 如果有左子结点
            while (leftIdx < data.size()) {
                // 取左子结点的元素对象，并且假定其为两个子结点中最大的
                T maxChild = data.get(leftIdx);
                // 两个子结点中最大结点元素的位置，假定开始时为左子结点的位置
                int maxIdx = leftIdx;
                // 获取右子结点的位置
                int rightIdx = leftIdx + 1;
                // 如果有右子结点
                if (rightIdx < data.size()) {
                    T rightChild = data.get(rightIdx);
                    // 找出两个子结点中的最大子结点
                    if (cmp.compare(rightChild, maxChild) > 0) {
                        maxChild = rightChild;
                        maxIdx = rightIdx;
                    }
                }
                // 如果最大子结点比父结点大，则需要向下调整
                if (cmp.compare(maxChild, intent) > 0) {
                    // 将较大的子结点向上移
                    data.set(idx, maxChild);
                    // 记录上移结点的位置
                    idx = maxIdx;
                    // 找到上移结点的左子结点的位置
                    leftIdx = 2 * idx + 1;
                }
                // 最大子结点不比父结点大，说明父子路径已经按从大到小排好顺序了，不需要调整了
                else {
                    break;
                }
            }
            // index此时记录是的最后一个被上移的子结点的位置（也可能是自身），
            // 所以将最开始的调整的元素值放入index位置即可
            data.set(idx, intent);
        }
        /**
         * 添加一个元素
         *
         * @param item 添加的元素
         */
        public void add(T item) {
            // 将元素添加到最后
            data.add(item);
            // 上移，以完成重构
            shiftUp(data.size() - 1);
        }
        /**
         * 删除堆顶结点
         *
         * @return 堆顶结点
         */
        public T deleteTop() {
            // 如果堆已经为空，就抛出异常
            if (data.isEmpty()) {
                throw new RuntimeException("The heap is empty.");
            }
            // 获取堆顶元素
            T first = data.get(0);
            // 删除最后一个元素
            T last = data.remove(data.size() - 1);
            // 删除元素后，如果堆为空的情况，说明删除的元素也是堆顶元素
            if (data.size() == 0) {
                return last;
            } else {
                // 将删除的元素放入堆顶
                data.set(0, last);
                // 自上向下调整堆
                shiftDown(0);
                // 返回堆顶元素
                return first;
            }
        }
        /**
         * 获取堆顶元素，但不删除
         *
         * @return 堆顶元素
         */
        public T getTop() {
            // 如果堆已经为空，就抛出异常
            if (data.isEmpty()) {
                throw new RuntimeException("The heap is empty.");
            }
            return data.get(0);
        }
        /**
         * 获取堆的大小
         *
         * @return 堆的大小
         */
        public int size() {
            return data.size();
        }
        /**
         * 判断堆是否为空
         *
         * @return 堆是否为空
         */
        public boolean isEmpty() {
            return data.isEmpty();
        }
        /**
         * 清空堆
         */
        public void clear() {
            data.clear();
        }
        /**
         * 获取堆中所有的数据
         *
         * @return 堆中所在的数据
         */
        public List<T> getData() {
            return data;
        }
    }
    /**
     * 升序比较器
     */
    private static class IncComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }
    /**
     * 降序比较器
     */
    private static class DescComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }
    private static class DynamicArray {
        private Heap<Integer> max;
        private Heap<Integer> min;
        public DynamicArray() {
            max = new Heap<>(new IncComparator());
            min = new Heap<>(new DescComparator());
        }
        /**
         * 插入数据
         *
         * @param num 待插入的数据
         */
        public void insert(Integer num) {
            // 已经有偶数个数据了（可能没有数据）
            // 数据总数是偶数个时把新数据插入到小堆中
            if ((min.size() + max.size()) % 2 == 0) {
                // 大堆中有数据，并且插入的元素比大堆中的元素小
                if (max.size() > 0 && num < max.getTop()) {
                    // 将num加入的大堆中去
                    max.add(num);
                    // 删除堆顶元素，大堆中的最大元素
                    num = max.deleteTop();
                }
                // num插入到小堆中，当num小于大堆中的最大值进，
                // num就会变成大堆中的最大值，见上面的if操作
                // 如果num不小于大堆中的最大值，num就是自身
                min.add(num);
            }
            // 数据总数是奇数个时把新数据插入到大堆中
            else {
                // 小堆中有数据，并且插入的元素比小堆中的元素大
                if (min.size() > 0 && num > min.size()) {
                    // 将num加入的小堆中去
                    min.add(num);
                    // 删除堆顶元素，小堆中的最小元素
                    num = min.deleteTop();
                }
                // num插入到大堆中，当num大于小堆中的最小值进，
                // num就会变成小堆中的最小值，见上面的if操作
                // 如果num不大于大堆中的最小值，num就是自身
                max.add(num);
            }
        }
        public double getMedian() {
            int size = max.size() + min.size();
            if (size == 0) {
                throw new RuntimeException("No numbers are available");
            }
            if ((size & 1) == 1) {
                return min.getTop();
            } else {
                return (max.getTop() + min.getTop()) / 2.0;
            }
        }
    }
    public static void main(String[] args) {
        DynamicArray array = new DynamicArray();
        array.insert(5);
        System.out.println(array.getMedian()); // 5
        array.insert(2);
        System.out.println(array.getMedian()); // 3.5
        array.insert(3);
        System.out.println(array.getMedian()); // 3
        array.insert(4);
        System.out.println(array.getMedian()); // 3.5
        array.insert(1);
        System.out.println(array.getMedian()); // 3
        array.insert(6);
        System.out.println(array.getMedian()); // 3.5
        array.insert(7);
        System.out.println(array.getMedian()); // 4
        array.insert(0);
        System.out.println(array.getMedian()); // 3.5
        array.insert(8);
        System.out.println(array.getMedian()); // 4
    }
}