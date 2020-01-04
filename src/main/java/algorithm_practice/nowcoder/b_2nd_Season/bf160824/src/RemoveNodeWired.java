package algorithm_practice.nowcoder.b_2nd_Season.bf160824.src;

public class RemoveNodeWired {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	public static void removeNodeWired(Node node) {
		if (node == null) {
			return;
		}
		Node next = node.next;
		if (next == null) {
			throw new RuntimeException("can not remove last node.");
		}
		node.value = next.value;
		node.next = next.next;
	}

	public static void printLinkedList(Node head) {
		System.out.print("Linked List: ");
		while (head != null) {
			System.out.print(head.value + " ");
			head = head.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		Node node = head;
		printLinkedList(head);
		removeNodeWired(node);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		node = head.next;
		printLinkedList(head);
		removeNodeWired(node);
		printLinkedList(head);

//		head = new BinaryTreeNode(1);
//		head.next = new BinaryTreeNode(2);
//		head.next.next = new BinaryTreeNode(3);
//		node = head.next.next;
//		printLinkedList(head);
//		removeNodeWired(node);
//		printLinkedList(head);

	}

}
