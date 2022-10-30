package algorithm_practice.LeetCode.code900;

import common.util.SysOut;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

/*
我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。

（这里，平面上两点之间的距离是欧几里德距离。）

你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。

 

示例 1：

输入：points = [[1,3],[-2,2]], K = 1
输出：[[-2,2]]
解释：
(1, 3) 和原点之间的距离为 sqrt(10)，
(-2, 2) 和原点之间的距离为 sqrt(8)，
由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
示例 2：

输入：points = [[3,3],[5,-1],[-2,4]], K = 2
输出：[[3,3],[-2,4]]
（答案 [[-2,4],[3,3]] 也会被接受。）
 

提示：

1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/k-closest-points-to-origin
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by nibnait on 2020/11/9
 */
public class M973_最接近原点的K个点 {
    
    @Test
    public void testCase() {
        int[][] points = new int[][]{
                {1,3},{-2,2}
        };
        int K = 1;
        int[][] excepted = new int[][]{{-2,2}};
        int[][] actuals = kClosest(points, K);
        SysOut.printArray(actuals);
        Assert.assertArrayEquals(excepted, actuals);
        
        points = new int[][]{
                {3,3},{5,-1},{-2,4}
        };
        K = 2;
        excepted = new int[][]{{-2,4},{3,3}};
        actuals = kClosest(points, K);
        SysOut.printArray(actuals);
        Assert.assertArrayEquals(excepted, actuals);

    }

    /**
     * 堆排思想
     * 传统大根堆，从第一个非叶子节点开始 依次sink
     */
    public int[][] kClosest(int[][] points, int K) {
        int dist[][] = new int[K][2];
        for (int i = 0; i < K; i++) {
            int x = points[i][0];
            int y = points[i][1];
            dist[i][0] = (x*x)+(y*y);
            dist[i][1] = i;
        }

        for (int i = K/2; i >= 0; i--) {
            sink(dist, i, K-1);
        }

        int i = K;
        while (i < points.length) {
            int x = points[i][0];
            int y = points[i][1];
            int distI = (x*x)+(y*y);

            if (distI < dist[0][0]) {
                dist[0][0] = distI;
                dist[0][1] = i;

                sink(dist, 0, K-1);
            }
            i++;
        }

        int[][] res = new int[K][2];
        for (int j = 0; j < K; j++) {
            res[j] = points[dist[j][1]];
        }

        return res;
    }


    private void sink(int[][] dist, int k, int n) {
        while (2*k < n) {
            int j = 2*k + 1;
            if (j < n && dist[j][0] < dist[j+1][0]) {
                j++;
            }

            if (dist[j][0] > dist[k][0]) {
                swap(dist, j, k);
                k = j;
            } else {
                break;
            }
        }
    }

    private void swap(int[][] arr, int i, int j) {
        int[] temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 使用优先队列 做一个长度为 k 的大根堆，
     * 从 k+1 开始遍历point[]，只要比对顶元素小，就弹出队首元素并入队。
     */
    public int[][] kClosest2(int[][] points, int K) {
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });

        for (int i = 0; i < K; i++) {
            int x = points[i][0];
            int y = points[i][1];
            priorityQueue.offer(new int[]{(x*x)+(y*y), i});
        }

        for (int i = K; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];
            int dist = (x*x)+(y*y);
            int[] peek = priorityQueue.peek();
            if (dist < peek[0]) {
                priorityQueue.poll();
                priorityQueue.offer(new int[]{dist, i});
            }
        }

        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            res[i] = points[priorityQueue.poll()[1]];
        }
        return res;
    }
}
