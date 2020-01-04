package algorithm_practice.SwordOffer.old;

/**
 * 题目：输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则返回 true。否则返回 false。
 * 假设输入的数组的任意两个数字都互不相同。
 *
 * 【解】：
 *      递归
 *      盯准根结点所在的位置！
 *
 * Created by nibnait on 2016/9/26.
 */
public class d24_二叉搜索树的后序遍历序列 {


    private static boolean verifySequenceOfBST(int[] squence) {
        if (squence == null || squence.length<=0){
            return false;
        }
        return verifySequenceOfBST(squence, 0, squence.length-1);
    }

    private static boolean verifySequenceOfBST(int[] squence, int start, int end) {
        if (start >= end){
            return true;
        }
        int root = squence[end];    //end位置为根结点
        int index = 0;
        //找到第一个比根结点大的数的位置
        while (squence[index]<root && index<end-1){
            index++;
        }
        int mid = index;
        while (squence[index]>root && index<end-1){
            index++;
        }
        if (index != end-1){    //end-1位置应该为root的右子树的根结点
            return false;   //出现了异常，无法完成此出站序列
        }
        return verifySequenceOfBST(squence,start,mid-1) && verifySequenceOfBST(squence,mid,end-1);
    }


    public static void main(String[] args) {
        
        //           10
        //         /   \
        //        6     14
        //       /\     /\
        //      4  8  12  16
        int[] squence = {4, 8, 6, 12, 16, 14, 10};
        System.out.println("true: " + verifySequenceOfBST(squence));
        //           5
        //          / \
        //         4   7
        //            /
        //           6
        int[] squence2 = {4, 6, 7, 5};
        System.out.println("true: " + verifySequenceOfBST(squence2));
        //               5
        //              /
        //             4
        //            /
        //           3
        //          /
        //         2
        //        /
        //       1
        int[] squence3 = {1, 2, 3, 4, 5};
        System.out.println("true: " + verifySequenceOfBST(squence3));
        // 1
        //  \
        //   2
        //    \
        //     3
        //      \
        //       4
        //        \
        //         5
        int[] squence4 = {5, 4, 3, 2, 1};
        System.out.println("true: " + verifySequenceOfBST(squence4));
        // 树中只有1个结点
        int[] squence5 = {5};
        System.out.println("true: " + verifySequenceOfBST(squence5));
        int[] squence6 = {7, 4, 6, 5};
        System.out.println("false: " + verifySequenceOfBST(squence6));
        int[] squence7 = {4, 6, 12, 8, 16, 14, 10};
        System.out.println("false: " + verifySequenceOfBST(squence7));

    }

}
