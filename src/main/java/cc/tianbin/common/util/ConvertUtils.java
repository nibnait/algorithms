package cc.tianbin.common.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by nibnait on 2023/03/26
 */
public class ConvertUtils {

    private ConvertUtils() {
    }

    public static List<Integer> convert2IntegerList(int[] arr) {
        return Arrays.stream(arr)
                .boxed()
                .collect(Collectors.toList());
    }
}
