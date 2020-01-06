package algorithm_practice.SwordOffer.old;

/**
 * 【题目】
     * 把一个数组最开始的若干个元素搬到数组的末尾， 我们称之数组的旋转。
     * 输入一个递增排序的数组的一个旋转， 输出旋转数组的最小元素。
     * 例如数组{ 3, 4, 5, 1, 2} 为{ l, 2, 3, 4, 5}的一个旋转，该数组的最小值为 1。
 *
 * Created by nibnait on 2016/9/20.
 */
public class b08_旋转数组中二分查找最小数字 {

    private static int MinNumberInRotatedArray(int[] arr){
        if (arr==null || arr.length==0){
            throw new IllegalArgumentException("args should not be null or empty");
        }

        int lo = 0;
        int hi = arr.length - 1;
        int mid = 0;
        while (arr[lo] >= arr[hi]){
            if (hi-lo == 1){
                return arr[hi]; //当lo和hi挨着的时候，则hi（后面那个数）一定就是最小值了
            }
            mid = (lo+hi)>>1;  //防止溢出
            if (arr[mid]==arr[lo] && arr[mid]==arr[hi]){
                //1,0,1,1,1
                //1,1,1,0,1
                //此时就要遍历整个arr[lo...hi]了，
                return MinInOrder(arr, lo, hi);
            }
            if (arr[mid] >= arr[lo]){
                lo = mid;
            } else if (arr[mid] < arr[hi]) {
                hi = mid;
            }
        }
        return arr[mid];
    }

    private static int MinInOrder(int[] arr, int lo, int hi) {
        int min = arr[lo];
        for (int i = lo; i < hi; i++) {
            min = arr[i]>min? min: arr[i];
        }
        return min;
    }

    public static void main(String[] args) {
        // 典型输入，单调升序的数组的一个旋转
        int[] array1 = {3, 4, 5, 1, 2};
        System.out.println(MinNumberInRotatedArray(array1));
        // 有重复数字，并且重复的数字刚好的最小的数字
        int[] array2 = {3, 4, 5, 1, 1, 2};
        System.out.println(MinNumberInRotatedArray(array2));
        // 有重复数字，但重复的数字不是第一个数字和最后一个数字
        int[] array3 = {3, 4, 5, 1, 2, 2};
        System.out.println(MinNumberInRotatedArray(array3));
        // 有重复的数字，并且重复的数字刚好是第一个数字和最后一个数字
        int[] array4 = {1, 0, 1, 1, 1};
        System.out.println(MinNumberInRotatedArray(array4));
        // 单调升序数组，旋转0个元素，也就是单调升序数组本身
        int[] array5 = {1, 2, 3, 4, 5};
        System.out.println(MinNumberInRotatedArray(array5));
        // 数组中只有一个数字
        int[] array6 = {2};
        System.out.println(MinNumberInRotatedArray(array6));
        // 数组中数字都相同
        int[] array7 = {1, 1, 1, 1, 1, 1, 1};
        System.out.println(MinNumberInRotatedArray(array7));
        // 输入NULL
//        System.out.println(MinNumberInRotatedArray(null));
    }

}
