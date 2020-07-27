package algorithm_practice.LeetCode.code000;

import apple.laf.JRSUIUtils.Tree;
import common.datastruct.TreeNode;
import common.util.BTreePrinter;
import common.util.PrintBinaryTree;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class M095_不同的二叉搜索树II {

  /*
  给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。

 

示例：

输入：3
输出：
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
解释：
以上的输出对应以下 5 种不同结构的二叉搜索树：

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */

  /**
   * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
   * right; TreeNode() {} TreeNode(int val) { this.val = val; } TreeNode(int val, TreeNode left,
   * TreeNode right) { this.val = val; this.left = left; this.right = right; } }
   */

  public static void main(String[] args) {
    for (TreeNode treeNode : new M095_不同的二叉搜索树II().generateTrees(3)) {
      BTreePrinter.printTreeNode(treeNode);
    }
  }

  public List<TreeNode> generateTrees(int n) {
    if (n == 0) {
      return Collections.emptyList();
    }

    return generateTrees(1, n);
  }

  private List<TreeNode> generateTrees(int start, int end) {
    List<TreeNode> allTrees = new ArrayList<>();
    if (start > end) {
      allTrees.add(null);
      return allTrees;
    }

    // for every position as root
    for (int i = start; i <= end; i++) {
      List<TreeNode> leftTrees = generateTrees(start, i - 1);
      List<TreeNode> rightTrees = generateTrees(i + 1, end);

      for (TreeNode left : leftTrees) {
        for (TreeNode right : rightTrees) {
          TreeNode treeNode = new TreeNode(i, left, right);
          allTrees.add(treeNode);
        }
      }
    }

    return allTrees;
  }

}
