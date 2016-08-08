package others;

/**
 * Created by nibnait on 2016/8/8.
 */
public class Matrix_Print1 {

    public static void main(String[] args) {
        int[][] arr = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
        int[] x = arrayScan(arr, 5);
        for (int i = 0; i < 25; i++) {
            System.out.print(x[i]+"  ");
        }
    }

    private static int[] arrayScan(int[][] arr, int n) {

        int[] a = new int[n*n];
        int cnt = n*(n+1)/2;
        int cntt = cnt;
        int x = 0;
        int i = 0;
        for (int k = arr.length-1; k >=0; k--) {
            i = k;
            for (int j = 0; j<arr.length-x ; i--,j++) {
                a[--cnt] = arr[i][n-1-j];
            }
            x++;
        }


        x=0;
        for (int k = 1; k < arr.length; k++) {
            i = k;
            for (int j = arr.length-1; j >x && i<a.length; i++, j--) {
                a[cntt++] = arr[i][n-1-j];
            }
            x++;
        }
        return a;
    }
}

