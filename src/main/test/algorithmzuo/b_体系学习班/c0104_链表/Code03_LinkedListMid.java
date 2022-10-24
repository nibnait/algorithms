package algorithmzuo.b_体系学习班.c0104_链表;

import common.datastruct.LinkedNode;
import org.junit.Test;

import java.util.ArrayList;

public class Code03_LinkedListMid {

	@Test
	public void test() {
		LinkedNode test = null;
		test = new LinkedNode(0);
		test.next = new LinkedNode(1);
		test.next.next = new LinkedNode(2);
		test.next.next.next = new LinkedNode(3);
		test.next.next.next.next = new LinkedNode(4);
		test.next.next.next.next.next = new LinkedNode(5);
		test.next.next.next.next.next.next = new LinkedNode(6);
		test.next.next.next.next.next.next.next = new LinkedNode(7);
		test.next.next.next.next.next.next.next.next = new LinkedNode(8);

		LinkedNode ans1 = null;
		LinkedNode ans2 = null;

		ans1 = midOrUpMidLinkedNode(test);
		ans2 = right1(test);
		System.out.println(ans1 != null ? ans1.val : "无");
		System.out.println(ans2 != null ? ans2.val : "无");

		ans1 = midOrDownMidLinkedNode(test);
		ans2 = right2(test);
		System.out.println(ans1 != null ? ans1.val : "无");
		System.out.println(ans2 != null ? ans2.val : "无");

		ans1 = midOrUpMidPreLinkedNode(test);
		ans2 = right3(test);
		System.out.println(ans1 != null ? ans1.val : "无");
		System.out.println(ans2 != null ? ans2.val : "无");

		ans1 = midOrDownMidPreLinkedNode(test);
		ans2 = right4(test);
		System.out.println(ans1 != null ? ans1.val : "无");
		System.out.println(ans2 != null ? ans2.val : "无");
	}

	/**
	 * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
	 */
	public static LinkedNode midOrUpMidLinkedNode(LinkedNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return head;
		}
		// 链表有3个点或以上
		LinkedNode slow = head.next;
		LinkedNode fast = head.next.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	/**
	 * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
	 */
	public static LinkedNode midOrDownMidLinkedNode(LinkedNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		LinkedNode slow = head.next;
		LinkedNode fast = head.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	/**
	 * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
	 */
	public static LinkedNode midOrUpMidPreLinkedNode(LinkedNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		LinkedNode slow = head;
		LinkedNode fast = head.next.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	/**
	 * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
	 */
	public static LinkedNode midOrDownMidPreLinkedNode(LinkedNode head) {
		if (head == null || head.next == null) {
			return null;
		}
		if (head.next.next == null) {
			return head;
		}
		LinkedNode slow = head;
		LinkedNode fast = head.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	//-------------------------- 对数器 --------------------------//
	public static LinkedNode right1(LinkedNode head) {
		if (head == null) {
			return null;
		}
		LinkedNode cur = head;
		ArrayList<LinkedNode> arr = new ArrayList<>();
		while (cur != null) {
			arr.add(cur);
			cur = cur.next;
		}
		return arr.get((arr.size() - 1) / 2);
	}

	public static LinkedNode right2(LinkedNode head) {
		if (head == null) {
			return null;
		}
		LinkedNode cur = head;
		ArrayList<LinkedNode> arr = new ArrayList<>();
		while (cur != null) {
			arr.add(cur);
			cur = cur.next;
		}
		return arr.get(arr.size() / 2);
	}

	public static LinkedNode right3(LinkedNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		LinkedNode cur = head;
		ArrayList<LinkedNode> arr = new ArrayList<>();
		while (cur != null) {
			arr.add(cur);
			cur = cur.next;
		}
		return arr.get((arr.size() - 3) / 2);
	}

	public static LinkedNode right4(LinkedNode head) {
		if (head == null || head.next == null) {
			return null;
		}
		LinkedNode cur = head;
		ArrayList<LinkedNode> arr = new ArrayList<>();
		while (cur != null) {
			arr.add(cur);
			cur = cur.next;
		}
		return arr.get((arr.size() - 2) / 2);
	}

}
