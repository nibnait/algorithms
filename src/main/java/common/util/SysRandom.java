package common.util;

import io.github.nibnait.common.utils.DataUtils;

import java.util.Random;

/**
 * 生成随机数模板
 */
public class SysRandom {

    private static final Random random = new Random();   //伪随机数发生器

    private static final Integer RANDOM_MAX_VALUE = 1000;
    private static final String INVALID_RANGE = "Invalid range, a: {}, b: {}";

    private static double random() {
        return Math.random() * RANDOM_MAX_VALUE;
    }

    /**
     * 随机返回 [0,n)之间的一个int值
     */
    public static int random(int n) {
        if (n < 0) {
            throw new IllegalArgumentException(DataUtils.format("Parameter {} must be positive", n));
        }
        if (n == 0) {
            return 0;
        }
        return random.nextInt(n);
    }

    /**
     * 随机返回 [a,b)之间的一个double值
     */
    public static double randomDouble(double a, double b) {
        if (a >= b) {
            throw new IllegalArgumentException(DataUtils.format(INVALID_RANGE, a, b));
        }
        return a + random() * (b - a);
    }

    /**
     * 随机返回 [a,b)之间的一个int值
     */
    public static int randomInt(int a, int b) {
        if (b <= a) {
            throw new IllegalArgumentException(DataUtils.format(INVALID_RANGE, a, b));
        }
        if ((long) b - a >= Integer.MAX_VALUE) {
            throw new IllegalArgumentException(DataUtils.format(INVALID_RANGE, a, b));
        }
        return a + random(b - a);
    }

    /**
     * 返回一个[-100,100)范围的随机int型数组
     */
    public static int[] randomArr() {
        return randomArr(random(50));
    }

    public static int[] randomArr(int arrLength) {
        int[] a = new int[arrLength];
        for (int i = 0; i < a.length; i++) {
            a[i] = randomInt(-100, 100);
        }
        return a;
    }

    /**
     * 返回一个[0,100)范围的随机int型数组
     */
    public static int[] randomArrNaturalNum() {
        return randomArrNaturalNum(random(50));
    }

    public static int[] randomArrNaturalNum(int arrLength) {
        int[] a = new int[arrLength];
        for (int i = 0; i < a.length; i++) {
            a[i] = randomInt(0, 100);
        }
        return a;
    }

    public static int[] randomArrNaturalNum(int arrLength, int maxValue) {
        int[] a = new int[arrLength];
        for (int i = 0; i < a.length; i++) {
            a[i] = randomInt(0, maxValue);
        }
        return a;
    }
}
