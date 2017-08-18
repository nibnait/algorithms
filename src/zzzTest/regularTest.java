package zzzTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regularTest {
    public static void main(String[] args) {

        Pattern p = Pattern.compile("肯德基|麦当劳|");
        Matcher m = p.matcher("m肯德基及");
        //完全匹配
        System.out.println(m.matches());
        //部分匹配
        System.out.println(m.find());
    }
}
