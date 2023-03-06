package cc.tianbin.algorithmzuo.b_体系学习班.c0201_链表;

import java.util.HashMap;

public class Code06_CopyListWithRandom {

	public static class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}

	/**
	 * https://leetcode.cn/problems/copy-list-with-random-pointer/
	 * 一种特殊的单链表节点类描述如下
	 * class Node {
	 * int value;
	 * Node next;
	 * Node rand;
	 * Node(int val) { value = val; }
	 * }
	 * rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null
	 * 给定一个由Node节点类型组成的无环单链表的头节点head，请实现一个函数完成这个链表的复制
	 * 返回复制的新链表的头节点，要求时间复杂度O(N)，额外空间复杂度O(1)
	 */
	public static Node copyRandomList1(Node head) {
		// key 老节点
		// value 新节点
		HashMap<Node, Node> map = new HashMap<Node, Node>();
		Node cur = head;
		while (cur != null) {
			map.put(cur, new Node(cur.val));
			cur = cur.next;
		}
		cur = head;
		while (cur != null) {
			// cur 老
			// map.get(cur) 新
			// 新.next ->  cur.next克隆节点找到
			map.get(cur).next = map.get(cur.next);
			map.get(cur).random = map.get(cur.random);
			cur = cur.next;
		}
		return map.get(head);
	}

	public static Node copyRandomList2(Node head) {
		if (head == null) {
			return null;
		}
		Node cur = head;
		Node next = null;
		// 1 -> 2 -> 3 -> null
		// 1 -> 1' -> 2 -> 2' -> 3 -> 3'
		while (cur != null) {
			next = cur.next;
			cur.next = new Node(cur.val);
			cur.next.next = next;
			cur = next;
		}
		cur = head;
		Node copy = null;
		// 1 1' 2 2' 3 3'
		// 依次设置 1' 2' 3' random指针
		while (cur != null) {
			next = cur.next.next;
			copy = cur.next;
			copy.random = cur.random != null ? cur.random.next : null;
			cur = next;
		}
		Node res = head.next;
		cur = head;
		// 老 新 混在一起，next方向上，random正确
		// next方向上，把新老链表分离
		while (cur != null) {
			next = cur.next.next;
			copy = cur.next;
			cur.next = next;
			copy.next = next != null ? next.next : null;
			cur = next;
		}
		return res;
	}

}
