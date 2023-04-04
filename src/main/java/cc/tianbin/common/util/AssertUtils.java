package cc.tianbin.common.util;

import cc.tianbin.common.datastruct.ListNode;
import io.github.nibnait.common.utils.DataUtils;

import java.util.List;

/**
 * @see org.junit.Assert
 * Created by nibnait on 2022/12/26
 */
public class AssertUtils {

    private AssertUtils() {
        throw new AssertionError("工具类不允许被实例化");
    }

    public static void compareListList(List<List<Integer>> expect, List<List<Integer>> actual, int[] nums) {
        if (!CompareUtils.compareListList(expect, actual)) {
            LogUtil.log("nums: {}", nums);
            log(expect, actual);
            throw new AssertionError();
        }
    }

    public static void compareListList(List<List<Integer>> expect, List<List<Integer>> actual) {
        if (!CompareUtils.compareListList(expect, actual)) {
            log(expect, actual);
            throw new AssertionError();
        }
    }

    public static void compareListNode(ListNode expect, ListNode actual) {
        if (!CompareUtils.compareSingleListNode(expect, actual)) {
            LogUtil.print("expect: ");
            SysOut.printLinkedNode(expect);
            LogUtil.print("actual: ");
            SysOut.printLinkedNode(actual);
            throw new AssertionError();
        }
    }

    public static void compareString(String expect, String actual) {
        if (!io.github.nibnait.common.utils.compare.CompareUtils.matchObject(expect, actual)) {
            log(expect, actual);
            throw new AssertionError();
        }
    }

    public static void compareInteger(Integer expect, Integer actual) {
        if (expect.intValue() != actual.intValue()) {
            log(expect, actual);
            throw new AssertionError();
        }
    }

    public static void compareStringList(List<String> expect, List<String> actual) {
        if (actual.size() != expect.size()) {
            log(expect, actual);
            throw new AssertionError();
        }

        try {
            for (int i = 0; i < expect.size(); i++) {
                compareString(expect.get(i), actual.get(i));
            }
        } catch (Throwable e) {
            log(expect, actual);
            throw new AssertionError();
        }
    }

    public static void compareIntegerList(List<Integer> expect, List<Integer> actual) {
        if (actual.size() != expect.size()) {
            log(expect, actual);
            throw new AssertionError();
        }

        try {
            for (int i = 0; i < expect.size(); i++) {
                compareInteger(expect.get(i), actual.get(i));
            }
        } catch (Throwable e) {
            log(expect, actual);
            throw new AssertionError();
        }
    }

    public static void compareIntegerArray(int[] expect, int[] actual) {
        if (actual.length != expect.length) {
            log(expect, actual);
            throw new AssertionError();
        }

        try {
            for (int i = 0; i < expect.length; i++) {
                compareInteger(expect[i], actual[i]);
            }
        } catch (Throwable e) {
            log(expect, actual);
            throw new AssertionError();
        }
    }

    private static void log(Object expect, Object actual) {
        LogUtil.log("expect: {}", DataUtils.toJsonStringArray(expect));
        LogUtil.log("actual: {}", DataUtils.toJsonStringArray(actual));
    }
}
