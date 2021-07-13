package common.util.compare;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import common.bo.compare.CompareBO;
import common.bo.compare.CompareResultDTO;
import common.datastruct.ListNode;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * Created by nibnait on 2020/11/20
 */
@Slf4j
public class CompareUtil {

    public static boolean compareListNode(ListNode left, ListNode right) {
        while (left != null && right != null && left.val == right.val) {
            left = left.next;
            right = right.next;
        }
        return left == right;
    }

    public static <T> boolean match(T actual, T expect) {
        return match(actual, expect, Sets.newHashSet());
    }

    public static <T> boolean match(T actual, T expect, Set<String> excludeFields) {
        CompareBO compareBO = CompareBO.newNoOptimizeCompareBO();
        compareBO.setExcludeFields(excludeFields);

        CompareResultDTO compareResultDTO = JsonUtils.diffStr(JSON.toJSONString(actual), JSON.toJSONString(expect), compareBO);
        if (!compareResultDTO.isMatch()) {
            log.info("差异字段: {}", JSON.toJSONString(compareResultDTO));
        }
        return compareResultDTO.isMatch();
    }
}
