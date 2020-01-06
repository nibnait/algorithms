package algorithm_practice.nowcoder.b_2nd_Season.bj160928;

import common.datastruct.BinaryTreeNode;
import common.util.SysOut;

import static algorithm_practice.nowcoder.b_2nd_Season.bj160928.IsSearchBinaryTree.isBST;
import static algorithm_practice.nowcoder.b_2nd_Season.bj160928.RecoverBST.recoverTree;

/**
 * Created by nibnait on 2016/10/9.
 */
public class testRecoverBST {

    public static void main(String[] args) {
        BinaryTreeNode head = new BinaryTreeNode(5);
        head.left = new BinaryTreeNode(3);
        head.right = new BinaryTreeNode(7);
        head.left.left = new BinaryTreeNode(2);
        head.left.right = new BinaryTreeNode(4);
        head.right.left = new BinaryTreeNode(6);
        head.right.right = new BinaryTreeNode(8);
        head.left.left.left = new BinaryTreeNode(1);
        SysOut.printBinaryTree(head);
        System.out.println(isBST(head));

        // 1, 7 -> e1, 5 -> e2
        System.out.println("situation 1");
        BinaryTreeNode head1 = new BinaryTreeNode(7);
        head1.left = new BinaryTreeNode(3);
        head1.right = new BinaryTreeNode(5);
        head1.left.left = new BinaryTreeNode(2);
        head1.left.right = new BinaryTreeNode(4);
        head1.right.left = new BinaryTreeNode(6);
        head1.right.right = new BinaryTreeNode(8);
        head1.left.left.left = new BinaryTreeNode(1);
        SysOut.printBinaryTree(head1);
        System.out.println(isBST(head1));
        BinaryTreeNode res1 = recoverTree(head1);
        SysOut.printBinaryTree(res1);
        System.out.println(isBST(res1));

        // 2, 6 -> e1, 5 -> e2
        System.out.println("situation 2");
        BinaryTreeNode head2 = new BinaryTreeNode(6);
        head2.left = new BinaryTreeNode(3);
        head2.right = new BinaryTreeNode(7);
        head2.left.left = new BinaryTreeNode(2);
        head2.left.right = new BinaryTreeNode(4);
        head2.right.left = new BinaryTreeNode(5);
        head2.right.right = new BinaryTreeNode(8);
        head2.left.left.left = new BinaryTreeNode(1);
        SysOut.printBinaryTree(head2);
        System.out.println(isBST(head2));
        BinaryTreeNode res2 = recoverTree(head2);
        SysOut.printBinaryTree(res2);
        System.out.println(isBST(res2));

        // 3, 8 -> e1, 5 -> e2
        System.out.println("situation 3");
        BinaryTreeNode head3 = new BinaryTreeNode(8);
        head3.left = new BinaryTreeNode(3);
        head3.right = new BinaryTreeNode(7);
        head3.left.left = new BinaryTreeNode(2);
        head3.left.right = new BinaryTreeNode(4);
        head3.right.left = new BinaryTreeNode(6);
        head3.right.right = new BinaryTreeNode(5);
        head3.left.left.left = new BinaryTreeNode(1);
        SysOut.printBinaryTree(head3);
        System.out.println(isBST(head3));
        BinaryTreeNode res3 = recoverTree(head3);
        SysOut.printBinaryTree(res3);
        System.out.println(isBST(res3));

        // 4, 5 -> e1, 3 -> e2
        System.out.println("situation 4");
        BinaryTreeNode head4 = new BinaryTreeNode(3);
        head4.left = new BinaryTreeNode(5);
        head4.right = new BinaryTreeNode(7);
        head4.left.left = new BinaryTreeNode(2);
        head4.left.right = new BinaryTreeNode(4);
        head4.right.left = new BinaryTreeNode(6);
        head4.right.right = new BinaryTreeNode(8);
        head4.left.left.left = new BinaryTreeNode(1);
        SysOut.printBinaryTree(head4);
        System.out.println(isBST(head4));
        BinaryTreeNode res4 = recoverTree(head4);
        SysOut.printBinaryTree(res4);
        System.out.println(isBST(res4));

        // 5, 5 -> e1, 2 -> e2
        System.out.println("situation 5");
        BinaryTreeNode head5 = new BinaryTreeNode(2);
        head5.left = new BinaryTreeNode(3);
        head5.right = new BinaryTreeNode(7);
        head5.left.left = new BinaryTreeNode(5);
        head5.left.right = new BinaryTreeNode(4);
        head5.right.left = new BinaryTreeNode(6);
        head5.right.right = new BinaryTreeNode(8);
        head5.left.left.left = new BinaryTreeNode(1);
        SysOut.printBinaryTree(head5);
        System.out.println(isBST(head5));
        BinaryTreeNode res5 = recoverTree(head5);
        SysOut.printBinaryTree(res5);
        System.out.println(isBST(res5));

        // 6, 5 -> e1, 4 -> e2
        System.out.println("situation 6");
        BinaryTreeNode head6 = new BinaryTreeNode(4);
        head6.left = new BinaryTreeNode(3);
        head6.right = new BinaryTreeNode(7);
        head6.left.left = new BinaryTreeNode(2);
        head6.left.right = new BinaryTreeNode(5);
        head6.right.left = new BinaryTreeNode(6);
        head6.right.right = new BinaryTreeNode(8);
        head6.left.left.left = new BinaryTreeNode(1);
        SysOut.printBinaryTree(head6);
        System.out.println(isBST(head6));
        BinaryTreeNode res6 = recoverTree(head6);
        SysOut.printBinaryTree(res6);
        System.out.println(isBST(res6));

        // 7, 4 -> e1, 3 -> e2
        System.out.println("situation 7");
        BinaryTreeNode head7 = new BinaryTreeNode(5);
        head7.left = new BinaryTreeNode(4);
        head7.right = new BinaryTreeNode(7);
        head7.left.left = new BinaryTreeNode(2);
        head7.left.right = new BinaryTreeNode(3);
        head7.right.left = new BinaryTreeNode(6);
        head7.right.right = new BinaryTreeNode(8);
        head7.left.left.left = new BinaryTreeNode(1);
        SysOut.printBinaryTree(head7);
        System.out.println(isBST(head7));
        BinaryTreeNode res7 = recoverTree(head7);
        SysOut.printBinaryTree(res7);
        System.out.println(isBST(res7));

        // 8, 8 -> e1, 7 -> e2
        System.out.println("situation 8");
        BinaryTreeNode head8 = new BinaryTreeNode(5);
        head8.left = new BinaryTreeNode(3);
        head8.right = new BinaryTreeNode(8);
        head8.left.left = new BinaryTreeNode(2);
        head8.left.right = new BinaryTreeNode(4);
        head8.right.left = new BinaryTreeNode(6);
        head8.right.right = new BinaryTreeNode(7);
        head8.left.left.left = new BinaryTreeNode(1);
        SysOut.printBinaryTree(head8);
        System.out.println(isBST(head8));
        BinaryTreeNode res8 = recoverTree(head8);
        SysOut.printBinaryTree(res8);
        System.out.println(isBST(res8));

        // 9, 3 -> e1, 2 -> e2
        System.out.println("situation 9");
        BinaryTreeNode head9 = new BinaryTreeNode(5);
        head9.left = new BinaryTreeNode(2);
        head9.right = new BinaryTreeNode(7);
        head9.left.left = new BinaryTreeNode(3);
        head9.left.right = new BinaryTreeNode(4);
        head9.right.left = new BinaryTreeNode(6);
        head9.right.right = new BinaryTreeNode(8);
        head9.left.left.left = new BinaryTreeNode(1);
        SysOut.printBinaryTree(head9);
        System.out.println(isBST(head9));
        BinaryTreeNode res9 = recoverTree(head9);
        SysOut.printBinaryTree(res9);
        System.out.println(isBST(res9));

        // 10, 7 -> e1, 6 -> e2
        System.out.println("situation 10");
        BinaryTreeNode head10 = new BinaryTreeNode(5);
        head10.left = new BinaryTreeNode(3);
        head10.right = new BinaryTreeNode(6);
        head10.left.left = new BinaryTreeNode(2);
        head10.left.right = new BinaryTreeNode(4);
        head10.right.left = new BinaryTreeNode(7);
        head10.right.right = new BinaryTreeNode(8);
        head10.left.left.left = new BinaryTreeNode(1);
        SysOut.printBinaryTree(head10);
        System.out.println(isBST(head10));
        BinaryTreeNode res10 = recoverTree(head10);
        SysOut.printBinaryTree(res10);
        System.out.println(isBST(res10));

        // 11, 6 -> e1, 2 -> e2
        System.out.println("situation 11");
        BinaryTreeNode head11 = new BinaryTreeNode(5);
        head11.left = new BinaryTreeNode(3);
        head11.right = new BinaryTreeNode(7);
        head11.left.left = new BinaryTreeNode(6);
        head11.left.right = new BinaryTreeNode(4);
        head11.right.left = new BinaryTreeNode(2);
        head11.right.right = new BinaryTreeNode(8);
        head11.left.left.left = new BinaryTreeNode(1);
        SysOut.printBinaryTree(head11);
        System.out.println(isBST(head11));
        BinaryTreeNode res11 = recoverTree(head11);
        SysOut.printBinaryTree(res11);
        System.out.println(isBST(res11));

        // 12, 8 -> e1, 2 -> e2
        System.out.println("situation 12");
        BinaryTreeNode head12 = new BinaryTreeNode(5);
        head12.left = new BinaryTreeNode(3);
        head12.right = new BinaryTreeNode(7);
        head12.left.left = new BinaryTreeNode(8);
        head12.left.right = new BinaryTreeNode(4);
        head12.right.left = new BinaryTreeNode(6);
        head12.right.right = new BinaryTreeNode(2);
        head12.left.left.left = new BinaryTreeNode(1);
        SysOut.printBinaryTree(head12);
        System.out.println(isBST(head12));
        BinaryTreeNode res12 = recoverTree(head12);
        SysOut.printBinaryTree(res12);
        System.out.println(isBST(res12));

        // 13, 6 -> e1, 4 -> e2
        System.out.println("situation 13");
        BinaryTreeNode head13 = new BinaryTreeNode(5);
        head13.left = new BinaryTreeNode(3);
        head13.right = new BinaryTreeNode(7);
        head13.left.left = new BinaryTreeNode(2);
        head13.left.right = new BinaryTreeNode(6);
        head13.right.left = new BinaryTreeNode(4);
        head13.right.right = new BinaryTreeNode(8);
        head13.left.left.left = new BinaryTreeNode(1);
        SysOut.printBinaryTree(head13);
        System.out.println(isBST(head13));
        BinaryTreeNode res13 = recoverTree(head13);
        SysOut.printBinaryTree(res13);
        System.out.println(isBST(res13));

        // 14, 8 -> e1, 4 -> e2
        System.out.println("situation 14");
        BinaryTreeNode head14 = new BinaryTreeNode(5);
        head14.left = new BinaryTreeNode(3);
        head14.right = new BinaryTreeNode(7);
        head14.left.left = new BinaryTreeNode(2);
        head14.left.right = new BinaryTreeNode(8);
        head14.right.left = new BinaryTreeNode(6);
        head14.right.right = new BinaryTreeNode(4);
        head14.left.left.left = new BinaryTreeNode(1);
        SysOut.printBinaryTree(head14);
        System.out.println(isBST(head14));
        BinaryTreeNode res14 = recoverTree(head14);
        SysOut.printBinaryTree(res14);
        System.out.println(isBST(res14));

    }
    
}
