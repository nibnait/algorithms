package algorithmzuo.b_体系学习班.c0500_图;

import data_struct.ds4_图.Node;
import org.junit.Test;

import java.util.*;

/**
 * Created by nibnait on 2022/11/06
 */
public class Code02_DFS {

    @Test
    public void test() {

    }

    public List<Node> dfs(Node start) {
        List<Node> travelArr = new ArrayList<>();
        if (start == null) {
            return travelArr;
        }

        Stack<Node> stack = new Stack<Node>();
        Set<Node> set = new HashSet<>();
        stack.push(start);
        set.add(start);
        travelArr.add(start);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    travelArr.add(next);
                    break;
                }
            }
        }

        return travelArr;
    }

}
