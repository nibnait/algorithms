package zzzTest.parseJsonWithAnnotation.Models;

import lombok.Data;
import zzzTest.parseJsonWithAnnotation.abstractClass.ReasonTitle;

@Data
public class PermitReason {
    //资质照片
    @ReasonTitle("资质照片")
    private String permitPic;
    //单位名称
    @ReasonTitle("单位名称")
    private String permitCorpName;
    //法定代表人
    @ReasonTitle("法定代表人")
    private String permitPerson;
    //注册号
    @ReasonTitle("注册号")
    private String permitNum;
    //注册地址
    @ReasonTitle("注册地址")
    private String permitAddress;
    //有效期
    @ReasonTitle("有效期")
    private String permitExpireDate;

    @ReasonTitle("其他")
    private String permitOther;

    @ReasonTitle("自定义")
    private String permitCustom;
}
