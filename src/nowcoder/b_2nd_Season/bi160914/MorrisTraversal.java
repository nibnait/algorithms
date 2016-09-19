package nowcoder.b_2nd_Season.bi160914;

import Standard.Node;
import Standard.PrintBinaryTree;

/**
 * 二叉树的神级遍历
 * 空间复杂度：O(1)【使用二叉树节点中大量的指向null的指针。】
 * 时间复杂度：O(h)
 * Created by nibnait on 2016/9/19.
 */
public class MorrisTraversal {

    //左右中
    //先打印根结点的左结点，再将此根即其右结点逆序打印
    public static void MorrisPos(Node head){
        if (head == null){
            return;
        }
        Node cur1 = head;
        Node cur2 = null;
        while (cur1 != null){
            cur2 = cur1.left;
            if (cur2 != null){
                while (cur2.right != null && cur2.right != cur1){
                    cur2 = cur2.right;
                }
                if (cur2.right == null){
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                    printEdge(cur1.left);
                }
            }
            cur1 = cur1.right;
        }
        printEdge(head);    //最后，再将整个二叉树的右边界 逆序打印
        System.out.println();

    }

    /**
     * 打印以head为根结点的右边界的逆序
     * @param head
     */
    private static void printEdge(Node head) {
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur != null){
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    private static Node reverseEdge(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null){
            next = head.right;
            head.right = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    //左中右
    public static void MorrisIn(Node head) {
        if (head == null) {
            return;
        }

        Node cur1 = head;
        Node cur2 = null;
        while (cur1 != null){
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right!=cur1) {      //
                    cur2 = cur2.right;  //一直遍历到左子树的最右结点
                }
                if (cur2.right == null) {
                    cur2.right = cur1;  //将左子树的最右结点指向cur1
                    cur1 = cur1.left;   //loop，
                    continue;           //直到遍历到最左结点。
                } else {
                    cur2.right = null;  //将左子树的最右结点调换回来，重新指向null
                }
            }
            System.out.print(cur1.value + " ");
            cur1 = cur1.right;      //沿着right指针往下走
        }
        System.out.println();
    }

    //中左右
    public static void MorrisPre(Node head) {
        if (head == null){
            return;
        }
        Node cur1 = head;
        Node cur2 = null;
        while (cur1 != null){
            cur2 = cur1.left;
            if (cur2 != null){
                while (cur2.right != null && cur2.right != cur1){
                    cur2 = cur2.right;
                }
                if (cur2.right == null){
                    cur2.right = cur1;
                    System.out.print(cur1.value + " ");
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            }else {
                System.out.print(cur1.value + " ");
            }
            cur1 = cur1.right;
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
        MorrisIn(head); //Morris中序
        MorrisPre(head);
        MorrisPos(head);
    }

}