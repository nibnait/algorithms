package algorithmzuo.b_体系学习班.c0500_图;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by nibnait on 2022/11/08
 */
public class Code07_NetworkDelayTime {

    @Test
    public void test() {

    }

    /**
     * https://leetcode.com/problems/network-delay-time/
     * 有 n 个网络节点，标记为 1 到 n。
     * 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
     * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
     */
    // 方法一 : 普通堆 + 屏蔽已经计算过的点
    public static int networkDelayTime1(int[][] times, int n, int k) {
        ArrayList<ArrayList<int[]>> nexts = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            nexts.add(new ArrayList<>());
        }
        for (int[] delay : times) {
            nexts.get(delay[0]).add(new int[] { delay[1], delay[2] });
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        heap.add(new int[] { k, 0 });
        boolean[] used = new boolean[n + 1];
        int num = 0;
        int max = 0;
        while (!heap.isEmpty() && num < n) {
            int[] record = heap.poll();
            int cur = record[0];
            int delay = record[1];
            if (used[cur]) {
                continue;
            }
            used[cur] = true;
            num++;
            max = Math.max(max, delay);
            for (int[] next : nexts.get(cur)) {
                heap.add(new int[] { next[0], delay + next[1] });
            }
        }
        return num < n ? -1 : max;
    }

    // 方法二 : 加强堆的解法
    public static int networkDelayTime2(int[][] times, int n, int k) {
        ArrayList<ArrayList<int[]>> nexts = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            nexts.add(new ArrayList<>());
        }
        for (int[] delay : times) {
            nexts.get(delay[0]).add(new int[] { delay[1], delay[2] });
        }
        Heap heap = new Heap(n);
        heap.add(k, 0);
        int num = 0;
        int max = 0;
        while (!heap.isEmpty()) {
            int[] record = heap.poll();
            int cur = record[0];
            int delay = record[1];
            num++;
            max = Math.max(max, delay);
            for (int[] next : nexts.get(cur)) {
                heap.add(next[0], delay + next[1]);
            }
        }
        return num < n ? -1 : max;
    }

    // 加强堆
    public static class Heap {
        public boolean[] used;
        public int[][] heap;
        public int[] hIndex;
        public int size;

        public Heap(int n) {
            used = new boolean[n + 1];
            heap = new int[n + 1][2];
            hIndex = new int[n + 1];
            Arrays.fill(hIndex, -1);
            size = 0;
        }

        public void add(int cur, int delay) {
            if (used[cur]) {
                return;
            }
            if (hIndex[cur] == -1) {
                heap[size][0] = cur;
                heap[size][1] = delay;
                hIndex[cur] = size;
                heapInsert(size++);
            } else {
                int hi = hIndex[cur];
                if (delay <= heap[hi][1]) {
                    heap[hi][1] = delay;
                    heapInsert(hi);
                }
            }
        }

        public int[] poll() {
            int[] ans = heap[0];
            swap(0, --size);
            heapify(0);
            used[ans[0]] = true;
            hIndex[ans[0]] = -1;
            return ans;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private void heapInsert(int i) {
            int parent = (i - 1) / 2;
            while (heap[i][1] < heap[parent][1]) {
                swap(i, parent);
                i = parent;
                parent = (i - 1) / 2;
            }
        }

        private void heapify(int i) {
            int l = (i * 2) + 1;
            while (l < size) {
                int smallest = l + 1 < size && heap[l + 1][1] < heap[l][1] ? (l + 1) : l;
                smallest = heap[smallest][1] < heap[i][1] ? smallest : i;
                if (smallest == i) {
                    break;
                }
                swap(smallest, i);
                i = smallest;
                l = (i * 2) + 1;
            }
        }

        private void swap(int i, int j) {
            int[] o1 = heap[i];
            int[] o2 = heap[j];
            int o1hi = hIndex[o1[0]];
            int o2hi = hIndex[o2[0]];
            heap[i] = o2;
            heap[j] = o1;
            hIndex[o1[0]] = o2hi;
            hIndex[o2[0]] = o1hi;
        }

    }


}
