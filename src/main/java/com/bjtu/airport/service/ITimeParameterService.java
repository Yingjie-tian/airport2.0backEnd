package com.bjtu.airport.service;

import com.bjtu.airport.entity.AirlineLimit;
import com.bjtu.airport.entity.TimeParameter;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;

public interface ITimeParameterService extends IService<TimeParameter>{

    List<TimeParameter> getParameterInfo(String airportname, String userId);

    Integer insertinfo(String airportname,
                       String  paraname,
                       Double warningtime1,
                       Double warningtime2,
                       String userId,
                       String updateTime);

    Integer updateInfo(Integer id,
                       String airportname,
                       String  paraname,
                       Double warningtime1,
                       Double warningtime2,
                       String userId,
                       String updateTime);

    Integer batchDelete(Integer id);
}
