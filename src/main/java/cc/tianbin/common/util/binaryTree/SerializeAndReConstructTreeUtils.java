package cc.tianbin.common.util.binaryTree;

import cc.tianbin.common.datastruct.TreeNode;
import cc.tianbin.common.util.SysOut;
import org.apache.commons.collections4.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by nibnait on 2022/11/02
 */
public class SerializeAndReConstructTreeUtils {

    /**
     * 序列化
     */
    public static void serialByPreOrder(TreeNode head) {
        printTravelArr(new SerializeAndReConstructTreeImpl().preSerial(head));
    }
    public static void serialByPosOrder(TreeNode head) {
        printTravelArr(new SerializeAndReConstructTreeImpl().posSerial(head));
    }
    public static void serialByLevelOrder(TreeNode head) {
        printTravelArr(new SerializeAndReConstructTreeImpl().levelSerial(head));
    }

    /**
     * 反序列化
     */
    public static TreeNode buildByPreOrder(List<String> pre) {
        return new SerializeAndReConstructTreeImpl().buildByPreQueue(convert2LinkedList(pre));
    }
    public static TreeNode buildByPosOrder(List<String> pos) {
        return new SerializeAndReConstructTreeImpl().buildByPosQueue(convert2LinkedList(pos));
    }
    public static TreeNode buildByLevelOrder(List<String> level) {
        return new SerializeAndReConstructTreeImpl().buildByLevelQueue(convert2LinkedList(level));
    }

    private static LinkedList<String> convert2LinkedList(List<String> pre) {
        LinkedList<String> queue = new LinkedList<>();
        if (CollectionUtils.isEmpty(pre)) {
            return queue;
        }
        for (String s : pre) {
            queue.add(s);
        }
        return queue;
    }

    private static void printTravelArr(Queue<String> travelArr) {
        if (travelArr == null) {
            return;
        }
        while (!travelArr.isEmpty()) {
            SysOut.print("\"" + travelArr.poll() + "\", ");
        }
    }
}
