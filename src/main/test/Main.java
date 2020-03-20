import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import common.datastruct.TreeNode;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by nibnait on 2020-01-08
 */
public class Main extends TestCase {

    @Test
    public void testCase() {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(null);
        queue.add(null);
        queue.add(null);
        queue.add(null);

        System.out.println(queue.isEmpty());

    }

    public String compressString(String S) {
        Stack<Character> stack = new Stack<Character>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                Character peek = stack.peek();
                if (peek.equals(c)) {
                    stack.push(c);
                } else {
                    sb.append(peek).append(stack.size());
                    stack.clear();

                    stack.push(c);
                }
            }
        }

        if (!stack.isEmpty()) {
            sb.append(stack.peek()).append(stack.size());
        }

        String result = sb.toString();
        return result.length() > S.length() ? S : result;
    }

}