package com.bjtu.airport.service.impl;

import com.bjtu.airport.entity.AirlineLimit;
import com.bjtu.airport.mapper.AirlineLimitMapper;
import com.bjtu.airport.service.IAirlineLimitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */
@Service
public class AirlineLimitServiceImpl extends ServiceImpl<AirlineLimitMapper, AirlineLimit> implements IAirlineLimitService {

//    @Autowired
//    private AirlineLimitMapper AirlineLimitMapper;
//
//    @Override
//    public List<AirlineLimit> getinfo() {
//        List<AirlineLimit> info = AirlineLimitMapper.getinfo();
//        return info;
//    }
    @Autowired
    AirlineLimitMapper airlineLimitMapper;

    @Override
    public List<AirlineLimit> getLimitInfo(String airportname, String userId){

        List<AirlineLimit> info = airlineLimitMapper.getLimitInfo(airportname, userId);

        return info;

    }


    @Override
    public Integer insertinfo(String airlineCd,
                              String airlinename,
                              String  parkinggate,
                              Integer state,
                              String airportname,
                              String userId,
                              String updateTime){

        Integer info = airlineLimitMapper.insertinfo(airlineCd, airlinename, parkinggate,state, airportname, userId,updateTime);

        return info;

    }

    @Override
    public Integer updateInfo(Integer id,
                              String airlineCd,
                              String airlinename,
                              String  parkinggate,
                              Integer state,
                              String airportname,
                              String userId,
                              String updateTime){

        Integer info = airlineLimitMapper.updateInfo(id, airlineCd, airlinename, parkinggate,state, airportname, userId,updateTime);

        return info;

    }

    @Override
    public Integer batchDelete(Integer id){

        Integer info = airlineLimitMapper.batchDelete(id);

        return info;

    }














    @Override
    public List<AirlineLimit> getLimitInfoByuserId(String airportname, String userId){

        List<AirlineLimit> info = airlineLimitMapper.getLimitInfoByuserId(airportname, userId);

        return info;

    }



//    @Override
//    public int insertinfo(String airline_cd, String parkinggate,
//                          Integer state,
//                          String airportname,  String userId,
//                          String updateTime) {
//
//        int info = airlineLimitMapper.insertinfo(airline_cd, parkinggate, state, airportname,userId, updateTime);
//
//        return info;
//    }


    @Override
    public int updateinfo1(Integer id, String airline_cd,  String userId){

        int info = airlineLimitMapper.updateinfo1(id,airline_cd, userId);

        return info;
    }


    @Override
    public int updateinfo2(Integer id, String parkinggate,  String userId){

        int info = airlineLimitMapper.updateinfo2(id,parkinggate, userId);

        return info;
    }


    @Override
    public int updateinfo3(Integer id, Integer state,  String userId){

        int info = airlineLimitMapper.updateinfo3(id,state,userId);

        return info;
    }


    @Override
    public int deleteAirlineLimit(Integer id){

        int info = airlineLimitMapper.deleteAirlineLimit(id);

        return info;
    }

}
