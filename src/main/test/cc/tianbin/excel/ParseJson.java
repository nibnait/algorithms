package cc.tianbin.excel;

import com.google.common.collect.Lists;
import io.github.nibnait.common.bo.excel.SheetBO;
import io.github.nibnait.common.bo.excel.WorkBookBO;
import io.github.nibnait.common.utils.DataUtils;

import java.util.List;

/**
 * Created by nibnait on 2022/01/18
 */
public class ParseJson {

    public static void main(String[] args) {
        String str = "[{\"skuId\":1000098016,\"stock\":239,\"frozenStock\":583,\"outStock\":0,\"isolateStock\":0,\"purchaseStock\":840,\"warehouseIds\":\"\",\"warnStock\":1,\"frozenUndeliveryStock\":0,\"ctime\":1623744663000,\"mtime\":1642147764000,\"boxNum\":0,\"onsaleBoxNum\":0}]";
        List<StockDTO> stockDTOS = DataUtils.parseArray(str, StockDTO.class);

        WorkBookBO workBookBO = new WorkBookBO();
        SheetBO sheetBO = workBookBO.getSheetList().get(0);

        for (StockDTO stockDTO : stockDTOS) {
            sheetBO.appendRow(Lists.newArrayList(stockDTO.getSkuId()+"", stockDTO.getStock()+""));
        }

        workBookBO.writeToDefaultLocalPath("stock");

    }

}
