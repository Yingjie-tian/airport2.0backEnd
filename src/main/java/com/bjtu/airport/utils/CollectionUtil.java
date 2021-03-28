package com.bjtu.airport.utils;

import java.util.Set;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2021/3/21 11:58
 */
public class CollectionUtil {

    public static double compareSame(Set<String> a,Set<String> b){
        double count = 0d;
        for (String out:a){
           if (b.contains(out)){
               count ++;
           }
        }
        return Double.parseDouble(String.format("%.2f", (count*2)/(a.size()+b.size()))) ;
    }
}
