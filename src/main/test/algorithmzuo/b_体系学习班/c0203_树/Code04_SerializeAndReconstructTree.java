package algorithmzuo.b_体系学习班.c0203_树;

import common.datastruct.TreeNode;

import java.util.Queue;

/**
 * Created by nibnait on 2022/10/27
 */
public interface Code04_SerializeAndReconstructTree {

    Queue<String> preSerial(TreeNode head);

    Queue<String> posSerial(TreeNode head);

    Queue<String> levelSerial(TreeNode head);

    TreeNode buildByPreQueue(Queue<String> pre);

    TreeNode buildByPosQueue(Queue<String> pos);

    TreeNode buildByLevelQueue(Queue<String> level);

}
