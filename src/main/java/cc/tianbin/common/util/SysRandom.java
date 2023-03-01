package cc.tianbin.common.util;

import io.github.nibnait.common.utils.DataUtils;

import java.util.Random;

/**
 * 生成随机数模板
 */
public class SysRandom {

    private static final Random random = new Random();   //伪随机数发生器

    private static final Integer RANDOM_MAX_VALUE = 1000;
    private static final String INVALID_RANGE = "Invalid range, a: {}, b: {}";

    private static double generate() {
        return Math.random() * RANDOM_MAX_VALUE;
    }

    /**
     * 随机返回 [0,n)之间的一个int值
     */
    public static int generateNaturalNum(int n) {
        if (n < 0) {
            throw new IllegalArgumentException(DataUtils.format("Parameter {} must be positive", n));
        }
        if (n == 0) {
            return 0;
        }
        return random.nextInt(n);
    }

    public static int generatePositiveInt(int n) {
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
    public static double generateDouble(double a, double b) {
        if (a >= b) {
            throw new IllegalArgumentException(DataUtils.format(INVALID_RANGE, a, b));
        }
        return a + generate() * (b - a);
    }

    /**
     * 随机返回 [a,b)之间的一个int值
     */
    public static int generateInt(int a, int b) {
        if (b <= a) {
            throw new IllegalArgumentException(DataUtils.format(INVALID_RANGE, a, b));
        }
        if ((long) b - a >= Integer.MAX_VALUE) {
            throw new IllegalArgumentException(DataUtils.format(INVALID_RANGE, a, b));
        }
        return a + generateNaturalNum(b - a);
    }

    /**
     * 返回一个[-100,100)范围的随机int型数组
     */
    public static int[] generateArr() {
        return generateArr(generateNaturalNum(50));
    }

    public static int[] generateArr(int arrLength) {
        int[] a = new int[arrLength];
        for (int i = 0; i < a.length; i++) {
            a[i] = generateInt(-100, 100);
        }
        return a;
    }

    /**
     * 返回一个[0,100)范围的随机int型数组
     */
    public static int[] generateArrNaturalNum() {
        return generateArrNaturalNum(generateNaturalNum(50));
    }

    public static int[] randomArrNaturalNumRandomLength(int randomLength) {
        return generateArrNaturalNum(generateNaturalNum(randomLength));
    }

    public static int[] generateArrNaturalNum(int maxLen) {
        int arrLen = generateNaturalNum(maxLen);
        int[] a = new int[arrLen];
        for (int i = 0; i < a.length; i++) {
            a[i] = generateInt(0, 100);
        }
        return a;
    }

    public static int[] generateArrPositiveInteger(int maxLen) {
        int arrLen = generateNaturalNum(maxLen);
        int[] a = new int[arrLen];
        for (int i = 0; i < a.length; i++) {
            a[i] = generateInt(1, 100);
        }
        return a;
    }

    public static int[] generateArrPositiveIntegerNoRepeat(int maxLen) {
        int maxValue = 100;
        int arrLen = generateNaturalNum(maxLen);
        int[] arr = new int[arrLen];
        boolean[] has = new boolean[maxValue];
        for (int i = 0; i < arrLen; i++) {
            do {
                arr[i] = generateInt(1, maxValue);
            } while (has[arr[i]]);
            has[arr[i]] = true;
        }
        return arr;
    }

    public static int[] generateArrNaturalNum(int maxLen, int maxValue) {
        int arrLen = generateNaturalNum(maxLen);
        int[] arr = new int[arrLen];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = generateInt(0, maxValue);
        }
        return arr;
    }

    /**
     * 返回一个 0 1 矩阵
     */
    public static char[][] generateMatrixZeroOneChar(int row, int col) {
        if (row < 0 || col < 0) {
            return null;
        }
        char[][] board = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = Math.random() < 0.5 ? '1' : '0';
            }
        }
        return board;
    }

    public static int[][] generateMatrixNaturalNum(int row, int col) {
        if (row < 0 || col < 0) {
            return null;
        }
        int[][] result = new int[row][col];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = generateNaturalNum(100);
            }
        }
        return result;
    }

    /**
     * 生成一个随机长度的 由 123456789 组成String
     */
    public static String generateStringNaturalNum(int randomLen) {
        int length = generateNaturalNum(randomLen);
        char[] str = new char[length];
        for (int i = 0; i < length; i++) {
            str[i] = (char) (generateNaturalNum(10) + '0');
        }
        return String.valueOf(str);
    }

    /**
     * 生成一个随机长度的 由 abc....xyz 组成String
     */
    public static String generateStringLowercaseLetters(int randomLen) {
        int length = generateNaturalNum(randomLen);
        char[] str = new char[length];
        for (int i = 0; i < length; i++) {
            str[i] = (char) (generateNaturalNum(27) + 'a');
        }
        return String.valueOf(str);
    }
}
