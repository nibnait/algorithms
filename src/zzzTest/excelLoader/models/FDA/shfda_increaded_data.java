package zzzTest.excelLoader.models.FDA;

import zzzTest.excelLoader.excelReader.ExcelColumnTitle;

public class shfda_increaded_data {
    @ExcelColumnTitle("城市ID")
    public String city_id;

    @ExcelColumnTitle("数据更新日期")
    public String data_deadline;

    @ExcelColumnTitle("上周新增合规商户")
    public String last_week_increase;

    @ExcelColumnTitle("上周同比增长")
    public String week_YoY;

    @ExcelColumnTitle("上周环比增长")
    public String week_QoQ;

    @ExcelColumnTitle("上月新增合规商户数")
    public String last_month_increase;

    @ExcelColumnTitle("上月同比增长")
    public String month_YoY;

    @ExcelColumnTitle("上月环比增长")
    public String month_QoQ;

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getData_deadline() {
        return data_deadline;
    }

    public void setData_deadline(String data_deadline) {
        this.data_deadline = data_deadline;
    }

    public String getLast_week_increase() {
        return last_week_increase;
    }

    public void setLast_week_increase(String last_week_increase) {
        this.last_week_increase = last_week_increase;
    }

    public String getWeek_YoY() {
        return week_YoY;
    }

    public void setWeek_YoY(String week_YoY) {
        this.week_YoY = week_YoY;
    }

    public String getWeek_QoQ() {
        return week_QoQ;
    }

    public void setWeek_QoQ(String week_QoQ) {
        this.week_QoQ = week_QoQ;
    }

    public String getLast_month_increase() {
        return last_month_increase;
    }

    public void setLast_month_increase(String last_month_increase) {
        this.last_month_increase = last_month_increase;
    }

    public String getMonth_YoY() {
        return month_YoY;
    }

    public void setMonth_YoY(String month_YoY) {
        this.month_YoY = month_YoY;
    }

    public String getMonth_QoQ() {
        return month_QoQ;
    }

    public void setMonth_QoQ(String month_QoQ) {
        this.month_QoQ = month_QoQ;
    }
}
