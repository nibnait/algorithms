package common.datastruct;

/**
 * 《剑指offer》二叉树结点
 * Created by nibnait on 2016/9/15.
 */
public class BinaryTreeNode {
    public int value;
    public BinaryTreeNode left = null;
    public BinaryTreeNode right = null;

    public BinaryTreeNode() {
    }

    public BinaryTreeNode(int data) {
        this.value = data;
    }

    @Override
    public String toString() {
        return value + "";
    }
}
