package xiaozhao;

import java.util.Scanner;

/**
 *
 异或
 时间限制：C/C++语言 1000MS；其他语言 3000MS
 内存限制：C/C++语言 65536KB；其他语言 589824KB
 题目描述：
 给定整数m以及n个数字A1, A2, …, An，将数列A中所有元素两两异或，共能得到n(n-1)/2个结果。请求出这些结果中大于m的有多少个。
 输入
 第一行包含两个整数n, m。
 第二行给出n个整数A1, A2, …, An。
 输出
 输出仅包括一行，即所求的答案。

 样例输入
 3 10
 6 5 10
 样例输出
 2

 Hint
 样例解释
 可能的两两异或的结果有：
 5 xor 6 = 3
 5 xor 10 = 15
 6 xor 10 = 12
 所以异或值大于10的有两种方案。

 数据范围
 对于30%的数据，1 <= n, m <= 1000
 对于100%的数据，1 <= n, m, Ai <= 10^5

 * Created by nibnait on 2016/9/21.
 */
public class TouTiao_02 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = null;
        while (sc.hasNext()){
            str = sc.nextLine();
            String[] strArr = str.split(" ");
            int n = Integer.valueOf(strArr[0]);
            int m = Integer.valueOf(strArr[1]);
            str = sc.nextLine();
            strArr = str.split(" ");
            Long[] arr = new Long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Long.valueOf(strArr[i]);
            }

            Long or = 0l;
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    or = arr[i] ^ arr[j];
                    if (or >= m){
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);
        }
    }
}
