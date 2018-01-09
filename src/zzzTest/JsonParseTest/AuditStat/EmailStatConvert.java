package zzzTest.JsonParseTest.AuditStat;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import zzzTest.JsonParseTest.JsonHelperTest.JsonHelper;
import zzzTest.JsonParseTest.certificationConvert.OperatorDto;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmailStatConvert {

    private static final int START_DATE_INDEX = 1;
    private static final int TOTAL_STAT_DAYS = 7;

    public static void main(String[] args) throws FileNotFoundException {
        Path filePath = Paths.get("/Users/nibnait/Desktop/1.txt");
        List<EmailStatDto> emailStatDtoList = new ArrayList<>();
        try {
            String jsonString = new String(Files.readAllBytes(filePath));
            emailStatDtoList = JsonHelper.toJsonObject(jsonString, ArrayList.class, EmailStatDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<ExcelDto> excelDtoList = new ArrayList<>();
        for (EmailStatDto emailStatDto : emailStatDtoList) {
            ExcelDto newExcelDto = null;
            boolean flag = true;
            for (ExcelDto excelDto : excelDtoList) {
                if (emailStatDto.getOperatorName().equals(excelDto.getName())) {
                    List<Excel_Date_AuditNumber> auditNumber = excelDto.getAuditNumber();
                    auditNumber.add(new Excel_Date_AuditNumber(emailStatDto.getOperationDate(), emailStatDto.getAuditNumber().toString()));
                    excelDto.setAuditNumber(auditNumber);
                    flag = false;
                    break;
                }
            }

            if (flag || excelDtoList == null || excelDtoList.size()==0) {
                newExcelDto = new ExcelDto();
            }

            if (newExcelDto != null) {
                newExcelDto.setName(emailStatDto.getOperatorName());
                List<Excel_Date_AuditNumber> auditNumberList = new ArrayList<>();
                auditNumberList.add(new Excel_Date_AuditNumber(emailStatDto.getOperationDate(), emailStatDto.getAuditNumber().toString()));
                newExcelDto.setAuditNumber(auditNumberList);
                excelDtoList.add(newExcelDto);
            }
        }

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("审核统计");
        createHeadRow(sheet);
        int rowNum = 1;
        for (ExcelDto excelDto : excelDtoList) {
            XSSFRow row = sheet.createRow(rowNum++);
            int columIndex = 0;
            row.createCell(columIndex++).setCellValue(excelDto.getName());
            for (int i = START_DATE_INDEX; i <= TOTAL_STAT_DAYS; i++, columIndex++) {
                for (Excel_Date_AuditNumber excel_date_auditNumber : excelDto.getAuditNumber()) {
                    if (excel_date_auditNumber.getDate().equals(LocalDate.now().minusDays(i).atStartOfDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))) {
                        row.createCell(columIndex).setCellValue(excel_date_auditNumber.getNumber());
                    }
                }
            }
        }


        creatExcelFile(workbook);
        System.out.println(rowNum);
    }

    private static void creatExcelFile(XSSFWorkbook workbook) throws FileNotFoundException {
        String excelFilePath = "/Users/nibnait/Desktop/审核统计.xlsx";
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
        int columIndex = 1;
        headRow.createCell(0).setCellValue("姓名");
        for (int i = START_DATE_INDEX; i <= TOTAL_STAT_DAYS; i++) {
            String endDate = LocalDate.now().minusDays(i).atStartOfDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            headRow.createCell(columIndex++).setCellValue(endDate);
        }
    }
}
