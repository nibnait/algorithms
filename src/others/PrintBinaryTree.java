package others;

import StandardStruct.BinaryTreeNode;

/**
 * 打印二叉树
 *
 * Created by nibnait on 2016/9/15.
 */
public class PrintBinaryTree {

    private static final int NODE_LENGTH = 17;      //二叉树中每个节点的长度

    public static void print(BinaryTreeNode head) {

        System.out.println("Binary Tree：");
        printInOrder(head, 0, "*");
        System.out.println();
    }

    private static void printInOrder(BinaryTreeNode head, int height, String to) {
        if (head == null){
            return;
        }
        printInOrder(head.left, height+1, "~");
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (NODE_LENGTH - lenM) / 2;
        int lenR = NODE_LENGTH - lenL - lenM;
        val = getSpace(height*NODE_LENGTH + lenL) + val + getSpace(lenR);
        System.out.println(val);
        printInOrder(head.right, height+1, "_");
    }

    private static String getSpace(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        BinaryTreeNode head = new BinaryTreeNode(1);
        head.left = new BinaryTreeNode(-222222222);
        head.right = new BinaryTreeNode(3);
        head.left.left = new BinaryTreeNode(Integer.MIN_VALUE);
        head.right.left = new BinaryTreeNode(55555555);
        head.right.right = new BinaryTreeNode(66);
        head.left.left.right = new BinaryTreeNode(777);
        print(head);

        head = new BinaryTreeNode(1);
        head.left = new BinaryTreeNode(2);
        head.right = new BinaryTreeNode(3);
        head.left.left = new BinaryTreeNode(4);
        head.right.left = new BinaryTreeNode(5);
        head.right.right = new BinaryTreeNode(6);
        head.left.left.right = new BinaryTreeNode(7);
        print(head);

        head = new BinaryTreeNode(1);
        head.left = new BinaryTreeNode(1);
        head.right = new BinaryTreeNode(1);
        head.left.left = new BinaryTreeNode(1);
        head.right.left = new BinaryTreeNode(1);
        head.right.right = new BinaryTreeNode(1);
        head.left.left.right = new BinaryTreeNode(1);
        print(head);

    }


}
