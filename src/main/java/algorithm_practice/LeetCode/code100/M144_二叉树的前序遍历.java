package algorithm_practice.LeetCode.code100;

import common.datastruct.TreeNode;
import common.util.ConstructBinaryTree;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
给定一个二叉树，返回它的前序遍历。

示例:

输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [1,2,3]
进阶: 递归算法很简单，你可以通过迭代算法完成吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 Created by nibnait on 2019-07-17
 */
public class M144_二叉树的前序遍历 extends TestCase {

    /*
     非递归过程：
       头结点入栈
       while (stack.isNotEmpty()) {
           出栈并打印
           右结点非空 ? 右结点先入栈 : ;
           左结点非空 ? 左结点后入栈 : ;
       }
     */
    public List<Integer> preorderTraversal(TreeNode head) {
        List<Integer> result = new ArrayList<>();
        if (head == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            result.add(head.val);
            if (head.right != null) {
                stack.push(head.right);
            }
            if (head.left != null) {
                stack.push(head.left);
            }
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
        System.out.println(preorderTraversal(treeNode));
        System.out.println(preorderTraversal_recursion(treeNode));
    }

    public List<Integer> preorderTraversal_recursion(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preOrderTraversal(root, result);
        return result;
    }

    private List<Integer> preOrderTraversal(TreeNode treeNode, List<Integer> result) {
        if (treeNode == null) {
            return null;
        }
        result.add(treeNode.val);
        preOrderTraversal(treeNode.left, result);
        preOrderTraversal(treeNode.right, result);
        return result;
    }

}
