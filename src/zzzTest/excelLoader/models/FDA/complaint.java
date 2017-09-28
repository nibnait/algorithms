package zzzTest.excelLoader.models.FDA;

import zzzTest.excelLoader.excelReader.ExcelColumnTitle;

public class complaint {
    @ExcelColumnTitle("店铺id")
    public String shop_id;

    @ExcelColumnTitle("城市id")
    public String city_id;

    @ExcelColumnTitle("区域id")
    public String area;

    @ExcelColumnTitle("投诉类别")
    public String reason;

    @ExcelColumnTitle("投诉描述（页面会展示）")
    public String detailed_reason;

    @ExcelColumnTitle("投诉时间")
    public String off_at;

    @ExcelColumnTitle("是否展示营业执照")
    public String business_license_tag;

    @ExcelColumnTitle("是否展示卫生许可证")
    public String service_license_tag;

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

    public String getBusiness_license_tag() {
        return business_license_tag;
    }

    public void setBusiness_license_tag(String business_license_tag) {
        this.business_license_tag = business_license_tag;
    }

    public String getService_license_tag() {
        return service_license_tag;
    }

    public void setService_license_tag(String service_license_tag) {
        this.service_license_tag = service_license_tag;
    }
}
