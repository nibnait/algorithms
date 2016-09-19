package others;

import java.util.Scanner;

/**
 * Created by nibnait on 2016/9/19.
 */

/*
最短最优升级路径
时间限制：C/C++语言 1000MS；其他语言 3000MS
内存限制：C/C++语言 65536KB；其他语言 589824KB
题目描述：
游戏网站提供若干升级补丁，每个补丁大小不一，玩家要升级到最新版，如何选择下载哪些补丁下载量最小。
输入
第一行输入                        第一个数为用户版本  第二个数为最新版本，空格分开
接着输入N行补丁数据        第一个数补丁开始版本 第二个数为补丁结束版本 第三个数为补丁大小，空格分开
输出
对于每个测试实例，输出一个升级路径以及最后实际升级的大小

样例输入
1000 1050
1000 1020 50
1000 1030 70
1020 1030 15
1020 1040 30
1030 1050 40
1040 1050 20
样例输出
1000->1020->1040->1050(100)

 */

public class Perfect_World_02 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = null;
        str = sc.nextLine();
        String[] sArr = str.split(" ");
        String startStr = sArr[0];
        String endStr = sArr[1];
        int start = Integer.valueOf(startStr);
        int end = Integer.valueOf(endStr);
        int[][] patch = new int[end][end];
        int[] startArr = new int[end];
        int[] endArr = new int[end];
        int cntStart = 0;
        int cntEnd = 0;
        while (sc.hasNext()){
            str = sc.nextLine();
            sArr = str.split(" ");
            startStr = sArr[0];
            start = Integer.valueOf(startStr);
            startArr[cntStart++] = start;
            endStr = sArr[1];
            end = Integer.valueOf(endStr);
            endArr[cntEnd] = end;
            patch[start][end] = Integer.valueOf(sArr[3]);
        }
        //暴力出奇迹
        int sum = 0;
        for (int i = 0; i < end; ) {
            sum += patch[startArr[i]][endArr[i]];
            for (int j = i; j < end; ) {

            }
        }

    }


}
