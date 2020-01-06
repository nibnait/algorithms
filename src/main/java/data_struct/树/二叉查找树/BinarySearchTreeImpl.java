package data_struct.树.二叉查找树;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  二叉搜索树实现
 *  Created by nibnait on 2019-07-25
 */
public class BinarySearchTreeImpl<Key extends Comparable<Key>, Value> implements BinarySearchTree {
    private BinarySearchTreeNode root;

    public BinarySearchTreeImpl() {
        this.root = null;
    }

    @Override
    public BinarySearchTreeNode put(Comparable key, Object value) {
        if (key == null) {
            return null;
        }
        if (value == null) {
            delete(key);
        }
        if (root == null) {
            root = new BinarySearchTreeNode(key, value);
            return root;
        }
        return put(root, key, value);
    }

    private BinarySearchTreeNode put(BinarySearchTreeNode root, Comparable key, Object value) {
        if (root == null) {
            return new BinarySearchTreeNode(key, value);
        }
        int compareTo = root.key.compareTo(key);
        if (compareTo == 0) {
            root.value = value;
        } else if (compareTo > 0) {
            root.left = put(root.left, key, value);
        } else {
            root.right = put(root.right, key, value);
        }
        root.N = size(root) + 1;
        return root;
    }

    @Override
    public Object get(Comparable key) {
        if (key == null) {
            return null;
        }
        return get(root, key);
    }


    private Object get(BinarySearchTreeNode root, Comparable key) {
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
        if (key == null) {
            return;
        }
        root = delete(root, key);
    }


    private BinarySearchTreeNode delete(BinarySearchTreeNode root, Comparable key) {
        if (root == null) {
            return root;
        }
        int compareTo = root.key.compareTo(key);
        if (compareTo == 0) {
            if (root.right == null) {
                return root.left;
            }
            if (root.left == null) {
                return root.right;
            }
            BinarySearchTreeNode tempHead = root;
            root = minNode(tempHead.right);
            root.right = deleteMin(tempHead.right);
            root.left = tempHead.left;
        } else if (compareTo > 0) {
            delete(root.left, key);
        } else {
            delete(root.right, key);
        }
        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    private BinarySearchTreeNode minNode(BinarySearchTreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            return minNode(root.left);
        }
        return root;
    }

    @Override
    public Comparable min() {
        return min(root);
    }

    private Comparable min(BinarySearchTreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            return min(root.left);
        }
        return root.key;
    }

    @Override
    public Comparable max() {
        return max(root);
    }

    private Comparable max(BinarySearchTreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.right != null) {
            return max(root.right);
        }
        return root.key;
    }

    @Override
    public void deleteMin() {
        root = deleteMin(root);
    }

    private BinarySearchTreeNode deleteMin(BinarySearchTreeNode root) {
        if (root == null) {
            return root;
        }
        if (root.left == null) {
            return root.right;
        }
        root.left = deleteMin(root.left);
        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    @Override
    public void deleteMax() {
        root = deleteMax(root);
    }

    private BinarySearchTreeNode deleteMax(BinarySearchTreeNode root) {
        if (root == null) {
            return root;
        }
        if (root.right == null) {
            return root.left;
        }
        root.right = deleteMax(root.right);
        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    @Override
    public Comparable floor(Comparable key) {
        if (key == null) {
            return null;
        }
        BinarySearchTreeNode floor = floor(root, key);
        return floor != null ? floor.key : null;
    }

    /**
     * 小于等于key的最大的那个KeyNode
     *
     * @param root
     * @param key
     * @return
     */
    private BinarySearchTreeNode floor(BinarySearchTreeNode root, Comparable key) {
        if (root == null) {
            return null;
        }
        int compareTo = root.key.compareTo(key);
        if (compareTo == 0) {
            if (root.left != null) {
                return root.left;
            }
        }

        if (compareTo > 0) {
            if (root.left == null) {
//                throw new RuntimeException(String.format("key：%s, 此树最小值为%s, 无法向下取整", key, String.valueOf(root.val)));
            }
            return floor(root.left, key);
        }

        if (compareTo < 0) {
            BinarySearchTreeNode nextFloor = floor(root.right, key);
            if (nextFloor != null && nextFloor.key.compareTo(key) < 0) {
                return nextFloor;
            }
        }
        return root;
    }

    @Override
    public Comparable ceiling(Comparable key) {
        if (key == null || root == null) {
            return null;
        }
        BinarySearchTreeNode ceiling = ceiling(root, key);
        return ceiling != null ? ceiling.key : null;
    }

    /**
     * 大于等于key的最小的那个KeyNode
     *
     * @param root
     * @param key
     * @return
     */
    private BinarySearchTreeNode ceiling(BinarySearchTreeNode root, Comparable key) {
        if (root == null) {
            return null;
        }
        int compareTo = root.key.compareTo(key);
        if (compareTo == 0) {
            if (root.right != null) {
                return root.right;
            }
        }

        if (compareTo > 0) {
            BinarySearchTreeNode nextCeiling = ceiling(root.left, key);
            if (nextCeiling != null && nextCeiling.key.compareTo(key) > 0) {
                return nextCeiling;
            }
        }

        if (compareTo < 0) {
            if (root.right == null) {
//                throw new RuntimeException(String.format("key：%s, 此树最大值为%s, 无法向上取整", key, String.valueOf(root.val)));
            }
            return ceiling(root.right, key);
        }
        return root;
    }

    @Override
    public int rank(Comparable key) {
        if (key == null || root == null) {
            return -1;
        }
        return rank(root, key);
    }

    private int rank(BinarySearchTreeNode root, Comparable key) {
        if (root == null) {
            return 0;
        }
        int compareTo = root.key.compareTo(key);
        if (compareTo == 0) {
            return size(root.left) + 1;
        } else if (compareTo > 0) {
            return rank(root.left, key);
        } else {
            return size(root.left) + 1 + rank(root.right, key);
        }
    }

    @Override
    public Comparable select(int k) {
        if (k < 0 || k >= size(root)) {
            return null;
        }
        return select(root, k).key;
    }

    private BinarySearchTreeNode select(BinarySearchTreeNode root, int k) {
        if (root == null) {
            return null;
        }
        Integer rank = size(root.left);
        int compareTo = rank.compareTo(k);
        if (compareTo == 0) {
            return root;
        } else if (compareTo > 0) {
            return select(root.left, k);
        } else {
            return select(root.right, k - rank - 1);
        }
    }

    @Override
    public int size(Comparable lo, Comparable hi) {
        return 0;
    }

    @Override
    public Iterable<Comparable> keys(Comparable lo, Comparable hi) {
        Queue<Comparable> queue = new LinkedList<Comparable>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(BinarySearchTreeNode root, Queue<Comparable> queue, Comparable lo, Comparable hi) {
        if (root == null) {
            return;
        }
        int cmplo = lo.compareTo(root.key);
        int cmphi = hi.compareTo(root.key);
        if (cmplo < 0) {
            keys(root.left, queue, lo, hi);
        }
        if (cmplo <= 0 && cmphi >= 0) {
            queue.add(root.key);
        }
        if (cmphi > 0) {
            keys(root.right, queue, lo, hi);
        }
    }

    @Override
    public Iterable<Comparable> keys() {
        return keys(min(), max());
    }

    @Override
    public Integer size() {
        return size(root);
    }

    private Integer size(BinarySearchTreeNode root) {
        if (root == null) {
            return 0;
        }
        return root.N;
    }

    @Override
    public boolean contains(Comparable key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return root == null ? true : root.N < 1;
    }
}