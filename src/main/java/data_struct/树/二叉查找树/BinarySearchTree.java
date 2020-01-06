package data_struct.树.二叉查找树;

/**
 *  二叉搜索树
 *  Created by nibnait on 2019-07-25
 */
public interface BinarySearchTree<Key extends Comparable<Key>, Value> {

    /***************************** 基本方法 *******************/
    /**
     * 向集合中添加键值对
     *  若key为空，则return null
     *  若value为空，则代表删除此该key
     *  若key重复，则使用新value替代旧value
     * @param key
     * @param value
     */
    BinarySearchTreeNode put(Key key, Value value);

    /**
     * 查找key所对应的value值
     *  若key不存在，则return null
     * @param key
     * @return
     */
    Value get(Key key);

    /**
     * 删除 某个结点
     * @param key
     */
    void delete(Key key);

    /***************** 最大键和最小键 && 删除最大键和删除最小键 *******************/
    /**
     * 获取集合最小的键
     * @return
     */
    Key min();

    /**
     * 获取集合最大的键
     * @return
     */
    Key max();

    /**
     * 删除最小键
     */
    void deleteMin();

    /**
     * 删除最大键
     */
    void deleteMax();

    /***************************** 向下取整和向上取整 *******************/
    /**
     * 向下取整 小于等于key的最大键
     * @param key
     * @return
     */
    Key floor(Key key);

    /**
     * 向上取整 大于等于key的最小键
     * @param key
     * @return
     */
    Key ceiling(Key key);

    /***************************** 排名和选择 *******************/
    /**
     * (给定键key的)排名
     *  即小于key的键的数量（rank从0开始数）
     * @param key
     * @return
     */
    int rank(Key key);

    /**
     * 选择
     *  选择排名为k的键（排名从0开始数）
     * @param k
     * @return
     */
    Key select(int k);

    /***************************** 范围查找 *******************/
    /**
     * [lo, hi]之间键的数量
     * @param lo
     * @param hi
     * @return
     */
    int size(Key lo, Key hi);

    /**
     * [lo, hi]之间的所有键值（已排序）
     * @param lo
     * @param hi
     * @return
     */
    Iterable<Key> keys(Key lo, Key hi);

    /**
     * 迭代 获取集合所有的键值（已排序）（中序遍历）
     * @return
     */
    Iterable<Key> keys();

    /***************************** 默认方法 *******************/
    /**
     * 集合中结点的数量
     * @return
     */
    Integer size();

    /**
     * 键key在集合中是否存在
     * @param key
     * @return
     */
    boolean contains(Key key);

    /**
     * 集合是否为空
     * @return
     */
    boolean isEmpty();

}