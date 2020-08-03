package algorithm_practice.LeetCode.code100;

import common.datastruct.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class M114_二叉树展开为链表 {

  /*
  给定一个二叉树，原地将它展开为一个单链表。

 

例如，给定二叉树

    1
   / \
  2   5
 / \   \
3   4   6
将其展开为：

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */
  public static void main(String[] args) {

  }

  /**
   * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
   * right; TreeNode() {} TreeNode(int val) { this.val = val; } TreeNode(int val, TreeNode left,
   * TreeNode right) { this.val = val; this.left = left; this.right = right; } }
   */
  /*
  解法：
先使用递归算法完成二叉树先根序遍历，结果存放在list中，然后拼凑成右子树链表。
   */
  public void flatten(TreeNode root) {
    List<TreeNode> tnList = new ArrayList<>();
    preOrderTravel(root, tnList);

    int size = tnList.size();
    for(int i=1; i<tnList.size(); i++) {
      TreeNode prev = tnList.get(i-1);
      TreeNode node = tnList.get(i);
      prev.left = null;
      prev.right = node;
    }
  }

  private void preOrderTravel(TreeNode root, List<TreeNode> tnList) {
    if(root != null) {
      tnList.add(root);
      preOrderTravel(root.left, tnList);
      preOrderTravel(root.right, tnList);
      int a = Integer.MIN_VALUE;
    }
  }

}
