package Standard;

/**
 * Created by nibnait on 2016/8/5.
 */
public class std {

    public static void exch(int[] a, int i, int j){

        int temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
/*        Algorithms_4thEdition[i] ^= Algorithms_4thEdition[j];
        Algorithms_4thEdition[j] ^= Algorithms_4thEdition[i];
        Algorithms_4thEdition[i] ^= Algorithms_4thEdition[j];*/
    }

    public static void swap(int a, int b){
        int t;
        t = a; a = b; b = t;
    }

}
