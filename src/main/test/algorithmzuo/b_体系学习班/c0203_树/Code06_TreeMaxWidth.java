package algorithmzuo.b_体系学习班.c0203_树;

import common.CommonConstants;
import common.datastruct.TreeNode;
import common.util.binaryTree.ConstructBinaryTree;
import common.util.binaryTree.PrintBinaryTree;
import common.util.SysOut;
import common.util.SysRandom;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by nibnait on 2022/10/30
 */
public class Code06_TreeMaxWidth {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        int maxLevel = SysRandom.randomInt(1, 10);
        int maxValue = SysRandom.random(100);

        TreeNode head = ConstructBinaryTree.generateRandomBinaryTree(maxLevel, maxValue);
        PrintBinaryTree.print(head);
        SysOut.printSeparator();

        int result = maxWidth(head);

        Assert.assertEquals(comparator(head), result);

    }

    /**
     * 求二叉树的最大宽度
     */
    private int maxWidth(TreeNode head) {
        return 0;
    }

    //-------------------------- 对数器 --------------------------//
    private int comparator(TreeNode head) {
        if (head == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        // key 在 哪一层，value
        HashMap<TreeNode, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1; // 当前你正在统计哪一层的宽度
        int curLevelNodes = 0; // 当前层curLevel层，宽度目前是多少
        int max = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (cur.left != null) {
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }
}
