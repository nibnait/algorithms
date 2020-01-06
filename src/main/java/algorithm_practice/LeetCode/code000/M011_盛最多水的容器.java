package algorithm_practice.LeetCode.code000;

import junit.framework.TestCase;
import org.junit.Test;

/*
给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
说明：你不能倾斜容器，且 n 的值至少为 2。

示例:
输入: [1,8,6,2,5,4,8,3,7]
输出: 49

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/container-with-most-water
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M011_盛最多水的容器 extends TestCase {

    @Test
    public void testCase() {
        int[] arr = new int[]{1,8,6,2,5,4,8,3,7};
        int[] arr2 = new int[]{1,1};

        System.out.println(maxArea(arr2));
    }

    /*
        双指针
        p1 = 0
        p2 = length-1

        maxArea = Math.max(maxArea, minHeight * (p2-p1))
     */
    private int maxArea(int[] height) {
        if (height.length < 2) {
            return 0;
        }

        int p1 = 0;
        int p2 = height.length-1;
        int minHeitht = 0;
        int maxArea = 0;

        while (p1 != p2) {
            if (height[p1] < height[p2]) {
                minHeitht = height[p1];
                maxArea = Math.max(maxArea, minHeitht * (p2-p1));
                p1++;
            } else {
                minHeitht = height[p2];
                maxArea = Math.max(maxArea, minHeitht * (p2-p1));
                p2--;
            }
        }
        return maxArea;
    }


}
