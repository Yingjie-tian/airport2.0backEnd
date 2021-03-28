package com.bjtu.airport.service.impl;

import com.bjtu.airport.entity.FlightInfo;
import com.bjtu.airport.entity.FlightPriority;
import com.bjtu.airport.mapper.FlightPriorityMapper;
import com.bjtu.airport.service.IFlightPriorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */
@Service
public class FlightPriorityServiceImpl extends ServiceImpl<FlightPriorityMapper, FlightPriority> implements IFlightPriorityService {


    @Autowired
    FlightPriorityMapper flightPriorityMapper;

    @Override
    public List<JSONObject> getPriorityByAirportname(String airportname, String userId) {
        List<JSONObject> priorityInfo = flightPriorityMapper.getPriorityByAirportname(airportname, userId);
        return priorityInfo;
    }

    @Override
    public int updatesortrule(Integer id, Integer sortrule) {
        int info = flightPriorityMapper.updatesortrule(id, sortrule);
        return info;
    }

    @Override
    public int deleteByAirportName(String airportname, String userId) {
        int info = flightPriorityMapper.deleteByAirportName(airportname, userId);
        return info;
    }

    @Override
    public int insertinfo(String airportname, String flighttype, String flightkey, Integer prioritynum, Integer sortrule, String userId, String updateTime) {
        int info = flightPriorityMapper.insertinfo(airportname, flighttype, flightkey, prioritynum, sortrule, userId, updateTime);
        return info;
    }
}
