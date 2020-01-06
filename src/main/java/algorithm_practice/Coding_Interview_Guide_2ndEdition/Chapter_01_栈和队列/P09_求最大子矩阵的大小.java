package algorithm_practice.Coding_Interview_Guide_2ndEdition.Chapter_01_栈和队列;

import com.google.common.collect.Lists;
import junit.framework.TestCase;
import org.junit.Test;
import common.util.SysOut;

import java.util.List;
import java.util.Stack;

/*
【题目】
给定一个整型矩阵map，其中的值只有0 和1 两种，求其中全是1 的所有矩形区域中，最大的矩形区域为1 的数量。

例如：
1 1 1 0
其中，最大的矩形区域有3个1，所以返回3。

再如：
1 0 1 1
1 1 1 1
1 1 1 0
其中，最大的矩形区域有6 个1，所以返回6。

【难度】
校 ★★★☆

 */
public class P09_求最大子矩阵的大小 extends TestCase {

    @Test
    public void testCase() {
//        int[][] map = new int[][]{
//                {1, 0, 1, 1},
//                {1, 1, 1, 1},
//                {1, 1, 1, 0}
//        };

        int[][] map = new int[][]{};
        SysOut.println("最大矩形区域面积为：%s", maxRecSize(map));
    }

    private int maxRecSize(int[][] map) {
        if (map == null || map.length==0 || map[0].length==0) {
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                height[j] = map[i][j] == 0 ? 0 : height[j] + map[i][j];
            }
            maxArea = Math.max(maxRecFromBottom(height), maxArea);
        }
        return maxArea;
    }

    /**
     * 求以当前行 为底时，所组成的最大矩形面积
     * 即寻找i位置左右两边，离i位置最近，且比arr[i]小的两个index。
     * area = (res[1]-res[0]-1) * arr[i]
     *
     * @param arr
     * @return
     */
    private int maxRecFromBottom(int[] arr) {
        if (arr==null || arr.length==0) {
            return 0;
        }
        Stack<List<Integer>> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek().get(0)]) {
                List<Integer> popList = stack.pop();
                for (Integer pop : popList) {
                    int leftMin = stack.isEmpty() ? -1 : stack.peek().get(0);
                    maxArea = Math.max(((i - leftMin - 1) * arr[pop]), maxArea);
                }
            }
            if (!stack.isEmpty() && arr[i] == arr[stack.peek().get(0)]) {
                stack.peek().add(i);
            } else {
                stack.push(Lists.newArrayList(i));
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> popList = stack.pop();
            for (Integer pop : popList) {
                int leftMin = stack.isEmpty() ? -1 : stack.peek().get(0);
                maxArea = Math.max(((arr.length - leftMin - 1) * arr[pop]), maxArea);
            }
        }
        return maxArea;
    }

}
