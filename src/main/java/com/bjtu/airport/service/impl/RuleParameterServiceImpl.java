package com.bjtu.airport.service.impl;

import com.bjtu.airport.entity.RuleParameter;
import com.bjtu.airport.mapper.RuleParameterMapper;
import com.bjtu.airport.service.IRuleParameterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class RuleParameterServiceImpl extends ServiceImpl<RuleParameterMapper, RuleParameter> implements IRuleParameterService {
    @Autowired
    private RuleParameterMapper RuleParameterMapper;

    @Override
    public List<RuleParameter> getinfo() {
        List<RuleParameter> info = RuleParameterMapper.getinfo();
        return info;
    }

    @Autowired
    RuleParameterMapper ruleParameterMapper;

    @Override
    public List<RuleParameter> getinfo(String airportname,String useId){

        List<RuleParameter> info = ruleParameterMapper.getinfo(airportname, useId);

        return info;

    }


    @Override
    public List<RuleParameter> getParameter(String airportname, String userId){

        List<RuleParameter> info = ruleParameterMapper.getParameter(airportname, userId);

        return info;

    }



    @Override
    public int insertinfo(Double warningtime1, Double warningtime2,
                          String airportname,  String userId,
                          String updateTime) {

        int info = ruleParameterMapper.insertinfo(warningtime1, warningtime2, airportname,userId, updateTime);

        return info;
    }


    @Override
    public int updateinfo1(Integer id, Double warningtime1, String userId){

        int info = ruleParameterMapper.updateinfo1(id,warningtime1, userId);

        return info;
    }


    @Override
    public int updateinfo2(Integer id, Double warningtime2, String userId){

        int info = ruleParameterMapper.updateinfo2(id,warningtime2, userId);

        return info;
    }

}
