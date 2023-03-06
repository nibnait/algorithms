package cc.tianbin.algorithmzuo.b_体系学习班.c0203_树;

import com.google.common.collect.Lists;
import cc.tianbin.common.datastruct.TreeNode;
import cc.tianbin.common.util.binaryTree.PrintBinaryTree;
import io.github.nibnait.common.utils.compare.CompareUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by nibnait on 2022/10/27
 */
@Slf4j
public class Code01_RecursiveTraversalBT {

    @Test
    public void testCase() {
        TreeNode head = new TreeNode(5);
        head.left = new TreeNode(3);
        head.right = new TreeNode(8);
        head.left.left = new TreeNode(2);
        head.left.right = new TreeNode(4);
        head.left.left.left = new TreeNode(1);
        head.right.left = new TreeNode(7);
        head.right.left.left = new TreeNode(6);
        head.right.right = new TreeNode(10);
        head.right.right.left = new TreeNode(9);
        head.right.right.right = new TreeNode(11);

        PrintBinaryTree.print(head);

        List<Integer> preOrderArr = preOrderRecur(head);
        List<Integer> expectPreOrderArr = Lists.newArrayList(5, 3, 2, 1, 4, 8, 7, 6, 10, 9, 11);
        Assert.assertTrue(CompareUtils.match(preOrderArr, expectPreOrderArr));

        List<Integer> inOrderArr = inOrderRecur(head);
        List<Integer> expectInOrderArr = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        Assert.assertTrue(CompareUtils.match(inOrderArr, expectInOrderArr));

        List<Integer> posOrderArr = posOrderRecur(head);
        List<Integer> expectPosOrderArr = Lists.newArrayList(1, 2, 4, 3, 6, 7, 9, 11, 10, 8, 5);
        Assert.assertTrue(CompareUtils.match(posOrderArr, expectPosOrderArr));

    }

    /**
     * 先序
     */
    private List<Integer> preOrderRecur(TreeNode head) {
        List<Integer> travelArr = Lists.newArrayList();
        if (head == null) {
            return travelArr;
        }

        processPreOrder(head, travelArr);
        return travelArr;
    }

    private void processPreOrder(TreeNode head, List<Integer> travelArr) {
        if (head == null) {
            return;
        }

        travelArr.add(head.val);
        processPreOrder(head.left, travelArr);
        processPreOrder(head.right, travelArr);
    }

    private List<Integer> inOrderRecur(TreeNode head) {
        List<Integer> travelArr = Lists.newArrayList();
        if (head == null) {
            return travelArr;
        }

        processInOrder(head, travelArr);
        return travelArr;
    }

    private void processInOrder(TreeNode head, List<Integer> travelArr) {
        if (head == null) {
            return;
        }

        processInOrder(head.left, travelArr);
        travelArr.add(head.val);
        processInOrder(head.right, travelArr);
    }

    private List<Integer> posOrderRecur(TreeNode head) {
        List<Integer> travelArr = Lists.newArrayList();
        if (head == null) {
            return travelArr;
        }

        processPosOrder(head, travelArr);
        return travelArr;
    }

    private void processPosOrder(TreeNode head, List<Integer> travelArr) {
        if (head == null) {
            return;
        }

        processPosOrder(head.left, travelArr);
        processPosOrder(head.right, travelArr);
        travelArr.add(head.val);
    }
}
