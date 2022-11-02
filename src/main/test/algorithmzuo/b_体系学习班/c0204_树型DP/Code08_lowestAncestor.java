package algorithmzuo.b_体系学习班.c0204_树型DP;

import com.google.common.collect.Lists;
import common.CommonConstants;
import common.datastruct.TreeNode;
import common.util.SysOut;
import common.util.SysRandom;
import common.util.binaryTree.ConstructBinaryTree;
import common.util.binaryTree.PrintBinaryTree;
import common.util.binaryTree.SerializeAndReConstructTreeUtils;
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
        PrintBinaryTree.print(head);
        TreeNode a = ConstructBinaryTree.pickRandomOne(head);
        TreeNode b = ConstructBinaryTree.pickRandomOne(head);
        if (head != null) {
            SysOut.println("\na=%s, b=%s", a.val, b.val);
        }
        SerializeAndReConstructTreeUtils.serialByLevelOrder(head);
        SysOut.printSeparator("\n");

        TreeNode result = lowestAncestor(head, a, b);
        if (head == null) {
            Assert.assertNull(result);
        } else {
            Assert.assertEquals(comparator(head, a, b).val, result.val);
        }
    }

    @Test
    public void testSpecialCase() {
        TreeNode head = SerializeAndReConstructTreeUtils.buildByLevelOrder(Lists.newArrayList("53", "#", "30", "#", "37", "23", "3", "61", "27", "9", "35", "#", "#", "#", "#", "#", "#", "#", "#"));
        PrintBinaryTree.print(head);
        TreeNode a = ConstructBinaryTree.pickOne(head, 35);
        TreeNode b = ConstructBinaryTree.pickOne(head, 37);
        if (head != null) {
            SysOut.println("\na=%s, b=%s", a.val, b.val);
        }
        SerializeAndReConstructTreeUtils.serialByLevelOrder(head);
        SysOut.printSeparator("\n");

        TreeNode result = lowestAncestor(head, a, b);
        TreeNode expectResult = comparator(head, a, b);
        SysOut.println("\nresult=%s, expectResult=%s", result.val, expectResult.val);
        if (head == null) {
            Assert.assertNull(result);
        } else {
            Assert.assertEquals(expectResult.val, result.val);
        }
    }

    /**
     * 给定一棵二叉树的头节点head，和另外两个节点a和b，返回a和b的最低公共祖先
     */
    protected TreeNode lowestAncestor(TreeNode head, TreeNode a, TreeNode b) {
        if (head == null) {
            return null;
        }
        return null;
    }


    @AllArgsConstructor
    private class Info {
    }


    //-------------------------- 比较器 --------------------------//
    private TreeNode comparator(TreeNode head, TreeNode a, TreeNode b) {
        if (head == null) {
            return null;
        }
        // key的父节点是value
        HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();
        parentMap.put(head, null);
        fillParentMap(head, parentMap);
        HashSet<TreeNode> o1Set = new HashSet<>();
        TreeNode cur = a;
        o1Set.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = b;
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
