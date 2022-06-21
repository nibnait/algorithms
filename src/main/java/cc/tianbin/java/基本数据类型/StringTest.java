package cc.tianbin.java.基本数据类型;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.nibnait.common.constants.CommonConstants;
import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by nibnait on 2020-01-06
 */
public class StringTest extends TestCase {

    @Test
    public void testJoin() {
        Set<String> set = new HashSet<>();
        String join = String.join(CommonConstants.COMMA_SEPARATOR, set);
        System.out.println(join);
    }

    @Test
    public void testStringUtils() {
        StringUtils.isEmpty("");
        StringUtils.isBlank("");
    }

    @Test
    public void testGetLong() {
        String str = "{\"bizNo\":\"111\"}";
        JSONObject result = JSON.parseObject(str);
        Long bizNo = result.getLong("bizNo");
        System.out.println(bizNo);
    }

    @Test
    public void testCase() {
        String str = "asdf";
        Character x = str.charAt(1);
        Stack<Integer> stack;
        System.out.println(x);
    }

    @Test
    public void testSubString() {
        String str = "123";
        System.out.println(str.substring(0, str.length() - 1));

        //output: 12
    }

    @Test
    public void testSplit() {
        String str = "1234,1243";
        System.out.println(str.split(",")[0]);
    }
}
