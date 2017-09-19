package zzzTest.excelLoader;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class ExcelLoader<T> {

    /**
     * 要导入的excel文件
     */
    protected File excel;
    /**
     * 打开的excel对象
     */
    protected Workbook workbook;

    /**
     * 泛型类
     */
    protected Class clazz;


    public File getExcel() {
        return excel;
    }

    public void setExcel(File excel) {
        this.excel = excel;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public ExcelLoader() {
        //传入的泛型参数
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class) type.getActualTypeArguments()[0];
    }

    /**
     * 得到excel poi 的 workbook对象
     * @param file 要读取的excel文件
     * @return workbook对象
     */
    public Workbook createWorkBook(File file){
        Workbook book = null;

        //处理null  文件夹 文件不存在
        if(file == null || file.isDirectory() || !file.exists()){
            return null;
        }

        //文件类型不同 采用不同的加载器
        if(file.getName().endsWith(".xlsx")){
            try {
                book = new XSSFWorkbook(file);
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(file.getName().endsWith(".xls")){
            try (FileInputStream fis = new FileInputStream(file)){
                book = new HSSFWorkbook(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return book;
    }

    public ExcelLoader(File file){
        this();
        workbook = createWorkBook(file);
    }

    /**
     * 加载标题
     * @param row 第一行
     * @return 返回第n列 对应的 属性
     */
    protected Map<Integer, Field> loadTitle(Row row){

        Map<Integer, Field> num2Field = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();

        int i = 0;
        //判断标题，并将标题放入 列号--字段名  的 Map中
        for (Cell cell : row) {
            String title = cell.getStringCellValue();
            try {
                for (Field field : fields) {
                    if(field.isAnnotationPresent(ExcelColumnTitle.class)){
                        ExcelColumnTitle excelTitle = field.getAnnotation(ExcelColumnTitle.class);
//                        System.out.println(excelTitle.name() + "  " + excelTitle.value());
                        if(excelTitle.value().equals(title)){
                            field.setAccessible(true);
                            num2Field.put(i, field);
                        }
                    }

                }
            } catch (SecurityException e) {
                e.printStackTrace();
            }
            i++;

        }

        return num2Field;
    }

    /**
     * 将一行数据 封装到 一个实体类
     * @param row excel的一行
     * @param num2Field 第n列对应的 属性
     * @return  返回 实体类
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws SecurityException
     */
    protected T newEntity(Row row, Map<Integer, Field> num2Field) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException{
        T t = (T) clazz.newInstance();


        int i = 0;
        for (Cell cell : row) {
            if(num2Field.get(i) != null){
                num2Field.get(i).set(t, getCellValue(cell));
            }
            i++;
        }
        return t;
    }

    /**
     * 加载第几个excel表格
     * @param index
     * @return 返回第一页的 所有行的数据
     */
    public List<T> loadSheet(int index){
        if(workbook == null){
            return null;
        }

        //加载的内容存放位置
        List<T> list = new ArrayList<>();

        Sheet sheet = workbook.getSheetAt(index);

        Map<Integer, Field> num2Field = new HashMap<>();
        int i = 0;
        //查找首行
        for(Row row : sheet){
            //如果是第一行  加载标题
            if(i == 0){
                num2Field = loadTitle(row);
            }else{
                //否则 装载一个实体类
                try {
                    list.add(newEntity(row, num2Field));
                } catch (InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
                    e.printStackTrace();
                }
            }
            i++;
        }

        return list;
    }

    /**
     *  判断单元格的类型是不是时间
     * 	由于poi 的dateutil没有处理中文日期情况
     * 	如yyyy年m月d日  dataFormat = 31
     *         m月d日  dataFormat = 28
     *  故对其简单封装
     * @param cell 单元格
     * @return   是否是时间格式
     */
    public static boolean isDateType(Cell cell){
        return DateUtil.isCellDateFormatted(cell)
                || cell.getCellStyle().getDataFormat() == 28
                || cell.getCellStyle().getDataFormat() == 31;
    }

    /**
     * 将单元格的类型装换为字符串并返回
     * @param cell  单元格
     * @return   单元格内容的字符串形式
     */
    public static String getCellValue(Cell cell){
        CellType cellType = cell.getCellTypeEnum();

        if(cellType == CellType.NUMERIC || cellType == CellType.FORMULA){
            //判断是不是时间格式
            if(isDateType(cell)){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = cell.getDateCellValue();
                return sdf.format(date);
            }
//            return String.valueOf(cell.getNumericCellValue()).trim();
            return String.valueOf(Double.valueOf(String.valueOf(cell.getNumericCellValue())).longValue());
        }else if (cellType == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue()).trim();
        }else if(cellType == CellType.STRING){
            return cell.getStringCellValue().trim();
        }else{
            return "";
        }

    }

}

