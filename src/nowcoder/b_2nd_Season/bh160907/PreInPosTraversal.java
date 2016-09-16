package nowcoder.b_2nd_Season.bh160907;

import Standard.Node;
import Standard.PrintBinaryTree;

import java.util.Stack;

/**
 * 分别用递归和非递归方式实现二叉树先序、中序和后序遍历
 *
 * Created by nibnait on 2016/9/16.
 */
public class PreInPosTraversal {

    private static void posOrderUnRecur2(Node head) {
        System.out.print("pos-order2: ");
        if (head != null){
            Stack<Node> stack = new Stack<Node>();
            Node cur = stack.push(head);
            Node pre = head;
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

    private static void posOrderUnRecur1(Node head) {
        System.out.print("pos-order: ");
        if (head != null){
            Stack<Node> s1 = new Stack<Node>();
            Stack<Node> s2 = new Stack<Node>();
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

    private static void inOrderUnRecur(Node head) {
        System.out.print("in-order: ");
        if (head != null){
            Stack<Node> stack = new Stack<Node>();
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

    private static void preOrderUnRecur(Node head) {
        System.out.print("pre-order: ");
        if (head != null){
            Stack<Node> stack = new Stack<Node>();
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
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);
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

    private static void preOrderRecur(Node head) {
        if (head == null){
            return;
        }
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    private static void inOrderRecur(Node head) {
        if (head == null){
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }

    private static void posOrderRecur(Node head) {
        if (head == null){
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value + " ");
    }
}
