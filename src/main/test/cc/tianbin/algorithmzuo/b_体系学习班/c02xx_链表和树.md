## 链表

[链表专题](../../../../main/java/algorithm_practice/nowcoder/b_2nd_Season/bf160824/README.md)

## 二叉树
[二叉树专题（一）](../../../../main/java/algorithm_practice/nowcoder/b_2nd_Season/bh160907/README.md)
[二叉树专题（二）](../../../../main/java/algorithm_practice/nowcoder/b_2nd_Season/bi160914/README.md)
[二叉树专题（三）](../../../../main/java/algorithm_practice/nowcoder/b_2nd_Season/bj160928/README.md)

 - [Code01_先序、中序、后序遍历-递归](./c0203_树/Code01_RecursiveTraversalBT.java)
 - [Code02_先序、中序、后序遍历-非递归](./c0203_树/Code02_UnRecursiveTraversalBT.java)
 - [Code03_二叉树的按层遍历](./c0203_树/Code03_LevelTraversalBT.java)
 - [Code04_二叉树的序列化和反序列化](../../../java/common/util/binaryTree/SerializeAndReConstructTree.java)
 - [Code05_N叉树如何通过二叉树来序列化、并完成反序列化](./c0203_树/Code05_EncodeNaryTreeToBinaryTree.java)
 - [Code06_求二叉树的最大宽度](./c0203_树/Code06_TreeMaxWidth.java)

### 树型 DP 的通用套路

 - [Code01_判断二叉树是不是平衡二叉树](./c0204_树型DP/Code01_IsBalanced.java)
 - [Code02_判断二叉树是不是搜索二叉树](./c0204_树型DP/Code02_IsBST.java)
 - ⭐️[Code03_给定一棵二叉树的头节点head，任何两个节点之间都存在距离，返回整棵二叉树的最大距离](./c0204_树型DP/Code03_MaxDistance.java)
 - [Code04_判断二叉树是不是满二叉树](./c0204_树型DP/Code04_IsFull.java)
 - ⭐️[Code05_给定一棵二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的大小](./c0204_树型DP/Code05_MaxSubBSTSize.java)
 - ⭐️[Code06_判断二叉树是不是完全二叉树](./c0204_树型DP/Code06_IsCBT.java)
 - ️[Code07_给定一棵二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的头节点](./c0204_树型DP/Code07_MaxSubBSTHead.java)
 - ️[Code08_给定一棵二叉树的头节点head，和另外两个节点a和b，返回a和b的最低公共祖先](./c0204_树型DP/Code08_lowestAncestor.java)
 - ️[Code09_派对的最大快乐值](./c0204_树型DP/Code09_MaxHappy.java)


**二叉树的递归套路**  
1）假设以X节点为头，假设可以向X左树和X右树要任何信息  
2）在上一步的假设下，讨论以X为头节点的树，得到答案的可能性（最重要）  
3）列出所有可能性后，确定到底需要向左树和右树要什么样的信息  
4）把左树信息和右树信息求全集，就是任何一棵子树都需要返回的信息S  
5）递归函数都返回S，每一棵子树都这么要求  
6）写代码，在代码中考虑如何把左树的信息和右树信息整合出整棵树的信息  