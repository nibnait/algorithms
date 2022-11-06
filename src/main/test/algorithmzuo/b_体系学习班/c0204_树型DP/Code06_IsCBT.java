package algorithmzuo.b_体系学习班.c0204_树型DP;

import common.CommonConstants;
import common.datastruct.TreeNode;
import common.util.binaryTree.ConstructBinaryTree;
import common.util.SysRandom;
import lombok.AllArgsConstructor;
import org.junit.Assert;
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
        Assert.assertEquals(comparator(head), result);
    }

    /**
     * 判断二叉树是不是完全二叉树（正在变成满二叉树的过程中）
     */
    public boolean isCBT(TreeNode head) {
        if (head == null) {
            return true;
        }
        return process(head).isCBT;
    }

    private Info process(TreeNode head) {
        if (head == null) {
            return new Info(true, true, 0);
        }

        Info left = process(head.left);
        Info right = process(head.right);

        boolean isFull = left.isFull && right.isFull && left.height == right.height;
        int height = Math.max(left.height, right.height) + 1;
        boolean isCBT = false;
        if (left.isFull && right.isCBT && left.height == right.height) {
            isCBT = true;
        } else if (left.isFull && right.isFull && left.height == right.height) {
            isCBT = true;
        } else if (left.isCBT && right.isFull && left.height == right.height + 1) {
            isCBT = true;
        } else if (left.isFull && right.isFull && left.height == right.height + 1) {
            isCBT = true;
        }

        return new Info(isFull, isCBT, height);
    }

    /**
     * 左满   右完全 -> 左高 == 右高
     * 左满   右满  -> 左高 == 右高
     * 左完全 右满  -> 左高 == 右高+1
     * 左满   右满  -> 左高 == 右高+1
     */
    @AllArgsConstructor
    private class Info {
        public boolean isFull;
        public boolean isCBT;
        public int height;
    }

    //-------------------------- 比较器 --------------------------//
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
