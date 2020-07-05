package algorithm_practice.LeetCode.code100;

import common.datastruct.TreeNode;
import common.util.BTreePrinter;

/*

将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

示例:

给定有序数组: [-10,-3,0,5,9],

一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

      0
     / \
   -3   9
   /   /
 -10  5

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class E108_将有序数组转换为平衡二叉树 {

  public static void main(String[] args) {
    E108_将有序数组转换为平衡二叉树 obj  = new E108_将有序数组转换为平衡二叉树();
    BTreePrinter.printTreeNode((obj.sortedArrayToBST(new int[]{-10,-3,0,5,9})));
  }

  public TreeNode sortedArrayToBST(int[] nums) {
    return calTree(nums, 0, nums.length - 1);
  }

  /*
  二分递归，每次从中间开始一分为二，中间是root。终止条件是left > right。
  每次选择的新的根结点都是 (left + right) /2
   */
  private TreeNode calTree(int[] nums, int start, int end) {
    if(start > end) {
      return null;
    }
    int mid = (start + end) / 2;
    TreeNode root = new TreeNode(nums[mid]);
    root.left = calTree(nums, start, mid - 1);
    root.right = calTree(nums, mid +1, end);
    return root;
  }
}
