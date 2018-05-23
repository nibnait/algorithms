package zzzTest;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class EnumTest {

    public static void main(String[] args) {
        System.out.println(UpdatedAtType.TODAY.equals(UpdatedAtType.TODAY));
    }

}
@Getter
@AllArgsConstructor
enum UpdatedAtType {
    ALL(0, "ALL", "全部"),
    TODAY(1, "TODAY", "当天"),
    LAST_3_DAYS(2, "LAST_3_DAYS", "近3天"),
    LAST_5_DAYS(3, "LAST_5_DAYS", "近5天"),
    FIVE_DAYS_AGO(4, "FIVE_DAYS_AGO", "5天前");

    private int index;
    private String description;
    private String chineseDesc;


}