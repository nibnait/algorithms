package zzzTest.parseJsonWithAnnotation.Models;

import lombok.Data;
import zzzTest.parseJsonWithAnnotation.abstractClass.ReasonTitle;

@Data
public class StoreReason {
    @ReasonTitle("门店名称")
    private String storeName;
    //门脸照
    @ReasonTitle("门脸照")
    private String doorPic;
    //店内照
    @ReasonTitle("店内照")
    private String storePic;

    @ReasonTitle("其他")
    private String storeOther;

    @ReasonTitle("自定义")
    private String storeCustom;
}
