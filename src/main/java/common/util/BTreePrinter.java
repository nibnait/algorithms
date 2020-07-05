package common.util;

import common.datastruct.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BTreePrinter {

  public static void printTreeNode(TreeNode root) {
    int maxLevel = BTreePrinter.maxLevel(root);

    printTreeNodeInternal(Collections.singletonList(root), 1, maxLevel);
  }

  private static void printTreeNodeInternal(List<TreeNode> TreeNodes, int level, int maxLevel) {
    if (TreeNodes.isEmpty() || BTreePrinter.isAllElementsNull(TreeNodes))
      return;

    int floor = maxLevel - level;
    int edgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
    int firstSpaces = (int) Math.pow(2, (floor)) - 1;
    int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

    BTreePrinter.printWhitespaces(firstSpaces);

    List<TreeNode> newTreeNodes = new ArrayList<>();
    for (TreeNode TreeNode : TreeNodes) {
      if (TreeNode != null) {
        System.out.print(TreeNode.val);
        newTreeNodes.add(TreeNode.left);
        newTreeNodes.add(TreeNode.right);
      } else {
        newTreeNodes.add(null);
        newTreeNodes.add(null);
        System.out.print(" ");
      }

      BTreePrinter.printWhitespaces(betweenSpaces);
    }
    System.out.println("");

    for (int i = 1; i <= edgeLines; i++) {
      for (int j = 0; j < TreeNodes.size(); j++) {
        BTreePrinter.printWhitespaces(firstSpaces - i);
        if (TreeNodes.get(j) == null) {
          BTreePrinter.printWhitespaces(edgeLines + edgeLines + i + 1);
          continue;
        }

        if (TreeNodes.get(j).left != null)
          System.out.print("/");
        else
          BTreePrinter.printWhitespaces(1);

        BTreePrinter.printWhitespaces(i + i - 1);

        if (TreeNodes.get(j).right != null)
          System.out.print("\\");
        else
          BTreePrinter.printWhitespaces(1);

        BTreePrinter.printWhitespaces(edgeLines + edgeLines - i);
      }

      System.out.println("");
    }

    printTreeNodeInternal(newTreeNodes, level + 1, maxLevel);
  }

  private static void printWhitespaces(int count) {
    for (int i = 0; i < count; i++)
      System.out.print(" ");
  }

  private static int maxLevel(TreeNode TreeNode) {
    if (TreeNode == null)
      return 0;

    return Math.max(BTreePrinter.maxLevel(TreeNode.left), BTreePrinter.maxLevel(TreeNode.right)) + 1;
  }

  private static  boolean isAllElementsNull(List list) {
    for (Object object : list) {
      if (object != null)
        return false;
    }

    return true;
  }

}
