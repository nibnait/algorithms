package algorithm_practice.nowcoder.b_2nd_Season.bi160914;

import common.datastruct.BinaryTreeNode;
import common.util.SysOut;

import java.util.HashMap;
import java.util.Map;

public class BiggestBSTTopologyInTree {

    /**
     * 拓扑贡献（l，r）
     */
    public static class Record {
        public int l;
        public int r;

        public Record(int left, int right) {
            this.l = left;
            this.r = right;
        }
    }

    public static int bstTopoSize(BinaryTreeNode head) {
        Map<BinaryTreeNode, Record> map = new HashMap<BinaryTreeNode, Record>();
        return posOrder(head, map);
    }

    /**
     *
     * @param h
     * @param map 以h为头的情况下，最大搜索拓扑结构的大小。（返回值）
     * @return
     */
    public static int posOrder(BinaryTreeNode h, Map<BinaryTreeNode, Record> map) {
        if (h == null) {
            return 0;
        }
        int ls = posOrder(h.left, map);
        int rs = posOrder(h.right, map);
        modifyMap(h.left, h.value, map, true);
        modifyMap(h.right, h.value, map, false);
        Record lr = map.get(h.left);
        Record rr = map.get(h.right);
        int lbst = lr == null ? 0 : lr.l + lr.r + 1;
        int rbst = rr == null ? 0 : rr.l + rr.r + 1;
        map.put(h, new Record(lbst, rbst));
        return Math.max(lbst + rbst + 1, Math.max(ls, rs));
    }

    /**
     *
     * @param n
     * @param v 新头部的值（当前头部的值）
     * @param m
     * @param s  true：当前是左子树（关心右边界），false：当前是右子树（关心左边界）
     * @return
     */
    public static int modifyMap(BinaryTreeNode n, int v, Map<BinaryTreeNode, Record> m, boolean s) {
        if (n == null || (!m.containsKey(n))) {
            return 0;
        }
        Record r = m.get(n);
        if ((s && n.value > v) || ((!s) && n.value < v)) {
            m.remove(n);
            return r.l + r.r + 1;
        } else {
            int minus = modifyMap(s ? n.right : n.left, v, m, s);
            if (s) {
                r.r = r.r - minus;
            } else {
                r.l = r.l - minus;
            }
            m.put(n, r);
            return minus;
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode head = new BinaryTreeNode(6);
        head.left = new BinaryTreeNode(1);
        head.left.left = new BinaryTreeNode(0);
        head.left.right = new BinaryTreeNode(3);
        head.right = new BinaryTreeNode(12);
        head.right.left = new BinaryTreeNode(10);
        head.right.left.left = new BinaryTreeNode(4);
        head.right.left.left.left = new BinaryTreeNode(2);
        head.right.left.left.right = new BinaryTreeNode(5);
        head.right.left.right = new BinaryTreeNode(14);
        head.right.left.right.left = new BinaryTreeNode(11);
        head.right.left.right.right = new BinaryTreeNode(15);
        head.right.right = new BinaryTreeNode(13);
        head.right.right.left = new BinaryTreeNode(20);
        head.right.right.right = new BinaryTreeNode(16);
        SysOut.printBinaryTree(head);

        System.out.println(bstTopoSize(head));
    }

}
