package xiaozhao;

import java.util.Scanner;

/**

 保留最大的数
 时间限制：C/C++语言 1000MS；其他语言 3000MS
 内存限制：C/C++语言 131072KB；其他语言 655360KB
 题目描述：
 给定一个十进制的正整数number，选择从里面去掉一部分数字，希望保留下来的数字组成的正整数最大。
 输入
 输入为两行内容，第一行是正整数number，注意可能是很大的。第二行是希望去掉的数字数量。
 输出
 输出保留下来的结果。

 样例输入
 325
 1
 样例输出
 35

 * Created by nibnait on 2016/9/21.
 */
public class SoHu_02 {

        public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()){
                String s= sc.next();
                int num= sc.nextInt();

                getDelResult(num, s);
            }
        }

        private static void getDelResult(int n, String num) {

            char c;
            int index, j;
            if (n >= num.length()) {
                System.out.println("error");
                return;
            }
            for (int i = 0; i < n; i++) {
                j = 0;
                while (j < num.length()) {
                    if (j == num.length() - 1) {
                        num = num.substring(0, j);
                        break;
                    }
                    index = j + 1;
                    c = num.charAt(index);
                    if ((num.charAt(j) - '0') >= (c - '0')) {
                        j++;
                        continue;
                    } else {
                        num = num.substring(0, j) + num.substring(index, num.length());
                        break;
                    }
                }
            }
            System.out.println(num);

        }
}
