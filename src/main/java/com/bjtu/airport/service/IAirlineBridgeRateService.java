package com.bjtu.airport.service;


import com.bjtu.airport.entity.AirlineBridgeRate;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bjtu.airport.entity.AirlineLimit;

import java.util.List;

public interface IAirlineBridgeRateService extends IService<AirlineBridgeRate>{

    List<AirlineBridgeRate> getBridgeRateInfo(String airportname, String userId);

    Integer insertinfo(String airportname, String airlineCd,
                       String  airlinename,
                       String bridgerate,
                       String userId,
                       String updateTime);

    Integer updateInfo(Integer id,
                       String bridgerate,
                       String  airlinename,
                       String airlineCd,
                       String userId,
                       String updateTime);

    Integer batchDelete(Integer id);
}
