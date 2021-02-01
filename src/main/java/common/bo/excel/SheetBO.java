package common.bo.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import java.util.List;

/**
 * Created by nibnait on 2021/01/21
 */
public class SheetBO {

    private HSSFSheet sheet;
    private Integer rowIndex;

    public SheetBO(HSSFSheet sheet) {
        this.sheet = sheet;
        rowIndex = 0;
    }

    public void appendRow(List<String> row) {
        HSSFRow hssfRow = sheet.createRow(rowIndex);
        HSSFCell hssfCell;
        rowIndex += 1;
        for (int i = 0; i < row.size(); i++) {
            hssfCell = hssfRow.createCell(i);
            hssfCell.setCellValue(row.get(i));
        }
    }

}
