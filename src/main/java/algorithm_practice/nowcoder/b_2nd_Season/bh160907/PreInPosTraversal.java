package algorithm_practice.nowcoder.b_2nd_Season.bh160907;

import common.datastruct.BinaryTreeNode;
import common.util.PrintBinaryTree;

import java.util.Stack;

/**
 * 分别用递归和非递归方式实现二叉树先序、中序和后序遍历
 *
 * Created by nibnait on 2016/9/16.
 */
public class PreInPosTraversal {

    private static void posOrderUnRecur2(BinaryTreeNode head) {
        System.out.print("pos-order2: ");
        if (head != null){
            Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
            BinaryTreeNode cur = stack.push(head);
            BinaryTreeNode pre = head;
            while (!stack.isEmpty()){
                cur = stack.peek();
                if (cur.left!=null && pre!=cur.left &&  pre!=cur.right){
                    stack.push(cur.left);
                } else  if (cur.right!=null && pre!=cur.right){
                    stack.push(cur.right);
                } else {
                    pre = stack.pop();
                    System.out.print(pre.value + " ");
                }
            }
        }
        System.out.println();
    }

    private static void posOrderUnRecur1(BinaryTreeNode head) {
        System.out.print("pos-order: ");
        if (head != null){
            Stack<BinaryTreeNode> s1 = new Stack<BinaryTreeNode>();
            Stack<BinaryTreeNode> s2 = new Stack<BinaryTreeNode>();
            s1.add(head);
            while (!s1.isEmpty()){
                head = s1.pop();
                s2.push(head);
                if (head.left != null){
                    s1.push(head.left);
                }
                if (head.right != null){
                    s1.push(head.right);
                }
            }
            while (!s2.isEmpty()){
                head = s2.pop();
                System.out.print(head.value + " ");
            }
        }
        System.out.println();
    }

    private static void inOrderUnRecur(BinaryTreeNode head) {
        System.out.print("in-order: ");
        if (head != null){
            Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
            while (head!=null || !stack.isEmpty()){
                if (head != null){
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.value + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    private static void preOrderUnRecur(BinaryTreeNode head) {
        System.out.print("pre-order: ");
        if (head != null){
            Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
            stack.add(head);
            while (!stack.isEmpty()){
                head = stack.pop();
                System.out.print(head.value + " ");
                if (head.right != null){
                    stack.push(head.right);
                }
                if (head.left != null){
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        BinaryTreeNode head = new BinaryTreeNode(5);
        head.left = new BinaryTreeNode(3);
        head.right = new BinaryTreeNode(8);
        head.left.left = new BinaryTreeNode(2);
        head.left.right = new BinaryTreeNode(4);
        head.left.left.left = new BinaryTreeNode(1);
        head.right.left = new BinaryTreeNode(7);
        head.right.left.left = new BinaryTreeNode(6);
        head.right.right = new BinaryTreeNode(10);
        head.right.right.left = new BinaryTreeNode(9);
        head.right.right.right = new BinaryTreeNode(11);
        PrintBinaryTree.print(head);

        // recursive
        System.out.println("==============recursive==============");
        System.out.print("pre-order: ");
        preOrderRecur(head);
        System.out.println();
        System.out.print("in-order: ");
        inOrderRecur(head);
        System.out.println();
        System.out.print("pos-order: ");
        posOrderRecur(head);
        System.out.println();

        // unrecursive
        System.out.println("============unrecursive=============");
        preOrderUnRecur(head);
        inOrderUnRecur(head);
        posOrderUnRecur1(head);
        posOrderUnRecur2(head);
    }

    private static void preOrderRecur(BinaryTreeNode head) {
        if (head == null){
            return;
        }
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    private static void inOrderRecur(BinaryTreeNode head) {
        if (head == null){
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }

    private static void posOrderRecur(BinaryTreeNode head) {
        if (head == null){
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value + " ");
    }
}
