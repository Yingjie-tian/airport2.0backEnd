package com.bjtu.airport.vo;


import com.bjtu.airport.contant.EmptyResult;
import com.bjtu.airport.contant.RespResult;
import com.bjtu.airport.contant.Result;
import com.bjtu.airport.contant.ResultCode;

/**
 * @author: LiuYi
 * @date: 2019/4/19 22:20
 */
public class ResultVo {

    public static Result ok(Object data) {
        return new RespResult(ResultCode.SUCCESS, data);
    }

    public static Result ok() {
        return new RespResult(ResultCode.SUCCESS, null);
    }

    public static Result error(ResultCode resultCode) {
        return new EmptyResult(resultCode);
    }

    public static Result error(ResultCode resultCode, Object data) {
        return new RespResult(resultCode, data);
    }

    public static Result error(Object data) {
        return new RespResult(ResultCode.FAIL, data);
    }


}
