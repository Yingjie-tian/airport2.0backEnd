package com.bjtu.airport.contant;

/**
 * @author: LiuYi
 * @date: 2019/4/19 22:44
 */
public interface Result {

    boolean isSuccess();

    int getCode();

    String getMessage();

    default Object getData() {
        return null;
    }

    ;
}
