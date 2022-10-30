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

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by nibnait on 2022/10/31
 */
public class Code08_lowestAncestor {

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
        TreeNode o1 = ConstructBinaryTree.pickRandomOne(head);
        TreeNode o2 = ConstructBinaryTree.pickRandomOne(head);
        PrintBinaryTree.print(head);
        SysOut.printSeparator();

        TreeNode result = lowestAncestor(head, o1, o2);
        Assert.assertEquals(comparator(head, o1, o2), result);
    }

    /**
     * 给定一棵二叉树的头节点head，和另外两个节点a和b，返回a和b的最低公共祖先
     */
    private TreeNode lowestAncestor(TreeNode head, TreeNode o1, TreeNode o2) {
        if (head == null) {
            return null;
        }
        return null;
    }

    @AllArgsConstructor
    private class Info {

    }


    //-------------------------- 对数器 --------------------------//
    private TreeNode comparator(TreeNode head, TreeNode o1, TreeNode o2) {
        if (head == null) {
            return null;
        }
        // key的父节点是value
        HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();
        parentMap.put(head, null);
        fillParentMap(head, parentMap);
        HashSet<TreeNode> o1Set = new HashSet<>();
        TreeNode cur = o1;
        o1Set.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }


    public void fillParentMap(TreeNode head, HashMap<TreeNode, TreeNode> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }
}
