package cc.tianbin.algorithmzuo.b_体系学习班.c0203_树;

import com.google.common.collect.Lists;
import cc.tianbin.common.datastruct.TreeNode;
import cc.tianbin.common.util.binaryTree.PrintBinaryTree;
import io.github.nibnait.common.utils.compare.CompareUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by nibnait on 2022/10/27
 */
@Slf4j
public class Code03_LevelTraversalBT {

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

        List<Integer> levelOrderArr = levelTravel(head);
        List<Integer> expectOrderArr = Lists.newArrayList(5, 3, 8, 2, 4, 7, 10, 1, 6, 9, 11);
        Assert.assertTrue(CompareUtils.match(levelOrderArr, expectOrderArr));
    }

    private List<Integer> levelTravel(TreeNode head) {
        List<Integer> travelArr = new ArrayList<>();
        if (head == null) {
            return travelArr;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);

        while (!queue.isEmpty()) {
            head = queue.poll();
            travelArr.add(head.val);
            
            if (head.left != null) {
                queue.offer(head.left);
            }
            if (head.right != null) {
                queue.offer(head.right);
            }
        }

        return travelArr;
    }

}
