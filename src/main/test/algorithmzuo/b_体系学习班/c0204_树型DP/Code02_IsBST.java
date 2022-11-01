package algorithmzuo.b_体系学习班.c0204_树型DP;

import common.CommonConstants;
import common.datastruct.TreeNode;
import common.util.*;
import common.util.binaryTree.ConstructBinaryTree;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by nibnait on 2022/10/28
 */
public class Code02_IsBST {

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

        boolean result = isBST(head);

        Assert.assertEquals(comparator(head), result);
    }

    /**
     * 判断二叉树是不是搜索二叉树（整棵树无重复节点）
     * 即：任何一棵子树。都是搜索二叉树
     */
    protected boolean isBST(TreeNode head) {
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }

    private Info process(TreeNode head) {
        if (head == null) {
            return null;
        }

        Info left = process(head.left);
        Info right = process(head.right);

        int max = CompareUtils.max(head.val,
                left != null ? left.max : Integer.MIN_VALUE,
                right != null ? right.max : Integer.MIN_VALUE);
        int min = CompareUtils.min(head.val,
                left != null ? left.min : Integer.MAX_VALUE,
                right != null ? right.min : Integer.MAX_VALUE);

        boolean isBST = true;
        if (left != null && !left.isBST) {
            isBST = false;
        }
        if (right != null && !right.isBST) {
            isBST = false;
        }
        if (left != null && left.max >= head.val) {
            isBST = false;
        }
        if (right != null && right.min <= head.val) {
            isBST = false;
        }
        return new Info(max, min, isBST);
    }

    @AllArgsConstructor
    private class Info {
        public int max;
        public int min;
        public boolean isBST;
    }

    //-------------------------- 对数器 --------------------------//
    private boolean comparator(TreeNode head) {
        if (head == null) {
            return true;
        }
        ArrayList<TreeNode> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).val <= arr.get(i - 1).val) {
                return false;
            }
        }
        return true;
    }

    public static void in(TreeNode head, ArrayList<TreeNode> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }
}
