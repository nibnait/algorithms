package zzzTest.parseJsonWithAnnotation.Models;

import lombok.Data;
import zzzTest.parseJsonWithAnnotation.abstractClass.ReasonTitle;

@Data
public class LegalReason {
    //真实姓名
    @ReasonTitle("真实姓名")
    private String realName;
    //证件号码
    @ReasonTitle("证件号码")
    private String idNum;
    //正面照
    @ReasonTitle("证件照片-正面")
    private String positivePic;
    //反面照
    @ReasonTitle("证件照片-反面")
    private String oppositePic;
    //手持正面照
    @ReasonTitle("证件照片-手持正面照")
    private String holdOnPic;

    @ReasonTitle("其他")
    private String legalOther;

    @ReasonTitle("自定义")
    private String legalCustom;
}
