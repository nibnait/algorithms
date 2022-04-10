package common.bo.excel;


import common.CommonConstants;
import common.util.DateUtils;
import common.util.EnhanceFileUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nibnait on 2021/01/21
 */
@Data
@Slf4j
public class WorkBookBO {

    private HSSFWorkbook workbook;

    private List<SheetBO> sheetList = new ArrayList<>();

    public WorkBookBO() {
        workbook = new HSSFWorkbook();
        sheetList.add(new SheetBO(workbook.createSheet("Sheet1")));
    }

    public WorkBookBO(List<List<String>> sheetTitle, String... sheetName) {
        workbook = new HSSFWorkbook();
        if (CollectionUtils.isEmpty(sheetTitle) || sheetName == null
                || sheetTitle.size() != sheetName.length) {
            // 直接创建一个默认的sheet
            sheetList.add(new SheetBO(workbook.createSheet("Sheet1")));
            return;
        }

        for (int i = 0; i < sheetTitle.size(); i++) {
            HSSFSheet sheet = workbook.createSheet(sheetName[i]);
            SheetBO sheetBO = new SheetBO(sheet);
            sheetBO.appendRow(sheetTitle.get(i));
            sheetList.add(sheetBO);
        }
    }

    /**
     * 将 workbook 写成文件
     * @param fileName fileName 重命名文件（可以为空）
     */
    public File writeToFile(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            fileName = String.valueOf(DateUtils.currentTimeMillis());
        }

//        fileName = CommonConstants.FILE_PATH + CommonConstants.SLASH_SEPARATOR + fileName + CommonConstants.SUFFIX_XLS;
        fileName = "/Users/nibnait/data/file" + CommonConstants.SLASH_SEPARATOR + fileName + CommonConstants.SUFFIX_XLS;
        File file = new File(fileName);

        try {
            file = EnhanceFileUtils.createIfNecessary(fileName);
            workbook.write(file);
            workbook.close();
        } catch (Exception e) {
            log.error("WorkBookBO writeToFile error ", e);
        }

        return file;
    }
}
