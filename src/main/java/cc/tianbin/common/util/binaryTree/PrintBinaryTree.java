package cc.tianbin.common.util.binaryTree;


import cc.tianbin.common.datastruct.BinaryTreeNode;
import cc.tianbin.common.datastruct.TreeNode;
import cc.tianbin.common.util.SysOut;

import java.util.Queue;

/**
 * 打印二叉树
 * <p>
 * Created by nibnait on 2016/9/15.
 */
public class PrintBinaryTree {

    private static final int NODE_LENGTH = 17;      //二叉树中每个结点的长度

    /**
     * ‘*’ 代表根结点
     * ‘^’ 代表左子结点
     * ‘_’ 代表右子结点
     *
     * @param head 树的根结点
     */
    public static void print(BinaryTreeNode head) {

        System.out.println("Binary Tree：");
        printInOrder(head, 0, "*");
        System.out.println();
    }

    private static void printInOrder(BinaryTreeNode head, int height, String to) {
        if (head == null) {
            return;
        }
        printInOrder(head.left, height + 1, "^");
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (NODE_LENGTH - lenM) / 2;
        int lenR = NODE_LENGTH - lenL - lenM;
        val = getSpace(height * NODE_LENGTH + lenL) + val + getSpace(lenR);
        System.out.println(val);
        printInOrder(head.right, height + 1, "_");
    }

    public static void print(TreeNode head) {
        System.out.println("Binary Tree：");
        printInOrder(head, 0, "*");
        System.out.println();
    }

    private static void printInOrder(TreeNode head, int height, String to) {
        if (head == null) {
            return;
        }
        printInOrder(head.left, height + 1, "^");
        String val = to + head.val + to;
        int lenM = val.length();
        int lenL = (NODE_LENGTH - lenM) / 2;
        int lenR = NODE_LENGTH - lenL - lenM;
        val = getSpace(height * NODE_LENGTH + lenL) + val + getSpace(lenR);
        if (!val.contains("null")) {
            System.out.println(val);
        }
        printInOrder(head.right, height + 1, "_");
    }

    private static String getSpace(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

}
