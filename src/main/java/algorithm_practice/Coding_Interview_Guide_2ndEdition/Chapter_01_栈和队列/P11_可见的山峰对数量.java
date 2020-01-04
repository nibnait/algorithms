package algorithm_practice.Coding_Interview_Guide_2ndEdition.Chapter_01_栈和队列;

import junit.framework.TestCase;
import lombok.Getter;
import org.junit.Test;
import common.util.SysOut;

import java.util.Stack;

/*
【题目】
一个不含有负数的数组可以代表一圈环形山，每个位置的值代表山的高度。比如，{3,1,2,4,5}、{4,5,3,1,2}或{1,2,4,5,3}都代表同样结构的环形山。3->1->2->4->5->3 方向叫作next 方向（逆时针），3->5->4->2->1->3 方向叫作last 方向（顺时针），如图1-8 所示。

山峰A 和山峰B 能够相互看见的条件为：
1．如果A 和B 是同一座山，认为不能相互看见。
2．如果A 和B 是不同的山，并且在环中相邻，认为可以相互看见。比如图1-8 中，相邻的山峰对有(1,2)(2,4)(4,5)(3,5)(1,3)。
3．如果A 和B 是不同的山，并且在环中不相邻，假设两座山高度的最小值为min。如果A通过next 方向到B 的途中没有高度比min 大的山峰，或者A 通过last 方向到B 的途中没有高度比min 大的山峰，认为A 和B 可以相互看见。比如图1-8 中，高度为3 的山和高度为4 的山，两座山的高度最小值为3。3 从last 方向走向4，中途会遇见5，所以last 方向走不通；3 从next方向走向4，中途会遇见1 和2，但是都不大于两座山高度的最小值3，所以next 方向可以走通。
有一个能走通就认为可以相互看见。再如，高度为2 的山和高度为5 的山，两个方向上都走不通，所以不能相互看见。图1-8 中所有在环中不相邻，并且能看见的山峰对有(2,3)(3,4)。给定一个不含有负数且没有重复值的数组arr，请返回有多少对山峰能够相互看见。

进阶问题：给定一个不含有负数但可能含有重复值的数组arr，返回有多少对山峰能够相互看见。

【要求】
如果arr 长度为N，没有重复值的情况下时间复杂度达到O(1)，可能有重复值的情况下时间复杂度请达到O(N)。

【难度】
原问题 士 ★☆☆☆
进阶问题 将 ★★★★

 */
public class P11_可见的山峰对数量 extends TestCase {

    @Test
    public void testCase() {
        int[] arr = new int[]{5, 4, 3, 5, 4, 2, 4, 4, 5, 3, 2};
        SysOut.println("共有%s个可见山峰对", getVisibleNum(arr));
    }

    /**
     * 单调栈中的元素（因为有重复值，所以要记住每个元素出现的次数）
     */
    @Getter
    class Record {
        private int value;
        private int times;

        public Record(int value) {
            this.value = value;
            this.times = 1;
        }

        public void timesAddOne() {
            this.times += 1;
        }
    }

    private int getVisibleNum(int[] arr) {
        int length = arr.length;
        if (arr == null || length <= 1) {
            return 0;
        }

        int res = 0;
        Stack<Record> stack = new Stack<>();
        //先找到最大值下标，放入stack中
        int maxIndex = getMaxIndex(arr);
        stack.push(new Record(arr[maxIndex]));

        //开始遍历
        int i = getNextIndex(maxIndex, length);
        while (i != maxIndex) {
            while (!stack.isEmpty() && arr[i] > stack.peek().getValue()) {
                res += getCurrentNodeNum(stack.pop());
            }
            if (!stack.isEmpty() && arr[i] == stack.peek().getValue()) {
                stack.peek().timesAddOne();
            } else {
                stack.add(new Record(arr[i]));
            }
            i = getNextIndex(i, length);
        }

        //开始清算
        while (stack.size() > 2) {
            res += getCurrentNodeNum(stack.pop());
        }
        //还是两种大小的山峰时
        if (stack.size() == 2) {
            Record pop = stack.pop();
            res += getCurrentNodeNum(pop) + getCircleNodeNum(pop.getTimes(), stack.peek());
        }
        //还是最后一组同样高的山峰时
        Record pop = stack.pop();
        res += getCurrentNodeNum(pop) - 2*pop.getTimes();   //因为两边已经没有比他高的山峰了
        return res;
    }

    /* pop为倒数第2组结点
       peek为最后一组结点

       如果peek只有1个，则pop这组结点 还要加上pop.times，（getCurrentNodeNum只计算了从next方向上计算的数量）
                                         （从last方向上看的组合）
           peek有多个，则加上pop.times * peek.times

     */
    private int getCircleNodeNum(int times, Record peek) {
        return peek.getTimes() == 1 ? times
                                    : times * peek.getTimes();  //
    }

    /**
     * 返回下一个结点的位置
     */
    private int getNextIndex(int i, int length) {
        return i < length - 1 ? i + 1 : 0;
    }

    /*
       1. 转化为单调栈结构
       因为矮的山峰之间如果隔了一个高的山峰，就互相看不到了，因此此题可转化为：【寻找离每个结点最近的比它大的元素的index】
       即 需要栈顶到栈底依次递增

       因为是环形 可重复的数组，因此需要从其中任意一个最大值，开始遍历。
       这样才能保持单调栈结构，是从栈顶到栈底依次递增的。
     */
    private int getMaxIndex(int[] arr) {
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            maxIndex = arr[i] > arr[maxIndex] ? i : maxIndex;
        }
        return maxIndex;
    }

    /*
      2. 计算当前结点的可见山峰数
      当前弹出的结点
      times == 1，说明pop next方向的邻结点（遍历到当前的结点），比pop.value大
                     pop last方向的邻结点（stack.peek）也比pop.value大
                  则此结点的可见山峰数为2
      times > 1，说明有多个与pop.value相同的结点，这几个pop结点之间 比pop.value小的结点已经被弹出，并将自己的可见山峰数计算完毕
                下面只需计算这几个value相同的结点的可见山峰数即可，假设有k个【k = pop.getTimes()】
                1. 这些山峰都可以看见next方向和last方向的两个高峰，记为：2 * k
                2. 这些山峰之间，两两可见，记为：(k * k-1) / 2
    */
    private int getCurrentNodeNum(Record pop) {
        int k = pop.getTimes();
        return k == 1 ? 2
                : 2 * k + (k * (k - 1)) / 2;
    }

}
