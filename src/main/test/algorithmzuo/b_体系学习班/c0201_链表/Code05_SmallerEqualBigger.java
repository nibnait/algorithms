package algorithmzuo.b_体系学习班.c0201_链表;

import common.datastruct.ListNode;
import org.junit.Test;

public class Code05_SmallerEqualBigger {

	/**
	 * 给定一个单链表的头节点head，给定一个整数n，将链表按n划分成左边<n、中间==n、右边>n
	 */
	@Test
	public void test() {
		ListNode head1 = new ListNode(7);
		head1.next = new ListNode(9);
		head1.next.next = new ListNode(1);
		head1.next.next.next = new ListNode(8);
		head1.next.next.next.next = new ListNode(5);
		head1.next.next.next.next.next = new ListNode(2);
		head1.next.next.next.next.next.next = new ListNode(5);
		printLinkedList(head1);
		// head1 = listPartition1(head1, 4);
		head1 = listPartition2(head1, 5);
		printLinkedList(head1);
	}


	public static ListNode listPartition1(ListNode head, int pivot) {
		if (head == null) {
			return head;
		}
		ListNode cur = head;
		int i = 0;
		while (cur != null) {
			i++;
			cur = cur.next;
		}
		ListNode[] listNodeArr = new ListNode[i];
		i = 0;
		cur = head;
		for (i = 0; i != listNodeArr.length; i++) {
			listNodeArr[i] = cur;
			cur = cur.next;
		}
		arrPartition(listNodeArr, pivot);
		for (i = 1; i != listNodeArr.length; i++) {
			listNodeArr[i - 1].next = listNodeArr[i];
		}
		listNodeArr[i - 1].next = null;
		return listNodeArr[0];
	}

	public static void arrPartition(ListNode[] listNodeArr, int pivot) {
		int small = -1;
		int big = listNodeArr.length;
		int index = 0;
		while (index != big) {
			if (listNodeArr[index].val < pivot) {
				swap(listNodeArr, ++small, index++);
			} else if (listNodeArr[index].val == pivot) {
				index++;
			} else {
				swap(listNodeArr, --big, index);
			}
		}
	}

	public static void swap(ListNode[] listNodeArr, int a, int b) {
		ListNode tmp = listNodeArr[a];
		listNodeArr[a] = listNodeArr[b];
		listNodeArr[b] = tmp;
	}

	public static ListNode listPartition2(ListNode head, int pivot) {
		ListNode sH = null; // small head
		ListNode sT = null; // small tail
		ListNode eH = null; // equal head
		ListNode eT = null; // equal tail
		ListNode mH = null; // big head
		ListNode mT = null; // big tail
		ListNode next = null; // save next LinkedNode
		// every LinkedNode distributed to three lists
		while (head != null) {
			next = head.next;
			head.next = null;
			if (head.val < pivot) {
				if (sH == null) {
					sH = head;
					sT = head;
				} else {
					sT.next = head;
					sT = head;
				}
			} else if (head.val == pivot) {
				if (eH == null) {
					eH = head;
					eT = head;
				} else {
					eT.next = head;
					eT = head;
				}
			} else {
				if (mH == null) {
					mH = head;
					mT = head;
				} else {
					mT.next = head;
					mT = head;
				}
			}
			head = next;
		}
		// 小于区域的尾巴，连等于区域的头，等于区域的尾巴连大于区域的头
		if (sT != null) { // 如果有小于区域
			sT.next = eH;
			eT = eT == null ? sT : eT; // 下一步，谁去连大于区域的头，谁就变成eT
		}
		// 下一步，一定是需要用eT 去接 大于区域的头
		// 有等于区域，eT -> 等于区域的尾结点
		// 无等于区域，eT -> 小于区域的尾结点
		// eT 尽量不为空的尾巴节点
		if (eT != null) { // 如果小于区域和等于区域，不是都没有
			eT.next = mH;
		}
		return sH != null ? sH : (eH != null ? eH : mH);
	}

	public static void printLinkedList(ListNode ListNode) {
		System.out.print("Linked List: ");
		while (ListNode != null) {
			System.out.print(ListNode.val + " ");
			ListNode = ListNode.next;
		}
		System.out.println();
	}

}
