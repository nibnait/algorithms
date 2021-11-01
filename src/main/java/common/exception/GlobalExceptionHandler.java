package common.exception;

import common.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public GlobalExceptionResponse methodArgumentTypeMismatchExceptionHandler(HttpServletRequest req, MethodArgumentTypeMismatchException e) {

        String p = e.getParameter().getParameterName();
        String message = "param error: " + p;
        Long code = ErrorCode.SERVICE_ERROR.getCode();
        GlobalExceptionResponse globalExceptionResponse = new GlobalExceptionResponse(code, message);
        log.error("MethodArgumentTypeMismatchException: from:{} uri:{} code:{} msg:{}", req.getRemoteHost(), req.getRequestURI(), code, message, e);
        return globalExceptionResponse;
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public GlobalExceptionResponse missingServletRequestParameterExceptionHandler(HttpServletRequest req, MissingServletRequestParameterException e) {

        String p = e.getParameterName();
        String message = "param missing error: " + p;

        Long code = ErrorCode.SERVICE_ERROR.getCode();
        GlobalExceptionResponse globalExceptionResponse = new GlobalExceptionResponse(code, message);
        log.error("MethodArgumentTypeMismatchException: from:{} uri:{} code:{} msg:{}", req.getRemoteHost(), req.getRequestURI(), code, message, e);
        return globalExceptionResponse;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public GlobalExceptionResponse methodArgumentNotValidExceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e) {
        String p = e.getParameter().getParameterName();
        BindingResult bindingResult = e.getBindingResult();
        String fieldErrorMessage = "";
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                sb.append(((FieldError) objectError).getField() + ":").append(objectError.getDefaultMessage()).append("; ");
            }
            fieldErrorMessage = sb.toString();
        }
        String message = "param error: " + p + ", " + fieldErrorMessage;
        Long code = ErrorCode.SERVICE_ERROR.getCode();
        GlobalExceptionResponse globalExceptionResponse = new GlobalExceptionResponse(code, message);
        log.error("MethodArgumentNotValidException: from:{} uri:{} code:{} msg:{}", req.getRemoteHost(), req.getRequestURI(), code, message, e);
        return globalExceptionResponse;
    }

    @ExceptionHandler(common.exception.ServiceException.class)
    @ResponseBody
    public GlobalExceptionResponse exceptionHandler(HttpServletRequest req, common.exception.ServiceException e) {
        String message = e.getMessage();
        if (null == message || message.equals("")) {
            message = "server runtime error";
        }

        Long code = e.getCode();
        if (null == code) {
            code = ErrorCode.SERVICE_ERROR.getCode();
        }

        GlobalExceptionResponse globalExceptionResponse = new GlobalExceptionResponse(code, message);
        log.error("ServiceException: from:{} uri:{} code:{} msg:{}", req.getRemoteHost(), req.getRequestURI(), code, message, e);
        return globalExceptionResponse;
    }

    @ExceptionHandler(ClientViewException.class)
    @ResponseBody
    public GlobalExceptionResponse viewExceptionHandler(HttpServletRequest req, ClientViewException e) {
        String message = e.getMessage();
        if (null == message || message.equals("")) {
            message = "server runtime error";
        }

        Long code = e.getCode();
        if (null == code) {
            code = ErrorCode.SERVICE_ERROR.getCode();
        }
        GlobalExceptionResponse globalExceptionResponse = new GlobalExceptionResponse(code, message);
        log.info("ClientViewException: from:{} uri:{} code:{} message:{}", req.getRemoteHost(), req.getRequestURI(), code, message, e);
        return globalExceptionResponse;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public GlobalExceptionResponse illegalArgumentExceptionHandler(HttpServletRequest req, IllegalArgumentException e) {
        String message = e.getMessage();
        if (null == message || message.equals("")) {
            message = "IllegalArgumentException";
        }

        GlobalExceptionResponse globalExceptionResponse = new GlobalExceptionResponse(ErrorCode.SERVICE_ERROR.getCode(), message);
        log.info("IllegalArgumentException: from:{} uri:{} message:{}", req.getRemoteHost(), req.getRequestURI(), message, e);
        return globalExceptionResponse;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public GlobalExceptionResponse runtimeExceptionHandler(HttpServletRequest req, RuntimeException e) {
        log.error("RuntimeException: from:{} uri:{} error:{}", req.getRemoteHost(), req.getRequestURI(), e);
        return new GlobalExceptionResponse(ErrorCode.SERVICE_ERROR.getCode(), ErrorCode.SERVICE_ERROR.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public GlobalExceptionResponse defaultExceptionHandler(HttpServletRequest req, Exception e) {
        log.error("Exception: from:{} uri:{} error:{}", req.getRemoteHost(), req.getRequestURI(), e);
        return new GlobalExceptionResponse(ErrorCode.SERVICE_ERROR.getCode(), ErrorCode.SERVICE_ERROR.getMessage());
    }

}
