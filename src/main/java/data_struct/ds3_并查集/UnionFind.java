package data_struct.ds3_并查集;

import com.google.common.collect.Lists;
import data_struct.Node;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Created by nibnait on 2022/11/02
 */
public class UnionFind {

    @Test
    public void testCase() {

        Node node1 = Node.v(1);
        Node node2 = Node.v(2);
        Node node3 = Node.v(3);
        Node node4 = Node.v(4);
        Node node5 = Node.v(5);

        UnionFindImpl unionFind = new UnionFindImpl(Lists.newArrayList(node1, node2, node3, node4, node5));
        Assert.assertEquals(5, unionFind.size());
        Assert.assertFalse(unionFind.isSameSet(node1, node2));
        Assert.assertEquals(node1, unionFind.findAncestor(node1));

        unionFind.union(node1, node2);
        Assert.assertTrue(unionFind.isSameSet(node1, node2));
        Assert.assertEquals(4, unionFind.size());
        Assert.assertEquals(node1, unionFind.findAncestor(node2));

        unionFind.union(node3, node4);
        Assert.assertEquals(3, unionFind.size());
        Assert.assertEquals(node3, unionFind.findAncestor(node4));

        unionFind.union(node1, node5);
        Assert.assertEquals(2, unionFind.size());

        unionFind.union(node2, node4);
        Assert.assertEquals(node1, unionFind.findAncestor(node4));

    }

    public class UnionFindImpl implements MyUnionFind<Node> {

        // 存放节点对应的直系父亲节点
        private Map<Node, Node> fathers;
        // 存放 祖先节点 对应的集合的大小。
        private Map<Node, Integer> sizeMap;

        public UnionFindImpl(List<Node> nodes) {
            fathers = new HashMap<>();
            sizeMap = new HashMap<>();
            if (CollectionUtils.isEmpty(nodes)) {
                return;
            }
            for (Node node : nodes) {
                fathers.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        /**
         * 优化点1：为了每次往上找的链 都能更短一点。
         * 可以找完之后把这一串节点全部打平，直接挂在 ancestor 下面
         */
        @Override
        public Node findAncestor(Node cur) {
            Stack<Node> stack = new Stack<>();
            while (cur != fathers.get(cur)) {
                stack.push(cur);
                cur = fathers.get(cur);
            }

            while (!stack.isEmpty()) {
                Node node = stack.pop();
                fathers.put(node, cur);
            }
            return cur;
        }

        @Override
        public boolean isSameSet(Node a, Node b) {
            return findAncestor(a) == findAncestor(b);
        }

        /**
         * 优化点2：为了链的长度涨的慢一点。
         * 可以小集合的头节点 往大集合上挂。
         */
        @Override
        public void union(Node a, Node b) {
            Node aHead = findAncestor(a);
            Node bHead = findAncestor(b);
            if (aHead == bHead) {
                return;
            }

            Integer aSize = sizeMap.get(aHead);
            Integer bSize = sizeMap.get(bHead);
            Node big = aSize >= bSize ? aHead : bHead;
            Node small = big == aHead ? bHead : aHead;
            fathers.put(small, big);
            sizeMap.put(big, aSize + bSize);
            sizeMap.remove(small);
        }

        @Override
        public int size() {
            return sizeMap.size();
        }
    }
}
