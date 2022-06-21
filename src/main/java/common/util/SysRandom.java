package common.util;

import java.util.Random;

/**
 * 生成随机数模板
 */
public class SysRandom {

    private static Random random = new Random();   //伪随机数发生器

    private static double random(){
        return random.nextDouble();
    }

    /**
     * 随机返回 [0,n)之间的一个int值
     */
    public static int random(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Parameter N must be positive");
        }
        return random.nextInt(n);
    }

    /**
     * 随机返回 [a,b)之间的一个double值
     */
    public static double randomDouble(double a, double b) {
        if (a >= b) {
            throw new IllegalArgumentException("Invalid range");
        }
        return a + random() * (b-a);
    }

    /**
     * 随机返回 [a,b)之间的一个int值
     */
    public static int randomInt(int a, int b) {
        if (b <= a) {
            throw new IllegalArgumentException("Invalid range");
        }
        if ((long) b - a >= Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Invalid range");
        }
        return a + random(b - a);
    }

    /**
     * 返回一个[0,100)范围的随机int型数组
     */
    public static int[] randomArr() {
        return randomArr(random(50));
    }

    public static int[] randomArr(int arrLength) {
        int[] a = new int[arrLength];
        for (int i = 0; i < a.length; i++) {
            a[i] = random(100);
        }
        return a;
    }
}
