课程回顾：[http://www.nowcoder.com/live/11/9/1](http://www.nowcoder.com/live/11/9/1)
课件下载：[https://pan.baidu.com/s/1kVk7k71](https://pan.baidu.com/s/1kVk7k71)


## 题目一： 在二叉树中找到一个节点的后继节点
【题目】
现在有一种新的二叉树节点类型如下：

    public class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;
    
        public Node(int data) {
            this.value = data;
        }
    }

该结构比普通二叉树节点结构多了一个指向父节点的parent指针。假设有一棵Node类型的节点组成的二叉树，树中每个节点的parent指针都正确地指向自己的父节点，头节点的parent指向null。只给二叉树中的某个节点node，请实现返回node的后继节点的函数。
后继节点：在二叉树的中序遍历的序列中，node的下一个节点叫作node的后继节点。

    



## 题目二： 遍历二叉树的神级方法
【题目】
给定一棵二叉树的头节点head，完成二叉树的先序、中序和后序遍历。

【要求】
如果二叉树的节点数为N，要求时间复杂度为O(N)，额外空间复杂度为O(1)。

    Morris遍历，时间复杂度是O(1)：使用二叉树节点中大量的指向null的指针。
    
    




## 题目三： 找到二叉树中符合搜索二叉树条件的最大拓扑结构
【题目】
给定一棵二叉树的头节点head，已知所有节点的值都不一样，返回其中最大的、且符合搜索二叉树条件的拓扑结构的节点数。这里的拓扑结构是指，你可以在二叉树中任意选择某些节点，只要这些节点是连在一起的，都叫做二叉树的拓扑结构。
​
