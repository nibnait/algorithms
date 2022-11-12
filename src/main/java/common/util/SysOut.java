package common.util;

import common.datastruct.BinaryTreeNode;
import common.datastruct.ListNode;
import io.github.nibnait.common.exception.ClientViewException;
import io.github.nibnait.common.utils.DataUtils;

import java.util.List;
import java.util.Stack;

/**
 * 标准输出
 */
public final class SysOut {

    public static void print(String format, Object... args) {
        print(String.format(format, args));
    }

    public static void print(String str) {
        System.out.print(str);
    }

    public static void println(String format, Object... args) {
        println(String.format(format, args));
    }

    public static void printf(String format, Object... args) {
        println(DataUtils.format(format, args));
    }

    public static void println(Object o) {
        System.out.println(o);
    }

    public static void println() {
        System.out.println();
    }

    public static void printSeparator() {
        println("===========================================");
    }

    public static void printSeparator(String str) {
        println(str + "===========================================");
    }

    public static void printSeparatorSuffixTitle(String title) {
        println("===========================================\n%s:\n", title);
    }


    //*********** 打印数组 ************************************/
    public static void printArray(int[][] a) {
        String digitFormat = "%3d";
        String digitFormat_Comma = "%3d, ";
        int n = a.length;
        int m = a[0].length;
        println("{");
        for (int i = 0; i < n; i++) {
            print("\t{");
            for (int j = 0; j < m; j++) {
                if (j == m - 1) {
                    print(digitFormat, a[i][j]);
                    continue;
                }
                print(digitFormat_Comma, a[i][j]);
            }
            println("},");
        }
        println("}");
    }

    public static void printList(List<String> arr) {
        for (int i = 0; i < arr.size(); i++) {
            if (i == arr.size() - 1) {
                print(arr.get(i) + "\n");
            } else {
                print(arr.get(i) + ", ");
            }
        }
    }

    public static void printListWrap(List<String> arr) {
        for (String s : arr) {
            println(s);
        }
    }

    public static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (i == a.length - 1) {
                print(a[i] + "\n");
            } else {
                print(a[i] + ", ");
            }
        }
    }

    //*********** 打印链表 ************************************/
    public static void printLinkedNode(String format, ListNode head) {
        print(format);
        printLinkedNode(head);
    }

    public static void printLinkedNode(ListNode head) {
        while (head != null) {
            String arrow = head != null ? " -> " : "";
            print(head.val + arrow);
            head = head.next;
        }
        println("null");
    }

    public static void printDoubleLinkedNode(String format, ListNode head) {
        print(format);
        printDoubleLinkedNode(head);
    }

    public static void printDoubleLinkedNode(ListNode head) {
        print("NULL <- ");
        Integer prevVal = null;
        while (head != null) {
            print(head.val + "");
            if (head.prev != null && prevVal != null && !prevVal.equals(head.prev.val)) {
                throw new ClientViewException("{}.prev 实际为: {}, 应该为: {}", head.val,head.prev.val, prevVal);
            }

            if (head.next != null) {
                print(" <-> ");
            }

            prevVal = head.val;
            head = head.next;
        }

        println(" -> NULL");
    }

    public static void printDoubleLinkedList(BinaryTreeNode head) {
        println("Double Linked List: ");
        BinaryTreeNode end = null;
        while (head != null) {
            print(head.value + " ");
            end = head;
            head = head.right;
        }
        print("| ");
        while (end != null) {
            print(end.value + " ");
            end = end.left;
        }
        println();
    }

    //*********** 二叉树的前中后需遍历 ************************************/
    public static void preOrderPrint(BinaryTreeNode head) {
        if (head == null) {
            return;
        }
        print(head.value + " ");
        preOrderPrint(head.left);
        preOrderPrint(head.right);
    }

    public static void inOrderPrint(BinaryTreeNode head) {
        if (head == null) {
            return;
        }
        inOrderPrint(head.left);
        print(head.value + " ");
        inOrderPrint(head.right);
    }

    public static void posOrderPrint(BinaryTreeNode head) {
        if (head == null) {
            return;
        }
        posOrderPrint(head.left);
        posOrderPrint(head.right);
        print(head.value + " ");
    }

    //*********** 打印二叉树 ************************************/
    private static final int NODE_LENGTH = 17;      //二叉树中每个结点的长度

    public static void printBinaryTree(BinaryTreeNode head) {

        println("Binary Tree：");
        printInOrder(head, 0, "*");
        println();
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
        println(val);
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
            println(pop);
        }

        while (!originStack.isEmpty()) {
            stack.push(originStack.pop());
        }
    }
}
