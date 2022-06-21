## 内容：
- 讲解二进制、位运算
- 介绍什么是算法
- 讲解冒泡、选择、插入排序
## 题目：
### 实现打印一个整数的二进制
```java
/**
 * 无符号整数
 */
public void print(int num) {
    for(int i=31; i>=0; i--) {
        System.out.print( num & (1<<i) == 0 ? "0" : "1");   
    }
    System.out.println();
}
```
从右往左数，第31位二进制数，num & 1000...000 == 0 ？ "0"  : "1"
从右往左数，第30位二进制数，num & 0100...000 == 0 ？ "0"  : "1"
...
从右往左数，第0位二进制数，num & 0000...001 == 0 ？ "0"  : "1"
**负数为什么要取反+1？**
为了计算 正数+负数时，与正数+正数，底层位运算用一套逻辑。所以负数的二进制要取反+1
### 给定一个参数N，返回1!+2!+3!+4!+…+N!的结果
f1：暴力计算，每轮都乘n次
f2：保存上一次的接口，每轮只乘1次
### 实现选择排序
0 ~ n-1
1 ~ n-1
每轮选个最小的 放到前面
```java
for (int i = 0; i < length - 1; i++) {
    int minValueIndex = i;
    for (int j = i+1; j < length; j++) {
        minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;
    }
    swap(arr, i, minValueIndex);
}
```
### 实现冒泡排序
0 ~ n-1
0 ~ n-2
每轮冒泡 冒一个最大的放到后面
```java
for (int end = length - 1; end > 0; end--) {
    for (int i = 0; i < end; i++) {
        if (arr[i] > arr[i + 1]) {
            swap(arr, i, i + 1);
        }
    }
}
```
### 实现插入排序
打扑克 抓牌、码牌
```java
for (int end = 1; end < length; end++) {
    int newNumIndex = end;
    while (newNumIndex - 1 >= 0 && arr[newNumIndex - 1] > arr[newNumIndex]) {
        swap(arr, newNumIndex - 1, newNumIndex);
        newNumIndex--;
    }
}
```
