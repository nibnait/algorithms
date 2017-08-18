package zzzTest.excelLoader;

import zzzTest.excelLoader.test.Student;

import java.io.File;

public class ExcelReader extends ExcelLoader<Student>{

    public ExcelReader(File file) {
        super(file);
    }

}
