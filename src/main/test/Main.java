import com.alibaba.fastjson.JSON;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by nibnait on 2020-01-08
 */
public class Main extends TestCase {

    public static void main(String[] args) {
        System.out.println(9/2);
    }


    @Test
    public void testCase() {
        Object left = null;
        String s = JSON.toJSONString(left);
        System.out.println(s);
    }

    public String reverseWords(String s) {
        String[] split = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<split.length; i++) {
            sb.append(reverse(split[i]))
                    .append(" ");
        }

        return sb.toString().substring(0, sb.toString().length()-1);
    }

    private String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i=s.length()-1; i>=0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}