package zzzTest;

public class ExceptionTest {

    public static void main(String[] args) throws Exception {
        testThrow();
        System.out.println(1);
    }

    private static void testThrow() throws Exception {
        try {
            int x = 1/0;
        } catch (Exception e) {
            throw new Exception("数字错误");
        }
        System.out.println(2);
    }
}
