package zzzTest.parseJsonWithAnnotation.Models;

import lombok.Data;

import java.util.Map;

@Data
public class StoreInfoReason {
    //门店信息
    private Map<String, String> storeReason;

    //门店logo
    private Map<String, String> logoReason;

    //门店信息其他
    private String storeInfoOther;

    public StoreInfoReason() {
    }

    public StoreInfoReason(String storeInfoOther) {
        this.storeInfoOther = storeInfoOther;
    }
}
