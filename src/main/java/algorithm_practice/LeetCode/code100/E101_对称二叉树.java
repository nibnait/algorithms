package algorithm_practice.LeetCode.code100;

import common.datastruct.TreeNode;
import common.util.ConstructBinaryTree;
import junit.framework.TestCase;
import org.junit.Test;

/*
 给定一个二叉树，检查它是否是镜像对称的。

 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

     1
    / \
   2   2
  / \ / \
 3  4 4  3

 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     1
    / \
   2   2
    \   \
    3    3


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/symmetric-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by nibnait on 2019-08-02
 */
public class E101_对称二叉树 extends TestCase {

    @Test
    public void testCase() {
        Integer[] BFSArray = {1, 2, 2, 3, 4, 4, 3};
        TreeNode treeNode = ConstructBinaryTree.constructByBFSArray(BFSArray);
        System.out.println(isSymmetric(treeNode));

        Integer[] BFSArray2 = {1, 2, 2, null, 3, null, 3};
        TreeNode treeNode2 = ConstructBinaryTree.constructByBFSArray(BFSArray2);
        System.out.println(isSymmetric(treeNode2));
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);

    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if ((left == null && right != null) || (left != null && right == null)
            || (left.val == null && right.val != null) || (left.val != null && right.val == null)) {
            return false;
        }
        return left.val.intValue() == right.val.intValue()
                && isSymmetric(left.left, right.right)
                && isSymmetric(left.right, right.left);
    }

}
