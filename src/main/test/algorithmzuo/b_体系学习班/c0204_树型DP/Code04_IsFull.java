package algorithmzuo.b_体系学习班.c0204_树型DP;

import common.CommonConstants;
import common.datastruct.TreeNode;
import common.util.ConstructBinaryTree;
import common.util.PrintBinaryTree;
import common.util.SysOut;
import common.util.SysRandom;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by nibnait on 2022/10/29
 */
public class Code04_IsFull {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        int maxLevel = SysRandom.randomInt(1, 6);
        int maxValue = SysRandom.random(100);

        TreeNode head = ConstructBinaryTree.generateRandomBinaryTree(maxLevel, maxValue);
        PrintBinaryTree.print(head);
        SysOut.printSeparator();

        Assert.assertEquals(isFull1(head), isFull2(head));
    }

    /**
     * 判断二叉树是不是满二叉树
     */
    private boolean isFull1(TreeNode head) {
        if (head == null) {
            return true;
        }
        return process1(head).isFull;
    }

    private Info1 process1(TreeNode head) {
        if (head == null) {
            return new Info1(true, 0);
        }

        Info1 left = process1(head.left);
        Info1 right = process1(head.right);

        boolean isFull = left.isFull && right.isFull && left.height == right.height;
        int height = Math.max(left.height, right.height) + 1;

        return new Info1(isFull, height);
    }

    @AllArgsConstructor
    private class Info1 {
        public boolean isFull;
        public int height;
    }

    /**
     * 法2: 只有满二叉树满足 : 2 ^ h - 1 == n
     */
    private boolean isFull2(TreeNode head) {
        if (head == null) {
            return true;
        }
        Info2 headInfo = process2(head);
        return headInfo.size == Math.pow(2, headInfo.height) - 1;
    }

    private Info2 process2(TreeNode head) {
        if (head == null) {
            return new Info2(0, 0);
        }
        Info2 left = process2(head.left);
        Info2 right = process2(head.right);

        int size = left.size + right.size + 1;
        int height = Math.max(left.height, right.height) + 1;
        return new Info2(size, height);
    }

    @AllArgsConstructor
    private class Info2 {
        public int size;
        public int height;
    }
}
