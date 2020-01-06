package algorithm_practice.LeetCode.code200;

import common.datastruct.TreeNode;
import common.util.ConstructBinaryTree;
import common.util.PrintBinaryTree;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/*
序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。

请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

示例: 

你可以将以下二叉树：

    1
   / \
  2   3
     / \
    4   5

序列化为 "[1,2,3,null,null,4,5]"
提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。

说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by nibnait on 2019-08-02
 */
public class H297_二叉树的序列化与反序列化 extends TestCase {

    @Test
    public void testCase() {
        Integer[] BFSArray = {1, 2, 3, null, null, 4, 5};
        TreeNode treeNode = ConstructBinaryTree.constructByBFSArray(BFSArray);
        String serialize = serialize(treeNode);
        System.out.println(serialize);
        PrintBinaryTree.print(deserialize(serialize));
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            return sb.toString();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Queue<TreeNode> newQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                TreeNode poll = queue.poll();
                if (poll != null) {
                    if (poll.val != null) {
                        sb.append(poll.val).append(",");
                    }
                    newQueue.add(poll.left);
                    newQueue.add(poll.right);
                } else {
                    sb.append("null,");
                    sb.append("null,");
                }
            }
            queue = newQueue;
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] bfsArray = data.split(",");
        if (bfsArray == null || bfsArray.length == 0) {
            return null;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        int count = 0;
        TreeNode head = new TreeNode(Integer.valueOf(bfsArray[count++]));
        queue.add(head);
        while (!queue.isEmpty() && count < bfsArray.length) {
            TreeNode pop = queue.pop();
            Integer leftValue = bfsArray[count++].equalsIgnoreCase("null") ? null : Integer.valueOf(bfsArray[count]);
            pop.left = new TreeNode(leftValue);
            queue.add(pop.left);
            Integer rightValue = bfsArray[count++].equalsIgnoreCase("null") ? null : Integer.valueOf(bfsArray[count]);
            pop.right = new TreeNode(rightValue);
            queue.add(pop.right);
        }
        return head;
    }
}
