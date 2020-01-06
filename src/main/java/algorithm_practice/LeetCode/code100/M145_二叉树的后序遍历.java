package algorithm_practice.LeetCode.code100;

import common.datastruct.TreeNode;
import common.util.ConstructBinaryTree;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
给定一个二叉树，返回它的后序遍历。

示例:

输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [3,2,1]
进阶: 递归算法很简单，你可以通过迭代算法完成吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 Created by nibnait on 2019-07-17
 */
public class M145_二叉树的后序遍历 extends TestCase {

    /*
     非递归过程：
        因为深度优先搜索【后序遍历】的顺序是从下到上、从左至右，
        而树的宽度优先遍历的顺序正好是按照从上到下、从左至右的顺序

        因此可以直接宽度优先遍历整个树，然后把树的结果逆序输出（此处用到了LinkedList.addFirst()方法，即可从后往前添加数组元素）

        根结点入栈
        while (stack.isNotEmpty) {
            出栈并addFirst
            左结点非空 ? 左结点入栈 : ;
            右结点非空 ? 右结点入栈 : ;
        }
     */
    public List<Integer> postorderTraversal(TreeNode head) {
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (head == null) {
            return output;
        }

        stack.push(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            output.addFirst(head.val);
            if (head.left != null) {
                stack.add(head.left);
            }
            if (head.right != null) {
                stack.add(head.right);
            }
        }
        return output;
    }


    public List<Integer> postorderTraversal_recursion(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postOrderTravel(root, result);
        return result;
    }

    private List<Integer> postOrderTravel(TreeNode treeNode, List<Integer> result) {
        if (treeNode == null) {
            return null;
        }
        postOrderTravel(treeNode.left, result);
        postOrderTravel(treeNode.right, result);
        result.add(treeNode.val);
        return result;
    }

    @Test
    public void testCase() {
        Integer[] preOrderArray4 = {1, null, 2, 3};
        Integer[] preOrderArray3 = {1};
        Integer[] preOrderArray1 = {1, 2};
        Integer[] preOrderArray2 = {1, null, 2};
        Integer[] preOrderArray = {1, 2, 4, null, 7, null, null, null, 3, 5, null, null, 6, 8, null};
        TreeNode treeNode = ConstructBinaryTree.constructByPreOrderArray(preOrderArray);
        System.out.println(postorderTraversal(treeNode));
        System.out.println(postorderTraversal_recursion(treeNode));
    }
}
