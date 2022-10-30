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

import java.util.ArrayList;

/**
 * Created by nibnait on 2022/10/31
 */
public class Code07_MaxSubBSTHead {

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

        TreeNode result = maxSubBSTHead(head);

        Assert.assertEquals(result, maxSubBSTHead1(head));
    }

    /**
     * 给定一棵二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的头节点
     */
    private TreeNode maxSubBSTHead(TreeNode head) {
        if (head == null) {
            return null;
        }
        return null;
    }


    @AllArgsConstructor
    private class Info {
    }

    //-------------------------- 对数器 --------------------------//
    private TreeNode maxSubBSTHead1(TreeNode head) {
        if (head == null) {
            return null;
        }
        if (getBSTSize(head) != 0) {
            return head;
        }
        TreeNode leftAns = maxSubBSTHead1(head.left);
        TreeNode rightAns = maxSubBSTHead1(head.right);
        return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
    }

    public static int getBSTSize(TreeNode head) {
        if (head == null) {
            return 0;
        }
        ArrayList<TreeNode> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).val <= arr.get(i - 1).val) {
                return 0;
            }
        }
        return arr.size();
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
