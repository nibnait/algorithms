package zzzTest.excelLoader.test;

import zzzTest.excelLoader.ExcelColumnTitle;

public class FoodMall {

    @ExcelColumnTitle("店铺名称")
    public String shop_name;
    @ExcelColumnTitle("店铺ID（选填）")
    public Long shop_id;
    @ExcelColumnTitle("店铺绑定手机号")
    public String shop_phone;
    @ExcelColumnTitle("报备类型")
    public String shop_type;        //TODO
    @ExcelColumnTitle("美食城名称")
    public String tag_mall_name;
    @ExcelColumnTitle("美食城地址")
    public String tag_shop_address;
    @ExcelColumnTitle("美食城共用的营业执照号")
    public String tag_shop_license_num;
    @ExcelColumnTitle("美食城共用的许可证号")
    public String tag_shop_permit_num;
    @ExcelColumnTitle("管理部")
    public String manage_department;
    @ExcelColumnTitle("战区")
    public String division_name;
    @ExcelColumnTitle("战团")
    public String corps_name;
    @ExcelColumnTitle("战营")
    public String camp_name;
    @ExcelColumnTitle("提报人")
    public String apply_user_name;
    public String tag_name="美食城";
    public String tag_brand_name="美食城";

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public Long getShop_id() {
        return shop_id;
    }

    public void setShop_id(Long shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_phone() {
        return shop_phone;
    }

    public void setShop_phone(String shop_phone) {
        this.shop_phone = shop_phone;
    }

    public String getShop_type() {
        return shop_type;
    }

    public void setShop_type(String shop_type) {
        this.shop_type = shop_type;
    }

    public String getTag_mall_name() {
        return tag_mall_name;
    }

    public void setTag_mall_name(String tag_mall_name) {
        this.tag_mall_name = tag_mall_name;
    }

    public String getTag_shop_address() {
        return tag_shop_address;
    }

    public void setTag_shop_address(String tag_shop_address) {
        this.tag_shop_address = tag_shop_address;
    }

    public String getTag_shop_license_num() {
        return tag_shop_license_num;
    }

    public void setTag_shop_license_num(String tag_shop_license_num) {
        this.tag_shop_license_num = tag_shop_license_num;
    }

    public String getTag_shop_permit_num() {
        return tag_shop_permit_num;
    }

    public void setTag_shop_permit_num(String tag_shop_permit_num) {
        this.tag_shop_permit_num = tag_shop_permit_num;
    }

    public String getManage_department() {
        return manage_department;
    }

    public void setManage_department(String manage_department) {
        this.manage_department = manage_department;
    }

    public String getDivision_name() {
        return division_name;
    }

    public void setDivision_name(String division_name) {
        this.division_name = division_name;
    }

    public String getCorps_name() {
        return corps_name;
    }

    public void setCorps_name(String corps_name) {
        this.corps_name = corps_name;
    }

    public String getCamp_name() {
        return camp_name;
    }

    public void setCamp_name(String camp_name) {
        this.camp_name = camp_name;
    }

    public String getApply_user_name() {
        return apply_user_name;
    }

    public void setApply_user_name(String apply_user_name) {
        this.apply_user_name = apply_user_name;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public String getTag_brand_name() {
        return tag_brand_name;
    }

    public void setTag_brand_name(String tag_brand_name) {
        this.tag_brand_name = tag_brand_name;
    }
}
