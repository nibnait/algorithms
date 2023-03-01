package cc.tianbin.common.datastruct;

import lombok.NoArgsConstructor;

/**
 * 双向链表的节点
 * 单向链表的节点（只要不取 prev 这个属性值就好了）
 * Created by nibnait on 2022/07/06
 */
@NoArgsConstructor
public class Node<T> {

   public T value;
   public Node<T> prev;
   public Node<T> next;

   public Node(T value) {
      this.value = value;
   }
}
