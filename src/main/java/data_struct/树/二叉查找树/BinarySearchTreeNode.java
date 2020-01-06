package data_struct.树.二叉查找树;

/**
 *  二叉搜索树结点
 *  Created by nibnait on 2019-07-25
 */
public class BinarySearchTreeNode<Key extends Comparable<Key>, Value> {

    /**
     * 键（用于排序）
     */
    public Key key;

    /**
     * 值（用于存储）
     */
    public Value value;

    /**
     * 左子树
     */
    public BinarySearchTreeNode left;

    /**
     * 右子树
     */
    public BinarySearchTreeNode right;

    /**
     * 以该结点为根的子树中的结点总数
     */
    public Integer N;

    public BinarySearchTreeNode(Key key, Value value) {
        this.key = key;
        this.value = value;
        this.N = 1;
    }

}
