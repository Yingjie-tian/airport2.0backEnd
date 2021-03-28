package com.bjtu.airport.service.impl;

import com.bjtu.airport.entity.AirlineLimit;
import com.bjtu.airport.entity.TimeParameter;
import com.bjtu.airport.mapper.AirlineLimitMapper;
import com.bjtu.airport.mapper.TimeParameterMapper;
import com.bjtu.airport.service.IAirlineLimitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjtu.airport.service.ITimeParameterService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class TimeParameterServiceImpl extends ServiceImpl<TimeParameterMapper, TimeParameter> implements ITimeParameterService {

    @Autowired
    private TimeParameterMapper timeParameterMapper;

    @Override
    public List<TimeParameter> getParameterInfo(String airportname, String userId){

        List<TimeParameter> info = timeParameterMapper.getParameterInfo(airportname, userId);

        return info;

    }

    @Override
    public Integer insertinfo(String airportname,
                              String  paraname,
                              Double warningtime1,
                              Double warningtime2,
                              String userId,
                              String updateTime){

        Integer info = timeParameterMapper.insertinfo(airportname, paraname, warningtime1,warningtime2, userId,updateTime);

        return info;

    }

    @Override
    public Integer updateInfo(Integer id,
                              String airportname,
                              String  paraname,
                              Double warningtime1,
                              Double warningtime2,
                              String userId,
                              String updateTime){

        Integer info = timeParameterMapper.updateInfo(id, airportname, paraname, warningtime1,warningtime2, userId,updateTime);

        return info;

    }

    @Override
    public Integer batchDelete(Integer id){

        Integer info = timeParameterMapper.batchDelete(id);

        return info;

    }

}