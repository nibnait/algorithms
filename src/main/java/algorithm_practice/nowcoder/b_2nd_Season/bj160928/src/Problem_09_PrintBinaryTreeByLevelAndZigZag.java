package algorithm_practice.nowcoder.b_2nd_Season.bj160928.src;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Problem_09_PrintBinaryTreeByLevelAndZigZag {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static void printByLevel(Node head) {
		if (head == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<Node>();
		int level = 1;
		Node last = head;
		Node nLast = null;
		queue.offer(head);
		System.out.print("Level " + (level++) + " : ");
		while (!queue.isEmpty()) {
			head = queue.poll();
			System.out.print(head.value + " ");
			if (head.left != null) {
				queue.offer(head.left);
				nLast = head.left;
			}
			if (head.right != null) {
				queue.offer(head.right);
				nLast = head.right;
			}
			if (head == last && !queue.isEmpty()) {
				System.out.print("\nLevel " + (level++) + " : ");
				last = nLast;
			}
		}
		System.out.println();
	}

	public static void printByZigZag(Node head) {
		if (head == null) {
			return;
		}
		Deque<Node> dq = new LinkedList<Node>();
		int level = 1;
		boolean lr = true;
		Node cur = head;
		Node last = head;
		Node nLast = null;
		dq.offerFirst(head);
		pringLevelAndOrientation(level++, lr);
		while (!dq.isEmpty()) {
			if (lr) {
				cur = dq.pollFirst();
				if (cur.left != null) {
					nLast = nLast == null ? cur.left : nLast;
					dq.offerLast(cur.left);
				}
				if (cur.right != null) {
					nLast = nLast == null ? cur.right : nLast;
					dq.offerLast(cur.right);
				}
			} else {
				cur = dq.pollLast();
				if (cur.right != null) {
					nLast = nLast == null ? cur.right : nLast;
					dq.offerFirst(cur.right);
				}
				if (cur.left != null) {
					nLast = nLast == null ? cur.left : nLast;
					dq.offerFirst(cur.left);
				}
			}
			System.out.print(cur.value + " ");
			if (cur == last && !dq.isEmpty()) {
				lr = !lr;
				last = nLast;
				nLast = null;
				System.out.println();
				pringLevelAndOrientation(level++, lr);
			}
		}
		System.out.println();
	}

	public static void pringLevelAndOrientation(int level, boolean lr) {
		System.out.print("Level " + level + " from ");
		System.out.print(lr ? "left to right: " : "right to left: ");
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
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.right.left = new Node(5);
		head.right.right = new Node(6);
		head.right.left.left = new Node(7);
		head.right.left.right = new Node(8);

		printTree(head);

		System.out.println("===============");
		printByLevel(head);

		System.out.println("===============");
		printByZigZag(head);

	}

}
