package common.util;

import common.datastruct.BinaryTreeNode;
import common.datastruct.ListNode;

import java.util.Stack;

/**
 * 标准输出
 */
public final class SysOut {

    public static void print(String format, Object... args) {
        System.out.print(String.format(format, args));
    }

    public static void println(String format, Object... args) {
        System.out.println(String.format(format, args));
    }

    public static void printSeparator() {
        println("===========================================");
    }

    public static void printSeparator(String title) {
        println("===========================================\n%s:\n", title);
    }


    //*********** 打印数组 ************************************/
    public static void printArray(int[][] a) {
        String digitFormat = "%3d";
        String digitFormat_Comma = "%3d, ";
        int n = a.length;
        int m = a[0].length;
        System.out.println("{");
        for (int i = 0; i < n; i++) {
            System.out.print("\t{");
            for (int j = 0; j < m; j++) {
                if (j == m - 1) {
                    print(digitFormat, a[i][j]);
                    continue;
                }
                print(digitFormat_Comma, a[i][j]);
            }
            System.out.println("},");
        }
        System.out.println("}");
    }


    public static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (i == a.length - 1) {
                System.out.print(a[i] + "\n");
            } else {
                System.out.print(a[i] + ", ");
            }
        }
    }

    //*********** 打印链表 ************************************/
    public static void printList(ListNode head) {
        while (head != null) {
            String arrow = head != null ? " -> " : "";
            System.out.print(head.val + arrow);
            head = head.next;
        }
        System.out.println("null");
    }

    public static void printDoubleLinkedList(BinaryTreeNode head) {
        System.out.println("Double Linked List: ");
        BinaryTreeNode end = null;
        while (head != null) {
            System.out.print(head.value + " ");
            end = head;
            head = head.right;
        }
        System.out.print("| ");
        while (end != null) {
            System.out.print(end.value + " ");
            end = end.left;
        }
        System.out.println();

    }

    //*********** 二叉树的前中后需遍历 ************************************/
    public static void preOrderPrint(BinaryTreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        preOrderPrint(head.left);
        preOrderPrint(head.right);
    }

    public static void inOrderPrint(BinaryTreeNode head) {
        if (head == null) {
            return;
        }
        inOrderPrint(head.left);
        System.out.print(head.value + " ");
        inOrderPrint(head.right);
    }

    public static void posOrderPrint(BinaryTreeNode head) {
        if (head == null) {
            return;
        }
        posOrderPrint(head.left);
        posOrderPrint(head.right);
        System.out.print(head.value + " ");
    }

    //*********** 打印二叉树 ************************************/
    private static final int NODE_LENGTH = 17;      //二叉树中每个结点的长度

    public static void printBinaryTree(BinaryTreeNode head) {

        System.out.println("Binary Tree：");
        printInOrder(head, 0, "*");
        System.out.println();
    }

    private static void printInOrder(BinaryTreeNode head, int height, String to) {
        if (head == null) {
            return;
        }
        printInOrder(head.left, height + 1, "~");
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (NODE_LENGTH - lenM) / 2;
        int lenR = NODE_LENGTH - lenL - lenM;
        val = getSpace(height * NODE_LENGTH + lenL) + val + getSpace(lenR);
        System.out.println(val);
        printInOrder(head.right, height + 1, "_");
    }

    private static String getSpace(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    //*********** 打印一个栈 ************************************/
    public static void printStack(Stack<Integer> stack) {
        Stack<Integer> originStack = new Stack<>();
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            originStack.push(pop);
            System.out.println(pop);
        }

        while (!originStack.isEmpty()) {
            stack.push(originStack.pop());
        }
    }
}
