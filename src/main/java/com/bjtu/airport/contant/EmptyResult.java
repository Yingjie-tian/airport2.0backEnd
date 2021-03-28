package com.bjtu.airport.contant;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: LiuYi
 * @date: 2019/4/18 22:13
 */
@Data
@ToString
@NoArgsConstructor
public class EmptyResult implements Result {

    //操作是否成功
    boolean success;

    //操作代码
    int code;

    //提示信息
    String message;

    public EmptyResult(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public EmptyResult(ResultCode resultCode) {
        this.success = resultCode.isSuccess();
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public static EmptyResult SUCCESS() {
        return new EmptyResult(ResultCode.SUCCESS);
    }

    public static EmptyResult FAIL() {
        return new EmptyResult(ResultCode.FAIL);
    }

}



