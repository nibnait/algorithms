package algorithm_practice.nowcoder.b_2nd_Season.bj160928;

import common.datastruct.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断一棵二叉树是否为 完全二叉树 
 * Created by nibnait on 2016/10/9.
 */
public class IsCompleteBinaryTree {

    public static boolean isCBT(BinaryTreeNode head) {
        if (head == null) {
            return true;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();	//按层遍历
        boolean leaf = false;
        BinaryTreeNode l = null;
        BinaryTreeNode r = null;
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if (   (leaf && (l != null || r != null))    ||    (l == null && r != null)   ) {
                //当前是叶结点的阶段 && head居然还有孩子			//不管哪个阶段，此结点只要有右无左
                return false;
            }
            if (l != null) {
                queue.offer(l);
            }
            if (r != null) {
                queue.offer(r);
            } else {	//有左无右 或 无左无右
                leaf = true;
            }
        }
        return true;
    }
    
}
