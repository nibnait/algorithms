package zzzTest.JsonParseTest.certificationConvert;

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

/**
 * 文件读取
 * Json对象转换
 * excel文件创建
 */
public class CertificationConvert {

    public static void main(String[] args) throws FileNotFoundException {
        Path filePath = Paths.get("/Users/nibnait/Desktop/user.txt");
        List<OperatorDto> operatorDtoList = new ArrayList<>();
        try {
            String jsonString = new String(Files.readAllBytes(filePath));
            operatorDtoList = JsonHelper.toJsonObject(jsonString, ArrayList.class, OperatorDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("BD更换记录");
        createHeadRow(sheet);
        int rowNum = 1;
        StringBuilder sb = new StringBuilder();
        for (OperatorDto operatorDto : operatorDtoList) {
            sb.append(operatorDto.getUser_id()+",");
            XSSFRow row = sheet.createRow(rowNum++);
            int columNum = 0;
            row.createCell(columNum++).setCellValue(operatorDto.getUser_id());
            row.createCell(columNum++).setCellValue(operatorDto.getName());
            row.createCell(columNum++).setCellValue(operatorDto.getEmail());
            row.createCell(columNum++).setCellValue(operatorDto.getMobile());
            row.createCell(columNum++).setCellValue(operatorDto.getComment());
        }
        creatExcelFile(workbook);
        System.out.println(rowNum);
        System.out.println(sb.toString());
    }

    private static void creatExcelFile(XSSFWorkbook workbook) throws FileNotFoundException {
        String excelFilePath = "/Users/nibnait/Desktop/BD更换记录.xlsx";
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
        headRow.createCell(0).setCellValue("BDId");
        headRow.createCell(1).setCellValue("BD姓名");
        headRow.createCell(2).setCellValue("BD邮箱");
        headRow.createCell(3).setCellValue("BD电话");
        headRow.createCell(4).setCellValue("工号");
    }
}
