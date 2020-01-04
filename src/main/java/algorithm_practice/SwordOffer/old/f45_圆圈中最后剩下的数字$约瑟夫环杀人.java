package algorithm_practice.SwordOffer.old;

import java.util.LinkedList;
import java.util.List;

/**
 * 题目：0, 1, … , n-1 这 n 个数字排成一个圈圈，从数字 0 开始每次从圆圏里删除第 m 个数字。
 * 求出这个圈圈里剩下的最后一个数字。
 *
 * 【法1】：
 *      经典的环形链表，每次删除第m个结点，直到剩下最后一个
 *      这里我们可以用LinkedList来模拟环形链表，然后每次走m步，list.remove(idx);
 *
 * 【法2】：递归
 *   要得到n个数字的序列中最后剩下的数字，
 *   也就是n-1个数字的序列中最后剩下的数字，
 *   以此类推。。。
 *   当n==1时，也就是序列中开始只有数字0，那么很显然最后剩下的数字就是0.
 *
 * 【法3】：还可以将此递归直接改成循环
 *
 * Created by nibnait on 2016/10/2.
 */
public class f45_圆圈中最后剩下的数字$约瑟夫环杀人 {

    public static void main(String[] args) {
        System.out.println(lastRemaining(5, 3)); // 最后余下3
        System.out.println(lastRemaining(5, 2)); // 最后余下2
        System.out.println(lastRemaining(6, 7)); // 最后余下4
        System.out.println(lastRemaining(6, 6)); // 最后余下3
        System.out.println(lastRemaining(0, 0)); // 最后余下-1

        System.out.println(lastRemaining2(5, 3)); // 最后余下3
        System.out.println(lastRemaining2(5, 2)); // 最后余下2
        System.out.println(lastRemaining2(6, 7)); // 最后余下4
        System.out.println(lastRemaining2(6, 6)); // 最后余下3
        System.out.println(lastRemaining2(0, 0)); // 最后余下-1

        System.out.println(lastRemaining3(5, 3)); // 最后余下3
        System.out.println(lastRemaining3(5, 2)); // 最后余下2
        System.out.println(lastRemaining3(6, 7)); // 最后余下4
        System.out.println(lastRemaining3(6, 6)); // 最后余下3
        System.out.println(lastRemaining3(0, 0)); // 最后余下-1
    }

    /**
     * 法3：将递归改成循环
     * @param n
     * @param m
     * @return
     */
    private static int lastRemaining3(int n, int m) {
        if (n<=0 || m<=0){
            return -1;
        }
        int last = 0;
        for (int i = 2; i <= n; i++) {
            last = (last + m)%i;
        }
        return last;
    }


    /**
     * 法2：这里需要用到一个非常“牛逼”的公式：old =（new + m ）% n
     * @param n
     * @param m
     * @return
     */
    private static int lastRemaining2(int n, int m) {
        if (n<=0 || m<=0){
            return -1;
        }
        if (n == 1) {
            return 0;
        }
        return (lastRemaining2(n - 1, m) + m )% n;
    }

   /**
     * 法1：用LinkedList代替环形链表
     * @param n
     * @param m
     * @return
     */
    private static int lastRemaining(int n, int m) {
        if (n<=0 || m<=0){
            return -1;
        }
        List<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < n ; i++) {
            list.add(i);
        }

        int idx = 0;
        while (list.size()>1){
            // 只要移动m-1次就可以移动到下一个要删除的元素上
            for (int i = 1; i < m; i++) {
                idx ++;
                idx = idx % list.size();
            }
            list.remove(idx);
        }
        return list.get(0);
    }

}
