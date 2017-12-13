package zzzTest.JsonParseTest.certificationConvert;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import zzzTest.JsonParseTest.JsonHelperTest.JsonHelper;
import zzzTest.JsonParseTest.flavorConvert.FlavorDto;

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
public class certificationConvert {

    public static void main(String[] args) throws FileNotFoundException {
        Path filePath = Paths.get("/Users/nibnait/Desktop/1.txt");
        Path filePath2 = Paths.get("/Users/nibnait/Desktop/3.txt");
        List<CertificationDto> certificationDtoList = new ArrayList<>();
        List<OperatorDto> operatorDtoList = new ArrayList<>();
        try {
            String jsonString = new String(Files.readAllBytes(filePath));
            String jsonString2 = new String(Files.readAllBytes(filePath2));
            certificationDtoList = JsonHelper.toJsonObject(jsonString, ArrayList.class, CertificationDto.class);
            operatorDtoList = JsonHelper.toJsonObject(jsonString2, ArrayList.class, OperatorDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("资质信息");
        createHeadRow(sheet);
        int rowNum = 1;
        StringBuilder sb = new StringBuilder();
        for (CertificationDto certificationDto : certificationDtoList) {
            sb.append(certificationDto.getOperator_id()+",");
            XSSFRow row = sheet.createRow(rowNum++);
            int columNum = 0;
            row.createCell(columNum++).setCellValue(certificationDto.getRestaurant_id());
            row.createCell(columNum++).setCellValue(certificationDto.getCreated_at());
            row.createCell(columNum++).setCellValue(certificationDto.getUpdated_at());
            row.createCell(columNum++).setCellValue(certificationDto.getOperator_id());
            row.createCell(columNum++).setCellValue(certificationDto.getOperator_type());
            for (OperatorDto operatorDto : operatorDtoList) {
                if (operatorDto.getUser_id().equals(certificationDto.getOperator_id())) {
                    row.createCell(columNum++).setCellValue(operatorDto.getName());
                    row.createCell(columNum++).setCellValue(operatorDto.getEmail());
                    row.createCell(columNum++).setCellValue(operatorDto.getMobile());
                }
            }
        }
        creatExcelFile(workbook);
        System.out.println(rowNum);
        System.out.println(sb.toString());
    }

    private static void creatExcelFile(XSSFWorkbook workbook) throws FileNotFoundException {
        String excelFilePath = "/Users/nibnait/Desktop/证照上传日志统计.xlsx";
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
        headRow.createCell(1).setCellValue("创建时间");
        headRow.createCell(2).setCellValue("更新时间");
        headRow.createCell(3).setCellValue("操作人userId");
        headRow.createCell(4).setCellValue("操作人类型");
        headRow.createCell(5).setCellValue("操作人姓名");
        headRow.createCell(6).setCellValue("操作人邮箱");
        headRow.createCell(7).setCellValue("操作人电话");
    }
}
