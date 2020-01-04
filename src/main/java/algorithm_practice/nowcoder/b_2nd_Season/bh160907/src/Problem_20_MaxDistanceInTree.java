package algorithm_practice.nowcoder.b_2nd_Season.bh160907.src;

public class Problem_20_MaxDistanceInTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int maxDistance(Node head) {
		int[] record = new int[1];
		return posOrder(head, record);
	}

	/**
	 * head为头的情况下，此树的最大距离
	 * @param head
	 * @param record	距离
	 * @return
	 */
	public static int posOrder(Node head, int[] record) {
		if (head == null) {
			record[0] = 0;
			return 0;
		}
		int lMax = posOrder(head.left, record);
		int maxfromLeft = record[0];	//单独的左子树上的最大距离
		int rMax = posOrder(head.right, record);
		int maxFromRight = record[0];	//单独的右子树上的最大距离
		int curNodeMax = maxfromLeft + maxFromRight + 1;		//包含head的情况下的最大距离
		record[0] = Math.max(maxfromLeft, maxFromRight) + 1;	//单独在这棵树上 求得的最大距离
		return Math.max(Math.max(lMax, rMax), curNodeMax);
	}

	public static void main(String[] args) {
		Node head1 = new Node(1);
		head1.left = new Node(2);
		head1.right = new Node(3);
		head1.left.left = new Node(4);
		head1.left.right = new Node(5);
		head1.right.left = new Node(6);
		head1.right.right = new Node(7);
		head1.left.left.left = new Node(8);
		head1.right.left.right = new Node(9);
		System.out.println(maxDistance(head1));

		Node head2 = new Node(1);
		head2.left = new Node(2);
		head2.right = new Node(3);
		head2.right.left = new Node(4);
		head2.right.right = new Node(5);
		head2.right.left.left = new Node(6);
		head2.right.right.right = new Node(7);
		head2.right.left.left.left = new Node(8);
		head2.right.right.right.right = new Node(9);
		System.out.println(maxDistance(head2));

	}

}
