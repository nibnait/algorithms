package data_struct.ds4_图;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by nibnait on 2022/11/06
 */
public class Graph {

    public Map<Integer, Node> nodes;

    public Set<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
