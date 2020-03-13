package localtest.simpleTest;

import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/*

Created by nibnait on 2020-02-25
 */
public class JsonTest extends TestCase {

    @Test
    public void testCase() {

        String attribute_json = "{\"active_at\":1582566155,\"user_name\":\"32a9bd7c2\"}";
        JSONObject jsonObject = JSONObject.parseObject(attribute_json);
        System.out.println(jsonObject);
        String is_from_tp_new_retail = jsonObject.getString("is_from_tp_new_retail");
        System.out.println(is_from_tp_new_retail);

        if (StringUtils.isNotBlank(attribute_json) && jsonObject.getString("is_from_tp_new_retail") == "1") {
            System.out.println("asdfsafsafsa");
        }
    }


}