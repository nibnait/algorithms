package cc.tianbin.data_struct.ds3_并查集;

/**
 * Created by nibnait on 2022/11/02
 */
public interface MyUnionFind<T> {

    /**
     * 找到 当前节点 的代表节点
     */
    T findAncestor(T cur);

    /**
     * 是否在同一个集合内
     */
    boolean isSameSet(T a, T b);

    /**
     * 把 a 节点，并入 b 节点所在集合中
     */
    void union(T a, T b);

    /**
     * 当前一共有多少个集合
     */
    int size();
}
