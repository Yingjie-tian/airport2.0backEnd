package com.bjtu.airport.service.impl;

import com.bjtu.airport.entity.AirRule;
import com.bjtu.airport.mapper.AirRuleMapper;
import com.bjtu.airport.service.IAirRuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-17
 */
@Service
public class AirRuleServiceImpl extends ServiceImpl<AirRuleMapper, AirRule> implements IAirRuleService {

    @Autowired
    AirRuleMapper airRuleMapper;


    @Override
    public List<JSONObject> getAirport(){

        List<JSONObject> info = airRuleMapper.getAirport();

        return info;
    }


    @Override
    public int insertAirRule(String airportname, Integer statenum,  Integer state,
                             String userId, String updateTime) {

        int info = airRuleMapper.insertAirRule(airportname, statenum, state, userId, updateTime);

        return info;
    }


    @Override
    public int updateState(Integer id, Integer state, String userId){

        int info = airRuleMapper.updateState(id,state, userId);

        return info;
    }


    @Override
    public int deleteAirRule(Integer id, String userId){

        int info = airRuleMapper.deleteAirRule(id, userId);

        return info;
    }

}
