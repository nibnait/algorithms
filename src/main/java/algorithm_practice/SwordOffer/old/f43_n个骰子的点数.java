package algorithm_practice.SwordOffer.old;

/**
 * 题目：把 n 个骰子扔在地上，所有骰子朝上一面的点数之和为 s。输入 n，打印出 s 的所有可能的值出现的概率。
 *
 * 【法1】：递归
 *  第一次：将n个骰子分成两堆，第一堆只有1个骰子，第二堆有n-1个骰子
 *      这1个骰子有6种情况，和剩下的n-1个骰子点数相加
 *  第二次：继续把剩下的n-1个骰子分成两堆，一堆1个，一堆n-2个...
 *      同样，这一个骰子还是要分成6种情况与剩下的n-2个骰子的点数相加
 *      同时，这一次还要把这颗骰子的点数与上一颗骰子的点数相加，之后再递归调用下一次分割。
 *  直到：此次递归调用还剩1颗骰子时，
 *      我们用一个probabilities[]数组保存所有点数之和的情况，出现的次数
 *      在对应的probabilities【当前点数之和sum】++;
 *
 *
 *  【法2】：上述递归调用显然有很多计算是重复的，极大的影响性能
 *  下面我们用动态规划-->二维数组求解：
 *  probabilities[2][i]：
 *      第1行代表：第一堆的这个骰子 当前累加和的情况
 *      第2行代表：另一堆的那个骰子（6种情况） 分别给上一堆的每种情况再加上（1、2、3、4、5、6）
 *
 * Created by nibnait on 2016/10/2.
 */
public class f43_n个骰子的点数 {

    public static void main(String[] args) {
        printProbability(70);

        System.out.println();
        printProbability2(80);
    }
    private static final int g_maxValue = 4;    //骰子的最大值

    private static void printProbability2(int n) {
        if (n<=0){
            return;
        }
        int maxSum = n*g_maxValue;
        int[][] probabilities = new int[2][maxSum+1];
        //数组初始化
        for (int i = 0; i < maxSum+1; i++) {
            probabilities[0][i] = 0;
            probabilities[1][i] = 0;
        }
        //第1个骰子的6种情况
        for (int i = 1; i <= g_maxValue; i++) {
            probabilities[0][i] = 1;
        }
        //开始抛剩下的骰子
        int flag = 0;   //标记哪一行刚刚被使用过
        for (int k = 2; k <= n; k++) {
            // 如果抛出了k个骰子，那么和为[0, k-1]的出现次数为0
            for (int i = 0; i < k; i++) {
                probabilities[1-flag][i] = 0;
            }
            for (int i = k; i <= g_maxValue*k; i++) {
                probabilities[1-flag][i] = 0;   //先将此处初始化为0
                //统计和为i时的点数出现的次数
                for (int j = 1; j<=i && j<=g_maxValue; j++) {
                    probabilities[1-flag][i] += probabilities[flag][i-j];
                }
            }
            flag = 1-flag;
        }
        printProbability(n, maxSum, probabilities, flag);
    }

    private static void printProbability(int n, int maxSum, int[][] probabilities, int flag) {
        double total = Math.pow(g_maxValue, n);
        for (int i = n; i < maxSum+1; i++) {
            double ratio = probabilities[flag][i] / total;
            System.out.print("P("+i + ")=");
            System.out.printf("%.4f", ratio);
            if (i != maxSum){
                System.out.print(", ");
            }
        }

    }

    /**
     * n个骰子 所能扔出来的所有情况的概率分布：
     * 法1： 递归
     * @param n
     * @return
     */
    private static void printProbability(int n) {
        if (n<=0){
            return;
        }

        int maxSum = n*g_maxValue;
        int[] probabilities = new int[maxSum-n+1];  //一共有maxSum-n+1种情况

        for (int i = 1; i <= g_maxValue; i++) {
            probability(n, n, i, probabilities);    //第一个骰子的6种情况
        }

        printProbability(n, maxSum, probabilities);
    }

    /**
     * 法1：递归
     * @param n     一共有n个骰子
     * @param cur   此次递归调用 还剩cur个骰子
     * @param sum   当前的累加和
     * @param probabilities     所有情况的出现的次数数组
     */
    private static void probability(int n, int cur, int sum, int[] probabilities) {
        if (cur == 1){
            probabilities[sum-n]++;
        } else {
            for (int i = 1; i <= g_maxValue; i++) {
                probability(n, cur-1, i+sum, probabilities);
            }
        }
    }

    /**
     * 打印概率数组
     * @param n
     * @param maxSum
     * @param probabilities
     */
    private static void printProbability(int n, int maxSum, int[] probabilities) {
        double total = Math.pow(g_maxValue, n); //计算概率的分母
        for (int i = n; i <= maxSum; i++) {
            double ratio = probabilities[i-n]/total;
            System.out.print("P("+i + ")=");
            System.out.printf("%.4f", ratio);
            if (i != maxSum){
                System.out.print(", ");
            }
        }
    }
}
