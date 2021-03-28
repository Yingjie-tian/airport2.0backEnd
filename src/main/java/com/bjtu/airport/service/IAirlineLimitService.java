package com.bjtu.airport.service;

import com.bjtu.airport.entity.AirlineLimit;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */
public interface IAirlineLimitService extends IService<AirlineLimit> {

//    List<AirlineLimit> getinfo();


    List<AirlineLimit> getLimitInfo(String airportname, String userId);

    Integer insertinfo(String airlineCd,
                       String airlinename,
                       String  parkinggate,
                       Integer state,
                       String airportname,
                       String userId,
                       String updateTime);

    Integer updateInfo(Integer id,
                       String airlineCd,
                       String airlinename,
                       String  parkinggate,
                       Integer state,
                       String airportname,
                       String userId,
                       String updateTime);

    Integer batchDelete(Integer id);
















    List<AirlineLimit> getLimitInfoByuserId(String airportname, String userId);




    int updateinfo1(Integer id, String airline_cd,  String userId);


    int updateinfo2(Integer id, String parkinggate,  String userId);


    int updateinfo3(Integer id, Integer state,  String userId);


    int deleteAirlineLimit(Integer id);

}
