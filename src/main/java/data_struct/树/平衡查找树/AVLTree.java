package data_struct.树.平衡查找树;

/**
 *  平衡查找树
 *  Created by nibnait on 2019-07-25
 */
public abstract class AVLTree<Key extends Comparable<Key>, Value> {

    /***************************** 基本方法 *******************/
    /**
     * 插入
     * @param key
     * @param value
     * @return
     */
    public abstract AVLNode put(Key key, Value value);

    /**
     * 查找
     * @param key
     * @return
     */
    public abstract Value get(Key key);

    /**
     * 删除
     * @param key
     */
    public abstract void delete(Key key);

    /***************************** 其他方法 *******************/
    public abstract void deleteMin();
    public abstract void deleteMax();

    public abstract AVLNode floor(Key key);
    public abstract AVLNode ceiling(Key key);

    public abstract int rank(Key key);
    public abstract Key select(Key key);

    public abstract Iterable<Key> keys();

    /***************************** 基本操作 *******************/
    /**
     * 左旋
     * @param h
     * @return
     */
    private AVLNode rotateLeft(AVLNode h) {

        return null;
    }

    /**
     * 右旋
     * @param h
     * @return
     */
    private AVLNode rotateRight(AVLNode h) {

        return null;
    }
}
