package algorithm_practice.LeetCode.code200;

import common.datastruct.TreeNode;
import common.util.ConstructBinaryTree;
import org.junit.Assert;
import org.junit.Test;

/*
给出一个完全二叉树，求出该树的节点个数。

说明：

完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。

示例:

输入:
    1
   / \
  2   3
 / \  /
4  5 6

输出: 6


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/count-complete-tree-nodes
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by nibnait on 2020/11/24
 */
public class M222_完全二叉树的节点个数 {

    @Test
    public void testCase() {
        TreeNode root = ConstructBinaryTree.constructByBFSArray(new Integer[]{1,2,3,4,5,6});
        int excepted = 6;
        Assert.assertEquals(excepted, countNodes(root));

        root = ConstructBinaryTree.constructByBFSArray(new Integer[]{1,2,3,4,5,6,7});
        excepted = 7;
        Assert.assertEquals(excepted, countNodes(root));

    }

    /**
     * dfs
     */
    public int countNodes(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int res = 1;

        res += countNodes(root.left) + countNodes(root.right);

        return res;
    }


}
