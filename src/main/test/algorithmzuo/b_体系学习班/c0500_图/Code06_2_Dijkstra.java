package algorithmzuo.b_体系学习班.c0500_图;

import data_struct.ds4_图.Edge;
import data_struct.ds4_图.Node;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nibnait on 2022/11/06
 */
public class Code06_2_Dijkstra {


    @Test
    public void test() {

    }

    private class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    /**
     * 用 加强堆 实现 Dijkstra
     */
    public Map<Node, Integer> dijkstra(Node from, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(from, 0);
        HashMap<Node, Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            result.put(cur, distance);
        }
        return result;
    }

    private class NodeHeap {
        private Node[] nodes;
        private Map<Node, Integer> heapIndexMap;
        private Map<Node, Integer> distanceMap;
        private int size;

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (inHeap(node)) {
                // update
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                insertHeapify(node, heapIndexMap.get(node));
            }
            if (!isEntered(node)) {
                // add
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(node, size++);
            }
        }

        private void insertHeapify(Node node, Integer index) {
            int fatherIndex = (index - 1) / 2;
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[fatherIndex])) {
                swap(index, fatherIndex);
                index = fatherIndex;
            }
        }

        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            heapIndexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            nodes[size - 1] = null;
            heapify(0, --size);
            return nodeRecord;
        }

        private void heapify(int index, int size) {
            int leftChild = index * 2 + 1;
            while (leftChild < size) {
                int smallest = leftChild + 1 < size && distanceMap.get(nodes[leftChild + 1]) < distanceMap.get(nodes[leftChild])
                        ? leftChild + 1
                        : leftChild;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index])
                        ? smallest
                        : index;
                swap(smallest, index);
                index = smallest;
                leftChild = index * 2 + 1;
            }
        }

        private void swap(Integer index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }

        private boolean inHeap(Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        private boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

    }

}
