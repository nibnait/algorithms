package common.exception;

import common.util.DataUtils;

public class ClientViewException extends RuntimeException {

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

    public ClientViewException() { super(); }

    public ClientViewException(String message){
        super(message);
    }

    public ClientViewException(String message, Object... args) {
        super(DataUtils.format(message, args));
    }

    public ClientViewException(Long code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }

}
