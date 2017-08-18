package zzzTest;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List<Long> ids = new ArrayList<>();
        ids.add(1l);
        ids.add(2l);
        System.out.println(ids.lastIndexOf(']'));
        String str = ids.toString();
        System.out.println(str.substring(1, str.length()-1));
    }
}
