package zzzTest.parseJsonWithAnnotation.Models;

import lombok.Data;
import zzzTest.parseJsonWithAnnotation.abstractClass.ReasonTitle;

@Data
public class LogoReason {
    @ReasonTitle("门店logo")
    private String storeLogo;
    @ReasonTitle("自定义")
    private String logoCustom;
}
