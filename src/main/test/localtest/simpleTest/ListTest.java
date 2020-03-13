package localtest.simpleTest;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*

Created by nibnait on 2020-01-09
 */
public class ListTest extends TestCase {

    private static final String DEFAULT_CLOSE_VALUE = "0";
    private static final String DEFAULT_OPEN_VALUE = "-1";

    @Test
    public void testCase() {
        System.out.println(inGray("[0]", "1"));
        System.out.println(inGray("[-1]", "1"));
        System.out.println(inGray("[1]", "1"));
    }

    public static boolean inGray(String configValue, String cityId) {
        List<String> configList = new ArrayList<>();
        try {
            configList = JSONObject.parseObject(configValue, ArrayList.class);
            configList = Lists.transform(configList, Functions.toStringFunction());
        } catch (Exception e) {
//            LoggerUtil.error(e, log, "HuskarUtil.inGray error, configValue:%s", configValue);
        }
        if (configList.contains(DEFAULT_CLOSE_VALUE)) {
            return false;
        }
        if (configList.contains(DEFAULT_OPEN_VALUE)) {
            return true;
        }
        return configList.contains(cityId);
    }


}