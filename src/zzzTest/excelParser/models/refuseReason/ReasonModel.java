package zzzTest.excelParser.models.refuseReason;

import zzzTest.excelParser.excelParser.ExcelColumnTitle;

public class ReasonModel {
    @ExcelColumnTitle("模块")
    public String main_reason;

    @ExcelColumnTitle("驳回原因")
    public String detail_reason;

    public String getMain_reason() {
        return main_reason;
    }

    public void setMain_reason(String main_reason) {
        this.main_reason = main_reason;
    }

    public String getDetail_reason() {
        return detail_reason;
    }

    public void setDetail_reason(String detail_reason) {
        this.detail_reason = detail_reason;
    }
}
