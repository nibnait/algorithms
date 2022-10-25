package algorithmzuo.b_体系学习班.c0104_链表;

import common.datastruct.ListNode;
import org.junit.Test;

import java.util.ArrayList;

public class Code03_LinkedListMid {

	@Test
	public void test() {
		ListNode test = null;
		test = new ListNode(0);
		test.next = new ListNode(1);
		test.next.next = new ListNode(2);
		test.next.next.next = new ListNode(3);
		test.next.next.next.next = new ListNode(4);
		test.next.next.next.next.next = new ListNode(5);
		test.next.next.next.next.next.next = new ListNode(6);
		test.next.next.next.next.next.next.next = new ListNode(7);
		test.next.next.next.next.next.next.next.next = new ListNode(8);

		ListNode ans1 = null;
		ListNode ans2 = null;

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
	public static ListNode midOrUpMidLinkedNode(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return head;
		}
		// 链表有3个点或以上
		ListNode slow = head.next;
		ListNode fast = head.next.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	/**
	 * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
	 */
	public static ListNode midOrDownMidLinkedNode(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode slow = head.next;
		ListNode fast = head.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	/**
	 * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
	 */
	public static ListNode midOrUpMidPreLinkedNode(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		ListNode slow = head;
		ListNode fast = head.next.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	/**
	 * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
	 */
	public static ListNode midOrDownMidPreLinkedNode(ListNode head) {
		if (head == null || head.next == null) {
			return null;
		}
		if (head.next.next == null) {
			return head;
		}
		ListNode slow = head;
		ListNode fast = head.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	//-------------------------- 对数器 --------------------------//
	public static ListNode right1(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode cur = head;
		ArrayList<ListNode> arr = new ArrayList<>();
		while (cur != null) {
			arr.add(cur);
			cur = cur.next;
		}
		return arr.get((arr.size() - 1) / 2);
	}

	public static ListNode right2(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode cur = head;
		ArrayList<ListNode> arr = new ArrayList<>();
		while (cur != null) {
			arr.add(cur);
			cur = cur.next;
		}
		return arr.get(arr.size() / 2);
	}

	public static ListNode right3(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		ListNode cur = head;
		ArrayList<ListNode> arr = new ArrayList<>();
		while (cur != null) {
			arr.add(cur);
			cur = cur.next;
		}
		return arr.get((arr.size() - 3) / 2);
	}

	public static ListNode right4(ListNode head) {
		if (head == null || head.next == null) {
			return null;
		}
		ListNode cur = head;
		ArrayList<ListNode> arr = new ArrayList<>();
		while (cur != null) {
			arr.add(cur);
			cur = cur.next;
		}
		return arr.get((arr.size() - 2) / 2);
	}

}
