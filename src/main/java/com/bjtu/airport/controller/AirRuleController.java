package com.bjtu.airport.controller;


import com.bjtu.airport.entity.AirRule;
import com.bjtu.airport.service.IAirRuleService;
import com.bjtu.airport.service.IFlightInfoService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-17
 */
@RestController
@CrossOrigin
@RequestMapping("/air-rule")
public class AirRuleController {

    @Autowired
    IAirRuleService iAirRuleService;




    /**
     * 从AIR_RULE表中取得机场
     * @return
     */
    @RequestMapping("/getAirport")
    public List<JSONObject> getAirport(){

        List<JSONObject> list = iAirRuleService.getAirport();

        return list;
    }




    /**
     * 向AIR_RULE表中插入数据
     * @param airRuleInfo
     * @return
     */
    @RequestMapping(value = "/insertAirRule", method = RequestMethod.POST)
    public String insert(@RequestBody Object airRuleInfo) {

        net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(airRuleInfo);

        int sum = 0;

        for (int i = 0; i < jsonArray.size(); i++) {

            net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) jsonArray.get(i);

            int row = iAirRuleService.insertAirRule(

                    jsonObject.getString("airportname"),
                    jsonObject.getInt("statenum"),
                    jsonObject.getInt("state"),
                    jsonObject.getString("userId"),
                    jsonObject.getString("updateTime")

            );
            sum = sum + row;
        }
        return "执行插入机场规则数据成功，" + "影响" + sum + "行";
    }





    /**
     * 更改AIR_RULE表中规则的状态
     * @param ruleState
     * @return
     */
    @RequestMapping(value = "/updateState", method = RequestMethod.POST)

    public String update(@RequestBody Object ruleState){

        JSONObject jsonObject = JSONObject.fromObject(ruleState);

        int row = iAirRuleService.updateState(

                jsonObject.getInt("id"),

                jsonObject.getInt("state"),

                jsonObject.getString("userId")

        );

        return "执行更改规则状态成功，" + "影响" + row + "行";
    }





    /**
     * 删除AIR_RULE表中数据
     * @param airRuleId
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)

    public String delete(@RequestBody Object airRuleId){

        JSONObject jsonObject = JSONObject.fromObject(airRuleId);

        int row = iAirRuleService.deleteAirRule(

                jsonObject.getInt("id"),

                jsonObject.getString("userId")

        );
        return "执行删除机场规则数据成功，" + "影响" + row + "行";
    }

}
