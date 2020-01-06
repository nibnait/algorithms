package data_struct.树.平衡查找树;

import junit.framework.TestCase;
import org.junit.Test;

/**
 *  红黑树 测试类
 *  Created by nibnait on 2019-07-31
 */
public class RedBlackTreeTest extends TestCase {

    @Test
    public void testCase() {
        RedBlackTree redBlackTree = new RedBlackTreeImpl();
        redBlackTree.put('A', 1);
        redBlackTree.put('B', 1);
        redBlackTree.put('C', 1);
        redBlackTree.put('D', 1);
        redBlackTree.put('E', 1);

        System.out.println("\nRedBlackTree.size() = " + redBlackTree.size());

        redBlackTree.delete('B');
        System.out.println("\nRedBlackTree.size() = " + redBlackTree.size());


    }
}
