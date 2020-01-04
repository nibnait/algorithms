package algorithm_practice.SwordOffer.old;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 题目：输入两个树结点，求他们的最低公共祖先
 *
 * 【注意】：
 *  1. 此树不一定是二叉树，（如果是二叉搜索树==> 二分查找 O（logN））
 *  2. 树中的结点没有指向父结点的指针（直接将此题转化为：求两个链表的第一个公共结点问题）
 *
 *  【解】：
 *      即使是一颗多叉树，一样可以转化为 求两个链表的公共结点问题
 *      1. 将到达两个树结点的必经之路记录下来，
 *      2. 然后开始寻找两条链表的最后公共结点
 *
 * Created by nibnait on 2016/10/2.
 */
public class g50_树中两个结点的最低公共祖先 {

    static class TreeNode {

        public int value;
        public List<TreeNode> children = new LinkedList<>();

        public TreeNode() {
        }

        public TreeNode(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value + "";
        }
    }

    /**
     * 寻找树中两个结点的最低公共祖先
     * @param root  树的根结点
     * @param p1    结点1
     * @param p2    结点2
     * @return 公共结点，没有返回null
     */
    private static TreeNode getLastCommonParent(TreeNode root, TreeNode p1, TreeNode p2) {
        if (root==null || p1==null || p2==null){
            return null;
        }
        List<TreeNode> path1 = new LinkedList<>();
        getNodePath(root, p1, path1);
        List<TreeNode> path2 = new LinkedList<>();
        getNodePath(root, p2, path2);
        return getLastCommonNode(path1, path2);
    }

    /**
     * 寻找两条路径的最后一个公共结点
     * @param path1
     * @param path2
     * @return 没有则返回null
     */
    private static TreeNode getLastCommonNode(List<TreeNode> path1, List<TreeNode> path2) {
        Iterator<TreeNode> it1 = path1.iterator();
        Iterator<TreeNode> it2 = path2.iterator();
        TreeNode last = null;
        while (it1.hasNext() && it2.hasNext()){
            TreeNode tmp = it1.next();
            if (tmp == it2.next()){
                last = tmp;
            }
        }
        return last;
    }

    /**
     * 记录到达结点p的必经之路
     * @param root
     * @param p
     * @param path
     */
    private static void getNodePath(TreeNode root, TreeNode p, List<TreeNode> path) {
        if (root == null){
            return;
        }
        path.add(root);
        List<TreeNode> children = root.children;
        //挨个递归处理各个子结点
        for(TreeNode node: children){
            if (node == p){
                path.add(node);
                return;
            } else {
                getNodePath(node, p, path);
            }
        }
        //此路不通，原路返回
        path.remove(path.size() - 1);
    }


    public static void main(String[] args) {
        test01();
        System.out.println("==========");
        test02();
        System.out.println("==========");
        test03();
    }
    // 形状普通的树
    //             1
    //           /   \
    //         2      3
    //        /         \
    //      4            5
    //     / \        /  |  \
    //    6   7      8   9  10
    private static void test01() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);
        TreeNode n10 = new TreeNode(10);
        n1.children.add(n2);
        n1.children.add(n3);
        n2.children.add(n4);
        n4.children.add(n6);
        n4.children.add(n7);
        n3.children.add(n5);
        n5.children.add(n8);
        n5.children.add(n9);
        n5.children.add(n10);
        System.out.println(getLastCommonParent(n1, n6, n8));
    }
    // 树退化成一个链表
    //               1
    //              /
    //             2
    //            /
    //           3
    //          /
    //         4
    //        /
    //       5
    private static void test02() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n1.children.add(n2);
        n2.children.add(n3);
        n3.children.add(n4);
        n4.children.add(n5);
        System.out.println(getLastCommonParent(n1, n4, n5));
    }
    // 树退化成一个链表，一个结点不在树中
    //               1
    //              /
    //             2
    //            /
    //           3
    //          /
    //         4
    //        /
    //       5
    private static void test03() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        n1.children.add(n2);
        n2.children.add(n3);
        n3.children.add(n4);
        n4.children.add(n5);
        System.out.println(getLastCommonParent(n1, n5, n6));
    }

}
