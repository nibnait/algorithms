package algorithm_practice.SwordOffer.old;

/**
 * 输入数字n，按顺序打印出从1最大的n位十进制数。
 * 比如输入3，则打印出1、2、3、4、5、6...... 一直到最大的3位数即999。
 *
 * 【注意】
 * 大数问题：-->用数组
 *  法1：
 *      模拟大数相加，从0开始，每次加1，
 *      直到arr的最高位(倒数第n+1位)有进位时，即最大的n位数已经打印完毕。stop
 *
 *  法2：
 *      递归
 *      打印1 ~ n位的所有十进制数，其实就是从第1位开始设置0~9的全排列，直到递归将最后一个位置设置完毕，开始打印。
 *
 * Created by nibnait on 2016/9/21.
 */
public class c12_打印1到最大的n位数 {

    private static void Print1ToMaxOfNDigits2(int n) {
        if (n <= 0){
            throw new RuntimeException("位数N必须大于0");
        }
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }
        Print1ToMaxOfNDigits2(0, arr);
    }

    /**
     * 输入数字n，顺序打印1 ~ 最大的n位十进制数
     * @param i 当前处理的位置
     * @param arr
     */
    private static void Print1ToMaxOfNDigits2(int i, int[] arr) {
        if (i == arr.length){
            pringNumber(arr);
            return;
        }
        for (int j = 0; j <= 9; j++) {
            arr[i] = j;
            Print1ToMaxOfNDigits2(i+1, arr);
        }
    }

    private static void Print1ToMaxOfNDigits1(int n) {
        if (n <= 0){
            throw new IllegalArgumentException("Invalid args: 位数N必须大于0");
        }
        int[] arr = new int[n+1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }
        while (addOne(arr)){
            pringNumber(arr);
        }
    }

    /**
     * 对arr的最低位加1，return true;
     *      直到 加到最高位(index==0时)，如果还有进位，则return false;
     * @param arr
     * @return
     */
    private static boolean addOne(int[] arr){
        int carry = 1;  //进位值
        int index = arr.length-1; //最低位
        while (carry != 0 &&  index>0){
            arr[index] += carry;  //处理位的值+进位值
            carry = arr[index] / 10;
            arr[index] %= 10;
            index--;
        }
        if (carry>0 && index==0){   //看最高位是否进位
            return false;
        }
        return true;
    }

    /**
     * 从第一个非0位置开始打印数字
     * @param arr
     */
    private static void pringNumber(int[] arr){
        boolean isBeginning0 = true;
        for (int i = 0; i < arr.length; i++) {
            if (isBeginning0 && arr[i]!=0){
                isBeginning0 = false;
            }
            if (!isBeginning0){
                System.out.print(arr[i]);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        Print1ToMaxOfNDigits1(2);

        Print1ToMaxOfNDigits2(2);
    }

}
