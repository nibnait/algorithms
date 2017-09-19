package zzzTest.excelLoader.test;

import zzzTest.excelLoader.ExcelReader;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        File excelFile = new File("/Users/nibnait/Desktop/8.xlsx");
        ExcelReader excelReader = new ExcelReader(excelFile);
        List<ExcelModel> modelList = excelReader.loadSheet(0);

        Field[] fields = null;
        try {
            Class clz = Class.forName("zzzTest.excelLoader.test.ExcelModel");
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
        int length = modelList.size();
        int count = 0;
        for (ExcelModel model : modelList) {
            if (count == length - 1) {
                sb = reflectLastTime(model, sb);
                break;
            }
            sb = reflect(model, sb);
            count++;
        }
        baseStr.append(sb);

        try {
            //创建一个新文件
            Path filePath = Paths.get("/Users/nibnait/Desktop/1.txt");
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
        System.out.println(modelList.size());
    }

    private static StringBuilder reflectLastTime(ExcelModel obj, StringBuilder sb) {
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            sb.append("(");
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getType().getName().equals(
                        java.lang.String.class.getName())) {
                    sb.append("\'" + (fields[i].get(obj)!=null?fields[i].get(obj).toString():"")+ "\'");
                }
//                else if (fields[i].getType().getName().equals(java.lang.Integer.class.getName())
//                        || fields[i].getType().getName().equals("int")
//                        || fields[i].getType().getName().equals(java.lang.Long.class.getName())
//                        || fields[i].getType().getName().equals("long")) {
//                    sb.append(fields[i].get(obj).toString());
//                }
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

    private static StringBuilder reflect(ExcelModel obj, StringBuilder sb) {
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            sb.append("(");
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getType().getName().equals(java.lang.String.class.getName())) {
                    sb.append("\'" + (fields[i].get(obj)!=null?fields[i].get(obj).toString():"")+ "\'");
                }
//                else if (fields[i].getType().getName().equals(java.lang.Integer.class.getName())
//                        || fields[i].getType().getName().equals("int")
//                        || fields[i].getType().getName().equals(java.lang.Long.class.getName())
//                        || fields[i].getType().getName().equals("long")) {
//                    sb.append(fields[i].get(obj)!=null?fields[i].get(obj).toString():"");
//                }

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
