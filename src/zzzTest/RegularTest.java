package zzzTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularTest {
    public static void main(String[] args) {

        Pattern p = Pattern.compile("肯德基|麦当劳|呵呵");
//        Matcher m = p.matcher("呵呵肯德基哼哼哼");
        Matcher m = p.matcher("呵呵哼");
        //完全匹配
        System.out.println(m.matches());
        //部分匹配
        System.out.println(m.find());
        System.out.println(m.lookingAt());

    }
}
