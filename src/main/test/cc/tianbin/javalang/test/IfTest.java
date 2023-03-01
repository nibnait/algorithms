package cc.tianbin.javalang.test;

import com.alibaba.fastjson.JSON;
import io.github.nibnait.common.constants.CommonConstants;
import io.github.nibnait.common.utils.DataUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by nibnait on 2022/09/08
 */
@Slf4j
public class IfTest {













    @Test
    public void test01() {
        String extraData = "{\"itemsId\":1}";
        ItemsExtDataVO itemsExtDataVO = ItemsExtDataVO.getExtVOByExtStr(extraData);

        long operateTime = itemsExtDataVO != null ? itemsExtDataVO.getOperateTime() : 0L;
        System.out.println(operateTime);
    }
















    @Data
    public static class ItemsExtDataVO {

        /**
         * 商品id
         */
        private Long itemsId;

        /**
         * 商品最近操作时间（单位 毫秒）：
         *  - 商详点击【发布】商品后面审核流每一步的变动
         *  - 单独修改库存和价格
         *  - 上下架
         *  - 撤回提审操作
         * 【目前只有商家平台列表页会用到专用】
         */
        private Long operateTime;

        public static ItemsExtDataVO getExtVOByExtStr(String ext) {
            if (ext == null || "".equals(ext) || CommonConstants.NULL_STRING.equalsIgnoreCase(ext)) {
                return null;
            }
            ItemsExtDataVO result = null;
            try {
                result = JSON.parseObject(ext, ItemsExtDataVO.class);
            } catch (Exception e) {
                log.error(DataUtils.format("ItemsExtDataVO.getExtVOByStr error, extraDataStr: {} ", ext), e);
            }
            return result;
        }
    }


}
