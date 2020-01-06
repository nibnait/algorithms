package algorithm_practice.a_Sorting;

/**
 * Radix_Sort
 * 时间复杂度：O(k·n)，其中n是排序元素个数，k是数字位数
 * @author uu789
 *	【题目】
对于一个int数组，请编写一个基数排序算法，对数组元素排序。
给定一个int数组A及数组的大小n，请返回排序后的数组。
测试样例：
[1,2,3,5,2,3],6
[1,2,2,3,3,5]
 *
 *	【思路】
 *	从后往前 按位比较
 *
 */
public class g_基数排序 {
    public static void main(String[] args) {
        int[] A = new int[]{227,123,278,38,242,229,202,269,76,197,12,32,235,23,266,124,253,179,216,250,123,106,284,250,44,225,36,25,144,123,264,70,287,244,123,89,21,258,203,97,188,295,211,302,177,80,258,267,164,58,0,76,63,222,6,114,5,128,256,173,191,157,50,181,152,18,302,135,108};

        A = Radix_Sort(A, 69);

        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i]+" ");
        }
    }

    public static int[] Radix_Sort(int[] A, int n) {

        int max = getMax(A);

        int k = getBit(max);	//最大数字位数

        int[][] bucket = new int[10][n];
        int cnt = 0;
        int[] bucket_cnt = new int[10];
        for (int i = 0; i < k; i++) {
            //将桶清空、并充值一下 bucket_cnt计数器
            memset(bucket,bucket_cnt);

            for (int j = 0; j < A.length; j++) {
                int num = getNum(A[j], i+1);		//得到这一轮A[j] 应该被放在的bucketNum
                bucket[num][bucket_cnt[num]++] = A[j];
            }
            //整理
            cnt = 0;
            for (int j = 0; j < 10; j++) {
                for (int j2 = 0; j2 < getMax(bucket_cnt); j2++) {
                    if (bucket[j][j2]>=0) {
                        A[cnt++] = bucket[j][j2];
                    }
                }
            }
        }

        return A;
    }

    private static void memset(int[][] bucket, int[] bucket_cnt) {
        for (int j = 0; j < bucket.length; j++) {
            for (int j2 = 0; j2 < bucket[0].length; j2++) {
                bucket[j][j2] = -1;
            }
            bucket_cnt[j] = 0;
        }
    }

    private static void print(int[][] bucket) {
        System.out.println("----------------------------------");
        for (int j = 0; j < bucket.length; j++) {
            for (int j2 = 0; j2 < bucket[0].length; j2++) {
                System.out.print(bucket[j][j2]+" ");
            }
            System.out.println();
        }
        System.out.println("----------------------------------");
    }

    private static int getMax(int[] A) {
        int max = A[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i]>max) {
                max = A[i];
            }
        }
        return max;
    }

    private static int getBit(int num) {	//得到max的位数
        int k = 1;
        int temp = 10;
        while(num>temp){
            temp *= 10;
            k++;
        }
        return k;
    }

    private static int getNum(int i, int k) {
        int num = 0;
        for (int j = 0; j < k; j++) {
            num = i%10;
            i /= 10;
        }
        return num;
    }
}
