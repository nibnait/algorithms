package nowcoder.ba160720;

/**
 * 两根柱子围成最大面积（leetCode11）
 *
 * Created by nibnait on 2016/9/9.
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        int[] arr= {3,4,2,5};
        System.out.println(maxArea(arr));
    }

    public static int maxArea(int[] arr) {
        int area = Integer.MIN_VALUE;


        return area;
    }

/*

类似leetcode上的11题Container With Most Water，区别是leetcode11的两个相邻位置距离我们当成1处理。
解法1：这道题看起来难度不是很大，最朴素的想法就是对每一个位置遍历一遍，计算这个位置到其他位置围成的面积，保留最大的。这样的时间复杂度为O(N*N)，额外空间复杂度O(1)。
解法2：这里我们还是要玩双指针，左指针L指向数组的第一个位置，右指针R指向数组的最后一个位置。取两个位置值小的那一个，记为T，T*（R-L-1）就是L和R围成的面积。（这里leetcode11题的计算方式为T*(R-L)）。然后，T的位置向中间滑动一个位置（L++或者R--），两个指针相遇，算法结束，很明显我们实现了时间复杂度O(N)，额外空间复杂度O(1)。

    public int maxArea(int[] height) {
        if (height == null || height.length < 3) {// leetcode11 这里修改为 height.length < 2
            return 0;
        }
        int l = 0;
        int r = height.length - 1;
        int area = Integer.MIN_VALUE;
        while (l <= r) {
            if(height[l] < height[r]){
                area = Math.max(area, height[l] * (r-l-1));
                // leetcode11 这里修改为 area = Math.max(area, height[l] * (r-l));
                l++;
            }else {
                area = Math.max(area, height[r] * (r-l-1));
                // leetcode11 这里修改为 area = Math.max(area, height[r] * (r-l));
                r--;
            }
        }
        return area;
    }
*/

}
