package algorithm_practice.a_Sorting;

/**
 * 一些时间复杂度趋近于O(N)的排序算法
 *	 （不是基于比较的排序算法）
 *	思想来源于桶排序
 */

/**
 * Counting_Sort
 * 时间复杂度：
 *	【题目】
对于一个int数组，请编写一个计数排序算法，对数组元素排序。
给定一个int数组A及数组的大小n，请返回排序后的数组。
测试样例：
[1,2,3,5,2,3],6
[1,2,2,3,3,5]
 *
 *	【思路】
 * 桶排序
 *
 * Created by nibnait on 2016/9/23.
 */

public class g_计数排序 {
    public static void main(String[] args) {
        int[] A = new int[]{54,35,48,36,27,12,44,44,8,14,26,17,28};

        A = Counting_Sort(A, 13);

        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i]+" ");
        }
    }

    public static int[] Counting_Sort(int[] A, int n) {

        int max = A[0];
        int min = A[0];
        for (int i = 0; i < A.length; i++) {
            if (A[i]>max) {
                max = A[i];
            }
            if (A[i]<min) {
                min = A[i];
            }
        }

        //建立一个（max-min）这么大的数组
        int[] bucket = new int[max-min+1];
        int cnt=0;

        for (int i = 0; i < A.length; i++) {
            bucket[A[i]-min]++;
        }
        for (int i = 0; i < bucket.length; i++) {
            while(bucket[i]>0){
                A[cnt++] = i+min;
                bucket[i]--;
            }
        }

        return A;
    }

}
