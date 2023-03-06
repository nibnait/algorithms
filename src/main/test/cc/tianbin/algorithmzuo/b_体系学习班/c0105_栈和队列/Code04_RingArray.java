package cc.tianbin.algorithmzuo.b_体系学习班.c0105_栈和队列;

/**
 * 用环形数组实现队列
 * Created by nibnait on 2022/07/06
 */
public class Code04_RingArray {

    public static class MyQueue {
        private final int[] arr;
        private final int limit;
        private int pollIndex;
        private int addIndex;
        private int size;

        public MyQueue(int limit) {
            arr = new int[limit];
            pollIndex = 0;
            addIndex = 0;
            size = 0;
            this.limit = limit;
        }

        public void add(int value){
            if (size == limit) {
                throw new RuntimeException("队列满了");
            }
            size++;
            arr[addIndex] = value;
            addIndex = getNext(addIndex);
        }

        public int poll() {
            if (size == 0) {
                throw new RuntimeException("队列空了");
            }
            size--;
            int ans = arr[pollIndex];
            pollIndex = getNext(pollIndex);
            return ans;
        }

        private int getNext(int index){
            return index < limit - 1 ? index + 1 : 0;
        }
    }

}
