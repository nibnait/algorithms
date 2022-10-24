package algorithmzuo.b_体系学习班.c0104_链表;

import common.datastruct.LinkedNode;
import org.junit.Test;

public class Code05_SmallerEqualBigger {

	/**
	 * 给定一个单链表的头节点head，给定一个整数n，将链表按n划分成左边<n、中间==n、右边>n
	 */
	@Test
	public void test() {
		LinkedNode head1 = new LinkedNode(7);
		head1.next = new LinkedNode(9);
		head1.next.next = new LinkedNode(1);
		head1.next.next.next = new LinkedNode(8);
		head1.next.next.next.next = new LinkedNode(5);
		head1.next.next.next.next.next = new LinkedNode(2);
		head1.next.next.next.next.next.next = new LinkedNode(5);
		printLinkedList(head1);
		// head1 = listPartition1(head1, 4);
		head1 = listPartition2(head1, 5);
		printLinkedList(head1);
	}


	public static LinkedNode listPartition1(LinkedNode head, int pivot) {
		if (head == null) {
			return head;
		}
		LinkedNode cur = head;
		int i = 0;
		while (cur != null) {
			i++;
			cur = cur.next;
		}
		LinkedNode[] LinkedNodeArr = new LinkedNode[i];
		i = 0;
		cur = head;
		for (i = 0; i != LinkedNodeArr.length; i++) {
			LinkedNodeArr[i] = cur;
			cur = cur.next;
		}
		arrPartition(LinkedNodeArr, pivot);
		for (i = 1; i != LinkedNodeArr.length; i++) {
			LinkedNodeArr[i - 1].next = LinkedNodeArr[i];
		}
		LinkedNodeArr[i - 1].next = null;
		return LinkedNodeArr[0];
	}

	public static void arrPartition(LinkedNode[] LinkedNodeArr, int pivot) {
		int small = -1;
		int big = LinkedNodeArr.length;
		int index = 0;
		while (index != big) {
			if (LinkedNodeArr[index].val < pivot) {
				swap(LinkedNodeArr, ++small, index++);
			} else if (LinkedNodeArr[index].val == pivot) {
				index++;
			} else {
				swap(LinkedNodeArr, --big, index);
			}
		}
	}

	public static void swap(LinkedNode[] LinkedNodeArr, int a, int b) {
		LinkedNode tmp = LinkedNodeArr[a];
		LinkedNodeArr[a] = LinkedNodeArr[b];
		LinkedNodeArr[b] = tmp;
	}

	public static LinkedNode listPartition2(LinkedNode head, int pivot) {
		LinkedNode sH = null; // small head
		LinkedNode sT = null; // small tail
		LinkedNode eH = null; // equal head
		LinkedNode eT = null; // equal tail
		LinkedNode mH = null; // big head
		LinkedNode mT = null; // big tail
		LinkedNode next = null; // save next LinkedNode
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

	public static void printLinkedList(LinkedNode LinkedNode) {
		System.out.print("Linked List: ");
		while (LinkedNode != null) {
			System.out.print(LinkedNode.val + " ");
			LinkedNode = LinkedNode.next;
		}
		System.out.println();
	}

}
