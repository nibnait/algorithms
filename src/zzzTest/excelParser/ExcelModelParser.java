package zzzTest.excelParser;

import zzzTest.excelParser.ExcelModel;
import zzzTest.excelParser.excelParser.ExcelParser;

import java.io.File;

public class ExcelModelParser extends ExcelParser<ExcelModel>{
    public ExcelModelParser(File file) {
        super(file);
    }
}
