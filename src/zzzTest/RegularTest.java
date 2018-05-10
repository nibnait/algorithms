package zzzTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularTest {

    public static void main(String[] args) {
        test01();
    }

    private static void test02() {
        String bankCard = "12345678901234567";
        //位数 16-19位
        Pattern p = Pattern.compile("^d{16,19}");
        Matcher m = p.matcher(bankCard);
        System.out.println(m.matches());
        System.out.println(m.find());
        System.out.println(m.lookingAt());
    }

    private static void test01() {
        Pattern p = Pattern.compile("肯德基|满记甜品|呵呵|ofo");
//        Matcher m = p.matcher("呵呵肯德基哼哼哼");
        Matcher m = p.matcher("gogofod韩国料理");
//        Matcher m = p.matcher("满记甜品（M+购物中心店）");
        //完全匹配
//        System.out.println(m.matches());
        //部分匹配
        System.out.println(m.find());
//        System.out.println(m.lookingAt());
    }

}
