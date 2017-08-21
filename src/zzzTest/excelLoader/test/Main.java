package zzzTest.excelLoader.test;

import zzzTest.excelLoader.ExcelReader;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        File excelFile = new File("/Users/nibnait/Desktop/2.xlsx");
        ExcelReader excelReader = new ExcelReader(excelFile);
        String str = "";
        int count = 0;
        for (int i = 0; i < 6; i++) {
            List<ExcelModel> list = excelReader.loadSheet(i);
            String baseStr = "INSERT INTO investigation (shop_id; city_id; area; reason; detailed_reason; off_at) ";
            for (ExcelModel m : list){
                str += baseStr + "VALUES ( " + m.getShop_id() + "; " + m.getCity_id() + "; \'"+ m.getArea()+"\'; "
                            + "\' "+ m.getReason() + "\'; "
                            + "\' "+ m.getDetailed_reason() + "\'; "
                            + "\' "+ m.getOff_at().toString() + "\');";
            }
            count += list.size();
        }

//        System.out.println(str);
        System.out.println(count);
    }
}
