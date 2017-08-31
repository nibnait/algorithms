package zzzTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularTest {
    public static void main(String[] args) {

        Pattern p = Pattern.compile("肯德基|满记甜品|呵呵|五芳斋");
//        Matcher m = p.matcher("呵呵肯德基哼哼哼");
        Matcher m = p.matcher("满记甜品（M+购物中心店）");
        //完全匹配
        System.out.println(m.matches());
        //部分匹配
        System.out.println(m.find());
        System.out.println(m.lookingAt());

    }
}
