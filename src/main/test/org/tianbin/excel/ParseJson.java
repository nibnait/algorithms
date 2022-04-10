package org.tianbin.excel;

import com.google.common.collect.Lists;
import common.bo.excel.SheetBO;
import common.bo.excel.WorkBookBO;
import common.util.DataUtils;
import jxl.write.WriteException;

import java.io.IOException;
import java.util.List;

/**
 * Created by nibnait on 2022/01/18
 */
public class ParseJson {

    public static void main(String[] args) throws IOException, WriteException {
        String str = "[{\"skuId\":1000098016,\"stock\":239,\"frozenStock\":583,\"outStock\":0,\"isolateStock\":0,\"purchaseStock\":840,\"warehouseIds\":\"\",\"warnStock\":1,\"frozenUndeliveryStock\":0,\"ctime\":1623744663000,\"mtime\":1642147764000,\"boxNum\":0,\"onsaleBoxNum\":0}]";
        List<StockDTO> stockDTOS = DataUtils.parseArray(str, StockDTO.class);

        WorkBookBO workBookBO = new WorkBookBO();
        SheetBO sheetBO = workBookBO.getSheetList().get(0);

        for (StockDTO stockDTO : stockDTOS) {
            sheetBO.appendRow(Lists.newArrayList(stockDTO.getSkuId()+"", stockDTO.getStock()+""));
        }

        workBookBO.writeToFile("stock");

    }

}
