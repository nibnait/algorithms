package common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtils {

    private static final BigDecimal BIGDECIMAL_ONE_HUNDRED = BigDecimal.valueOf(100);
    // 除法，scale 保留2位小数
    private static final int SCALE = 2;
    // 除法，一律向下取整（舍弃scale后面的数字）
    private static final RoundingMode ROUNDING_MODE = RoundingMode.DOWN;

    /**
     * 非空 && >=0
     */
    public static boolean notNullAndGreaterThanOrEqualsZero(Integer o) {
        if (o == null) {
            return false;
        }
        return greaterThanOrEqualsZero(o);
    }

    public static boolean notNullAndGreaterThanOrEqualsZero(Long o) {
        if (o == null) {
            return false;
        }
        return greaterThanOrEqualsZero(o);
    }

    public static boolean notNullAndGreaterThanOrEqualsZero(BigDecimal o) {
        if (o == null) {
            return false;
        }
        return greaterThanOrEqualsZero(o);
    }

    /**
     * 非空 && >0
     */
    public static boolean notNullAndGreaterThanZero(Integer o) {
        return !nullOrLessThanOrEqualsZero(o);
    }

    public static boolean notNullAndGreaterThanZero(Long o) {
        return !nullOrLessThanOrEqualsZero(o);
    }

    public static boolean notNullAndGreaterThanZero(BigDecimal o) {
        return !nullOrLessThanOrEqualsZero(o);
    }

    /**
     * 空 || <= 0
     */
    public static boolean nullOrLessThanOrEqualsZero(Integer o) {
        if (o == null) {
            return true;
        }
        return lessThanOrEqualsZero(o);
    }

    public static boolean nullOrLessThanOrEqualsZero(Long o) {
        if (o == null) {
            return true;
        }
        return lessThanOrEqualsZero(o);
    }

    public static boolean nullOrLessThanOrEqualsZero(BigDecimal o) {
        if (o == null) {
            return true;
        }
        return lessThanOrEqualsZero(o);
    }

    public static boolean nullOrLessThanZero(Integer o) {
        if (o == null) {
            return true;
        }
        return lessThanZero(o);
    }

    public static boolean nullOrLessThanZero(Long o) {
        if (o == null) {
            return true;
        }
        return lessThanZero(o);
    }

    public static boolean nullOrLessThanZero(BigDecimal o) {
        if (o == null) {
            return true;
        }
        return lessThanZero(o);
    }

    /******** 基础方法：<= 0 ******/

    public static boolean lessThanOrEqualsZero(Integer o) {
        return lessThanOrEquals(o, 0);
    }

    public static boolean lessThanOrEqualsZero(Long o) {
        return lessThanOrEquals(o, 0L);
    }

    public static boolean lessThanOrEqualsZero(BigDecimal o) {
        return lessThanOrEquals(o, BigDecimal.ZERO);
    }

    public static <T extends Comparable<T>> boolean lessThanOrEquals(T o, T num) {
        if (num == null || o == null) {
            return false;
        }
        return o.compareTo(num) <= 0;
    }

    /******** 基础方法：< 0 ******/
    public static boolean lessThanZero(Integer o) {
        return lessThan(o, 0);
    }

    public static boolean lessThanZero(Long o) {
        return lessThan(o, 0L);
    }

    public static boolean lessThanZero(BigDecimal o) {
        return lessThan(o, BigDecimal.ZERO);
    }

    public static <T extends Comparable<T>> boolean lessThan(T o, T num) {
        if (num == null || o == null) {
            return false;
        }
        return o.compareTo(num) < 0;
    }

    /******** 基础方法：>= 0 ******/

    public static boolean greaterThanOrEqualsZero(Integer o) {
        return greaterThanOrEquals(o, 0);
    }

    public static boolean greaterThanOrEqualsZero(Long o) {
        return greaterThanOrEquals(o, 0L);
    }

    public static boolean greaterThanOrEqualsZero(BigDecimal o) {
        return greaterThanOrEquals(o, BigDecimal.ZERO);
    }

    public static <T extends Comparable<T>> boolean greaterThanOrEquals(T o, T num) {
        if (num == null || o == null) {
            return false;
        }
        return o.compareTo(num) >= 0;
    }

    /******** 基础方法：> 0 ******/

    public static boolean greaterThanZero(Integer o) {
        return greaterThan(o, 0);
    }

    public static boolean greaterThanZero(Long o) {
        return greaterThan(o, 0L);
    }

    public static boolean greaterThanZero(BigDecimal o) {
        return greaterThan(o, BigDecimal.ZERO);
    }

    public static <T extends Comparable<T>> boolean greaterThan(T o, T num) {
        if (num == null || o == null) {
            return false;
        }
        return o.compareTo(num) > 0;
    }

    /****************** 价格 单位转换 *******************/
    /**
     * 单位分 -> 单位元
     */
    public static BigDecimal getPriceYuan(Integer fen) {
        return greaterThanZero(fen)
                ? divide100(fen)
                : null;
    }

    public static BigDecimal getPriceYuan(Long fen) {
        return greaterThanZero(fen)
                ? divide100(fen)
                : null;
    }

    public static BigDecimal getPriceYuan(BigDecimal fen) {
        return greaterThanZero(fen)
                ? divide100(fen)
                : null;
    }

    /******** 基础方法：÷ 100 ******/
    public static BigDecimal divide100(Integer fen) {
        return divide(BigDecimal.valueOf(fen), BIGDECIMAL_ONE_HUNDRED);
    }

    public static BigDecimal divide100(Long fen) {
        return divide(BigDecimal.valueOf(fen), BIGDECIMAL_ONE_HUNDRED);
    }

    public static BigDecimal divide100(BigDecimal fen) {
        return divide(fen, BIGDECIMAL_ONE_HUNDRED);
    }

    /**
     * 除法，默认保留两位小数，向下取整
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return 商
     */
    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor) {
        if (dividend == null || divisor == null) {
            return BigDecimal.ZERO;
        }
        return dividend.divide(divisor, SCALE, ROUNDING_MODE);
    }

    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor, int scale, RoundingMode roundingMode) {
        if (dividend == null || divisor == null) {
            return BigDecimal.ZERO;
        }
        return dividend.divide(divisor, scale, roundingMode);
    }

    /**
     * 单位分 -> 单位元
     */
    public static BigDecimal getPriceFen(BigDecimal yuan) {
        if (yuan == null) {
            return null;
        }
        return yuan.multiply(BIGDECIMAL_ONE_HUNDRED);
    }

    public static BigDecimal getPriceFen(Double yuan) {
        if (yuan == null) {
            return null;
        }
        return BigDecimal.valueOf(yuan).multiply(BIGDECIMAL_ONE_HUNDRED);
    }

}
