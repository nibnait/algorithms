package zzzTest.excelLoader.test;

import zzzTest.excelLoader.ExcelReader;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        File excelFile = new File("/Users/nibnait/Desktop/1.xlsx");
        ExcelReader excelReader = new ExcelReader(excelFile);
        List<Student> list = excelReader.loadSheet(0);

        System.out.println(list.toString());
    }
}
