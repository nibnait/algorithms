package cc.tianbin.data_struct;

/**
 * Created by nibnait on 2022/11/02
 */
public class Node<T> {
    public T value;

    public Node(T v) {
        value = v;
    }

    public static <T> Node v(T v) {
        return new Node(v);
    }
}
