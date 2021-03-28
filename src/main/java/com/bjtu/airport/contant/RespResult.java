package com.bjtu.airport.contant;

import lombok.Data;

/**
 * @author: LiuYi
 * @date: 2019/4/19 22:22
 */
@Data
public class RespResult extends EmptyResult {

    Object data;

    public RespResult(ResultCode resultCode, Object data) {
        super(resultCode);
        this.data = data;
    }
}
