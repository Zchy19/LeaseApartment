package com.zchy.lease.common.exception;

import com.zchy.lease.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.rmi.dgc.Lease;

/**
 * @projectName: lease
 * @package: com.zchy.lease.common.exception
 * @className: GlobalExceptionHandler
 * @author: ZCH
 * @description:
 * @date: 8/4/2024 9:52 PM
 * @version: 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exception(Exception e) {
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(LeaseException.class)
    @ResponseBody
    public Result LeaseException(LeaseException e) {
        e.printStackTrace();
        return Result.fail(e.getCode(), e.getMessage());
    }


}
