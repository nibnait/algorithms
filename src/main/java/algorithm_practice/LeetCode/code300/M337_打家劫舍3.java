package algorithm_practice.LeetCode.code300;

import common.datastruct.TreeNode;
import common.util.ConstructBinaryTree;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。

计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。

示例 1:

输入: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \
     3   1

输出: 7
解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
示例 2:

输入: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \
 1   3   1

输出: 9
解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/house-robber-iii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M337_打家劫舍3 {

    @Test
    public void testCase() {
        Integer[] bfsArray = new Integer[]{3, 2, 3, null, 3, null, 1};
        TreeNode root = ConstructBinaryTree.constructByBFSArray(bfsArray);
        int excepted = 7;
        Assert.assertEquals(excepted, rob(root));

        bfsArray = new Integer[]{3, 4, 5, 1, 3, null, 1};
        root = ConstructBinaryTree.constructByBFSArray(bfsArray);
        excepted = 9;
        Assert.assertEquals(excepted, rob(root));

        bfsArray = new Integer[]{2,1,3,null,4};
        root = ConstructBinaryTree.constructByBFSArray(bfsArray);
        excepted = 7;
        Assert.assertEquals(excepted, rob(root));

        bfsArray = new Integer[]{};
        root = ConstructBinaryTree.constructByBFSArray(bfsArray);
        excepted = 0;
        Assert.assertEquals(excepted, rob(root));

    }

    private Map<TreeNode, Integer> memo = new HashMap<>();
    public int rob(TreeNode root) {
        if (root == null || root.val == null) {
            return 0;
        }

        if (memo.containsKey(root)) {
            return memo.get(root);
        }

        int rob = root.val + robLeftGrandSon(root) + robRightGrandSon(root);
        int notRob = rob(root.left) + rob(root.right);

        int res = Math.max(rob, notRob);
        memo.put(root, res);
        return res;
    }

    private Integer robRightGrandSon(TreeNode root) {
        return (root.right == null || root.right.val == null) ? 0 : (rob(root.right.left) + rob(root.right.right));
    }

    private Integer robLeftGrandSon(TreeNode root) {
        return (root.left == null || root.left.val == null) ? 0 : (rob(root.left.left) + rob(root.left.right));
    }

}
