package common.exception;

import common.enums.ErrorCode;

import java.io.Serializable;

/**
 * Created by nibnait on 2021/09/01
 */
public class GlobalExceptionResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    public Long code;
    public String message;
    public Integer errtag = 0;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GlobalExceptionResponse(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    public GlobalExceptionResponse(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}
