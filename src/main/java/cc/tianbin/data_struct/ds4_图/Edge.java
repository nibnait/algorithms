package cc.tianbin.data_struct.ds4_图;

/**
 * Created by nibnait on 2022/11/06
 */
public class Edge {

    public int weight;

    public Node from;

    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
