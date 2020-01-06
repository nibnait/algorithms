package data_struct.树.平衡查找树;

/**
 *  红黑树结点
 *  Created by nibnait on 2019-07-25
 */
public class RedBlackNode<Key extends Comparable<Key>, Value> {

    public static final boolean RED = true;
    public static final boolean BLACK = false;

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
    public RedBlackNode left;

    /**
     * 右子树
     */
    public RedBlackNode right;

    /**
     * 以该结点为根的子树中的结点总数
     */
    public Integer N;

    /**
     * 指向该结点的链接的颜色
     */
    public boolean color;

    public RedBlackNode(Key key, Value value, boolean color) {
        this.key = key;
        this.value = value;
        this.N = 1;
        this.color = color;
    }

}
