package algorithm_practice.LeetCode.code400;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;

/*
设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put。

get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
put(key, val) - 如果键不存在，请设置或插入值。当缓存达到其容量时，它应该在插入新项目之前，使最不经常使用的项目无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除。

进阶：
你是否可以在 O(1) 时间复杂度内执行两项操作？

示例：

        LFUCache cache = new LFUCache( 2 ); // capacity (缓存容量)

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回 1
        cache.put(3, 3);    // 去除 key 2
        cache.get(2);       // 返回 -1 (未找到key 2)
        cache.get(3);       // 返回 3
        cache.put(4, 4);    // 去除 key 1
        cache.get(1);       // 返回 -1 (未找到 key 1)
        cache.get(3);       // 返回 3
        cache.get(4);       // 返回 4

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/lfu-cache
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class H460_LFU缓存 extends TestCase {

    /*
      此处：不需要考虑并发、也不需要考虑freq的衰减。达到capacity 直接popTail，无需判断tail的freq
      HashMap<key, FreqNode> + 1个双向链表（用来装各个freq等级的FreqNode的head结点，正序）+ 若干个双向链表（放各个相同freq等级的FreqNode）

      head -- (0)head

      get操作：
        map中有此key ? moveToNextFreqHead(FreqNode) : return -1;

      put操作：
        if (capacity == 0) return;
        新key：if (达到了capacity) pop(head.next的尾结点); addToHeadNext()
        旧key：moveToNextFreqHead(FreqNode)

        moveToNextFreqHead(FreqNode) {
            removeNode(FreqNode);
            FreqNode.freq++;
            updateMap;
            addToNextFreqHead(FreqNode)
        }
     */

    class LFUCache {
        int capacity;
        HashMap<Integer, FreqNode> cache = new HashMap<>();
        FreqHeadNode head, tail;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            head = new FreqHeadNode();
            tail = new FreqHeadNode();

            head.next = tail;
            tail.next = head;
        }

        public int get(int key) {
            FreqNode cacheNode = cache.get(key);
            if (cacheNode != null) {
                moveToNextFreqHead(cacheNode);
                return cacheNode.value;
            }
            return -1;
        }

        private void moveToNextFreqHead(FreqNode cacheNode) {
            removeFreqNode(cacheNode);
            cacheNode.freq++;
            addToHeadNext(cacheNode, cacheNode.head);
        }

        private void removeFreqNode(FreqNode node) {
            if (node.head.first.next == node && node.next == node.head.last) {
                //当此环只剩下cacheNode时
                removeFreqHeadNode(node.head);
            } else {
                FreqNode next = node.next;
                FreqNode prev = node.prev;
                prev.next = next;
                next.prev = prev;
            }
            cache.remove(node.key);
        }

        private void removeFreqHeadNode(FreqHeadNode freqHeadNode) {
            FreqHeadNode next = freqHeadNode.next;
            FreqHeadNode prev = freqHeadNode.prev;
            prev.next = next;
            next.prev = prev;
        }

        public void put(int key, int value) {
            if (capacity == 0) {
                return;
            }
            FreqNode cacheNode = cache.get(key);
            if (cacheNode == null) {
                //新key
                FreqNode freqNode = new FreqNode(key, value);
//                boolean putOperation = true;
                if (cache.size() == capacity) {
                    popTail();
//                    if (!popTail()) {
//                        putOperation = false;
//                    }
                }
//                if (putOperation) {
                addToHeadNext(freqNode, head);
//                }
            } else {
                cacheNode.value = value;
                moveToNextFreqHead(cacheNode);
            }
        }

        private void addToHeadNext(FreqNode freqNode, FreqHeadNode currentHead) {
            FreqHeadNode nextHeadNode = currentHead.next;
            if (currentHead.next != tail && nextHeadNode.freq == freqNode.freq) {
                // 加到下一个freq等级的 head.next处
                FreqNode next = nextHeadNode.first.next;
                nextHeadNode.first.next = freqNode;
                freqNode.next = next;
                freqNode.prev = nextHeadNode.first;
                next.prev = freqNode;
                freqNode.head = nextHeadNode;
            } else {
                // 来到了一个新的freq
                FreqHeadNode freqHeadNode = new FreqHeadNode(freqNode.freq);

                if (currentHead != head && currentHead.first.next.key == freqNode.key && currentHead.first.next == currentHead.last.prev) {
                    // 当前currentHead已经被删除了。。只是freqNode没有删干净。特殊处理一下。。。
                    FreqHeadNode next = currentHead.next;
                    currentHead.prev.next = freqHeadNode;
                    freqHeadNode.next = next;
                    freqHeadNode.prev = currentHead.prev;
                    next.prev = freqHeadNode;

                    freqHeadNode.first = new FreqNode();
                    freqHeadNode.last = new FreqNode();
                    freqHeadNode.first.next = freqNode;
                    freqNode.prev = freqHeadNode.first;
                    freqNode.next = freqHeadNode.last;
                    freqHeadNode.last.prev = freqNode;

                    freqNode.head = freqHeadNode;
                } else {
                    FreqHeadNode next = currentHead.next;
                    currentHead.next = freqHeadNode;
                    freqHeadNode.next = next;
                    freqHeadNode.prev = currentHead;
                    next.prev = freqHeadNode;

                    freqHeadNode.first = new FreqNode();
                    freqHeadNode.last = new FreqNode();
                    freqHeadNode.first.next = freqNode;
                    freqNode.prev = freqHeadNode.first;
                    freqNode.next = freqHeadNode.last;
                    freqHeadNode.last.prev = freqNode;

                    freqNode.head = freqHeadNode;
                }
            }
            cache.put(freqNode.key, freqNode);
        }

        private boolean popTail() {
//            if (head.next.freq == 0) {
            removeFreqNode(head.next.last.prev);
            return true;
//            }
//            return false;
        }

    }

    class FreqHeadNode {
        int freq = -1;
        FreqHeadNode prev = null;
        FreqHeadNode next = null;
        FreqNode first = null;
        FreqNode last = null;

        public FreqHeadNode() {
        }

        public FreqHeadNode(int freq) {
            this.freq = freq;
        }
    }

    class FreqNode {
        int key = -1;
        int value = -1;
        int freq = -1;
        FreqNode prev = null;
        FreqNode next = null;
        FreqHeadNode head = null;

        public FreqNode() {
        }

        public FreqNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 0;
        }
    }

    /*
    ["LFUCache","put","put","get","put","get","get","put","get","get","get"]
    [[2],       [1,1],[2,2],[1],  [3,3], [2],  [3],  [4,4],[1],  [3],  [4]]
    预期结果：
    [null,null,null,1,null,-1,3,null,-1,3,4]
     */
    @Test
    public void testCase08() {
        LFUCache cache = new LFUCache(2);
        String[] operation = new String[]{"put","put","get","put","get","get","put","get","get","get"};
        int[][] data = {{1,1},{2,2},{1},{3,3},{2},{3},{4,4},{1},{3},{4}};
        Integer[] expectResult = {null,null,1,null,-1,3,null,-1, 3,4};
        //                        null,null,1,null, 2,3,null,-1,-1,4,

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < operation.length; i++) {
            if ("put".equals(operation[i])) {
                cache.put(data[i][0], data[i][1]);
                System.out.print("null,");
            }
            if ("get".equals(operation[i])) {
                int result = cache.get(data[i][0]);
                System.out.print(result + ",");
                if (result != expectResult[i]) {
                    String str = "\n" + expectResult[i] + " -- " + result + "\tcache.get(" + data[i][0] + ")";  //+ "\t i = "+i + ",\t data["+i+"] = " + data[i][0]
                    sb.append(str);
                }
            }
        }
        System.out.println(sb.toString());

    }

    /*
    ["LFUCache","put","get","put","get","get"]
    [[1],       [2,1],[2],  [3,2],[2],[3]]
     */
    @Test
    public void testCase07() {
        LFUCache cache = new LFUCache(1);
        String[] operation = new String[]{"put","get","put","get","get"};
        int[][] data = {{2,1},{2},{3,2},{2},{3}};
        for (int i = 0; i < operation.length; i++) {
            if ("put".equals(operation[i])) {
                cache.put(data[i][0], data[i][1]);
                System.out.print("null,");
            }
            if ("get".equals(operation[i])) {
                int result = cache.get(data[i][0]);
                System.out.print(result + ",");
            }
        }

    }

    /*
    ["LFUCache","put","put","put","put","get"]
    [[2],       [3,1],[2,1],[2,2],[4,4],[2]]
     */
    @Test
    public void testCase06() {
        LFUCache cache = new LFUCache(2);
        String[] operation = new String[]{"put","put","put","put","get"};
        int[][] data = {{3,1},{2,1},{2,2},{4,4},{2}};
        for (int i = 0; i < operation.length; i++) {
            if ("put".equals(operation[i])) {
                cache.put(data[i][0], data[i][1]);
                System.out.print("null,");
            }
            if ("get".equals(operation[i])) {
                int result = cache.get(data[i][0]);
                System.out.print(result + ",");
            }
        }

    }

    /*
    ["LFUCache","put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"]
    [[10],[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],[1,30],[11],[9,12],[7],[5],[8],[9],[4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],[8],[2,14],[1],[5],[4],[11,4],[12,24],[5,18],[13],[7,23],[8],[12],[3,27],[2,12],[5],[2,9],[13,4],[8,18],[1,7],[6],[9,29],[8,21],[5],[6,30],[1,12],[10],[4,15],[7,22],[11,26],[8,17],[9,29],[5],[3,4],[11,30],[12],[4,29],[3],[9],[6],[3,4],[1],[10],[3,29],[10,28],[1,20],[11,13],[3],[3,12],[3,8],[10,9],[3,26],[8],[7],[5],[13,17],[2,27],[11,15],[12],[9,19],[2,15],[3,16],[1],[12,17],[9,1],[6,19],[4],[5],[5],[8,1],[11,7],[5,2],[9,28],[1],[2,2],[7,4],[4,22],[7,24],[9,26],[13,28],[11,26]]
    预期结果：
    [null,null,null,null,null,null,-1,null,19,17,null,-1,null,null,null,-1,null,-1,5,-1,12,null,null,3,5,5,null,null,1,null,-1,null,30,5,30,null,null,null,-1,null,-1,24,null,null,18,null,null,null,null,14,null,null,18,null,null,11,null,null,null,null,null,18,null,null,-1,null,4,29,30,null,12,11,null,null,null,null,29,null,null,null,null,17,-1,18,null,null,null,-1,null,null,null,20,null,null,null,29,18,18,null,null,null,null,20,null,null,null,null,null,null,null]
     */
    @Test
    public void testCase05() {
        LFUCache cache = new LFUCache(10);
        String[] operation = new String[]{"put", "put", "put", "put", "put", "get", "put", "get", "get", "put", "get", "put", "put", "put", "get", "put", "get", "get", "get", "get", "put", "put", "get", "get", "get", "put", "put", "get", "put", "get", "put", "get", "get", "get", "put", "put", "put", "get", "put", "get", "get", "put", "put", "get", "put", "put", "put", "put", "get", "put", "put", "get", "put", "put", "get", "put", "put", "put", "put", "put", "get", "put", "put", "get", "put", "get", "get", "get", "put", "get", "get", "put", "put", "put", "put", "get", "put", "put", "put", "put", "get", "get", "get", "put", "put", "put", "get", "put", "put", "put", "get", "put", "put", "put", "get", "get", "get", "put", "put", "put", "put", "get", "put", "put", "put", "put", "put", "put", "put"};
        int[][] data = {{10, 13}, {3, 17}, {6, 11}, {10, 5}, {9, 10}, {13}, {2, 19}, {2}, {3}, {5, 25}, {8}, {9, 22}, {5, 5}, {1, 30}, {11}, {9, 12}, {7}, {5}, {8}, {9}, {4, 30}, {9, 3}, {9}, {10}, {10}, {6, 14}, {3, 1}, {3}, {10, 11}, {8}, {2, 14}, {1}, {5}, {4}, {11, 4}, {12, 24}, {5, 18}, {13}, {7, 23}, {8}, {12}, {3, 27}, {2, 12}, {5}, {2, 9}, {13, 4}, {8, 18}, {1, 7}, {6}, {9, 29}, {8, 21}, {5}, {6, 30}, {1, 12}, {10}, {4, 15}, {7, 22}, {11, 26}, {8, 17}, {9, 29}, {5}, {3, 4}, {11, 30}, {12}, {4, 29}, {3}, {9}, {6}, {3, 4}, {1}, {10}, {3, 29}, {10, 28}, {1, 20}, {11, 13}, {3}, {3, 12}, {3, 8}, {10, 9}, {3, 26}, {8}, {7}, {5}, {13, 17}, {2, 27}, {11, 15}, {12}, {9, 19}, {2, 15}, {3, 16}, {1}, {12, 17}, {9, 1}, {6, 19}, {4}, {5}, {5}, {8, 1}, {11, 7}, {5, 2}, {9, 28}, {1}, {2, 2}, {7, 4}, {4, 22}, {7, 24}, {9, 26}, {13, 28}, {11, 26}};
        Integer[] expectResult = {null, null, null, null, null, -1, null, 19, 17, null, -1, null, null, null, -1, null, -1, 5, -1, 12, null, null, 3, 5, 5, null, null, 1, null, -1, null, 30, 5, 30, null, null, null, -1, null, -1, 24, null, null, 18, null, null, null, null, 14, null, null, 18, null, null, 11, null, null, null, null, null, 18, null, null, -1, null, 4, 29, 30, null, 12, 11, null, null, null, null, 29, null, null, null, null, 17, -1, 18, null, null, null, -1, null, null, null, 20, null, null, null, 29, 18, 18, null, null, null, null, 20, null, null, null, null, null, null, null};
//                                null,null,null,null,null,-1,null,19,17,null,-1,null,null,null,-1,null,-1,5,-1,12,null,null,3,5,5,null,null,1,null,-1,null,30,5,30,null,null,null,-1,null,-1,24,null,null,18,null,null,null,null,-1,null,null,18,null,null,11,null,null,null,null,null,18,null,null,24,null,4,29,-1,null,12,11,null,null,null,null,29,null,null,null,null,17,-1,18,null,null,null,24,null,null,null,20,null,null,null,-1,18,18,null,null,null,null,20,null,null,null,null,null,null,null,
//                                                                                                                                                                                                                                ❌                                                                 ❌          ❌                                                                               ❌                                  ❌
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < operation.length; i++) {
            if ("put".equals(operation[i])) {
                cache.put(data[i][0], data[i][1]);
                System.out.print("null,");
            }
            if ("get".equals(operation[i])) {
                int result = cache.get(data[i][0]);
                System.out.print(result + ",");
                if (result != expectResult[i]) {
                    String str = "\n" + expectResult[i] + " -- " + result + "\tcache.get(" + data[i][0] + ")";  //+ "\t i = "+i + ",\t data["+i+"] = " + data[i][0]
                    sb.append(str);
                }
            }
        }
        System.out.println(sb.toString());

    }

    /*
    ["LFUCache","put","put","get","put","put","get"]
    [ [2],      [2,1],[2,2], [2], [1,1], [4,1],[2]]
     */
    @Test
    public void testCase04() {
        LFUCache cache = new LFUCache(2);

        cache.put(2, 1);
        cache.put(2, 2);
        printCacheGet(2, cache);
        cache.put(1, 1);
        cache.put(4, 1);
        printCacheGet(2, cache);
        printCacheGet(1, cache);
        printCacheGet(4, cache);
    }

    /*
    ["LFUCache","put", "get"]
    [[0],       [0,0],  [0]]
     */
    @Test
    public void testCase03() {
        LFUCache cache = new LFUCache(0);

        cache.put(0, 0);
        printCacheGet(0, cache);
    }

    /*
    ["LFUCache","put","put","get","get","get","put","put","get","get","get","get"]
    [[3],       [2,2],[1,1],[2],   [1],  [2], [3,3],[4,4], [3],  [2],  [1],  [4]]

    [null,null,null,2,1,2,null,null,                        -1,   2,    1,    4]
     */
    @Test
    public void testCase02() {
        LFUCache cache = new LFUCache(3);

        cache.put(2, 2);
        cache.put(1, 1);
        printCacheGet(2, cache);
        printCacheGet(1, cache);
        printCacheGet(2, cache);
        cache.put(3, 3);
        cache.put(4, 4);
        printCacheGet(3, cache);
        printCacheGet(2, cache);
        printCacheGet(1, cache);
        printCacheGet(4, cache);
    }

    @Test
    public void testCase() {
        LFUCache cache = new LFUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        printCacheGet(1, cache);       // 返回 1
        cache.put(3, 3);    // 去除 key 2
        printCacheGet(2, cache);       // 返回 -1 (未找到key 2)
        printCacheGet(3, cache);    // 返回  3
        cache.put(4, 4);    // 去除 key 1
        printCacheGet(1, cache);    // 返回  -1 (未找到)
        printCacheGet(3, cache);    // 返回  3
        printCacheGet(4, cache);    // 返回  4
    }

    private void printCacheGet(int key, LFUCache cache) {
        System.out.println("get(" + key + ") = " + cache.get(key));
    }
}
