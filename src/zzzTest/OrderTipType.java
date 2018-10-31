package zzzTest;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderTipType {

    OPEN_LATE("openLate", "没有打开app,导致漏单"),
    CONTACT_PHONE("contactPhone", "接单手机号"),
    INVENTORY("inventory", "库存"),
    CLOSE_EARLY("closeEarly", "由于打烊而无效订单"),
    NOT_FIT_DELIVERY_AREA("notFitDeliveryArea", "不恰当的配送范围"),
    CREDIT("credit","信用分"),
    WECHAT("wechat","微信接单小助手"),
    ARBITRATING("arbitrating", "仲裁中订单"),
    ORDER_BUSSINESS_2017("orderBussiness2017","2017商家版年度账单"),
    CONTRACT_POINT("contract_point", "履约评分"),
    UGC_UNREPLY_REMIND("ugc_unreply_remind", "用户回复评价"),

    ADVERST("adverst", "广告");

    private String code;
    private String description;

}
