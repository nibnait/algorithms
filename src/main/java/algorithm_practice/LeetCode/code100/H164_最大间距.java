package algorithm_practice.LeetCode.code100;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.OptionalInt;

/*
给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。

如果数组元素个数小于 2，则返回 0。

示例 1:

输入: [3,6,9,1]
输出: 3
解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
示例 2:

输入: [10]
输出: 0
解释: 数组元素个数小于 2，因此返回 0。
说明:

你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-gap
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by nibnait on 2020/11/26
 */
public class H164_最大间距 {

    @Test
    public void testCase() {
        int[] nums = new int[]{3, 6, 9, 1};
        int excepted = 3;
        Assert.assertEquals(excepted, maximumGap(nums));

        nums = new int[]{10};
        excepted = 0;
        Assert.assertEquals(excepted, maximumGap(nums));

        nums = new int[]{2, 99999999};
        excepted = 99999997;
        Assert.assertEquals(excepted, maximumGap(nums));

        nums = new int[]{1,1,1,1};
        excepted = 0;
        Assert.assertEquals(excepted, maximumGap(nums));

        nums = new int[]{1,1,1,1,1,5,5,5,5,5};
        excepted = 4;
        Assert.assertEquals(excepted, maximumGap(nums));

    }

    /**
     * 桶排序的思想：
     * 每个桶大小: bucketSize = (max - min) / length;
     * 桶的数量: bucketNum = (max - min) / bucketSize + 1;
     */
    public int maximumGap(int[] nums) {
        int length = nums.length;
        if (length < 2) {
            return 0;
        }

        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();

        if (max - min == 0) {
            return 0;
        }

        // 每个桶大小
        int bucketSize = (max - min) / length + 1;
        // 桶的数量
        int bucketNum = (max - min) / bucketSize + 1;
        // 存放每个桶的最大值和最小值
        int[][] bucket = new int[bucketNum][2];

        for (int i = 0; i < bucketNum; i++) {
            Arrays.fill(bucket[i], -1);
        }

        for (int num : nums) {
            int index = (num - min) / bucketSize;
            if (bucket[index][0] == -1) {
                bucket[index][0] = num;
                bucket[index][1] = num;
            } else {
                bucket[index][0] = Math.min(bucket[index][0], num);
                bucket[index][1] = Math.max(bucket[index][1], num);
            }
        }

        int res = 0;
        int preBucketMax = bucket[0][1];
        for (int i = 1; i < bucketNum; i++) {
            if (bucket[i][0] == -1) {
                continue;
            }

            res = Math.max(res, bucket[i][0] - preBucketMax);
            preBucketMax = bucket[i][1];
        }

        return res;
    }

    /**
     * 桶排序
     * 超出内存限制
     */
    public int maximumGap2(int[] nums) {
        int length = nums.length;
        if (length < 2) {
            return 0;
        }

        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();

        int[] bucket = new int[max - min + 1];
        for (int num : nums) {
            bucket[num - min]++;
        }

        int cnt = 0;
        for (int i = 0; i < bucket.length; i++) {
            int flag = bucket[i];
            while (flag > 0) {
                nums[cnt++] = min + i;
                flag--;
            }
        }

        int res = 0;
        for (int i = 1; i < length; i++) {
            res = Math.max(res, nums[i] - nums[i - 1]);
        }

        return res;
    }
}
