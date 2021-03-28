package com.bjtu.airport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjtu.airport.entity.RuleDescription;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */
public interface IRuleDescriptionService extends IService<RuleDescription> {

    List<RuleDescription>getinfo();


//    List<RuleDescription> getByAirportname(String airportname);

    /**
     * 根据机场名称和用户id查找相应规则
     * @param airportname
     * @param userId
     * @return
     */
    List<JSONObject> getByAirportname(String airportname, String userId);


    int insertinfo(Integer state, String describe,
                   Integer parastate, String corrtablename, String paraname,
                   String airportname, String userId,
                   String updateTime);

    int deleteinfo(Integer id);


    int updateinfo(Integer id, Integer state);

}
