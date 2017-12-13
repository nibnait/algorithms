package zzzTest.excelLoader.models.auditRule;

import zzzTest.excelLoader.excelReader.ExcelReader;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ProtectBrandConvert {
    public static void main(String[] args) {

        int count = 0;
        File excelFile = new File("/Users/nibnait/Desktop/品牌更新 11-22.xlsx");
        ExcelReader excelReader = new ExcelReader(excelFile);
//        List<ProtectBrand> modelList = excelReader.loadSheet();

        Field[] fields = null;
        try {
            Class clz = Class.forName(ProtectBrand.class.getName());
            fields = clz.getDeclaredFields();
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder baseStr = new StringBuilder("INSERT INTO runshop_audit_sensitive_word (");
        for (int i = 0; i < fields.length; i++) {
            if (i == fields.length - 1) {
                baseStr.append(fields[i].getName());
                baseStr.append(") VALUES ");
                continue;
            }
            baseStr.append(fields[i].getName());
            baseStr.append(", ");
        }

        StringBuilder sb = new StringBuilder();
//        int length = modelList.size();
//        for (ProtectBrand model : modelList) {
//            if (count == length - 1) {
//                sb = reflectLastTime(model, sb);
//                break;
//            }
//            sb = reflect(model, sb);
//            count++;
//        }
//        baseStr.append(sb);
//        System.out.println(length);
        try {
            //创建一个新文件
            String insertSQLFileName = "保护品牌";
            Path filePath = Paths.get("/Users/nibnait/Desktop/"+insertSQLFileName+".txt");
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                Files.createFile(filePath);
            } else {
                Files.createFile(filePath);
            }

            //写操作
            Files.write(filePath, baseStr.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static StringBuilder reflectLastTime(Object obj, StringBuilder sb) {
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            sb.append("(");
            for (int i = 0; i < fields.length; i++) {
                sb.append("\'" + (fields[i].get(obj)!=null?fields[i].get(obj).toString():"")+ "\'");
                if (i != fields.length - 1) {
                    sb.append(", ");
                } else {
                    sb.append(");");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb;
    }

    private static StringBuilder reflect(Object obj, StringBuilder sb) {
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            sb.append("(");
            for (int i = 0; i < fields.length; i++) {
                sb.append("\'" + (fields[i].get(obj) != null ? fields[i].get(obj).toString() : "") + "\'");
                if (i != fields.length - 1) {
                    sb.append(", ");
                } else {
                    sb.append("),");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb;
    }
}
