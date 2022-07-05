package common.datastruct;

/**
 * LeetCode 链表结点
 * Created by nibnait on 2016/9/20.
 */
public class LinkedNode {
    public int val;
    public LinkedNode prev = null;
    public LinkedNode next = null;

    public LinkedNode() {
    }

    public LinkedNode(int data){
        this.val = data;
    }

    public void printListNode() {
        String arrow = next != null ? "->" : "";
        System.out.print(val + arrow);
        if (next != null) {
            next.printListNode();
        }
    }
}
