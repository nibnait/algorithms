package xiaozhao;

import java.util.Scanner;

/**
 句子反转
 时间限制：C/C++语言 1000MS；其他语言 3000MS
 内存限制：C/C++语言 65536KB；其他语言 589824KB
 题目描述：
 给定一个句子（只包含字母和空格）， 将句子中的单词位置反转，单词用空格分割, 单词之间只有一个空格，前后没有空格。
 比如：
 （1） “hello xiao mi”-> “mi xiao hello”
 输入
 输入数据有多组，每组占一行，包含一个句子(句子长度小于1000个字符)
 输出
 对于每个测试示例，要求输出句子中单词反转后形成的句子

 样例输入
 hello xiao mi
 样例输出
 mi xiao hello
 * Created by nibnait on 2016/9/23.
 */
public class XiaoMi_02 {

    public static String reverse(String str) {
        String[] arr = str.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = arr.length - 1; i >= 0; i--) {
            sb.append(arr[i]).append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            System.out.println(reverse(sc.nextLine()));
        }
    }
}
