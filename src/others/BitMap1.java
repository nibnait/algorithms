package others;

/**
 * Created by nibnait on 2016/8/9.
 */
public class BitMap1 {
    public static void main(String[] args) {
        int i;
        int[] b= new int[100];
        int[] a={1,2,3,4,4};

        for(i=0;i<5;){
            if(b[a[i]]==1){
                System.out.print("数组含有重复值");
                break;
            }
            else{
                b[a[i]]=1;
                i++;
            }
        }

        if(i==5){
            System.out.print("数组含没有重复值");
        }
    }
}
