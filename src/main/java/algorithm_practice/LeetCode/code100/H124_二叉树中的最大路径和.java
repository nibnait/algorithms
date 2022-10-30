package algorithm_practice.LeetCode.code100;

import common.datastruct.TreeNode;
import common.util.ConstructBinaryTree;
import org.junit.Assert;
import org.junit.Test;

/*
给定一个非空二叉树，返回其最大路径和。

本题中，路径被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。

 

示例 1：

输入：[1,2,3]

       1
      / \
     2   3

输出：6
示例 2：

输入：[-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

输出：42


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by nibnait on 2020/11/24
 */
public class H124_二叉树中的最大路径和 {

    @Test
    public void testCase() {
        TreeNode root = ConstructBinaryTree.constructByBFSArray(new Integer[]{1, 2, 3});
        int excepted = 6;
        Assert.assertEquals(excepted, maxPathSum(root));

        root = ConstructBinaryTree.constructByBFSArray(new Integer[]{-10,9,20,null,null,15,7});
        excepted = 42;
        Assert.assertEquals(excepted, maxPathSum(root));

    }

    private int sum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return sum;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return root.val;
        }

        int val = root.val;
        int leftMaxSum = Math.max(dfs(root.left), 0);
        int rightMaxSum = Math.max(dfs(root.right), 0);
        int curSum = val + leftMaxSum + rightMaxSum;

        sum = Math.max(sum, curSum);

        return val + Math.max(leftMaxSum, rightMaxSum);
    }

}
