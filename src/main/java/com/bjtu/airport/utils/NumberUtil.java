package com.bjtu.airport.utils;

import java.util.Optional;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2021/3/23 17:08
 */
public class NumberUtil {
    private static final Integer ZERO_INT = 0;
    private static final Double  ZERO_DOUBLE = 0d;
    private static final Long  ZERO_LONG = 0L;
    public static Integer gdi(Integer val){
      return  Optional.ofNullable(val).orElse(0);
    }

    public static Double gdd(Double val){
        return  Optional.ofNullable(val).orElse(0d);
    }

    public static Double gdd(Integer val){
        return  Optional.ofNullable(val).orElse(ZERO_INT).doubleValue();
    }
    public static Double gdd(Long val){
        return  Optional.ofNullable(val).orElse(ZERO_LONG).doubleValue();
    }
}
