package common.util;

import io.github.nibnait.common.utils.DataUtils;

import java.util.function.Function;

/**
 * Created by nibnait on 2022/12/26
 */
public class LogUtil {

    private LogUtil() {
        throw new AssertionError("工具类不允许被实例化");
    }

    public static void printf(String format, Object... args) {
        System.out.println(String.format(format, args));
    }

    public static void print(Object obj) {
        System.out.print(obj);
    }

    public static <T, R> void println(Object obj, Function<T, R> function, T t) {
        System.out.println(obj);
        function.apply(t);
    }

    public static void log(String format, Object... args) {
        System.out.println(DataUtils.format(format, args));
    }

}
