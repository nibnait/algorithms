package zzzTest;

import utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nibnait on 2016/10/11.
 */
public class StringTest {
    public static void main(String[] args) {

        testSubString();
    }

    private static void testSplit() {
        String str = "253";
        String[] split = str.split(",");
        System.out.println(split.length);
        System.out.println(split);
    }

    private static void test02(){
        System.out.println(StringUtil.isNotBlank(null));
        System.out.println(StringUtil.isNotBlank(""));
        System.out.println(StringUtil.isNotBlank("  "));
    }

    public void testEquals(){
        String a = "ab";
        String b = "a"+"b";
        System.out.println(a.equals(b));    //true


        System.out.println("------------xxx-------------");

        String str2 = "a";
        String str3 = "b";

        List<String> stringList = new ArrayList<>();
        stringList.add(str2);
        stringList.add(str3);

        System.out.println(String.join(";", stringList));
    }

    private static void testSubString() {
        List<Long> ids = new ArrayList<>();
        ids.add(1l);
        ids.add(2l);
        ids.add(3l);
        System.out.println(ids.lastIndexOf(']'));
        String str = ids.toString();
        System.out.println(str.substring(1, str.length()-1));
        System.out.println(str);
    }

    /**
     * 检查字符串是否包含某个字符串
     */
    public void checkStringContains() {

        String haystack = "Programming in interview.Java";
        String needle1 = "interview";
        String needle2 = "Pascal";

        int index1 = haystack.indexOf(needle1);
        int index2 = haystack.indexOf(needle2);

        System.out.println(index1+"    "+index2);

        if (index1 != -1)
            System.out.println("The string contains the substring " + needle1);
        else
            System.out.println("The string does not contain the substring " + needle1);

        if (index2 != -1)
            System.out.println("The string contains the substring " + needle2);
        else
            System.out.println("The string does not contain the substring " + needle2);
    }
}
