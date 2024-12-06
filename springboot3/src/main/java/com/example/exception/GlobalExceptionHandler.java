package com.example.exception;

import com.example.common.Result;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice("com.example.controller")
public class GlobalExceptionHandler {

//    private static final Log log = LogFactory.get();

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
//        log.error("异常信息", e);
        e.printStackTrace();
        return Result.error();
    }

    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public Result error(CustomException e) {
        //        log.error("异常信息", e);
        return Result.error(e.getCode(), e.getMessage());
    }
}
