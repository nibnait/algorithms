package Standard;

/**
 * Created by nibnait on 2016/8/5.
 */
public class Std {

    public static void swap(int a[], int i, int j){

        int temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
/*        a[i] ^= a[j];
        a[j] ^= a[i];
        a[i] ^= a[j];*/
    }
}
