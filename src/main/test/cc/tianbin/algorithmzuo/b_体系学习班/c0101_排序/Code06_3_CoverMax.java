package cc.tianbin.algorithmzuo.b_体系学习班.c0101_排序;

import cc.tianbin.common.CommonConstants;
import io.github.nibnait.common.utils.DataUtils;
import io.github.nibnait.common.utils.compare.CompareUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by nibnait on 2022/10/22
 */
@Slf4j
public class Code06_3_CoverMax {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        int N = 10;
        int L = 0;
        int R = 200;
        int[][] lines = generateLines(N, L, R);
        int expectResult = comparator(lines);

        int maxCover = maxCover(lines);
        if (!CompareUtils.matchObject(maxCover, expectResult)) {
            log.error("lines: {}", DataUtils.toJsonStringArray(lines));
            log.error("actual: {}, expect: {}", maxCover, expectResult);
            throw new RuntimeException();
        }
    }

    /**
     * 给定很多线段，每个线段都有两个数[start, end]，
     * 表示线段开始位置和结束位置，左右都是闭区间
     * 规定：
     * 1）线段的开始和结束位置一定都是整数值
     * 2）线段重合区域的长度必须>=1
     * 返回线段最多重合区域中，包含了几条线段
     */
    /**
     * 先按照 每条线段的起点(line[0]) 从小到大排序
     * 准备一个小根堆
     * 遍历 lines 数组，
     * 先把堆中<当前线段起点(line[0])的所有元素 pop出去
     * 把当前线段的终点(line[1]) push 进堆中
     * 计算和当前线段重合线段数 = heap.size
     */
    private int maxCover(int[][] lines) {
        if (lines == null || lines.length == 0) {
            return 0;
        }
        // 按 lines[0][i] 线段头 排序
        Arrays.sort(lines, (a, b) -> (a[0] - b[0]));
        // 小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int ans = 0;
        for (int[] line : lines) {
            while (!heap.isEmpty() && heap.peek() <= line[0]) {
                heap.poll();
            }
            heap.add(line[1]);
            ans = Math.max(ans, heap.size());
        }

        return ans;
    }

    //-------------------------- 比较器 --------------------------//
    private int comparator(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int i = 0; i < lines.length; i++) {
                if (lines[i][0] < p && lines[i][1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }

    private int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }
}
