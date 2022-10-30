package algorithmzuo.b_体系学习班.c0204_树型DP;

import common.CommonConstants;
import common.datastruct.TreeNode;
import common.util.ConstructBinaryTree;
import common.util.SysRandom;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.LinkedList;

/**
 * Created by nibnait on 2022/10/29
 */
public class Code06_IsCBT {

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
//        PrintBinaryTree.print(head);
//        SysOut.printSeparator();

        boolean result = isCBT(head);
//        Assert.assertEquals(comparator(head), result);
    }

    /**
     * 判断二叉树是不是完全二叉树
     * 每棵子树，都有左右孩子。
     */
    private boolean isCBT(TreeNode head) {
        if (head == null) {
            return true;
        }
        return true;
    }

    @AllArgsConstructor
    private class Info {

    }


    //-------------------------- 对数器 --------------------------//
    private boolean comparator(TreeNode head) {
        if (head == null) {
            return true;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        TreeNode l = null;
        TreeNode r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if (
                // 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
                    (leaf && (l != null || r != null))
                            ||
                            (l == null && r != null)

            ) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }
}
