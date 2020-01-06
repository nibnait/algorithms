package algorithm_practice.LeetCode.code100;

import common.datastruct.TreeNode;
import common.util.ConstructBinaryTree;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.*;

/*
给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。

例如:
给定二叉树: [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回其层次遍历结果：

[
  [3],
  [9,20],
  [15,7]
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by nibnait on 2019-08-02
 */
public class M102_二叉树的层次遍历 extends TestCase {

    @Test
    public void testCase() {
        Integer[] BFSArray = {3, 9, 20, null, null, 15, 7};
        TreeNode treeNode = ConstructBinaryTree.constructByBFSArray(BFSArray);
        System.out.println(levelOrder(treeNode));
    }

    /**
     * 用队列
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Queue<TreeNode> newQueue = new LinkedList<>();
            List<Integer> levelNodes = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode poll = queue.poll();
                if (poll.val != null) {
                    levelNodes.add(poll.val);
                }
                if (poll.left != null) {
                    newQueue.add(poll.left);
                }
                if (poll.right != null) {
                    newQueue.add(poll.right);
                }
            }
            queue = newQueue;
            result.add(levelNodes);
        }

        return result;
    }
}
