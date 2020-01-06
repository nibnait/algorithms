package algorithm_practice.LeetCode.code000;

import common.datastruct.TreeNode;
import common.util.ConstructBinaryTree;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
给定一个二叉树，返回它的中序遍历。

示例:

输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [1,3,2]

进阶: 递归算法很简单，你可以通过迭代算法完成吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 Created by nibnait on 2019-07-17
 */
public class M094_二叉树的中序遍历 extends TestCase {

    /*
      非递归过程：

       while (head != null && stack.isNotEmpty()) {
           while (head != null) {
               入栈，
               head = head.left
           }
           出栈，并打印;
           head = head.right;
       }

     */
    public List<Integer> inorderTraversal(TreeNode head) {
        List<Integer> result = new ArrayList<>();
        if (head == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();

        while (!stack.isEmpty() || head != null) {
            while (head != null) {
                stack.push(head);
                head = head.left;
            }
            head = stack.pop();
            result.add(head.val);
            head = head.right;
        }

        return result;
    }

    @Test
    public void testCase() {
        Integer[] preOrderArray = {1, null, 2, 3};
        Integer[] preOrderArray3 = {1};
        Integer[] preOrderArray1 = {1, 2};
        Integer[] preOrderArray2 = {1, null, 2};
        TreeNode treeNode = ConstructBinaryTree.constructByPreOrderArray(preOrderArray);
        System.out.println(inorderTraversal(treeNode));
        System.out.println(inorderTraversal_recursion(treeNode));
    }

    public List<Integer> inorderTraversal_recursion(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversal(root, result);
        return result;
    }

    private List<Integer> inorderTraversal(TreeNode treeNode, List<Integer> result) {
        if (treeNode == null) {
            return null;
        }
        inorderTraversal(treeNode.left, result);
        result.add(treeNode.val);
        inorderTraversal(treeNode.right, result);
        return result;
    }

}
