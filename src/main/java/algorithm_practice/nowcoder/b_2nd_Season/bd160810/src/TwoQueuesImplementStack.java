package algorithm_practice.nowcoder.b_2nd_Season.bd160810.src;

import java.util.LinkedList;

public class TwoQueuesImplementStack {

	public static class Queue<T> {
		private LinkedList<T> queue = new LinkedList<T>();

		public boolean isEmpty() {
			return queue.isEmpty();
		}

		public int size() {
			return queue.size();
		}

		public void add(T value) {
			queue.addFirst(value);
		}

		public T pull() {
			return queue.pollLast();
		}

		public T peek() {
			return queue.peek();
		}

	}

	public static class Stack<T> {
		private Queue<T> dQueue = new Queue<T>();
		private Queue<T> hQueue = new Queue<T>();

		public boolean isEmpty() {
			return dQueue.isEmpty();
		}

		public int size() {
			return dQueue.size();
		}

		public void push(T value) {
			dQueue.add(value);
		}

		public T pop() {
			if (dQueue.isEmpty()) {
				throw new RuntimeException("Are you kidding? your Queue is empty!");
			}
			while (dQueue.size() != 1) {
				hQueue.add(dQueue.pull());
			}
			T res = dQueue.pull();
			while (!hQueue.isEmpty()) {
				dQueue.add(hQueue.pull());
			}
			return res;
		}

		public T peek() {
			if (dQueue.isEmpty()) {
				throw new RuntimeException("Are you kidding? your Queue is empty!");
			}
			while (dQueue.size() != 1) {
				hQueue.add(dQueue.pull());
			}
			T res = dQueue.pull();
			hQueue.add(res);
			while (!hQueue.isEmpty()) {
				dQueue.add(hQueue.pull());
			}
			return res;
		}

	}

	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		stack.push("1");
		stack.push("2");
		stack.push("3");
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		System.out.println(stack.size());
		stack.push("4");
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		System.out.println(stack.size());
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		System.out.println(stack.size());
	}

}
