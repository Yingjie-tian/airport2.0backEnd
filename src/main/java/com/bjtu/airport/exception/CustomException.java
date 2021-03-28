package com.bjtu.airport.exception;


import com.bjtu.airport.contant.ResultCode;

/**
 * @author: LiuYi
 * @date: 2019/4/18 21:43
 */

public class CustomException extends RuntimeException {

    private ResultCode resultCode;

    public CustomException(ResultCode resultCode) {
        super("错误代码：" + resultCode.getCode() + "   错误信息：" + resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}