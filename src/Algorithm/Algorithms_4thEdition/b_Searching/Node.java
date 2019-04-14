package Algorithm.Algorithms_4thEdition.b_Searching;

/**
 * Created by nibnait on 2016/9/2.
 */
public class Node<Key extends Comparable<Key>, Value>{

    public Key key;            //键
    public Value value;        //值
    public Node left, right;   //指向子树的链接
    public int N;              //以该结点为根的子树中结点总数
    
    public Node(Key key, Value value, int N){
        this.key = key;
        this.value = value;
        this.N = N;
    }

}
