package common.bo.excel;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public WorkBookBO(int sheetSize, List<List<String>> sheetTitle, String... sheetName) {
        workbook = new HSSFWorkbook();
        if (CollectionUtils.isEmpty(sheetTitle) || sheetName == null
                || sheetTitle.size() != sheetName.length) {
            // 直接创建一个默认的sheet
            sheetList.add(new SheetBO(workbook.createSheet("Sheet1")));
            return;
        }

        for (int i = 0; i < sheetSize; i++) {
            HSSFSheet sheet = workbook.createSheet(sheetName[i]);
            SheetBO sheetBO = new SheetBO(sheet);
            sheetBO.appendRow(sheetTitle.get(i));
            sheetList.add(sheetBO);
        }
    }

    /**
     * 将 workbook 写成文件
     * @param sourceFileName resources/excel 目录下的文件名
     * @param targetFileName targetFileName 重命名文件（可以为空）
     */
    public File writeToFile(String sourceFileName, String targetFileName) {

        if (StringUtils.isBlank(sourceFileName)) {
            return null;
        }

        File sourceFile = null;
        String sourceFilePathName = "/excel/" + sourceFileName + ".xls";
        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources(sourceFilePathName);
            sourceFile = resources[0].getFile();
            workbook.write(sourceFile);
            workbook.close();
            resources = resolver.getResources(sourceFilePathName);
            sourceFile = resources[0].getFile();

            if (StringUtils.isNotBlank(targetFileName) && sourceFile != null) {
                String path = sourceFile.getPath();
                Path copy = Files.copy(Paths.get(path), Paths.get(path.replaceAll(sourceFileName, targetFileName)));
                return copy.toFile();
            }
        } catch (Exception e) {
            log.error("fail to write excel sourceFile", e);
        }

        return sourceFile;
    }
}
