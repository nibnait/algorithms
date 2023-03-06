package cc.tianbin.data_struct.ds4_图;

import java.util.ArrayList;
import java.util.List;

/**
 * 图中的点
 * Created by nibnait on 2022/11/06
 */
public class Node {

    // 当前点的 值
    public int value;
    // 入度
    public int in;
    // 出度
    public int out;
    // 从当前点出发的 下一个点
    public List<Node> nexts;
    // 从当前点出发的 边
    public List<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
