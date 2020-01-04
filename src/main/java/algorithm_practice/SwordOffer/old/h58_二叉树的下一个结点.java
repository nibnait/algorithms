package algorithm_practice.SwordOffer.old;

import common.datastruct.BinaryTreeNodeWithParent;

/**
 * 题目：给定一棵二叉树和其中的一个结点，如何找出中序遍历顺序的下一个结点？
 * 树中的结点除了有两个分别指向左右子结点的指针以外，还有一个指向父结点的指针。
 *
 * 【解】：（此题有指向父结点的指针，极大的降低了难度，不然你还得先 先序遍历一些这棵树，把达到此结点的路径记录下来）
 *      要寻找中序遍历二叉树的下一个结点，
 *      1. 此结点有右子树，则其下一个结点为：其右子树上的最左结点
 *      2. 没有右子树，
 *           - 他是他爸爸的左孩子，那么他的下一个结点就是他的爸爸
 *           - 他是他爸爸的右孩子，他自己还没有右子树：这时我们就要向上找了，直到根结点或者此结点是他爸爸的右孩子（$_$ 语文不好，这个只能你自己画图体会了）
 *
 *（书上的字写错了，代码到是对了）
 * Created by nibnait on 2016/10/3.
 */
public class h58_二叉树的下一个结点 {

    private static BinaryTreeNodeWithParent getNext(BinaryTreeNodeWithParent head) {
        if (head == null) {
            return null;
        }

        BinaryTreeNodeWithParent target = null;
        if (head.right != null) {   //此结点有右子树
            target = head.right;
            while (target.left != null) {
                target = target.left;
            }
            return target;
        } else if (head.parent != null){    //没有右子树 && 不是二叉树根结点
            target = head.parent;
            BinaryTreeNodeWithParent cur = head;
            while (target != null && cur==target.right) {
                cur = target;
                target = target.parent;
            }
            return target;
        }
        return null;
    }

   
    public static void main(String[] args) {
        test01();
    }
    //                            1
    //                  2                   3
    //             4         5          6          7
    //          8     9   10   11   12   13    14   15
    private static void test01() {
        BinaryTreeNodeWithParent n1 = new BinaryTreeNodeWithParent(1); // 12
        BinaryTreeNodeWithParent n2 = new BinaryTreeNodeWithParent(2); // 10
        BinaryTreeNodeWithParent n3 = new BinaryTreeNodeWithParent(3); // 14
        BinaryTreeNodeWithParent n4 = new BinaryTreeNodeWithParent(4); // 9
        BinaryTreeNodeWithParent n5 = new BinaryTreeNodeWithParent(5); // 11
        BinaryTreeNodeWithParent n6 = new BinaryTreeNodeWithParent(6); // 13
        BinaryTreeNodeWithParent n7 = new BinaryTreeNodeWithParent(7); // 15
        BinaryTreeNodeWithParent n8 = new BinaryTreeNodeWithParent(8); // 4
        BinaryTreeNodeWithParent n9 = new BinaryTreeNodeWithParent(9); // 2
        BinaryTreeNodeWithParent n10 = new BinaryTreeNodeWithParent(10); // 5
        BinaryTreeNodeWithParent n11 = new BinaryTreeNodeWithParent(11); // 1
        BinaryTreeNodeWithParent n12 = new BinaryTreeNodeWithParent(12); // 6
        BinaryTreeNodeWithParent n13 = new BinaryTreeNodeWithParent(13); // 3
        BinaryTreeNodeWithParent n14 = new BinaryTreeNodeWithParent(14); // 7
        BinaryTreeNodeWithParent n15 = new BinaryTreeNodeWithParent(15); // null
        assemble(n1, n2, n3, null);
        assemble(n2, n4, n5, n1);
        assemble(n3, n6, n7, n1);
        assemble(n4, n8, n9, n2);
        assemble(n5, n10, n11, n2);
        assemble(n6, n12, n13, n3);
        assemble(n7, n14, n15, n3);
        assemble(n8, null, null, n4);
        assemble(n9, null, null, n4);
        assemble(n10, null, null, n5);
        assemble(n11, null, null, n5);
        assemble(n12, null, null, n6);
        assemble(n13, null, null, n6);
        assemble(n14, null, null, n7);
        assemble(n15, null, null, n7);
        System.out.println(getNext(n1));
        System.out.println(getNext(n2));
        System.out.println(getNext(n3));
        System.out.println(getNext(n4));
        System.out.println(getNext(n5));
        System.out.println(getNext(n6));
        System.out.println(getNext(n7));
        System.out.println(getNext(n8));
        System.out.println(getNext(n9));
        System.out.println(getNext(n10));
        System.out.println(getNext(n11));
        System.out.println(getNext(n12));
        System.out.println(getNext(n13));
        System.out.println(getNext(n14));
        System.out.println(getNext(n15));
    }

    private static void assemble(BinaryTreeNodeWithParent node,
                                 BinaryTreeNodeWithParent left,
                                 BinaryTreeNodeWithParent right,
                                 BinaryTreeNodeWithParent parent) {
        node.left = left;
        node.right = right;
        node.parent = parent;
    }

}
