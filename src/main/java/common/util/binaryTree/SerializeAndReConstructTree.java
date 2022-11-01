package common.util.binaryTree;

import common.datastruct.TreeNode;

import java.util.Queue;

/**
 * Created by nibnait on 2022/10/27
 */
public interface SerializeAndReConstructTree {

    Queue<String> preSerial(TreeNode head);

    Queue<String> posSerial(TreeNode head);

    Queue<String> levelSerial(TreeNode head);

    TreeNode buildByPreQueue(Queue<String> pre);

    TreeNode buildByPosQueue(Queue<String> pos);

    TreeNode buildByLevelQueue(Queue<String> level);

}
