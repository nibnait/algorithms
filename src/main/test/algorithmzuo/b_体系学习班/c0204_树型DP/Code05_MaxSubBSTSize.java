package algorithmzuo.b_体系学习班.c0204_树型DP;

import common.CommonConstants;
import common.datastruct.TreeNode;
import common.util.ConstructBinaryTree;
import common.util.SysRandom;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by nibnait on 2022/10/29
 */
public class Code05_MaxSubBSTSize {

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

        int result = maxSubBSTSize(head);

//        Assert.assertEquals(result, maxSubBSTSize1(head));
    }

    /**
     * 给定一棵二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的大小
     */
    private int maxSubBSTSize(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return 1;
    }


    @AllArgsConstructor
    private class Info {
    }

    //-------------------------- 对数器 --------------------------//
    private int maxSubBSTSize1(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
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
