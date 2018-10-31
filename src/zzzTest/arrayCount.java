package zzzTest;

import com.google.common.collect.Lists;

import java.util.ArrayList;

public class arrayCount {

    /**
     * in: [apple, bananaing, pear, oranging]
     *
     * out: 2
     */

    public static void main(String[] args) {
        ArrayList<String> inputArray = Lists.newArrayList("apple", "bananaing", "pear", "oranging");
        int count = 0;
        for (String param: inputArray) {
            int length = param.length();
            String substring = param.substring(length - 3, length);
            if (substring.equals("ing")) {
                count++;
            }
        }
        System.out.println("the count of contain 'ing' words is "+ count);
    }
}
