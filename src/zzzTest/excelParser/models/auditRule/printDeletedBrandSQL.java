package zzzTest.excelParser.models.auditRule;

import zzzTest.excelParser.excelParser.ExcelParser;

import java.io.File;
import java.util.List;

public class printDeletedBrandSQL {

    public static void main(String[] args) {
        File excelFile = new File("/Users/nibnait/Desktop/1.xlsx");
        ExcelParser<ProtectBrand> excelParser = new ExcelParser(excelFile);
        List<ProtectBrand> modelList = excelParser.loadSheet();

        StringBuffer sb = new StringBuffer();
        for (ProtectBrand model : modelList) {
            sb.append("\'" + model.getSensitive_word() + "\',");
        }
        String updateSQL = "UPDATE runshop_audit_sensitive_word\nSET is_valid = 0\nWHERE sensitive_word in (";
//        String updateSQL = "UPDATE runshop_audit_sensitive_word SET is_valid = 0 WHERE sensitive_word in (";
        System.out.println(updateSQL+sb.toString().substring(0, sb.length()-1) + ");");
    }

}

/*
UPDATE runshop_audit_sensitive_word
SET is_valid = 0
WHERE sensitive_word in ('吉野家华东','吉野家华南','吉野家华中','山东吉野家','食其家华东','食其家华南','食其家华北','大脸鸡排');

 */