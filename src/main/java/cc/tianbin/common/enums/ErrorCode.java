package cc.tianbin.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by nibnait on 2021/09/01
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {
    SUCCESS(0L, "success"),
    SERVICE_ERROR(83000004L, "service error"),

    ;

    private Long code;
    private String message;

}
