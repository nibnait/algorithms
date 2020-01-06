package common.datastruct;

import lombok.NoArgsConstructor;

/**
 * 双向链表结点
 */
@NoArgsConstructor
public class DoubleLinkedNode {
    public int value;
    public DoubleLinkedNode prev = null;
    public DoubleLinkedNode next = null;

    public DoubleLinkedNode(int data){
        this.value = data;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value +"";
    }

    void print() {
        System.out.print(value + " ");
        if (next != null) {
            next.print();
        }
    }
}
