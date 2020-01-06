package algorithm_practice.LeetCode.code200;

import common.datastruct.TreeNode;
import common.util.ConstructBinaryTree;
import common.util.PrintBinaryTree;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.*;

/*
翻转一棵二叉树。

示例：

输入：

     4
   /   \
  2     7
 / \   / \
1   3 6   9
输出：

     4
   /   \
  7     2
 / \   / \
9   6 3   1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/invert-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 Created by nibnait on 2019-07-21
 */
public class E226_翻转二叉树 extends TestCase {

    /*
     翻转的宽度优先遍历（从上到下，从右到左）

     根据宽度优先序列构建二叉树
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        List<Integer> bfs = new ArrayList<>();
        int nullNum = 0;    //queue中null的个数
        int popCount = 0;   //queue pop的次数
        int level = 0;      //当前循环，正在遍历的二叉树层数+1（从第0层开始）
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        //当queue中存的null的个数正好等于 下一层的所有叶子结点的个数时，循环终止
        while (1 << level != nullNum) {
            TreeNode poll = queue.poll();
            popCount++;
            if (popCount == 1 << level) {
                level++;
            }
            if (poll == null) {
                bfs.add(null);
                queue.add(null);
                queue.add(null);
                nullNum += 2 - 1;
            } else {
                bfs.add(poll.val);
                queue.add(poll.right);
                if (poll.right == null) {
                    nullNum += 1;
                }
                queue.add(poll.left);
                if (poll.left == null) {
                    nullNum += 1;
                }
            }
        }
        return constructByBFSArray(bfs);
    }

    private TreeNode constructByBFSArray(List<Integer> bfsList) {
        if (bfsList == null || bfsList.size() == 0) {
            return null;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        int count = 0;
        TreeNode head = new TreeNode(bfsList.get(count++));
        queue.add(head);
        while (!queue.isEmpty() && count < bfsList.size()) {
            TreeNode pop = queue.pop();
            Integer leftValue = bfsList.get(count++);
            pop.left = new TreeNode(leftValue);
            queue.add(pop.left);
            Integer rightValue = bfsList.get(count++);
            pop.right = new TreeNode(rightValue);
            queue.add(pop.right);
        }
        return head;
    }

    /**
     * 递归
     * @param root
     * @return
     */
    public TreeNode invertTree_recursion(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        root.left = invertTree(root.left);
        root.right = invertTree(root.right);
        return root;
    }

    @Test
    public void testCase() {
        Integer[] preOrderArray1 = {1, 2, 4, null, 7, null, null, null, 3, 5, null, null, 6, 8, null};
        Integer[] preOrderArray = {1,2};
        TreeNode treeNode = ConstructBinaryTree.constructByPreOrderArray(preOrderArray);
        PrintBinaryTree.print(treeNode);
        PrintBinaryTree.print(invertTree(treeNode));

        PrintBinaryTree.print(invertTree_recursion(treeNode));
    }
}
