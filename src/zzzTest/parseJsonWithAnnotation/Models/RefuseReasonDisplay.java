package zzzTest.parseJsonWithAnnotation.Models;

import lombok.Data;

@Data
public class RefuseReasonDisplay {
    //门店信息
    private StoreInfoReason  storeInfoReason;
    //资质信息
    private QualificationInfoReason qualificationInfoReason;
}
