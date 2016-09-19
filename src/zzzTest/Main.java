package zzzTest;

import java.util.Arrays;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String min = sc.nextLine();
        char[] str = s1.toCharArray();
        Arrays.sort(str);
        if(str.length>1 && str[0] == '0'){
            str[0] = str[1];
            str[1] = '0';
        }
        String ret = new String(str);
        if(ret.equals(min)){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }
}
