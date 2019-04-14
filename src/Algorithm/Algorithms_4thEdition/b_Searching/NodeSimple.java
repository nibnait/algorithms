package Algorithm.Algorithms_4thEdition.b_Searching;

/**
 *
 * Created by nibnait on 2016/9/2.
 */
public class NodeSimple {
    private int key;    //键 int
    private String value;   //值 String
    private NodeSimple left, right;   //指向子树的链接
    private int N;  //以该节点为根的子树中的结点总数

    public NodeSimple(int key, String value, int n) {
        this.key = key;
        this.value = value;
        N = n;
    }
}
