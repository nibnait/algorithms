package cc.tianbin.algorithmzuo.b_体系学习班.c0500_图;

import cc.tianbin.data_struct.ds4_图.Node;
import org.junit.Test;

import java.util.*;

/**
 * Created by nibnait on 2022/11/06
 */
public class Code01_BFS {

    @Test
    public void test() {

    }

    public List<Node> bfs(Node start) {
        List<Node> travelArr = new ArrayList<>();
        if (start == null) {
            return travelArr;
        }

        Queue<Node> queue = new LinkedList<Node>();
        Set<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            travelArr.add(cur);

            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    break;
                }

                queue.add(next);
                set.add(next);
            }
        }

        return travelArr;
    }

}
