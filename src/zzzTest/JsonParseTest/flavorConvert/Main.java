package zzzTest.JsonParseTest.flavorConvert;

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
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Path filePath = Paths.get("/Users/nibnait/Desktop/1.txt");
        List<FlavorDto> flavorDtoList = new ArrayList<>();
        try {
            String jsonString = new String(Files.readAllBytes(filePath));
            flavorDtoList = JsonHelper.toJsonObject(jsonString, ArrayList.class, FlavorDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("门店分类信息");
        createHeadRow(sheet);
        int rowNum = 1;
        StringBuilder sb = new StringBuilder();
        for (FlavorDto flavorDto : flavorDtoList) {
            if (flavorDto.getRst_id() != 0) {
                sb.append(flavorDto.getId()+",");
                XSSFRow row = sheet.createRow(rowNum++);
                int columNum = 0;
                row.createCell(columNum++).setCellValue(flavorDto.getRst_id());
                if (flavorDto.getSub_flavorname().equals("None")) {
                    row.createCell(columNum).setCellValue(flavorDto.getMain_flavorname());
                } else {
                    String flavor = flavorDto.getMain_flavorname() + "||" + flavorDto.getSub_flavorname();
                    row.createCell(columNum).setCellValue(flavor);
                }
            }
        }
        creatExcelFile(workbook);
        System.out.println(rowNum);
        System.out.println(sb.toString());
    }

    private static void creatExcelFile(XSSFWorkbook workbook) throws FileNotFoundException {
        String excelFilePath = "/Users/nibnait/Desktop/分类信息.xlsx";
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
        headRow.createCell(1).setCellValue("门店分类");
    }
}
