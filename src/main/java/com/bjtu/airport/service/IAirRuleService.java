package com.bjtu.airport.service;

import com.bjtu.airport.entity.AirRule;
import com.baomidou.mybatisplus.extension.service.IService;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-17
 */
public interface IAirRuleService extends IService<AirRule> {

    List<JSONObject> getAirport();


    int insertAirRule(String airportname, Integer statenum,  Integer state,
                      String userId, String updateTime);


    int updateState(Integer id, Integer state, String userId);


    int deleteAirRule(Integer id, String userId);

}
