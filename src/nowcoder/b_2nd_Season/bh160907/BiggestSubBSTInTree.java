package nowcoder.b_2nd_Season.bh160907;

import Standard.Node;
import Standard.PrintBinaryTree;

/**
 * 找到二叉树中的最大搜索二叉子树
 *
 * Created by nibnait on 2016/9/16.
 */
public class BiggestSubBSTInTree {

    private static Node biggestSubBST(Node head) {

        return null;
    }




    public static void main(String[] args) {

        Node head = new Node(6);
        head.left = new Node(1);
        head.left.left = new Node(0);
        head.left.right = new Node(3);
        head.right = new Node(12);
        head.right.left = new Node(10);
        head.right.left.left = new Node(4);
        head.right.left.left.left = new Node(2);
        head.right.left.left.right = new Node(5);
        head.right.left.right = new Node(14);
        head.right.left.right.left = new Node(11);
        head.right.left.right.right = new Node(15);
        head.right.right = new Node(13);
        head.right.right.left = new Node(20);
        head.right.right.right = new Node(16);

        PrintBinaryTree.print(head);
        Node bst = biggestSubBST(head);
        PrintBinaryTree.print(bst);

    }

}
