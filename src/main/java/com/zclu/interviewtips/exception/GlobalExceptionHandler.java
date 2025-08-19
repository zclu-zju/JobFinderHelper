package com.zclu.interviewtips.exception;

import com.zclu.interviewtips.result.Result;
import com.zclu.interviewtips.result.ResultCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result<Void> handleException(Exception e) {
        return Result.error(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result<Void> handleIllegalArgumentException(IllegalArgumentException e) {
        return Result.error(ResultCode.BAD_REQUEST, e.getMessage());
    }
}
