package os.jvm;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;

public class Jxl_FGC_Test {

    public static void main(String[] args) throws IOException, WriteException {
        for (int i = 0; i < 10; i++) {
            excelGen();
        }
    }

    private static void excelGen() throws IOException, WriteException {
        WritableWorkbook book = null;
        WritableSheet sheet;
        try {
            book = Workbook.createWorkbook(new File("/Users/nibnait/Desktop/test.xls"));
            sheet = book.createSheet("PageNo.1", 0);
            Label label = new Label(0, 0, "公众号：【阿飞的博客】");
            sheet.addCell(label);
            book.write();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            book.close();
        }
    }
}

/*
-Xmx512m -Xms512m -Xmn256m -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps
 */