package cc.tianbin.data_struct.ds1_堆;

import java.util.List;

/**
 * Created by nibnait on 2022/10/24
 */
public interface MyHeap<T> {

    boolean isEmpty();

    boolean isFull();

    default boolean contains(T obj) {
        throw new UnsupportedOperationException("不支持 contains 功能");
    }

    void push(T obj);

    T peek();

    T pop();

    default void remove(T obj) {
        throw new UnsupportedOperationException("不支持 remove 功能");
    }

    default void resign(T obj) {
        throw new UnsupportedOperationException("不支持 resign 功能");
    }

    List<T> getAllElements();
}
