package zzzTest.excelLoader.test;

import zzzTest.excelLoader.ExcelColumnTitle;

public class ProtectBrand {
    @ExcelColumnTitle("品牌")
    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}