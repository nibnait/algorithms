package algorithm_practice.SwordOffer.old;

import common.util.SwapUtil;

import java.util.Comparator;

/**
 * 题目：输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3， 32, 321}，
 * 则扫描输出这 3 个数字能排成的最小数字 321323。
 *
 *【解】：自定义排序规则，
 *      两个数字 m和n
     *      mn>nm： m>n
     *      mn==nm：m=n
     *      mn<nm： m<n
 *      但是这里隐含这一个大数问题，（mn拼接之后有可能超出int范围）
 *      所以，我们可以把数值的比较大小 直接转化为字符串的compare
 *
 *
 * Created by nibnait on 2016/10/1.
 */
public class e33_把数组排成最小的数 {


    public static void main(String[] args) {
        String[] data = {"3", "5", "1", "4", "2"};
        System.out.println(printMinNumber(data));
        String[] data2 = {"3", "32", "321"};
        System.out.println(printMinNumber(data2));
        String[] data3 = {"3", "323", "32123"};
        System.out.println(printMinNumber(data3));
        String[] data4 = {"1", "11", "111"};
        System.out.println(printMinNumber(data4));
        String[] data5 = {"321"};
        System.out.println(printMinNumber(data5));
    }

    private static String printMinNumber(String[] arr) {

        if (arr==null || arr.length<=0){
            throw new IllegalArgumentException("args should not be null or empty");
        }
        quickSort(arr, 0, arr.length-1, new StringComparator());
        StringBuffer sb = new StringBuffer();
        for (String str: arr) {
            sb.append(str);
        }
        return sb.toString();
    }

    private static void quickSort(String[] arr, int lo, int hi, StringComparator comparator) {
        if (lo >= hi){
            return;
        }
        //partition
        int flag = lo;
        for (int i = lo; i < hi; i++) {
            if (comparator.compare(arr[i], arr[hi]) < 0){
                SwapUtil.swap(arr, i, flag++);
            }
        }
        SwapUtil.swap(arr, hi, flag);
        quickSort(arr, lo, flag-1, comparator);
        quickSort(arr, flag+1, hi, comparator);
    }

    static class StringComparator implements Comparator<String>{
        @Override
        public int compare(String str1, String str2) {
            if (str1==null || str2==null){
                throw new IllegalArgumentException("args should not be null or empty");
            }

            return (str1+str2).compareTo(str2+str1);
        }
    }
}
