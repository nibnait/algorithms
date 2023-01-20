package common.util;

import common.datastruct.ListNode;
import io.github.nibnait.common.utils.DataUtils;
import io.github.nibnait.common.utils.compare.CompareUtils;

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
        if (!common.util.CompareUtils.compareListList(expect, actual)) {
            LogUtil.log("nums: {}", nums);
            LogUtil.log("except: {}", DataUtils.toJsonStringArray(expect));
            LogUtil.log("actual: {}", DataUtils.toJsonStringArray(actual));
            throw new AssertionError();
        }
    }

    public static void compareListNode(ListNode expect, ListNode actual) {
        if (!common.util.CompareUtils.compareSingleListNode(expect, actual)) {
            LogUtil.print("except: "); SysOut.printLinkedNode(expect);
            LogUtil.print("actual: "); SysOut.printLinkedNode(actual);
            throw new AssertionError();
        }
    }

    public static void compareString(String expect, String actual) {
        if (!CompareUtils.matchObject(expect, actual)) {
            LogUtil.log("except: {}", DataUtils.toJsonStringArray(expect));
            LogUtil.log("actual: {}", DataUtils.toJsonStringArray(actual));
            throw new AssertionError();
        }
    }

    public static void compareStringList(List<String> expect, List<String> actual) {
        if (actual.size() != expect.size()) {
            LogUtil.log("except: {}", DataUtils.toJsonStringArray(expect));
            LogUtil.log("actual: {}", DataUtils.toJsonStringArray(actual));
            throw new AssertionError();
        }

        for (int i = 0; i < expect.size(); i++) {
            compareString(expect.get(i), actual.get(i));
        }
    }
}
