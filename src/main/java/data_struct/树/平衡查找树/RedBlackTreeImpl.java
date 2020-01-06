package data_struct.树.平衡查找树;

/**
 * 红黑树实现
 * Created by nibnait on 2019-07-25
 */
public class RedBlackTreeImpl extends RedBlackTree {
    public RedBlackTreeImpl() {
        root = null;
    }

    /***************************** 基本方法 *******************/
    @Override
    public RedBlackNode put(Comparable key, Object value) {
        if (key == null) {
            return null;
        }
        if (value == null) {
            delete(key);
        }
        if (root == null) {
            root = new RedBlackNode(key, value, RedBlackNode.BLACK);
            return root;
        }
        root = put(root, key, value);
        root.color = RedBlackNode.BLACK;
        return root;
    }

    private RedBlackNode put(RedBlackNode root, Comparable key, Object value) {
        if (root == null) {
            //新结点 和父结点用红链接相连
            return new RedBlackNode(key, value, RedBlackNode.RED);
        }

        int compareTo = root.key.compareTo(key);
        if (compareTo == 0) {
            root.value = value;
        } else if (compareTo > 0) {
            root.left = put(root.left, key, value);
        } else {
            root.right = put(root.right, key, value);
        }

        //开始调整
        if (isRed(root.right) && !isRed(root.left)) {
            root = rotateLeft(root);
        }
        if (isRed(root.left) && isRed(root.left.left)) {
            root = rotateRight(root);
        }
        if (isRed(root.left) && isRed(root.right)) {
            flipColors(root);
        }

        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    @Override
    public Object get(Comparable key) {
        if (key == null) {
            return null;
        }
        return get(root, key);
    }

    private Object get(RedBlackNode root, Comparable key) {
        if (root == null || root.key == null) {
            return null;
        }
        int compareTo = root.key.compareTo(key);
        if (compareTo == 0) {
            return root.value;
        } else if (compareTo > 0) {
            return get(root.left, key);
        } else {
            return get(root.right, key);
        }
    }

    @Override
    public void delete(Comparable key) {

    }

    /***************************** 其他方法 *******************/
    @Override
    public void deleteMin() {

    }

    @Override
    public void deleteMax() {

    }

    @Override
    public RedBlackNode floor(Comparable comparable) {
        return null;
    }

    @Override
    public RedBlackNode ceiling(Comparable comparable) {
        return null;
    }

    @Override
    public int rank(Comparable comparable) {
        return 0;
    }

    @Override
    public Comparable select(Comparable comparable) {
        return null;
    }

    @Override
    public Iterable keys() {
        return null;
    }
}
