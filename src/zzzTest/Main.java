package zzzTest;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
//        int[] a = {3, 3, -1, 0, 2, 1};
        int[] a = {1,1,2};
        System.out.println(Solution(a));
    }
    public static int Solution(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] > nums[i - 1]) {
                count++;
            }
        }
        return count;
    }
}