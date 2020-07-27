package common.datastruct;

/**
 * LeetCode 二叉树结点
 */
public class TreeNode {

  public Integer val;
  public TreeNode left;
  public TreeNode right;

  public TreeNode(Integer val) {
    this.val = val;
  }

  public TreeNode() {
  }

  public TreeNode(Integer val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}
