package algorithm_practice.SwordOffer.old;

import common.datastruct.BinaryTreeNode;
import common.util.PrintBinaryTree;

import java.util.HashMap;

/**
 * 根据前序和中序序列（不含有重复的数字），构建一棵二叉树
 *
 * Created by nibnait on 2016/9/20.
 */
public class b06_重建二叉树 {

    private static BinaryTreeNode constructBinaryTree(int[] pre, int[] in) {

        if (pre==null || in==null || pre.length!=in.length || in.length<=0){
            return null;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        return construct(pre, 0, pre.length-1, in, 0, in.length-1, map);
    }

    /**
     *
     * @param pre   前序遍历数组
     * @param ps    前序遍历开始的位置
     * @param pe    前序遍历结束的位置
     * @param in    中序遍历数组
     * @param is    中序遍历开始的位置
     * @param ie    中序遍历结束的位置
     * @param map   中序数组
     * @return
     */
    private static BinaryTreeNode construct(int[] pre, int ps, int pe, int[] in, int is, int ie, HashMap<Integer, Integer> map) {

        if (ps>pe){
            //开始位置大于结束位置，说明已经没有需要处理的元素了
            return null;
        }

        int value = pre[ps];     // 取前序遍历的第一个数字，就是当前的根结点
        int i = 0;
        try {
            i = map.get(pre[ps]);   // 在中序遍历的数组中找根结点的位置
        }catch (Exception e){
            throw new IllegalArgumentException("Invalid args: 前序/中序数组有问题");
        }
        //创建当前根结点
        BinaryTreeNode head = new BinaryTreeNode(value);
        //递归：
        head.left = construct(pre, ps+1, ps+i-is, in, is, i-1, map);
        head.right = construct(pre, ps+1+i-is, pe, in, i+1, ie, map);
        return head;
    }

    public static void main(String[] args) {
        test1();
        System.out.println();
        test2();
        System.out.println();
        test3();
        System.out.println();
        test4();
        System.out.println();
        test5();
        System.out.println();
        test6();
        System.out.println();
        test7();
    }


    // 普通二叉树  
    //              1  
    //           /     \  
    //          2       3  
    //         /       / \  
    //        4       5   6  
    //         \         /  
    //          7       8  
    private static void test1() {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        BinaryTreeNode root = constructBinaryTree(pre, in);
        PrintBinaryTree.print(root);
    }
    // 所有结点都没有右子结点  
    //            1  
    //           /  
    //          2  
    //         /  
    //        3  
    //       /  
    //      4  
    //     /  
    //    5  
    private static void test2() {
        int[] pre = {1, 2, 3, 4, 5};
        int[] in = {5, 4, 3, 2, 1};
        BinaryTreeNode root = constructBinaryTree(pre, in);
        PrintBinaryTree.print(root);
    }
    // 所有结点都没有左子结点  
    //            1  
    //             \  
    //              2  
    //               \  
    //                3  
    //                 \  
    //                  4  
    //                   \  
    //                    5  
    private static void test3() {
        int[] pre = {1, 2, 3, 4, 5};
        int[] in = {1, 2, 3, 4, 5};
        BinaryTreeNode root = constructBinaryTree(pre, in);
        PrintBinaryTree.print(root);
    }
    // 树中只有一个结点  
    private static void test4() {
        int[] pre = {1};
        int[] in = {1};
        BinaryTreeNode root = constructBinaryTree(pre, in);
        PrintBinaryTree.print(root);
    }
    // 完全二叉树  
    //              1  
    //           /     \  
    //          2       3  
    //         / \     / \  
    //        4   5   6   7  
    private static void test5() {
        int[] pre = {1, 2, 4, 5, 3, 6, 7};
        int[] in = {4, 2, 5, 1, 6, 3, 7};
        BinaryTreeNode root = constructBinaryTree(pre, in);
        PrintBinaryTree.print(root);
    }
    // 输入空指针  
    private static void test6() {
        constructBinaryTree(null, null);
    }
    // 输入的两个序列不匹配  
    private static void test7() {
        int[] pre = {1, 2, 4, 5, 3, 6, 7};
        int[] in = {4, 2, 8, 1, 6, 3, 7};
        BinaryTreeNode root = constructBinaryTree(pre, in);
        PrintBinaryTree.print(root);
    }

}
