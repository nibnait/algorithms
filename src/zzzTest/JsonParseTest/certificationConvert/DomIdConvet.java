package zzzTest.JsonParseTest.certificationConvert;

import com.sun.java.browser.plugin2.DOM;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import zzzTest.JsonParseTest.JsonHelperTest.JsonHelper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DomIdConvet {

    public static void main(String[] args) throws FileNotFoundException {
        Path filePath = Paths.get("/Users/nibnait/Desktop/h.txt");
        List<DomIdDto> domIdDtoList = new ArrayList<>();
        try {
            String jsonString = new String(Files.readAllBytes(filePath));
            domIdDtoList = JsonHelper.toJsonObject(jsonString, ArrayList.class, DomIdDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("资质信息");
        createHeadRow(sheet);
        int rowNum = 1;
        StringBuilder sb = new StringBuilder();
        for (DomIdDto domIdDto: domIdDtoList) {
            sb.append(domIdDto.getDom_id()+",");
            XSSFRow row = sheet.createRow(rowNum++);
            int columNum = 0;
            row.createCell(columNum++).setCellValue(domIdDto.getDom_id());
        }
        creatExcelFile(workbook);
        System.out.println(rowNum);
        System.out.println(sb.toString());
    }

    private static void creatExcelFile(XSSFWorkbook workbook) throws FileNotFoundException {
        String excelFilePath = "/Users/nibnait/Desktop/王春草.xlsx";
        FileOutputStream fileOutputStream = new FileOutputStream(excelFilePath);
        try {
            workbook.write(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void createHeadRow(XSSFSheet sheet) {
        XSSFRow headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("门店ID");
    }
}
