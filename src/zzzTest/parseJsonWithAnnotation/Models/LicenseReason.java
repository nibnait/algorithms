package zzzTest.parseJsonWithAnnotation.Models;

import lombok.Data;
import zzzTest.parseJsonWithAnnotation.abstractClass.ReasonTitle;

@Data
public class LicenseReason {
    //资质照片
    @ReasonTitle("资质照片")
    private String licensePic;
    //单位名称
    @ReasonTitle("单位名称")
    private String licenseCorpName;
    //法定代表人
    @ReasonTitle("法定代表人")
    private String licensePerson;
    //注册号
    @ReasonTitle("注册号")
    private String licenseNum;
    //注册地址
    @ReasonTitle("注册地址")
    private String licenseAddress;
    //有效期
    @ReasonTitle("有效期")
    private String licenseExpireDate;

    @ReasonTitle("其他")
    private String licenseOther;

    @ReasonTitle("自定义")
    private String licenseCustom;
}
