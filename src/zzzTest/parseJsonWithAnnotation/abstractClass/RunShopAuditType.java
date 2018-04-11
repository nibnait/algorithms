package zzzTest.parseJsonWithAnnotation.abstractClass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum RunShopAuditType {

    AUDIT_STATUS(0,"审核状态"),
    SHOP_BASE(1, "店铺基础信息审核"),
    SHOP_LOGO(2, "店铺logo审核"),
    LEGAL_PERSON(3, "法人审核"),
    LICENSE(4, "主体资质审核"),
    PERMIT(5, "行业资质审核");

    private int code;
    private String desc;
}
