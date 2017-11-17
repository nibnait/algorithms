package zzzTest.excelLoader.models.auditRule;

import zzzTest.excelLoader.excelReader.ExcelReader;

import java.io.File;
import java.util.List;

public class soutDeletedProtectBrand {

    public static void main(String[] args) {
        File excelFile = new File("/Users/nibnait/Desktop/1.xlsx");
        ExcelReader excelReader = new ExcelReader(excelFile);
//        List<ProtectBrand> modelList = excelReader.loadSheet();

        StringBuffer sb = new StringBuffer();
//        for (ProtectBrand model : modelList) {
//            sb.append("\'" + model.getSensitive_word() + "\',");
//        }
        System.out.println(sb.toString());
    }

}
