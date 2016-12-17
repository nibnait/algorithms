package zzzTest;

/**
 * Created by nibnait on 2016/9/20.
 */
public class ReturnTest {

    public static void main(String[] args) {
        System.out.println(getNumber(0));
        System.out.println(getNumber(1));
        System.out.println(getNumber(2));
        System.out.println(getNumber(4));

//        System.out.println(getValue());
    }

    private static int getNumber(int num) {
        int result = Integer.MAX_VALUE;
        try{
            result = 2/num;
        } catch (Exception e){
            return 0;
        }
//        return result;
        finally {
                return -1;
        }
    }

    public static int getValue() {
        try{
            return 0;
        } finally {
            return 1;
        }
    }
}