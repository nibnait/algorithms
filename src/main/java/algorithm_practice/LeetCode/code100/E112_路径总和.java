package algorithm_practice.LeetCode.code100;

import common.datastruct.TreeNode;

public class E112_路径总和 {

  /*
  给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。

说明: 叶子节点是指没有子节点的节点。

示例: 
给定如下二叉树，以及目标和 sum = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/path-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */
  public static void main(String[] args) {
    TreeNode node5 = new TreeNode(5);
    TreeNode node4 = new TreeNode(4);
    TreeNode node8 = new TreeNode(8);
    TreeNode node11 = new TreeNode(11);
    TreeNode node13 = new TreeNode(13);
    TreeNode node4_2 = new TreeNode(4);
    TreeNode node7 = new TreeNode(7);
    TreeNode node2 = new TreeNode(2);
    TreeNode node1 = new TreeNode(1);
    node5.left = node4;
    node5.right = node8;
    node4.left = node11;
    node11.left = node7;
    node11.right = node2;
    node8.left = node13;
    node8.right = node4_2;
    node4_2.right = node1;
    assert true == hasPathSum(node5, 22);
  }

  /*
  解法：
  1. 递归调用，转换为子树的同样问题，根节点判断。
时间复杂度：O(N)，其中 N 是树的节点数。对每个节点访问一次。

空间复杂度：O(H)，其中 H 是树的高度。空间复杂度主要取决于递归时栈空间的开销，最坏情况下，树呈现链状，空间复杂度为 O(N)。平均情况下树的高度与节点数的对数正相关，空间复杂度为 O(logN)。
   */
  public static boolean hasPathSum(TreeNode root, int sum) {
    if (root == null) {
      return false;
    }
    if (root.left == null && root.right == null) {
      return sum == root.val;
    }
    return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
  }
}
