package common.util.binaryTree;

import common.CommonConstants;
import common.datastruct.TreeNode;
import common.util.CompareUtils;
import common.util.SysOut;
import common.util.SysRandom;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by nibnait on 2022/10/27
 */
@Slf4j
public class SerializeAndReConstructTreeImpl implements SerializeAndReConstructTree {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        int maxLevel = SysRandom.randomInt(1, 6);
        int maxValue = SysRandom.random(100);

        TreeNode head = ConstructBinaryTree.generateRandomBinaryTree(maxLevel, maxValue);
        PrintBinaryTree.print(head);
        SysOut.printSeparator();

        Queue<String> pre = preSerial(head);
        Queue<String> pos = posSerial(head);
        Queue<String> level = levelSerial(head);
        TreeNode preBuild = buildByPreQueue(pre);
        TreeNode posBuild = buildByPosQueue(pos);
        TreeNode levelBuild = buildByLevelQueue(level);

        Assert.assertTrue(CompareUtils.isSameValueStructure(head, preBuild));
        Assert.assertTrue(CompareUtils.isSameValueStructure(preBuild, posBuild));
        Assert.assertTrue(CompareUtils.isSameValueStructure(posBuild, levelBuild));
    }

    private static final String NULL_HEAD = "#";

    @Override
    public Queue<String> preSerial(TreeNode head) {
        Queue<String> travelArr = new LinkedList<>();
        if (head == null) {
            return travelArr;
        }

        processPreSerial(head, travelArr);
        return travelArr;
    }

    private void processPreSerial(TreeNode head, Queue<String> travelArr) {
        if (head == null) {
            travelArr.add(NULL_HEAD);
        } else {
            travelArr.add(String.valueOf(head.val));
            processPreSerial(head.left, travelArr);
            processPreSerial(head.right, travelArr);
        }
    }

    @Override
    public Queue<String> posSerial(TreeNode head) {
        Queue<String> travelArr = new LinkedList<>();
        if (head == null) {
            return travelArr;
        }
        processPosSerial(head, travelArr);
        return travelArr;
    }

    private void processPosSerial(TreeNode head, Queue<String> travelArr) {
        if (head == null) {
            travelArr.add(NULL_HEAD);
        } else {
            processPosSerial(head.left, travelArr);
            processPosSerial(head.right, travelArr);
            travelArr.add(String.valueOf(head.val));
        }
    }

    @Override
    public Queue<String> levelSerial(TreeNode head) {
        Queue<String> travelArr = new LinkedList<>();
        if (head == null) {
            travelArr.add(NULL_HEAD);
        } else {
            travelArr.add(String.valueOf(head.val));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()) {
                head = queue.poll();
                if (head.left != null) {
                    queue.add(head.left);
                    travelArr.add(String.valueOf(head.left.val));
                } else {
                    travelArr.add(NULL_HEAD);
                }

                if (head.right != null) {
                    queue.add(head.right);
                    travelArr.add(String.valueOf(head.right.val));
                } else {
                    travelArr.add(NULL_HEAD);
                }
            }
        }
        return travelArr;
    }

    @Override
    public TreeNode buildByPreQueue(Queue<String> pre) {
        if (pre == null || pre.isEmpty()) {
            return null;
        }
        return processBuildByPreQueue(pre);
    }

    private TreeNode processBuildByPreQueue(Queue<String> pre) {
        String headVal = pre.poll();
        if (NULL_HEAD.equals(headVal)) {
            return null;
        }
        TreeNode head = new TreeNode(headVal);
        head.left = processBuildByPreQueue(pre);
        head.right = processBuildByPreQueue(pre);
        return head;
    }

    @Override
    public TreeNode buildByPosQueue(Queue<String> pos) {
        if (pos == null || pos.isEmpty()) {
            return null;
        }

        // 左右中 -> 中右左
        Stack<String> stack = new Stack<String>();
        while (!pos.isEmpty()) {
            stack.push(pos.poll());
        }
        return processBuildByPosQueue(stack);
    }

    private TreeNode processBuildByPosQueue(Stack<String> stack) {
        String headVal = stack.pop();
        TreeNode head = new TreeNode(headVal);
        head.right = processBuildByPosQueue(stack);
        head.left = processBuildByPosQueue(stack);
        return head;
    }

    @Override
    public TreeNode buildByLevelQueue(Queue<String> level) {
        if (level == null || level.isEmpty()) {
            return null;
        }
        TreeNode head = generateNode(level.poll());
        Queue<TreeNode> queue = new LinkedList<>();
        if (head != null) {
            queue.add(head);
        }
        while (!queue.isEmpty()) {
            TreeNode curHead = queue.poll();
            curHead.left = generateNode(level.poll());
            curHead.right = generateNode(level.poll());
            if (curHead.left != null) {
                queue.add(curHead.left);
            }
            if (curHead.right != null) {
                queue.add(curHead.right);
            }
        }
        return head;
    }

    private TreeNode generateNode(String headVal) {
        if (headVal == null || NULL_HEAD.equals(headVal)) {
            return null;
        }
        return new TreeNode(headVal);
    }

}
