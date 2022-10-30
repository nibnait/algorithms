package algorithmzuo.b_体系学习班.c0204_树型DP;

import common.CommonConstants;
import common.datastruct.TreeNode;
import common.util.ConstructBinaryTree;
import common.util.SysRandom;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

public class Code01_IsBalanced {

	@Test
	public void loopTestCase() {
		for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
			testCase();
		}
	}

	@Test
	public void testCase() {
		int maxLevel = SysRandom.randomInt(0, 6);
		int maxValue = SysRandom.random(100);

		TreeNode head = ConstructBinaryTree.generateRandomBinaryTree(maxLevel, maxValue);
//		PrintBinaryTree.print(head);
//		PrintBinaryTree.printPreOrder(head);

		boolean result = isBalanced(head);

		Assert.assertEquals(comparator(head), result);
	}

	/**
	 * 判断二叉树是不是平衡二叉树
	 * 即：任何一棵子树的 | 左高度 - 右高度 | <= 1
	 */
	private boolean isBalanced(TreeNode head) {
		if (head == null) {
			return true;
		}

		return process(head).isBalance;
	}

	private Info process(TreeNode head) {
		if (head == null) {
			return new Info(0, true);
		}

		Info left = process(head.left);
		Info right = process(head.right);

		int height = Math.max(left.height, right.height) + 1;
		boolean isBalance = left.isBalance && right.isBalance && Math.abs(left.height - right.height) <= 1;
		return new Info(height, isBalance);
	}

	@AllArgsConstructor
	private class Info {
		public int height;
		public boolean isBalance;
	}

	//-------------------------- 对数器 --------------------------//
	private boolean comparator(TreeNode head) {
		boolean[] ans = new boolean[1];
		ans[0] = true;
		process1(head, ans);
		return ans[0];
	}

	private int process1(TreeNode head, boolean[] ans) {
		if (!ans[0] || head == null) {
			return -1;
		}
		int leftHeight = process1(head.left, ans);
		int rightHeight = process1(head.right, ans);
		if (Math.abs(leftHeight - rightHeight) > 1) {
			ans[0] = false;
		}
		return Math.max(leftHeight, rightHeight) + 1;
	}

}
