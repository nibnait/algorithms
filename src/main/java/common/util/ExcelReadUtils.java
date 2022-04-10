package common.util;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nibnait on 2022/03/10
 */
@Slf4j
public class ExcelReadUtils {

    /**
     * 使用 easyexcel 读
     */
    public static <T> List<T> read(InputStream in, Class<T> clazz) {
        List<T> list = new ArrayList<>();

        EasyExcelFactory.read(in, clazz, new AnalysisEventListener<T>() {
            @Override
            public void invoke(T data, AnalysisContext context) {
                list.add(data);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {}
        }).sheet().doRead();

        return list;
    }

    /**
     * 将文件的 inputStream 转成 workbook
     */
    public static Workbook convert2Workbook(InputStream inputStream) {
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(inputStream); // 解析2007版本
        } catch (Exception ex) {
            try {
                POIFSFileSystem pfs = new POIFSFileSystem(inputStream); // 解析2003版本
                workbook = new HSSFWorkbook(pfs);
            } catch (IOException e) {
                log.error("ExcelReadUtils convert2Workbook error ", e);
            }
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error("ExcelReadUtils convert2Workbook inputStream.close error ", e);
            }
        }
        return workbook;
    }

    /**
     * 读第1行数据
     */
    public static List<String> getTitle(Sheet sheet) {
        return getRowData(sheet, 0);
    }

    /**
     * 读指定行号
     *
     * @param sheet excel
     * @param rowNo 行号
     */
    public static List<String> getRowData(Sheet sheet, int rowNo) {
        List<String> rowData = Lists.newArrayList();

        Row row = sheet.getRow(rowNo);
        if (row == null) {
            return rowData;
        }

        // 读取列
        for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);

            if (cell == null) {
                rowData.add("");
                continue;
            }

            // 匹配列的数据类型
            String cellValue = "";
            CellType cellType = cell.getCellTypeEnum();
            switch (cellType) {
                case STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                case BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        cellValue = DateTimeConvertUtils.date2String(date);
                    } else {
                        cellValue = BigDecimal.valueOf(cell.getNumericCellValue()).stripTrailingZeros().toString();
                    }
                    break;
                default:
                    break;
            }
            rowData.add(cellValue);
        }

        return rowData;
    }

}
