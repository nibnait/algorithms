package common.util;

import lombok.Data;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by nibnait on 2022/03/10
 */
public class ExcelReadUtilsTest {

    @Data
    public static class NFT {
        private String sourceAddress;
        private String linkId;
        private Integer stock;
        private String specKey1;
        private String specKey2;
        private String specKey3;
    }

    @Test
    public void testReadByModule() throws FileNotFoundException {
        // 获取文件流
        String path = "/Users/nibnait/Desktop/";
        FileInputStream inputStream = new FileInputStream(path + "nft.xlsx");

        List<NFT> read = ExcelReadUtils.read(inputStream, NFT.class);
        System.out.println(DataUtils.toJsonStringArray(read));
    }

    @Test
    public void testReadByWorkbook() throws FileNotFoundException {
        // 获取文件流
        String path = "/Users/nibnait/Desktop/";
        FileInputStream inputStream = new FileInputStream(path + "nft.xlsx");

        Workbook workbook = ExcelReadUtils.convert2Workbook(inputStream);

        // 获取标题内容
        Sheet sheet = workbook.getSheetAt(0);
        List<String> title = ExcelReadUtils.getTitle(sheet);
        System.out.println(title);

        // 获取表中的内容
        int rowCount = sheet.getPhysicalNumberOfRows();
        for (int rowNum = 1; rowNum < rowCount; rowNum++) {
            List<String> rowData = ExcelReadUtils.getRowData(sheet, rowNum);
            System.out.println(rowData);
        }
    }


}
