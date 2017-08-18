package zzzTest.excelLoader.test;

import zzzTest.excelLoader.ExcelColumnTitle;

public class Student {
    @ExcelColumnTitle("店铺命名宣传性词汇库")
    private String xuanchuan = "";
    @ExcelColumnTitle("方向性（模糊）词汇库")
    private String fangxiang = "";

    public String getXuanchuan() {
        return xuanchuan;
    }

    public void setXuanchuan(String xuanchuan) {
        this.xuanchuan = xuanchuan;
    }

    public String getFangxiang() {
        return fangxiang;
    }

    public void setFangxiang(String fangxiang) {
        this.fangxiang = fangxiang;
    }
}
