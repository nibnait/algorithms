package algorithm_practice.nowcoder.b_2nd_Season.bi160914.src;

import java.util.HashMap;
import java.util.Map;

public class Problem_08_BiggestBSTTopologyInTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int bstTopoSize1(Node head) {
		if (head == null) {
			return 0;
		}
		int max = maxTopo(head, head);
		max = Math.max(bstTopoSize1(head.left), max);
		max = Math.max(bstTopoSize1(head.right), max);
		return max;
	}

	public static int maxTopo(Node h, Node n) {
		if (h != null && n != null && isBSTNode(h, n, n.value)) {
			return maxTopo(h, n.left) + maxTopo(h, n.right) + 1;
		}
		return 0;
	}

	public static boolean isBSTNode(Node h, Node n, int value) {
		if (h == null) {
			return false;
		}
		if (h == n) {
			return true;
		}
		return isBSTNode(h.value > value ? h.left : h.right, n, value);
	}

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

	public static int bstTopoSize2(Node head) {
		Map<Node, Record> map = new HashMap<Node, Record>();
		return posOrder(head, map);
	}

	/**
	 *
	 * @param h
	 * @param map 以h为头的情况下，最大搜索拓扑结构的大小。（返回值）
	 * @return
	 */
	public static int posOrder(Node h, Map<Node, Record> map) {
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
	public static int modifyMap(Node n, int v, Map<Node, Record> m, boolean s) {
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

	// for test -- print tree
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		Node head = new Node(6);
		head.left = new Node(1);
		head.left.left = new Node(0);
		head.left.right = new Node(3);
		head.right = new Node(12);
		head.right.left = new Node(10);
		head.right.left.left = new Node(4);
		head.right.left.left.left = new Node(2);
		head.right.left.left.right = new Node(5);
		head.right.left.right = new Node(14);
		head.right.left.right.left = new Node(11);
		head.right.left.right.right = new Node(15);
		head.right.right = new Node(13);
		head.right.right.left = new Node(20);
		head.right.right.right = new Node(16);
		printTree(head);

		System.out.println(bstTopoSize1(head));
		System.out.println(bstTopoSize2(head));

	}

}
