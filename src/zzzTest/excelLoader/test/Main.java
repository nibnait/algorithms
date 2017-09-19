package zzzTest.excelLoader.test;

import zzzTest.excelLoader.ExcelReader;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        File excelFile = new File("/Users/nibnait/Desktop/1.xlsx");
        ExcelReader excelReader = new ExcelReader(excelFile);
        List<ExcelModel> modelList8 = excelReader.loadSheet(0);

        File excelFile2 = new File("/Users/nibnait/Desktop/2.xlsx");
        ExcelReader excelReader2 = new ExcelReader(excelFile2);
        List<ExcelModel> modelList7 = excelReader2.loadSheet(0);

        List<String> modelList11 = new ArrayList<>();
        List<String> modelList77 = new ArrayList<>();
//        modelList7.forEach(item-> modelList77.add(item.getBrand()));
        for (ExcelModel excelModel: modelList7) {
            modelList77.add(excelModel.getBrand());
        }
        List<String> modelList88 = new ArrayList<>();
//        modelList8.forEach(item-> modelList88.add(item.getBrand()));
        for (ExcelModel excelModel: modelList8) {
            modelList88.add(excelModel.getBrand());
        }
        for (String brand : modelList88) {
            if (!modelList77.contains(brand)) {
                modelList11.add(brand);
            }
        }

        List<ExcelModel> modelList = new ArrayList<>();
//        modelList11.forEach(item -> modelList.add(new ExcelModel(item, 1)));
        for (String excelModel: modelList11) {
            modelList.add(new ExcelModel(excelModel, 1));
        }
        Field[] fields = null;
        try {
            Class clz = Class.forName("zzzTest.excelLoader.test.ExcelModel");
            fields = clz.getDeclaredFields();
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder baseStr = new StringBuilder("INSERT INTO runshop_audit_sensitive_word (");
        for (int i = 0; i < fields.length; i++) {
            if (i == fields.length-1) {
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
        for (ExcelModel model : modelList){
            if (count == length-1){
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
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(modelList.size());
    }

    private static StringBuilder reflectLastTime(Object obj, StringBuilder sb) {
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            sb.append("(");
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getType().getName().equals(
                        java.lang.String.class.getName())) {
                    sb.append("\'" + fields[i].get(obj).toString() + "\'");
                } else if (fields[i].getType().getName().equals(java.lang.Integer.class.getName())
                        || fields[i].getType().getName().equals("int")) {
                    sb.append(fields[i].get(obj).toString());
                }
                if (i!=fields.length-1) {
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

    private static StringBuilder reflect(Object obj, StringBuilder sb){

        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            sb.append("(");
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getType().getName().equals(
                        java.lang.String.class.getName())) {
                    sb.append("\'" + fields[i].get(obj).toString() + "\'");
                } else if (fields[i].getType().getName().equals(java.lang.Integer.class.getName())
                        || fields[i].getType().getName().equals("int")) {
                    sb.append(fields[i].get(obj).toString());
                }

                if (i!=fields.length-1) {
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
