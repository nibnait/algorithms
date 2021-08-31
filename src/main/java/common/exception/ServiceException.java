package common.exception;

import common.util.DataUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceException extends RuntimeException {

    private Long code;

    private String message;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String message) {
        this.message = message;
    }

    public ServiceException() { super(); }

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(Long code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }

    public ServiceException(String message, Object... args) {
        super(DataUtils.format(message, args));
    }

}
