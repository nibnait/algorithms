package common.datastruct;

/**
 * LeetCode 链表结点
 * Created by nibnait on 2016/9/20.
 */
public class ListNode {
    public int val;
    public ListNode next = null;

    public ListNode() {
    }
    public ListNode(int data){
        this.val = data;
    }

    @Override
    public String toString() {
        return val +"";
    }

    public void print() {
        String arrow = next != null ? "->" : "";
        System.out.print(val + arrow);
        if (next != null) {
            next.print();
        }
    }
}
