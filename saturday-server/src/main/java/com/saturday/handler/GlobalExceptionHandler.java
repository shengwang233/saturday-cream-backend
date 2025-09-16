package com.saturday.handler;


import com.saturday.exception.BaseException;
import com.saturday.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice

public class GlobalExceptionHandler {

    /**
     * global exception handler
     *
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex) {
        return Result.error(ex.getMessage());
    }

    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        String message = ex.getMessage();
        //Duplicate entry 'ming234' for key 'employee.idx_username';
        if (message.contains("Duplicate entry")) {
            String[] split = message.split(" ");
            String username = split[2];
            String msg = username + " " + "Already existed";
            return Result.error(msg);
        } else {
            return Result.error("Unknow Error");
        }
    }

}
