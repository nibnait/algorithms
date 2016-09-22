package xiaozhao;

import java.util.Scanner;

/**
 * 彩色宝石项链
 时间限制：C/C++语言 1000MS；其他语言 3000MS
 内存限制：C/C++语言 65536KB；其他语言 589824KB
 题目描述：
 有一条彩色宝石项链，是由很多种不同的宝石组成的，包括红宝石，蓝宝石，钻石，翡翠，珍珠等。有一天国王把项链赏赐给了一个学者，并跟他说，你可以带走这条项链，但是王后很喜欢红宝石，蓝宝石，紫水晶，翡翠和钻石这五种，我要你从项链中截取连续的一小段还给我，这一段中必须包含所有的这五种宝石，剩下的部分你可以带走。如果无法找到则一个也无法带走。请帮助学者找出如何切分项链才能够拿到最多的宝石。
 输入
 我们用每种字符代表一种宝石，A表示红宝石，B表示蓝宝石，C代表紫水晶，D代表翡翠，E代表钻石，F代表玉石，G代表玻璃等等，我们用一个全部为大写字母的字符序列表示项链的宝石序列，注意项链是首尾相接的。每行代表一种情况。
 输出
 输出学者能够拿到的最多的宝石数量。每行一个

 样例输入
 ABCYDYE
 ATTMBQECPD
 样例输出
 1
 3
 * Created by nibnait on 2016/9/21.
 */
public class SoHu_01 {

    static class ListNode{
        char value;
        ListNode next;

        ListNode(){}
        ListNode(char data){
            this.value = data;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = null;
        while (sc.hasNext()){
            str = sc.nextLine();
            char[] arr = str.toCharArray();
            ListNode head = new ListNode(arr[0]);
            ListNode tmp = new ListNode();
            head.next = tmp;
            for (int i = 1; i < arr.length; i++) {
                tmp.value = arr[i];
                tmp.next = new ListNode();
                tmp = tmp.next;
            }
            tmp.next = head;

            String string = new String("ABCDE");
            if (!str.contains("A") || !str.contains("B") || !str.contains("C") ||!str.contains("D") || !str.contains("E") ){
                System.out.println(0);
                continue;
            }
            tmp = head.next;
            int cnt = 0;
            int max = 0;
            char tmpChar = head.value;
            char newtmpChar = tmp.value;
            while (tmp != head){
                if (string.contains(""+tmp.value)){
                    max = Math.max(cnt, max);
                    cnt = -1;
                }
                tmpChar = tmp.value;
                tmp = tmp.next;
                newtmpChar = tmp.value;
                max = Math.max(cnt, max);
                cnt++;
            }

            System.out.println(max);
        }
    }

}
