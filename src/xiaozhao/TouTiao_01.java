package xiaozhao;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 头条校招
 时间限制：C/C++语言 1000MS；其他语言 3000MS
 内存限制：C/C++语言 65536KB；其他语言 589824KB
 题目描述：
 头条的2017校招开始了！为了这次校招，我们组织了一个规模宏大的出题团队。每个出题人都出了一些有趣的题目，而我们现在想把这些题目组合成若干场考试出来。在选题之前，我们对题目进行了盲审，并定出了每道题的难度系数。一场考试包含3道开放性题目，假设他们的难度从小到大分别为a, b, c，我们希望这3道题能满足下列条件：
 a <= b <= c
 b - a <= 10
 c - b <= 10
 所有出题人一共出了n道开放性题目。现在我们想把这n道题分布到若干场考试中（1场或多场，每道题都必须使用且只能用一次），然而由于上述条件的限制，可能有一些考试没法凑够3道题，因此出题人就需要多出一些适当难度的题目来让每场考试都达到要求。然而我们出题已经出得很累了，你能计算出我们最少还需要再出几道题吗？
 输入
 输入的第一行包含一个整数n，表示目前已经出好的题目数量。
 第二行给出每道题目的难度系数 d1, d2, …, dn。
 输出
 输出只包括一行，即所求的答案。

 样例输入
 4
 20 35 23 40
 样例输出
 2

 Hint
 样例解释
 在样例中，一种可行的方案是添加2个难度分别为20和50的题目，这样可以组合成两场考试：(20, 20, 23)和(35, 40, 50)。

 数据范围
 对于30%的数据，1 <= n, di <= 5；
 对于100%的数据，1 <= n <= 10^5, 1 <= di <= 100。
 * Created by nibnait on 2016/9/21.
 */
public class TouTiao_01 {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int number=sc.nextInt();
        LinkedList<Integer> list=new LinkedList<Integer>();
        for(int i=0;i<number;i++){
            list.add(sc.nextInt());
        }
        Collections.sort(list);
        int count=0;
        while(list.size()!=0){
            if(list.size()>=3&&list.get(0)<=list.get(1)&&list.get(1)<=list.get(2)&&list.get(1) - list.get(0) <= 10&&
                    list.get(2) - list.get(1) <= 10){
                list.remove(0);
                list.remove(0);
                list.remove(0);
            }else if(list.size()>=2&&list.get(1)-list.get(0)<=20){
                list.remove(0);
                list.remove(0);
                count++;
            }else if((list.size()>=2&&list.get(1)-list.get(0)>=20)||list.size()==1){
                list.remove(0);
                count++;
                count++;
            }
        }
        System.out.println(count);
    }



}
