package zzzTest.excelLoader.test;

import zzzTest.excelLoader.ExcelColumnTitle;

public class ProtectBrand {
    @ExcelColumnTitle("品牌")
    public String brand;

    public int word_type=1;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

}