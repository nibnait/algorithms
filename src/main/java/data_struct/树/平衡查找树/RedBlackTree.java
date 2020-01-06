package data_struct.树.平衡查找树;

/**
 * 红黑树
 * 1. 红链接均为左链接
 * 2. 没有任何一个结点同时和两条红链接相连
 * 3. 该树是完美黑色平衡的，即任意空链接到根结点的路径上的黑色链接数量相同
 * <p>
 * Created by nibnait on 2019-07-26
 */
public abstract class RedBlackTree<Key extends Comparable<Key>, Value> {

    public RedBlackNode root;

    /***************************** 基本方法 *******************/
    /**
     * 插入
     *  若key为空，则return null
     *  若value为空，则代表删除此该key
     *  若key重复，则使用新value替代旧value
     * @param key
     * @param value
     * @return
     */
    public abstract RedBlackNode put(Key key, Value value);

    /**
     * 查找
     *  若key不存在，则return null
     * @param key
     * @return
     */
    public abstract Value get(Key key);

    /**
     * 删除
     * @param key
     */
    public abstract void delete(Key key);

    /**
     * 集合中结点的数量
     * @return
     */
    public int size() {
        return size(root);
    }

    /**
     * 以root结点为根的子树中的结点总数
     * @param root
     * @return
     */
    public int size(RedBlackNode root) {
        if (root == null) {
            return 0;
        }
        return root.N;
    }

    /***************************** 其他方法 *******************/
    public abstract void deleteMin();

    public abstract void deleteMax();

    public abstract RedBlackNode floor(Key key);

    public abstract RedBlackNode ceiling(Key key);

    public abstract int rank(Key key);

    public abstract Key select(Key key);

    public abstract Iterable<Key> keys();

    /***************************** 基本插入操作 *******************/
    /**
     * 指向该结点的链接的颜色
     *
     * @param h
     * @return
     */
    public boolean isRed(RedBlackNode h) {
        if (h == null) {
            return false;
        }
        return h.color == RedBlackNode.RED;
    }

    /*
      左旋（当root.right 是红结点时，需要左旋操作，将红链接变成左链接）
         h                        x
       /  \\                    // \
      L    x         =>        h    R
          / \                 / \
         L   R               L   R

      @param h 要旋转的结点
      @return x = h.right（将原h子树中key较大的那个结点x作为根结点返回）
     */
    public RedBlackNode rotateLeft(RedBlackNode h) {
        RedBlackNode x = h.right;
        h.right = x.left;
        x.left = h;

        x.color = h.color;
        h.color = RedBlackNode.RED;

        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    /*
      右旋
           h                      x
         //  \                   / \\
        x    R         =>       L   h
       / \                         / \
      L   R                       L   R

      @param h
      @return
     */
    public RedBlackNode rotateRight(RedBlackNode h) {
        RedBlackNode x = h.left;
        h.left = x.right;
        x.right = h;

        x.color = h.color;
        h.color = RedBlackNode.RED;

        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    /**
     * 颜色转换
     * 转换一个结点的两个红结点的颜色
     * @param h
     */
    public void flipColors(RedBlackNode h) {
        h.color = RedBlackNode.RED;
        h.left.color = RedBlackNode.BLACK;
        h.right.color = RedBlackNode.BLACK;
    }

    /***************************** 删除操作 *******************/

}
