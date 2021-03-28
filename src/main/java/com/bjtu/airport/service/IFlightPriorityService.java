package com.bjtu.airport.service;

import com.bjtu.airport.entity.FlightInfo;
import com.bjtu.airport.entity.FlightPriority;
import com.baomidou.mybatisplus.extension.service.IService;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */
public interface IFlightPriorityService extends IService<FlightPriority> {

    List<JSONObject> getPriorityByAirportname(String airportname, String userId);

    int updatesortrule(Integer id, Integer sortrule);

    int deleteByAirportName(String airportname, String userId);

    int insertinfo(String airportname, String flighttype, String flightkey, Integer prioritynum, Integer sortrule, String userId, String updateTime);

}
