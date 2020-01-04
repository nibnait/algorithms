package algorithm_practice.SwordOffer.old;

/**
 * 题目：在数组中的两个数字如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组，求出这个数组中的逆序对的总数。
 * 例如在数组｛7, 5, 6, 4}中，
 * 一共存在 5 个逆序对，分别是（7, 6）、（7，5），(7, 4）、（6, 4）和（5, 4）。
 *
 * 【解】：归并排序的思想
 *  在把数组分成1个1个的数字之后，开始合并的过程中：
 *  - 两个{1个数的数组}开始合：
 *          只要第1个数比第2个数大，cnt++;
 *  - 两个{2个数的数组}开始合：
 *      p1：指向第一个数组的末尾
 *      p2：指向第二个数组的末尾
 *      p3：指向合并的{4个数的}大数组的末尾
 *          p1>p2： cnt+=2，aux[p3--] = aux[p1--],
 *          p1<=p2：cnt不变, aux[p3--] = aux[p2--],
 *  - 两个{4个数的数组}开始合：
 *      。。。
 *
 *
 * Created by nibnait on 2016/10/1.
 */
public class e36_数组中的逆序对 {

    public static void main(String[] args) {
//        int[] data = {1, 2, 3, 4, 7, 6, 5};
//        System.out.println(inversePairs(data)); // 3
//        int[] data2 = {6, 5, 4, 3, 2, 1};
//        System.out.println(inversePairs(data2)); //  15
//        int[] data3 = {1, 2, 3, 4, 5, 6};
//        System.out.println(inversePairs(data3)); // 0
//        int[] data4 = {1};
//        System.out.println(inversePairs(data4)); // 0
//        int[] data5 = {1, 2};
//        System.out.println(inversePairs(data5)); // 0
//        int[] data6 = {2, 1};
//        System.out.println(inversePairs(data6)); // 1
        int[] data7 = {1, 2, 1, 2, 1};
        System.out.println(inversePairs(data7)); // 3
    }

    private static int inversePairs(int[] arr) {
        if (arr==null || arr.length<=0){
            throw new IllegalArgumentException("args should not be null or empty");
        }
        int[] aux = new int[arr.length];
        System.arraycopy(arr, 0, aux, 0, arr.length);
        return inversePairsCore(arr, aux, 0, arr.length-1);
    }

    private static int inversePairsCore(int[] arr, int[] aux, int lo, int hi) {
        if (lo == hi){
            aux[lo] = arr[lo];
            return 0;
        }
        int len = (hi-lo)/2;
        int left = inversePairsCore(aux, arr, lo, lo+len);
        int right = inversePairsCore(aux, arr, lo+len+1, hi);

        int p1 = lo+len;
        int p2 = hi;
        int p3 = hi;
        int cnt = 0;
        while (p1>=lo && p2>=lo+len+1){
            if (arr[p1] > arr[p2]){
                cnt += p2-lo-len;
                aux[p3--] = arr[p1--];
            } else {
                aux[p3--] = arr[p2--];
            }
        }

        while (p1 >= lo){
            aux[p3--] = arr[p1--];
        }
        while (p2 >= lo+len+1){
            aux[p3--] = arr[p2--];
        }

        return cnt+left+right;
    }

}
