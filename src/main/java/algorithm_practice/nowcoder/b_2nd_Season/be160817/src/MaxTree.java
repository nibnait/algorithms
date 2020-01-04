package algorithm_practice.nowcoder.b_2nd_Season.be160817.src;

import java.util.HashMap;
import java.util.Map.Entry;

import java.util.Stack;

public class MaxTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node getMaxTree(int[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		Stack<Integer> stack = new Stack<Integer>();
		HashMap<Integer, Integer> lessBigMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < arr.length; i++) {
			if (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
				stackPopAndSetMap(arr, stack, lessBigMap, i);
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			stackPopAndSetMap(arr, stack, lessBigMap, null);
		}
		Node[] nArr = new Node[arr.length];
		for (int i = 0; i < arr.length; i++) {
			nArr[i] = new Node(arr[i]);
		}
		Node head = null;
		for (Entry<Integer, Integer> entry : lessBigMap.entrySet()) {
			Integer cindex = entry.getKey();
			Integer pindex = entry.getValue();
			if (pindex != null) {
				if (nArr[pindex].left == null) {
					nArr[pindex].left = nArr[cindex];
				} else {
					nArr[pindex].right = nArr[cindex];
				}
			} else {
				head = nArr[cindex];
			}
		}
		return head;
	}

	public static void stackPopAndSetMap(int[] arr, Stack<Integer> stack, HashMap<Integer, Integer> map, Integer r) {
		int mid = stack.pop();
		if (!stack.isEmpty() && r != null) {
			map.put(mid, arr[stack.peek()] < arr[r] ? stack.peek() : r);
		} else {
			map.put(mid, stack.isEmpty() ? r : stack.peek());
		}
	}

	// for test
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	// for test
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

	// for test
	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	// for test
	public static void main(String[] args) {
		int[] uniqueArr = { 3, 4, 5, 1, 2 };
		Node head = getMaxTree(uniqueArr);
		printTree(head);

	}

}
