package algorithm_practice.SwordOffer.old;

import common.datastruct.BinaryTreeNode;
import common.util.SysOut;

import java.util.LinkedList;
import java.util.List;

/**
 * 题目：请实现两个函数，分别用来序列化和反序列化二叉树。
 *
 *【解】：
 *  通过分析解决前面的第6题 b06_重建二叉树。我们知道可以从前序遍历和中序遍历构造出一棵二叉树。
 *  受此启发，我们可以先把一棵二叉树序列化成一个前序遍历序列和一个中序序列，然后再反序列化时通过这两个序列重构出原二叉树。
 *  【但是】 这个思路有两个缺点。
 *  一个缺点是该方法要求二叉树中不能用有数值重复的结点。
 *  另外只有当两个序列中所有数据都读出后才能开始反序列化。如果两个遍历序列的数据是从一个流里读出来的，那就可能需要等较长的时间。
 *
 *  实际上如果二叉树的序列化是从根结点开始的话，那么相应的反序列化在根结点的数值读出来的时候就可以开始了。
 *  【序列化】：
 *  因此我们可以根据前序遍历的顺序来序列化二叉树，因为前序遍历是从根结点开始的。
 *  当在遍历二叉树碰到 NULL 指针时，这些 NULL 指针序列化成一个特殊的字符（比如‘$’）。
 *                                 另外，结点的数值之间要用一个特殊字符（比如’,’）隔开。
 *  【反序列化】：
 *  按照前序遍历的顺序，来重建二叉树。
 *
 *
 *【法2】：我们还可以按层依次从左到右来序列化二叉树
 *   这样做有一个好处：
 *      arr[i]的左孩子：arr[2*i+1]
 *      arr[i]的右孩子：arr[2*i+2]
 *
 *
 * Created by nibnait on 2016/10/3.
 */
public class h62_序列化二叉树 {

    public static void serialize(BinaryTreeNode root, List<Integer> result) {
        if (root == null){
            result.add(null);
            return;
        }
        result.add(root.value);
        serialize(root.left, result);
        serialize(root.right, result);
    }

    private static BinaryTreeNode deserialize(BinaryTreeNode root, List<Integer> result) {
        if (result.get(0) == null) {
            result.remove(0);
            return null;
        }
        root = new BinaryTreeNode( result.remove(0));
        root.left = deserialize(root.left, result);
        root.right = deserialize(root.right, result);
        return root;
    }

    /* 按照法2的参数传递方式，这么重建二叉树，居然失败了？？？
    private static BinaryTreeNode deserialize(List<Integer> result, int idx) {
        if (result.get(idx) == null) {
            idx++;
            return null;
        }
        BinaryTreeNode root = new BinaryTreeNode(result.get(idx++));
        root.left = deserialize(result, idx);
        root.right = deserialize(result, idx);
        return root;
    }*/

    /**
     * 法2：按层序列化，
     * @param root
     * @param result
     */
    public static void serialize2(BinaryTreeNode root, List<Integer> result) {
        List<BinaryTreeNode> list = new LinkedList<>();
        list.add(root);
        BinaryTreeNode node;
        while (list.size() > 0) {
            node = list.remove(0);
            if (node == null) {
                result.add(null);
            }else {
                result.add(node.value);
                list.add(node.left);
                list.add(node.right);
            }
        }
    }

    public static BinaryTreeNode deserialize2(List<Integer> result, int idx) {
        if (result.size() < 1 || idx < 0 || result.size() <= idx || result.get(idx) == null) {
            return null;
        }
        BinaryTreeNode root = new BinaryTreeNode(result.get(idx));
        root.left = deserialize2(result, idx * 2 + 1);
        root.right = deserialize2(result, idx * 2 + 2);
        return root;
    }



    public static void main(String[] args) {
        System.out.println("书上的方法：");
        BinaryTreeNode root = initBinaryTree();
        List<Integer> result1 = new LinkedList<>();
        serialize(root, result1);
        System.out.println(result1);
        BinaryTreeNode root1 = new BinaryTreeNode();
        root1 = deserialize(root1, result1);
//        root1 = deserialize(result1, 0);
        SysOut.inOrderPrint(root1);

        System.out.println();
        System.out.println("法2：");
        List<Integer> result2 = new LinkedList<>();
        serialize2(root, result2);
        System.out.println(result2);
        BinaryTreeNode root2 = deserialize2(result2, 0) ;
        SysOut.inOrderPrint(root2);
    }

    //                            1
    //                  2                   3
    //             4         5          6          7
    //          8     9
    private static BinaryTreeNode initBinaryTree() {
        BinaryTreeNode n1 = new BinaryTreeNode(1);
        BinaryTreeNode n2 = new BinaryTreeNode(2);
        BinaryTreeNode n3 = new BinaryTreeNode(3);
        BinaryTreeNode n4 = new BinaryTreeNode(4);
        BinaryTreeNode n5 = new BinaryTreeNode(5);
        BinaryTreeNode n6 = new BinaryTreeNode(6);
        BinaryTreeNode n7 = new BinaryTreeNode(7);
        BinaryTreeNode n8 = new BinaryTreeNode(8);
        BinaryTreeNode n9 = new BinaryTreeNode(9);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        n4.left = n8;
        n4.right = n9;
        return n1;
    }
}
