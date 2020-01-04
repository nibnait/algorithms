package algorithm_practice.nowcoder.b_2nd_Season.bj160928.src;

public class Problem_13_IsBalancedTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static boolean isBalance(Node head) {
		return getHeight(head, 0) != -1;
	}

	public static int getHeight(Node head, int level) {
		if (head == null) {
			return level;
		}
		int lh = getHeight(head.left, level + 1);
		int rh = getHeight(head.right, level + 1);
		if (lh == -1 || rh == -1 || Math.abs(lh - rh) > 1) {
			return -1;
		}
		return Math.max(lh, rh);
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		System.out.println(isBalance(head));

	}

}
