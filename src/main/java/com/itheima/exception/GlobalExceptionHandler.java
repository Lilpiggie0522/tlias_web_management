package com.itheima.exception;

import com.itheima.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ClassName: GlobalExceptionHandler
 * Package: com.itheima.exception
 * Description:
 *
 * @Author Piggie
 * @Create 30/01/2024 5:01 pm
 * @Version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result ex(Exception exception) {
        exception.printStackTrace();
        return Result.error("sorry, execution failed");
    }
}
