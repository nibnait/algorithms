package xiaozhao;

import java.util.Scanner;

/**
 *
 袋鼠过河
 时间限制：C/C++语言 1000MS；其他语言 3000MS
 内存限制：C/C++语言 131072KB；其他语言 655360KB
 题目描述：
 一只袋鼠要从河这边跳到河对岸，河很宽，但是河中间打了很多桩子，每隔一米就有一个，每个桩子上都有一个弹簧，袋鼠跳到弹簧上就可以跳的更远。每个弹簧力量不同，用一个数字代表它的力量，如果弹簧力量为5，就代表袋鼠下一跳最多能够跳5米，如果为0，就会陷进去无法继续跳跃。河流一共N米宽，袋鼠初始位置就在第一个弹簧上面，要跳到最后一个弹簧之后就算过河了，给定每个弹簧的力量，求袋鼠最少需要多少跳能够到达对岸。如果无法到达输出-1
 输入
 输入分两行，第一行是数组长度N，第二行是每一项的值，用空格分隔
 输出
 输出最少的跳数，无法到达输出-1

 样例输入
 5
 2 0 1 1 1
 样例输出
 4
 * Created by nibnait on 2016/9/21.
 */
public class SoHu_03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int arr[] = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                arr[i] = sc.nextInt();
            }
            int times = 0;
            int s = 0;
            for (int i = 1; i < arr.length; i++) {
                if (s >= n) break;
                if (arr[i] != 0) {
                    times++;
                    s += arr[i];
                    continue;
                }
                if (arr[i] == 0 && arr[1 + s] > 0) {
                    s += arr[i];
                } else {
                    times = -1;
                    break;
                }
            }
            System.out.println(times);
        }
    }
}
