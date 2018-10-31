package zzzTest.excelParser;

import lombok.Data;
import zzzTest.excelParser.excelParser.ExcelColumnTitle;

@Data
public class ExcelModel {

    @ExcelColumnTitle("城市")
    private String cityName;
}
