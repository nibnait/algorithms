package zzzTest.excelParser;

import zzzTest.excelParser.excelParser.ExcelParser;
import zzzTest.excelParser.models.FDA.complaint;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main_ExcelParser {
    public static void main(String[] args) {

        int count = 0;
        File excelFile = new File("/Users/nibnait/Desktop/1.xlsx");
        ExcelParser<ExcelModel> excelParser = new ExcelModelParser(excelFile);
        List<ExcelModel> modelList = excelParser.loadSheet();

        Field[] fields = null;
        try {
            Class clz = Class.forName(ExcelModel.class.getName());
            fields = clz.getDeclaredFields();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        StringBuilder baseStr = new StringBuilder("INSERT INTO runshop_regist_tag (");
        StringBuilder baseStr = new StringBuilder("");
//        for (int i = 0; i < fields.length; i++) {
//            if (i == fields.length - 1) {
//                baseStr.append(fields[i].getName());
//                baseStr.append(") VALUES ");
//                continue;
//            }
//            baseStr.append(fields[i].getName());
//            baseStr.append(", ");
//        }

        StringBuilder sb = new StringBuilder();
        int length = modelList.size();
        for (ExcelModel model : modelList) {
            baseStr.append(model.getCityName()+",");
        }
//        for (ExcelModel model : modelList) {
//            if (count == length - 1) {
//                sb = reflectLastTime(model, sb);
//                break;
//            }
//            sb = reflect(model, sb);
//            count++;
//        }
//        baseStr.append(sb);

        try {
            //创建一个新文件
            String insertSQLFileName = "灰度城市";
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
                sb.append("\'" + (fields[i].get(obj)!=null?fields[i].get(obj).toString():"")+ "\'");
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
