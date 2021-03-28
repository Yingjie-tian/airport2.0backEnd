package com.bjtu.airport.service;

import com.bjtu.airport.entity.RuleParameter;
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
public interface IRuleParameterService extends IService<RuleParameter> {
    List<RuleParameter> getinfo();

    List<RuleParameter> getinfo(String airportname,String useId);

    List<RuleParameter> getParameter(String airportname, String userId);


    int insertinfo(Double warningtime1, Double warningtime2,
                   String airportname,  String userId,
                   String updateTime);


    int updateinfo1(Integer id, Double warningtime1, String userId);


    int updateinfo2(Integer id, Double warningtime2, String userId);

}
