package com.bjtu.airport.controller;


import com.bjtu.airport.entity.RuleParameter;
import com.bjtu.airport.service.IRuleParameterService;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Select;
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
@RequestMapping("/rule-parameter")
public class RuleParameterController {
    @Autowired
    IRuleParameterService iRuleParameterService;



    /**
     * //查询RULE_PARAMETER表中数据
     * @param airportInfo
     * @return
     */
    @RequestMapping("/select")
    public List<RuleParameter> getone(@RequestBody Object airportInfo){

        JSONObject jsonObject = JSONObject.fromObject(airportInfo);

        List<RuleParameter> list = iRuleParameterService.getinfo(jsonObject.getString("airportname"),jsonObject
                .getString("userId"));

        return list;
    }

    /**
     * //查询RULE_PARAMETER表中数据
     * @param airportUseridInfo
     * @return
     */
    @RequestMapping("/selectParameter")
    public List<RuleParameter> getParameter(@RequestBody Object airportUseridInfo){

        JSONObject jsonObject = JSONObject.fromObject(airportUseridInfo);

        List<RuleParameter> list = iRuleParameterService.getParameter(jsonObject.getString("airportname"),jsonObject.getString("userId"));

        return list;
    }


    //向RULE_PARAMETER表中插入数据
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestBody Object ruleParaInfo) {

        net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(ruleParaInfo);
        int sum = 0;
        for (int i = 0; i < jsonArray.size(); i++) {
            net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) jsonArray.get(i);

            int row = iRuleParameterService.insertinfo(
                    jsonObject.getDouble("warningtime1"),
                    jsonObject.getDouble("warningtime2"),
                    jsonObject.getString("airportname"),
                    jsonObject.getString("userId"),
                    jsonObject.getString("updateTime")
            );
            sum = sum + row;
        }
        return "执行插入数据成功，" + "影响" + sum + "行";

    }




    /**
     * //更改RULE_PARAMETER表中同机位冲突时间参数
     * @param sameGate
     * @return
     */
    @RequestMapping(value = "/update1", method = RequestMethod.POST)
    public String update1(@RequestBody Object sameGate){
        JSONObject jsonObject = JSONObject.fromObject(sameGate);
        int row = iRuleParameterService.updateinfo1(
                jsonObject.getInt("id"),
                jsonObject.getDouble("warningtime1"),
                jsonObject.getString("userId")
        );
        return "执行更改同机位冲突时间成功，" + "影响" + row + "行";
    }




    /**
     * //更改RULE_PARAMETER表中相邻机位冲突时间参数
     * @param adjacentGate
     * @return
     */
    @RequestMapping(value = "/update2", method = RequestMethod.POST)
    public String update2(@RequestBody Object adjacentGate){
        JSONObject jsonObject = JSONObject.fromObject(adjacentGate);
        int row = iRuleParameterService.updateinfo2(
                jsonObject.getInt("id"),
                jsonObject.getDouble("warningtime2"),
                jsonObject.getString("userId")
        );
        return "执行更改相邻机位冲突时间成功，" + "影响" + row + "行";
    }

}
