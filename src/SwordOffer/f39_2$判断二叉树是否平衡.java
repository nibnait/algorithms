package SwordOffer;

import Standard.Node;

import static SwordOffer.f39_1$二叉树的深度.TreeDepth;

/**
 * 题目二：输入一棵二叉树的根结点，判断该树是不是平衡二叉树。
 * 如果某二叉树中任意结点的左右子树的深度相差不超过 1 ，那么它就是一棵平衡二叉树。
 *
 * 【法1】：
 *      可以根据上一题已求出的TreeDepth，
 *      判断此结点的左右子树的深度相差如果如果不超过1，则平衡
 *
 * 【法2】：
 *      由于法1，每次判断都会重复遍历最下面叶子结点，影响性能！
 *      所以此题最优解：
 *          应该采用后序遍历的方法
 *          因为后序遍历，每遍历到一个根结点，他的左右子树都已经走过一遍了，
 *          所以 我们可以没走过一个结点，标上他的深度。然后即可直接判定此结点下面的二叉树是否平衡了
 *
 * Created by nibnait on 2016/10/1.
 */
public class f39_2$判断二叉树是否平衡 {

    private static boolean isBalanced(Node head){
        if (head == null){
            return true;
        }
        int left = TreeDepth(head.left);
        int right = TreeDepth(head.right);
        int diff = left - right;
        if (diff>1 || diff<-1){
            return false;
        }
        return isBalanced(head.left) && isBalanced(head.right);
    }

    /**
     * 法2：后序遍历，标上深度
     * @param head
     * @return
     */
    private static boolean isBalanced2(Node head){
        int[] depth = new int[]{0};
        return isBalanced2(head, depth);
    }

    private static boolean isBalanced2(Node head, int[] depth) {
        if (head == null){
            depth[0] = 0;
            return true;
        }
        int[] left = new int[]{0};
        int[] right = new int[]{0};
        if (isBalanced2(head.left, left) && isBalanced2(head.right, right)){
            int diff = left[0] - right[0];
            if (diff>=-1 && diff<=1){
                depth[0] = 1+ Math.max(left[0], right[0]);
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }
    // 完全二叉树
    //             1
    //         /      \
    //        2        3
    //       /\       / \
    //      4  5     6   7
    private static void test1() {
        Node n1 = new Node(1);
        Node n2 = new Node(1);
        Node n3 = new Node(1);
        Node n4 = new Node(1);
        Node n5 = new Node(1);
        Node n6 = new Node(1);
        Node n7 = new Node(1);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        System.out.println(isBalanced(n1));
        System.out.println(isBalanced2(n1));
        System.out.println("----------------");
    }
    // 不是完全二叉树，但是平衡二叉树
    //             1
    //         /      \
    //        2        3
    //       /\         \
    //      4  5         6
    //        /
    //       7
    private static void test2() {
        Node n1 = new Node(1);
        Node n2 = new Node(1);
        Node n3 = new Node(1);
        Node n4 = new Node(1);
        Node n5 = new Node(1);
        Node n6 = new Node(1);
        Node n7 = new Node(1);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n5.left = n7;
        n3.right = n6;
        System.out.println(isBalanced(n1));
        System.out.println(isBalanced2(n1));
        System.out.println("----------------");
    }
    // 不是平衡二叉树
    //             1
    //         /      \
    //        2        3
    //       /\
    //      4  5
    //        /
    //       7
    private static void test3() {
        Node n1 = new Node(1);
        Node n2 = new Node(1);
        Node n3 = new Node(1);
        Node n4 = new Node(1);
        Node n5 = new Node(1);
        Node n6 = new Node(1);
        Node n7 = new Node(1);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n5.left = n7;
        System.out.println(isBalanced(n1));
        System.out.println(isBalanced2(n1));
        System.out.println("----------------");
    }
    //               1
    //              /
    //             2
    //            /
    //           3
    //          /
    //         4
    //        /
    //       5
    private static void test4() {
        Node n1 = new Node(1);
        Node n2 = new Node(1);
        Node n3 = new Node(1);
        Node n4 = new Node(1);
        Node n5 = new Node(1);
        Node n6 = new Node(1);
        Node n7 = new Node(1);
        n1.left = n2;
        n2.left = n3;
        n3.left = n4;
        n4.left = n5;
        System.out.println(isBalanced(n1));
        System.out.println(isBalanced2(n1));
        System.out.println("----------------");
    }
    // 1
    //  \
    //   2
    //    \
    //     3
    //      \
    //       4
    //        \
    //         5
    private static void test5() {
        Node n1 = new Node(1);
        Node n2 = new Node(1);
        Node n3 = new Node(1);
        Node n4 = new Node(1);
        Node n5 = new Node(1);
        Node n6 = new Node(1);
        Node n7 = new Node(1);
        n1.right = n2;
        n2.right = n3;
        n3.right = n4;
        n4.right = n5;
        System.out.println(isBalanced(n1));
        System.out.println(isBalanced2(n1));
        System.out.println("----------------");
    }


}
