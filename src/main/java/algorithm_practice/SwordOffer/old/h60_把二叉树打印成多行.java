package algorithm_practice.SwordOffer.old;

import common.datastruct.BinaryTreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 题目：从上到下按层打印二叉树，同一层的结点按从左到右的顺序打印，每一层打印一行。
 *
 * 【解】：与23题 "d23_从上往下打印二叉树"类似
 *      同样 用一个队列来保存将要打印的结点。
 *      为了把二叉树的每一行单独打印到一行里，我们需要两个变量：
 *       - current：表示在当前的层中还没有打印的结点数，
 *       - next：表示下一层结点的数目。
 *
 * Created by nibnait on 2016/10/3.
 */
public class h60_把二叉树打印成多行 {

    //                            1
    //                  2                   3
    //             4         5          6          7
    //          8     9
    public static void main(String[] args) {
        BinaryTreeNode n1 = new BinaryTreeNode(1);
        BinaryTreeNode n2 = new BinaryTreeNode(2);
        BinaryTreeNode n3 = new BinaryTreeNode(3);
        BinaryTreeNode n4 = new BinaryTreeNode(4);
        BinaryTreeNode n5 = new BinaryTreeNode(5);
        BinaryTreeNode n6 = new BinaryTreeNode(6);
        BinaryTreeNode n7 = new BinaryTreeNode(7);
        BinaryTreeNode n8 = new BinaryTreeNode(8);
        BinaryTreeNode n9 = new BinaryTreeNode(9);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        n4.left = n8;
        n4.right = n9;
        print(n1);
    }

    private static void print(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        List<BinaryTreeNode> list = new LinkedList<>();
        BinaryTreeNode node;
        int current = 1;     // 当前层的结点个数
        int next = 0;   // 记录下一层的结点个数
        list.add(root);
        while (list.size() > 0) {
            node = list.remove(0);
            current--;
            System.out.printf("%-3d", node.value);
            if (node.left != null) {
                list.add(node.left);
                next++;
            }
            if (node.right != null) {
                list.add(node.right);
                next++;
            }
            if (current ==0) {
                System.out.println();
                current = next;
                next = 0;
            }
        }
    }
}
