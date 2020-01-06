package common.util;

/**
 * Created by nibnait on 2016/8/5.
 */
public class SwapUtil {

    public static void swap(String[] a, int i, int j){
        String temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void swap(int[] a, int i, int j){

        int temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
/*        a[i] ^= a[j];
        a[j] ^= a[i];
        a[i] ^= a[j];*/
    }

    public static void swap(char[] a, int i, int j){
        char tmp;
        tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

}
