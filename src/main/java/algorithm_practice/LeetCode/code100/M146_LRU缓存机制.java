package algorithm_practice.LeetCode.code100;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;

/*
运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。

获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
写入数据 put(key, val) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。

进阶:
你是否可以在 O(1) 时间复杂度内完成这两种操作？

示例:

        LRUCache cache = new LRUCache( 2 ); // 缓存容量

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/lru-cache
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M146_LRU缓存机制 extends TestCase {

    /*
      双向链表 + HashMap<Node.key, Node>
      <p>
      get操作：
        map中有此key ? 将此key，moveToHead, return Node.val : return -1;
      put操作：
        新key：达到了capacity ? popTail : ; addToHead
        旧key：更新Node.val，moveToHead
     */

    class LRUCache {
        HashMap<Integer, DoubleLinkedNode> cache = new HashMap<>();
        DoubleLinkedNode head, tail;
        int capacity;
        int NOT_EXIST = -1;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            head = new DoubleLinkedNode();
            tail = new DoubleLinkedNode();

            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            int result = NOT_EXIST;
            DoubleLinkedNode node = cache.get(key);
            if (node != null) {
                moveToHead(node);
                result = node.value;
            }
            return result;
        }

        public void put(int key, int value) {
            DoubleLinkedNode cacheNode = cache.get(key);
            if (cacheNode == null) {
                DoubleLinkedNode node = new DoubleLinkedNode(key, value);
                if (cache.size() >= capacity) {
                    popTail();
                }
                addToHead(node);
            } else {
                // update the val. !!!啊啊啊 此处为考虑到。。。
                cacheNode.value = value;
                moveToHead(cacheNode);
            }
        }

        private void addToHead(DoubleLinkedNode node) {
            cache.put(node.key, node);
            DoubleLinkedNode headNext = head.next;
            head.next = node;
            node.next = headNext;
            node.prev = head;
            headNext.prev = node;
        }

        private void popTail() {
            DoubleLinkedNode tailNode = tail.prev;
            removeNode(tailNode);
            cache.remove(tailNode.key);
        }

        private void moveToHead(DoubleLinkedNode node) {
            DoubleLinkedNode headNext = head.next;
            if (headNext.key != node.key) {
                removeNode(node);
                addToHead(node);
            }
        }

        private void removeNode(DoubleLinkedNode node) {
            DoubleLinkedNode nodePrev = node.prev;
            DoubleLinkedNode nodeNext = node.next;
            nodePrev.next = nodeNext;
            nodeNext.prev = nodePrev;
        }
    }

    class DoubleLinkedNode {
        int key;
        int value;
        DoubleLinkedNode prev = null;
        DoubleLinkedNode next = null;

        public DoubleLinkedNode() {
        }

        public DoubleLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    @Test
    public void testCase04() {
        LRUCache cache = new LRUCache(2);

        cache.put(2, 1);
        cache.put(2, 2);
        printCacheGet(2, cache);    // 返回  2
    }

    @Test
    public void testCase03() {
        LRUCache cache = new LRUCache(1);

        cache.put(2, 1);
        printCacheGet(2, cache);    // 返回  1
    }

    @Test
    public void testCase02() {
        LRUCache cache = new LRUCache(2);

        cache.put(2, 2);
        cache.put(1, 1);
        printCacheGet(2, cache);    // 返回  2
        cache.put(2, 2);
        cache.put(1, 1);    //该操作使1移到了head.next
        printCacheGet(2, cache);    // 返回  2
    }

    @Test
    public void testCase01() {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        printCacheGet(1, cache);    // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        printCacheGet(2, cache);    // 返回  -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        printCacheGet(1, cache);    // 返回  -1 (未找到)
        printCacheGet(3, cache);    // 返回  3
        printCacheGet(4, cache);    // 返回  4
    }

    private void printCacheGet(int key, LRUCache cache) {
        System.out.println("get(" + key + ") = " + cache.get(key));
    }

}
