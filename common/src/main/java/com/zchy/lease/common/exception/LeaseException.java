package com.zchy.lease.common.exception;

import com.zchy.lease.common.result.ResultCodeEnum;
import lombok.Data;

/**
 * @projectName: lease
 * @package: com.zchy.lease.common.exception
 * @className: LeaseException
 * @author: ZCH
 * @description:
 * @date: 8/5/2024 8:43 PM
 * @version: 1.0
 */
@Data
public class LeaseException extends RuntimeException{
    private Integer code;
    private String message;

    public LeaseException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }
}
