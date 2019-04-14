package Algorithm.Algorithms_4thEdition.b_Searching;

/**
 * 二叉查找树
 * <p>
 * Created by nibnait on 2016/9/2.
 */
public class BST<Key extends Comparable<Key>, Value> {

    private Node root;

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }
    }

    public Value get(Key key) {
        //在以x为根结点的子树中查找 key对应的Value值
        //如果没有，则返回null
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }

        int cmp = x.key.compareTo(key);
        if (cmp > 0) {
            return get(x.left, key);
        } else if (cmp < 0) {
            return get(x.right, key);
        } else {
            return (Value) x.value;
        }
    }

    public void put(Key key, Value value) {
        //查找key， 存在在更新此key对应的value，否则为其创建新结点
        this.root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value, 1);
        }

        int cmp = x.key.compareTo(key);
        if (cmp > 0) {
            x.left = put(x.left, key, value);
        } else if (cmp < 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min() {
        return (Key) min(root).key;
    }

    private Node min(Node x) {

        if (x == null) {
            return x;
        }
        return x.left;
    }

    public Key max() {
        return (Key) max(root).key;
    }

    private Node max(Node x) {

        if (x == null) {
            return x;
        }
        return x.right;
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) {
            return null;
        }
        return (Key) x.key;
    }

    private Node floor(Node x, Key key) {

        if (x == null) {
            return null;
        }
        int cmp = x.key.compareTo(key);
        //如果x的key大于 要查找的key
        //则floor(x, key)一定在x的左子树中。
        if (cmp == 0) {
            return x;
        }
        if (cmp > 0) {
            return floor(x.left, key);
        }
        //如果x的key小于 要查找的key
        //那么 只有当x的右子树中存在小于等于给定key的结点时， floor(x, key)才会出现在x的右子树中
        //否则x就是floor(x, key) 即小于等于key的最大键。
        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    //向上取整
    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) {
            return null;
        }
        return (Key) x.key;
    }

    private Node ceiling(Node x, Key key) {

        if (x == null) {
            return null;
        }
        int cmp = x.key.compareTo(key);
        if (cmp == 0) {
            return x;
        }
        //向上取整：如果x的key比 要查找的key小， 则ceiling(x, key) 一定在x的右子树中
        if (cmp < 0) {
            return ceiling(x.right, key);
        }
        Node t = ceiling(x.left, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    //查找排名为k的键
    private Key select(int k) {
        return (Key) select(root, k).key;
    }

    private Node select(Node x, int k) {
        if (x == null) {
            return null;
        }
        int t = size(x.left);
        if (t > k) {
            return select(x.left, k);
        } else if (t < k) {
            return select(x.right, k - t - 1);
        } else {
            return x;
        }
    }

    //求给定key的排名
    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node x, Key key) {

        if (x == null){
            return 0;
        }
        int cmp = x.key.compareTo(key);
        if (cmp > 0){
            return rank(x.left, key);
        }else if (cmp < 0){
            return rank(x.right, key) + size(x.left) + 1;
        }else {
            return size(x.left);
        }
    }

    public void deleteMin(){
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {

        if (x.left == null){
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax(){
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null){
            return x.left;
        }
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key){
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {

        if (x == null){
            return  null;
        }
        int cmp = x.key.compareTo(key);
        if (cmp > 0){
            x.left = delete(x.left, key);
        }else if (cmp < 0){
            x.right = delete(x.right, key);
        }else { //  找到了
            if (x.right == null){
                return x.left;
            }
            if (x.left == null){
                return x.right;
            }
            Node t = x;     // t 即为待删除的结点
            x = min(t.right);   //x可以说是t向上取整（第一个比t大的结点）
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;     //以x为根结点的结点总数
        return x;
    }

}
