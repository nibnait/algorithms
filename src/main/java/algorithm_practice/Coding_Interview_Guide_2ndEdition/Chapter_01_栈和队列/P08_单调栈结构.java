package algorithm_practice.Coding_Interview_Guide_2ndEdition.Chapter_01_栈和队列;

import com.google.common.collect.Lists;
import junit.framework.TestCase;
import org.junit.Test;
import common.util.SysOut;

import java.util.List;
import java.util.Stack;

/*
【题目】
给定一个不含有重复值的数组arr，找到每一个i 位置左边和右边离i 位置最近且值比arr[i]小的位置。返回所有位置相应的信息。

【举例】
arr = {3,4,1,5,6,2,7}
返回如下二维数组作为结果：

{
    {-1, 2},
    { 0, 2},
    {-1,-1},
    { 2, 5},
    { 3, 5},
    { 2,-1},
    { 5,-1}
}
-1 表示不存在。所以上面的结果表示在arr 中，0 位置左边和右边离0 位置最近且值比arr[0]小的位置是-1 和2；1 位置左边和右边离1 位置最近且值比arr[1]小的位置是0 和2；2 位置左边和右边离2 位置最近且值比arr[2]小的位置是-1 和-1……

进阶问题：给定一个可能含有重复值的数组arr，找到每一个i 位置左边和右边离i 位置最近且值比arr[i]小的位置。返回所有位置相应的信息。
「如果需要输出 距离该位置最近的最小值的位置，则需要用【双端队列】，leftMin = stack.peekFirst(), rightMin = i, 最后从右往左检查一遍rightMin.」

【要求】
如果arr 长度为N，实现原问题和进阶问题的解法，时间复杂度都达到O(N)。

【难度】
尉 ★★☆☆
 */
public class P08_单调栈结构 extends TestCase {

    @Test
    public void testCase() {
        int[] arr = new int[]{3, 4, 1, 5, 6, 2, 7};

        SysOut.printArray(getNearLessNoReapet(arr));

        int[] arrReapet = new int[]{3, 1, 3, 4, 3, 5, 3, 2, 2};
        SysOut.printArray(getNearLess(arrReapet));
    }

    private int[][] getNearLess(int[] arr) {
        if (arr==null || arr.length==0) {
            return new int[0][0];
        }
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek().get(0)]) {
                List<Integer> popList = stack.pop();
                int leftMin = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);
                for (Integer pop : popList) {
                    res[pop] = new int[]{leftMin, i};
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
            int leftMin = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);
            for (Integer pop : popList) {
                res[pop] = new int[]{leftMin, -1};
            }
        }

        return res;
    }

    /**
     * 如果要找到每个i位置左右两边离i位置最近且比arr[i]『大』的位置
     *  则需要让stack从栈顶到栈底 依次递增
     * @param arr
     * @return
     */
    private int[][] getNearLessNoReapet(int[] arr) {
        if (arr==null || arr.length==0) {
            return new int[0][0];
        }
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                Integer pop = stack.pop();
                int leftMin = stack.isEmpty() ? -1 : stack.peek();
                res[pop] = new int[]{leftMin, i};
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            int leftLimit = stack.isEmpty() ? -1 : stack.peek();
            res[pop] = new int[]{leftLimit, -1};
        }

        return res;
    }
}
