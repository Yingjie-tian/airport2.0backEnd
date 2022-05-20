package com.bjtu.airport.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2022/5/14 11:45
 */
public class FakeUtil {
    private static  final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static List<String> fakeXAxis(String startDate, String endDate){
        if (startDate.equals(endDate)){
            return Collections.singletonList(startDate);
        }
        LocalDate start = LocalDate.parse(startDate,formatter);
        LocalDate end = LocalDate.parse(endDate,formatter);
        long days = end.toEpochDay() - start.toEpochDay();
        List<String> list = new ArrayList<>();
        if (days <= 5){
            for (int i = 1; i <= days; i++) {
                list.add(start.plusDays(i).format(formatter));
            }
            return list;
        }else {
            long period = (days-1)/3;
            list.add(start.format(formatter));
            for (int i = 1; i <= 3; i++) {
                list.add(start.plusDays(i*period).format(formatter));
            }
            list.add(end.format(formatter));
            return list;
        }
    }

    public static List<String> fakeXAxis(LocalDate startDate,LocalDate  endDate){
        if (startDate.compareTo(endDate) == 0){
            return Collections.singletonList(startDate.format(formatter));
        }
        long days = endDate.toEpochDay() - startDate.toEpochDay();
        List<String> list = new ArrayList<>();
        if (days <= 5){
            for (int i = 1; i <= days; i++) {
                list.add(startDate.plusDays(i).format(formatter));
            }
            return list;
        }else {
            long period = (days-1)/3;
            list.add(startDate.format(formatter));
            for (int i = 1; i <= 3; i++) {
                list.add(startDate.plusDays(i*period).format(formatter));
            }
            list.add(endDate.format(formatter));
            return list;
        }
    }


}
