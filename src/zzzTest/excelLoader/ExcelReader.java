package zzzTest.excelLoader;


import zzzTest.excelLoader.test.ExcelModel;

import java.io.File;

public class ExcelReader extends ExcelLoader<ExcelModel>{

    public ExcelReader(File file) {
        super(file);
    }

}
