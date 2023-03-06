package cc.tianbin.common.datastruct;

/**
 * LeetCode 链表结点
 * Created by nibnait on 2016/9/20.
 */
public class ListNode {
    public int val;
    public ListNode prev = null;
    public ListNode next = null;

    public ListNode() {
    }

    public ListNode(int data){
        this.val = data;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public void printListNode() {
        String arrow = next != null ? "->" : "";
        System.out.print(val + arrow);
        if (next != null) {
            next.printListNode();
        }
    }
}
