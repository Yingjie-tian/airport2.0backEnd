package com.bjtu.airport.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjtu.airport.entity.RuleDescription;
import com.bjtu.airport.mapper.RuleDescriptionMapper;
import com.bjtu.airport.service.IRuleDescriptionService;
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
 * @since 2020-03-01
 */
@Service

public class RuleDescriptionImpl extends ServiceImpl<RuleDescriptionMapper, RuleDescription> implements IRuleDescriptionService {

    @Autowired
    RuleDescriptionMapper ruleDescriptionMapper;

    @Override
    public List<RuleDescription> getinfo(){
        List<RuleDescription> info = ruleDescriptionMapper.getinfo();
        return info;

    }


    /**
     * 根据机场名称和用户id查找相应规则
     * @param airportname
     * @param userId
     * @return
     */
    public List<JSONObject> getByAirportname(String airportname, String userId){

        List<JSONObject> info = ruleDescriptionMapper.getByAirportname(airportname,userId);

        return info;
    }



    @Override
    public int insertinfo(Integer state, String describe,
                          Integer parastate,  String corrtablename,String paraname,
                          String airportname,String userId,
                          String updateTime) {

        int info = ruleDescriptionMapper.insertinfo(state, describe, parastate,corrtablename, paraname, airportname, userId, updateTime);

        return info;
    }

    @Override
    public int deleteinfo(Integer id){
        int info = ruleDescriptionMapper.deleteinfo(id);
        return info;
    }


    @Override
    public int updateinfo(Integer id, Integer state){
        int info = ruleDescriptionMapper.updateinfo(id,state);
        return info;
    }
}
