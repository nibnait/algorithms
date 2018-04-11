package zzzTest.excelParser.models.refuseReason;

import Standard.StringUtils;
import zzzTest.excelParser.excelParser.ExcelParser;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RefuseReason_Main {

    public static void main(String[] args) {
        File excelFile = new File("/Users/nibnait/Desktop/1.xlsx");
        ExcelParser<ReasonModel> excelParser = new ExcelParser(excelFile);
        List<ReasonModel> modelList = excelParser.loadSheet();
        int cnt = make(modelList);
        System.out.println(cnt);
    }

    private static int make(List<ReasonModel> modelList) {
        StringBuilder baseStr = new StringBuilder("INSERT INTO runshop_audit_refuse_reason (audit_type, main_reason_id, main_reason, detail_reason) VALUES");

        StringBuilder sb = new StringBuilder();
        int audit_type = 1;
        int main_reason_id = 0;
        String main_reason = "";
        for (ReasonModel model : modelList) {
            if (StringUtils.isNotBlank(model.getMain_reason())) {
                if (model.getMain_reason().equals("自定义")) {
                    audit_type++;
                    main_reason_id = 0;
                } else {
                    if (!model.getMain_reason().equals(main_reason)) {
                        main_reason_id++;
                    }
                    main_reason = model.getMain_reason();

                    sb.append("(");
                    sb.append(audit_type + "," + main_reason_id + ",'" + main_reason + "','" + model.getDetail_reason() +"'");
                    sb.append("),");
                }
            }
        }
        baseStr.append(sb);

        try {
            //创建一个新文件
            String insertSQLFileName = "拒绝原因";
            Path filePath = Paths.get("/Users/nibnait/Desktop/"+insertSQLFileName+".txt");
            byte[] bytes = null;
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            } else {
                Files.createFile(filePath);
            }

            if (bytes != null) {
                String oldString = new String(bytes);
                baseStr.append(oldString);
            }
            //写操作
            Files.write(filePath, baseStr.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  modelList.size();
    }

    private static StringBuilder reflectLastTime(Object obj, StringBuilder sb) {
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            sb.append("(");
            for (int i = 0; i < fields.length; i++) {

                if (fields[i].getName().equals("business_license_tag") || fields[i].getName().equals("service_license_tag")){
                    sb.append(1);
                } else {
                    sb.append("\'" + (fields[i].get(obj) != null ? fields[i].get(obj).toString() : "") + "\'");
                }
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

                if (fields[i].getName().equals("business_license_tag") || fields[i].getName().equals("service_license_tag")){
                    sb.append(1);
                } else {
                    sb.append("\'" + (fields[i].get(obj) != null ? fields[i].get(obj).toString() : "") + "\'");
                }
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
