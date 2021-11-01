package common.bo;

import common.enums.ErrorCode;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public Long code;

    public String message;

    public T data;

    public Integer errtag = 0;

    public BaseResponse() {
    }

    public BaseResponse(Long code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(Long code, Integer errtag, String message, T data) {
        super();
        this.code = code;
        this.errtag = errtag;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(T data) {
        super();
        this.code = ErrorCode.SUCCESS.getCode();
        this.message = ErrorCode.SUCCESS.getMessage();
        this.data = data;
    }

    public BaseResponse(ErrorCode e, T data) {
        super();
        this.code = e.getCode();
        this.message = e.getMessage();
        this.data = data;
    }

}

