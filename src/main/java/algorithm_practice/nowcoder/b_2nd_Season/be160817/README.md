课程回顾：[http://www.Algorithm.algorithm_practice.nowcoder.com/live/11/5/1](http://www.nowcoder.com/live/11/5/1)
课件下载：[https://pan.baidu.com/s/1gf4XXIF](https://pan.baidu.com/s/1gf4XXIF)


## 题目一：猫狗队列
【题目】
宠物、狗和猫的类如下：
```
    public class Pet {
        private String type;
        public Pet(String type) {
            this.type = type;
        }
        public String getPetType() {
            return this.type;
        }
    }
    public class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }
    public class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }
    
```    
实现一种狗猫队列的结构，要求如下：  
用户可以调用add 方法将cat 类或dog 类的实例放入队列中；  
用户可以调用pollAll 方法，将队列中所有的实例按照进队列的先后顺序依次弹出；  
用户可以调用pollDog 方法，将队列中dog 类的实例按照进队列的先后顺序依次弹出；  
用户可以调用pollCat 方法，将队列中cat 类的实例按照进队列的先后顺序依次弹出；  
用户可以调用isEmpty 方法，检查队列中是否还有dog 或cat 的实例；  
用户可以调用isDogEmpty 方法，检查队列中是否有dog 类的实例；  
用户可以调用isCatEmpty 方法，检查队列中是否有cat 类的实例。  
【要求】
要求所有实现的方法，时间复杂度都为O(1)

    
代码：[./src/DogCat.java](./src/DogCat.java)
    

## 题目二：用栈来求解汉诺塔问题
【题目】
汉诺塔问题比较经典，这里修改一下游戏规则：现在限制不能从最左侧的塔直接移动到最右
侧，也不能从最右侧直接移动到最左侧，而是必须经过中间。求当塔有N 层的时候，打印
最优移动过程和最优移动总步数。

例如，当塔数为两层时，最上层的塔记为1，最下层的塔记为2，则打印：

    Move 1 from left to mid
    Move 1 from mid to right
    Move 2 from left to mid
    Move 1 from right to mid
    Move 1 from mid to left
    Move 2 from mid to right
    Move 1 from left to mid
    Move 1 from mid to right
    It will move 8 steps.
注意：关于汉诺塔游戏的更多讨论，将在本书递归与动态规划的章节中继续。

【要求】
用以下两种方法解决。
 - 方法一：递归的方法；
 - 方法二：非递归的方法，用栈来模拟汉诺塔的三个塔。

     - 方法一：递归   O（5*N）
        1. 1~(N-1)  L-->R
        2. N    L-->M
        3. 1~(N-1)  R-->L
        4. N    M-->R
        5. 1~(N-1)  L-->R
     - 方法二：用栈，只要满足这两个条件即可
         - 只能小压大
         - 相邻动作一定不是可逆的（No, LToM, MToL, MToR, RToM）
    

代码：[./src/Hanoi.java](./src/Hanoi.java)


## 题目三：构造数组的MaxTree
【题目】
定义二叉树结点如下：
```
    public class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }
```

一个数组的MaxTree 定义如下。
 - 数组必须没有重复元素。
 - MaxTree 是一棵二叉树，数组的每一个值对应一个二叉树结点。
 - 包括MaxTree 树在内且在其中的每一棵子树上，值最大的结点都是树的头。
 
给定一个没有重复元素的数组arr，写出生成这个数组的MaxTree 的函数，要求如果数组
长度为N，则时间复杂度为O(N)、额外空间复杂度为O(N)。

    用栈
         - 从栈顶到栈底，依次递增
         - 这样，对于每次的栈顶：他右边离他最近的比他大是数为null, 左边离他最近的比他大的数为他下面的那个数
    
代码：[./src/MaxTree.java](./src/MaxTree.java)
    

## 题目四：最大值减去最小值小于或等于num 的子数组数量
【题目】
给定数组arr 和整数num，共返回有多少个子数组满足如下情况：
 - max(arr[i..j]) - min(arr[i..j]) <= num
 - max(arr[i..j])表示子数组arr[i..j]中的最大值，min(arr[i..j])表示子数组arr[i..j]中的最小值。
 
【要求】
如果数组长度为N，请实现时间复杂度为O(N)的解法。

    继续 双端队列
    
    
代码：[./src/AllLessNumSubArray.java](./src/AllLessNumSubArray.java) 
