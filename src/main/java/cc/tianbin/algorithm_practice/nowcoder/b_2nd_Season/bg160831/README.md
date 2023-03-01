课程回顾：[http://www.Algorithm.algorithm_practice.nowcoder.com/live/11/7/1](http://www.nowcoder.com/live/11/7/1)
课件下载：[https://pan.baidu.com/s/1miEqE6k](https://pan.baidu.com/s/1miEqE6k)

## 题目一：将搜索二叉树转换成双向链表
【题目】  
对二叉树的结点来说，有本身的值域，有指向左孩子和右孩子的两个指针；对双向链表的结点来说，
有本身的值域，有指向上一个结点和下一个结点的指针。在结构上，两种结构有相似性，现在有一棵
搜索二叉树，请将其转换为一个有序的双向链表。
例如，结点定义为：

    public class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }
【要求】  
所以时间复杂度为O(N)，额外空间复杂度为O(h)，h 为二叉树的高度

    自然想法：
        中序遍历
     
           
    

代码：[./src/BSTtoDoubleLinkedList.java](./src/BSTtoDoubleLinkedList.java)


## 题目二： 清楚的讲一遍KMP 算法以及一个与其相关的推广
【题目】  
给定字符串str1 和str2，请返回str2 在str1 中第一次出现的位置；如果str1 不包含str2，返
回-1  
【要求】  
时间复杂度O(N)   （N为str1的长度）

【推广题目】  
给定两棵二叉树的头结点head1 和head2，判断head2 是不是head1 的子树  
【要求】  
时间复杂度O(N)

     - 前缀：从第一个字符开始向后扩，不包含最后一个字符
     - 后缀：从最后一个字符向前扩， 不包含第一个字符
     例： S(c) ： 在不包含"c"这个字符，只考察"c"前面的字符串，前缀和后缀相同的字符的最大长度。
        ababc  --> S(c)=2;
        aaaac --> S(c)=3;

详解：[../../KMP算法.md](../../a_1st_Season/aa_Manacher_bfprt_KMP/KMP算法.md)
    

## 题目三： 来自观众的一个极好的优化
【题目】
未排序数组中累加和小于或等于给定值的最长子数组长度问题
给定一个无序数组arr，其中元素可正、可负，可0，给定一个整数k。求arr 所有的子数组中累加
和小于或等于k 的最长子数组长度

【例子】  
arr=[3,-2,-4,0,6]，k=-2，相加和小于或等于-2 的最长子数组为[3,-2,-4,0]，所以结果返回
4

【要求】
实现时间复杂度O(N)的方法

    rightMinSumArray[i]：从右向左，以i位置开头的最小累加和
    HashMap： 
        key：i
        value：产生这个位置的最小累加和的 最右的位置
    
    



【特别说明】
在第二次课上，我们讲过这个题，当时讲解的是时间复杂度O(N*logN)的方法和相关的算法原型。
上一周，来自滴滴快车的秦凯杰(kaijieqin@gmail.com)工程师留言给我，说一个他认识的牛人实现出了时间复杂度O(N)的
方法，并把代码发给了我。我仔细阅读之后发现他提供的时间复杂度O(N)的方法是对的。感谢秦凯
杰工程师和他的牛人朋友。我们在这次课上详细讲述该方法。