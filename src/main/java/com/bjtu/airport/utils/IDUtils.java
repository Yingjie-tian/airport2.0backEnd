package com.bjtu.airport.utils;

import java.util.Calendar;
import java.util.UUID;

/**
 * @author: LiuYi
 * @date: 2019/5/6 0:08
 */
public class IDUtils {


    public static String id(Object object) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        StringBuilder builder = new StringBuilder();
        builder.append(year).append("X").append(uuid());
        return builder.toString();
    }


    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
