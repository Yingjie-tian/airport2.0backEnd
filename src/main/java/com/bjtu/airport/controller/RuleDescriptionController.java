package com.bjtu.airport.controller;


import com.bjtu.airport.entity.RuleDescription;
import com.bjtu.airport.service.IRuleDescriptionService;
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
 * @since 2020-03-01
 */
@RestController
@CrossOrigin
@RequestMapping("/rule-description")
public class RuleDescriptionController {

    @Autowired
    IRuleDescriptionService iRuleDescriptionService;



    /**
     * 查询RULE_DESCRIPTION表中所有数据
     * @return
     */
    @RequestMapping("/selectAll")
    public List<RuleDescription> getone(){
        List<RuleDescription> list = iRuleDescriptionService.getinfo();
        return list;
    }




    /**
     * 根据机场名称和用户id查找相应规则
     * @param airportUseridInfo
     * @return
     */
    @RequestMapping("/selectByAirport")
    public List<JSONObject> getByAirportname(@RequestBody Object airportUseridInfo){

        JSONObject jsonObject = JSONObject.fromObject(airportUseridInfo);
        try {
            List<JSONObject> list = iRuleDescriptionService.getByAirportname(jsonObject.getString("airportname"), jsonObject.getString("userId"));
            System.out.println("==============================结果为：" + list);
            return list;
        }catch (Exception e){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!"+ e);
        };
        return null;
    }




    /**
     * //向RELE_STATE表中插入数据
     * @param ruleStateInfo
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestBody Object ruleStateInfo) {

        net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(ruleStateInfo);
        int sum = 0;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);

            int row = iRuleDescriptionService.insertinfo(
                    jsonObject.getInt("state"),
                    jsonObject.getString("describe"),
                    jsonObject.getInt("parastate"),
                    jsonObject.getString("corrtablename"),
                    jsonObject.getString("paraname"),
                    jsonObject.getString("airportname"),
                    jsonObject.getString("userId"),
                    jsonObject.getString("updateTime")
            );
            sum = sum + row;
        }
        return "执行插入数据成功，" + "影响" + sum + "行";

    }




    /**
     *  //删除RULE_STATE表中数据
     * @param ruleId
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestBody Object ruleId){
        JSONObject jsonObject = JSONObject.fromObject(ruleId);
        int row = iRuleDescriptionService.deleteinfo(
                jsonObject.getInt("id")
        );
        return "执行删除规则数据成功，" + "影响" + row + "行";
    }



    /**
     * 更改RELE_STATE表中规则的状态
     * @param ruleState
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody Object ruleState){
        JSONObject jsonObject = JSONObject.fromObject(ruleState);
        int row = iRuleDescriptionService.updateinfo(
                jsonObject.getInt("id"),
                jsonObject.getInt("state")
        );
        return "执行更改规则状态成功，" + "影响" + row + "行";
    }

}
