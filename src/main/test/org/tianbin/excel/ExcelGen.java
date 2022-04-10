package org.tianbin.excel;

import com.google.common.collect.Lists;
import io.github.nibnait.common.bo.email.EmailAccount;
import io.github.nibnait.common.bo.email.EmailBO;
import io.github.nibnait.common.bo.excel.SheetBO;
import io.github.nibnait.common.bo.excel.WorkBookBO;
import io.github.nibnait.common.utils.EmailUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by nibnait on 2021/01/21
 */
public class ExcelGen {

    @Test
    public void test() {
        System.out.println(StringUtils.isBlank(" "));
    }

    private static final String DELAY_DELIVERY_REPORT_FILE_NAME = "报表-%s";
    public static final String DATE_TIME_FORMAT = "yyyyMMdd-HHmm";

    @Test
    public void excelGen() throws Exception {

        String targetFileName = String.format(DELAY_DELIVERY_REPORT_FILE_NAME,
                getNowDateTime(DATE_TIME_FORMAT));

        WorkBookBO workBookBO = new WorkBookBO();
        SheetBO sheet = workBookBO.getSheetList().get(0);

        xiegehanshu(sheet);

        File delay_delivery_report = workBookBO.writeToDefaultLocalPath(targetFileName);

        System.out.println(delay_delivery_report.getName());

        EmailUtils.sendEmailThrowException(new EmailAccount(), new EmailBO());
    }

    public static String getNowDateTime(String dateFormat) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateFormat));
    }


    private void xiegehanshu(SheetBO sheet) {
        sheet.appendRow(Lists.newArrayList("123", "234"));
    }

    private String getEmailContent() {
        StringBuilder content = new StringBuilder("<h4>Hi All: </h4>");
        content.append("&ensp;&ensp;&ensp;&ensp;");
        content.append("2021年1月21日");
        content.append(" 报表如下，请核实：");
        content.append("</br>");
        content.append("</br>");
        return content.toString();
    }
}
