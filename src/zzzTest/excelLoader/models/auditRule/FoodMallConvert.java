package zzzTest.excelLoader.models.auditRule;

import zzzTest.excelLoader.excelReader.ExcelReader;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FoodMallConvert {
    public static void main(String[] args) {

        int count = 0;
        File excelFile = new File("/Users/nibnait/Desktop/美食城名单更新.xlsx");
        ExcelReader excelReader = new ExcelReader(excelFile);
//        List<FoodMall> modelList = excelReader.loadSheet();

        Field[] fields = null;
        try {
            Class clz = Class.forName(FoodMall.class.getName());
            fields = clz.getDeclaredFields();
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder baseStr = new StringBuilder("INSERT INTO runshop_regist_tag (");
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
//        for (FoodMall model : modelList) {
//            if (count == length - 1) {
//                sb = reflectLastTime(model, sb);
//                break;
//            }
//            sb = reflect(model, sb);
//            count++;
//        }
        baseStr.append(sb);
//        System.out.println(length);
        try {
            //创建一个新文件
            String insertSQLFileName = "美食城标签";
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
                if (fields[i].getName().equals("shop_id")) {
                    sb.append(((fields[i].get(obj) != null && fields[i].get(obj).toString().length() > 0) ? fields[i].get(obj).toString().trim(): 0));
                    sb.append(",");
                    continue;
                }
                if (fields[i].getName().equals("shop_type")) {
                    sb.append(((fields[i].get(obj) != null && fields[i].get(obj).toString().length() > 0) ? fields[i].get(obj).toString().trim().equals("新店报备")?1:2 : 0));
                    sb.append(",");
                    continue;
                }
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
                if (fields[i].getName().equals("shop_id")) {
                    sb.append(((fields[i].get(obj) != null && fields[i].get(obj).toString().length() > 0) ? fields[i].get(obj).toString().trim() : 0));
                    sb.append(",");
                    continue;
                }
                if (fields[i].getName().equals("shop_type")) {
                    sb.append(((fields[i].get(obj) != null && fields[i].get(obj).toString().length() > 0) ? fields[i].get(obj).toString().trim().equals("新店报备")?1:2 : 0));
                    sb.append(",");
                    continue;
                }
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
