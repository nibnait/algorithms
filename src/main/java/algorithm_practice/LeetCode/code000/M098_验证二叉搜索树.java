package algorithm_practice.LeetCode.code000;

import common.datastruct.TreeNode;
import common.util.ConstructBinaryTree;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Stack;

/*
给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

结点的左子树只包含小于当前结点的数。
结点的右子树只包含大于当前结点的数。
所有左子树和右子树自身必须也是二叉搜索树。
示例 1:

输入:
    2
   / \
  1   3
输出: true
示例 2:

输入:
    5
   / \
  1   4
     / \
    3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
     根结点的值为 5 ，但是其右子结点值为 4 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/validate-binary-search-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 Created by nibnait on 2019-07-20
 */
public class M098_验证二叉搜索树 extends TestCase {

    /*
     中序遍历
     只要发现下一个结点比上一个结点大，直接return false
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        Integer prevValue = null;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (prevValue != null && prevValue.intValue() >= root.val) {
                return false;
            } else {
                prevValue = root.val;
            }
            root = root.right;
        }
        return true;
    }

    @Test
    public void testCase() {
        Integer[] preOrderArray1 = {1, 2, 4, null, 7, null, null, null, 3, 5, null, null, 6, 8, null};
        Integer[] preOrderArray2 = {0};
        Integer[] preOrderArray3 = {5, 1, null, null, 4, 3, 6};
        Integer[] preOrderArray4 = {2, 1, 3};
        Integer[] preOrderArray = {1, 1};
        TreeNode treeNode = ConstructBinaryTree.constructByPreOrderArray(preOrderArray);
        System.out.println(isValidBST(treeNode));
    }
}
