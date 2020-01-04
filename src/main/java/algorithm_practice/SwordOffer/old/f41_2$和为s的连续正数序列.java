package algorithm_practice.SwordOffer.old;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目二：输入一个正数 s，打印出所有和为 s 的连续正数序列（至少两个数）。
 * 例如输入 15，
 *   由于 1+2+3+4+5 = 4+5+6 ＝ 7+8 = 15，
 *   所以结果打出 3 个连续序列 1～5、4～6 和 7～8。
 *
 * 【解】：还是双指针
 *    p1 = 1，指向序列的第一个字符
 *    p2 = 2；指向序列的末尾
 *    由于序列至少要有两个数字，所以p1不可能>(1+s)/2。
 *    while(p1 < (1+s)/2){
 *        p1+...+p2 == s, 保留当前序列，
 *        while( curSum > s && p1<(1+s)/2){
 *             p1++;
 *             if(p1+...+p2 == s)
 *                 保留当前序列，
 *        }
 *        (p1+...+p2 < s)  p2++;
 *    }
 *
 *
 * Created by nibnait on 2016/10/2.
 */
public class f41_2$和为s的连续正数序列 {

    public static void main(String[] args) {
        System.out.println(findContinuousSequence(1));
        System.out.println(findContinuousSequence(3));
        System.out.println(findContinuousSequence(4));
        System.out.println(findContinuousSequence(9));
        System.out.println(findContinuousSequence(15));
        System.out.println(findContinuousSequence(100));
    }

    private static List<List<Integer>> findContinuousSequence(int s) {
        List<List<Integer>> result = new ArrayList<>();
        if (s < 3){
            return result;
        }
        int p1 = 1;
        int p2 = 2;
        int mid = (1+s) / 2;    //p1如果>mid, p1+p2一定大于s,
        int curSum = p1+p2;
        while (p1 < mid){
            if (curSum == s){
                addListToResult(p1, p2, result);
            }
            while (curSum > s && p1<mid){
                curSum -= p1;
                p1++;
                if (curSum == s){
                    addListToResult(p1, p2, result);
                }
            }
            //curSum < s
            p2++;
            curSum += p2;
        }
        return result;
    }

    private static void addListToResult(int p1, int p2, List<List<Integer>> result) {
        List<Integer> list = new ArrayList<>();
        for (int i = p1; i <= p2; i++) {
            list.add(i);
        }
        result.add(list);
    }


}
