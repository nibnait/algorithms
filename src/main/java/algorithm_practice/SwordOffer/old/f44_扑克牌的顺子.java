package algorithm_practice.SwordOffer.old;

import java.util.Arrays;

/**
 * 题目：从扑克牌中随机抽 5 张牌，判断是不是一个顺子， 即这 5 张牌是不是连续的。
 * 2～10 为数字本身， A 为 1。 J 为 11、Q 为 12、 K 为 13。大、小王可以看成任意数字。
 *
 * 【解】：
 *  1. 给这5个数排序，
 *      排序的过程中
 *  2. 统计"0"的个数
 *  3. 统计数组中的空缺总数，
 *      同时还要看是否含有除了'0'以外其他的对子，如果有两张一样的了，则一定不可能是顺子了。！！
 *      如果空缺总数 <= "0"的个数，可以，是连续的！
 *          空缺总数 >  "0"的个数，sorry，连不上！
 *
 * Created by nibnait on 2016/10/2.
 */
public class f44_扑克牌的顺子 {

    private static boolean isContinuous(int[] arr) {
        if (arr==null || arr.length<=0){
            return false;
        }
        Arrays.sort(arr);   //一言不合 先排个序

        int cntZero = 0;
        int cntGap = 0;
        int len = arr.length;
        //统计数组中0的个数
        for (int i = 0; i < len && arr[i]==0; i++) {
            cntZero++;
        }
        //统计数组中的空缺总数
        int p1 = cntZero;   //指向第一个不为0的数
        int p2 = p1+1;
        while (p2 < len){
            if (arr[p1] == arr[p2]){    //不好，有对子
                return false;
            }
            cntGap += arr[p2]-arr[p1]-1;
            p1++;
            p2++;
        }
        return cntGap<=cntZero ? true : false;
    }

    public static void main(String[] args) {
        int[] numbers1 = {1, 3, 2, 5, 4};
        System.out.println(isContinuous(numbers1));
        int[] numbers2 = {1, 3, 2, 6, 4};
        System.out.println(isContinuous(numbers2));
        int[] numbers3 = {0, 3, 2, 6, 4};
        System.out.println(isContinuous(numbers3));
        int[] numbers4 = {0, 3, 1, 6, 4};
        System.out.println(isContinuous(numbers4));
        int[] numbers5 = {1, 3, 0, 5, 0};
        System.out.println(isContinuous(numbers5));
        int[] numbers6 = {1, 3, 0, 7, 0};
        System.out.println(isContinuous(numbers6));
        int[] numbers7 = {1, 0, 0, 5, 0};
        System.out.println(isContinuous(numbers7));
        int[] numbers8 = {1, 0, 0, 7, 0};
        System.out.println(isContinuous(numbers8));
        int[] numbers9 = {3, 0, 0, 0, 0};
        System.out.println(isContinuous(numbers9));
        int[] numbers10 = {0, 0, 0, 0, 0};
        System.out.println(isContinuous(numbers10));
        int[] numbers11 = {1, 0, 0, 1, 0};
        System.out.println(isContinuous(numbers11));
    }

}
