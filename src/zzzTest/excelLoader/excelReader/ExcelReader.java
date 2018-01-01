package zzzTest.excelLoader.excelReader;


import zzzTest.excelLoader.models.FDA.complaint;
import zzzTest.excelLoader.models.FDA.investigation;
import zzzTest.excelLoader.models.FDA.shfda_increaded_data;
import zzzTest.excelLoader.models.auditRule.FoodMall;
import zzzTest.excelLoader.models.auditRule.ProtectBrand;

import java.io.File;

public class ExcelReader extends ExcelLoader<ProtectBrand>{

    public ExcelReader(File file) {
        super(file);
    }

}
