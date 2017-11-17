package zzzTest;

import Standard.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nibnait on 2016/10/11.
 */
public class StringTest {
    public static void main(String[] args) {

        String str = "276,251,285,286,278,279,282,283,284,41,252,253,254,255,256,257,258,287,288,289";
        int id = 289;
        System.out.println(str.contains(id+""));

        String s = "saf ";
        System.out.println(s.trim());

    }
    public static void test02(){
        System.out.println(StringUtils.isNotBlank(null));
        System.out.println(StringUtils.isNotBlank(""));
        System.out.println(StringUtils.isNotBlank("  "));
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

    private void testSunString() {
        List<Long> ids = new ArrayList<>();
        ids.add(1l);
        ids.add(2l);
        System.out.println(ids.lastIndexOf(']'));
        String str = ids.toString();
        System.out.println(str.substring(1, str.length()-1));
    }

    /**
     * 检查字符串是否包含某个字符串
     */
    public void checkStringContains() {

        String haystack = "Programming in Java";
        String needle1 = "Java";
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
