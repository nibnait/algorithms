## 体系学习班
class01 - class10

### 用master公式来估计时间复杂度
Master 公式：  
形如 T(N) = a * T(N/b) + O(N^d) （a、b、d都是常数）的递归函数，可以直接通过 Master 公式来确定时间复杂度
- 如果 log(b,a) < d, 复杂度为 O(N^d)
- 如果 log(b,a) > d, 复杂度为 O(N^log(b,a))
- 如果 log(b,a) == d, 复杂度为 O(N^d * logN)

### 堆 和 加强堆
[堆相关](../../../data_struct/堆)