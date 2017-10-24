package zzzTest.excelLoader.models.FDA;

import zzzTest.excelLoader.excelReader.ExcelColumnTitle;

public class investigation {
    @ExcelColumnTitle("店铺ID")
    public String shop_id;

    @ExcelColumnTitle("城市ID")
    public String city_id;

    @ExcelColumnTitle("区域ID")
    public String area;

    @ExcelColumnTitle("下架原因")
    public String reason;

    @ExcelColumnTitle("下架描述")
    public String detailed_reason;

    @ExcelColumnTitle("下架时间")
    public String off_at;

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDetailed_reason() {
        return detailed_reason;
    }

    public void setDetailed_reason(String detailed_reason) {
        this.detailed_reason = detailed_reason;
    }

    public String getOff_at() {
        return off_at;
    }

    public void setOff_at(String off_at) {
        this.off_at = off_at;
    }
}
