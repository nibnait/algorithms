package algorithm_practice.nowcoder.b_2nd_Season.bi160914;

import common.datastruct.BinaryTreeNode;
import common.util.PrintBinaryTree;

/**
 * 二叉树的神级遍历
 * 空间复杂度：O(1)【使用二叉树结点中大量的指向null的指针。】
 * 时间复杂度：O(h)
 * Created by nibnait on 2016/9/19.
 */
public class MorrisTraversal {

    //左右中
    //先打印根结点的左结点，再将此根即其右结点逆序打印
    public static void MorrisPos(BinaryTreeNode head){
        if (head == null){
            return;
        }
        BinaryTreeNode cur1 = head;
        BinaryTreeNode cur2 = null;
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
    private static void printEdge(BinaryTreeNode head) {
        BinaryTreeNode tail = reverseEdge(head);
        BinaryTreeNode cur = tail;
        while (cur != null){
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    private static BinaryTreeNode reverseEdge(BinaryTreeNode head) {
        BinaryTreeNode pre = null;
        BinaryTreeNode next = null;
        while (head != null){
            next = head.right;
            head.right = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    //左中右
    public static void MorrisIn(BinaryTreeNode head) {
        if (head == null) {
            return;
        }

        BinaryTreeNode cur1 = head;     //当前结点
        BinaryTreeNode cur2 = null;
        while (cur1 != null){
            cur2 = cur1.left;       //当前结点的左子树的头
            if (cur2 != null) {
                while (cur2.right != null && cur2.right!=cur1) {
                    cur2 = cur2.right;  //一直遍历到左子树的最右结点
                }
                if (cur2.right == null) {
                    cur2.right = cur1;  //将左子树的最右结点指向cur1
                    cur1 = cur1.left;   //loop，
                    continue;           //直到遍历到最左结点。
                } else {    //cur2.right==cur1
                    cur2.right = null;  //将左子树的最右结点调换回来，重新指向null
                }
            }
            System.out.print(cur1.value + " ");
            cur1 = cur1.right;      //沿着right指针往下走
        }
        System.out.println();
    }

    //中左右
    public static void MorrisPre(BinaryTreeNode head) {
        if (head == null){
            return;
        }
        BinaryTreeNode cur1 = head;
        BinaryTreeNode cur2 = null;
        while (cur1 != null){
            cur2 = cur1.left;
            if (cur2 != null){
                while (cur2.right != null && cur2.right != cur1){
                    cur2 = cur2.right;
                }
                if (cur2.right == null){
                    cur2.right = cur1;
                    System.out.print(cur1.value + " ");     //满足标准1：（其左子树的最右结点指向null）
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            } else {	//当前结点没有左子树
                System.out.print(cur1.value + " ");
            }
            cur1 = cur1.right;
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
        MorrisIn(head); //Morris中序
        MorrisPre(head);
        MorrisPos(head);
    }

}