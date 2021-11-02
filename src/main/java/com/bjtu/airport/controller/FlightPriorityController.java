package com.bjtu.airport.controller;


import com.bjtu.airport.entity.FlightInfo;
import com.bjtu.airport.service.IFlightPriorityService;
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
@RequestMapping("/flight-priority")
public class FlightPriorityController {

    @Autowired
    IFlightPriorityService iFlightPriorityService;

    /*
    * 按机场名称和用户名查询航班任务优先级数据
    * */
    @RequestMapping("/selectPriorityByAirportname")
    public List<JSONObject> getPriorityByAirportname(@RequestBody Object Airportname){
        JSONObject jsonObject = JSONObject.fromObject(Airportname);
        List<JSONObject> listOfPriority = iFlightPriorityService.getPriorityByAirportname(
                jsonObject.getString("airportname"),
                jsonObject.getString("userId")
        );
        return listOfPriority;
    }

    /*
    * 根据id修改排序规则
    * */
    @RequestMapping(value = "/updateSortRule", method = RequestMethod.POST)
    public String updatesortrule(@RequestBody Object rule){
        JSONObject jsonObject = JSONObject.fromObject(rule);
        int row = iFlightPriorityService.updatesortrule(
                jsonObject.getInt("id"),
                jsonObject.getInt("sortrule")
        );
        return "执行成功，" + "影响" + row + "行";
    }

    /*
     * 根据机场名称和用户名删除数据
     * */
    @RequestMapping(value = "/deleteByAirportName", method = RequestMethod.POST)
    public String deleteByAirportName(@RequestBody Object info) {
        JSONObject jsonObject = JSONObject.fromObject(info);
        int row = iFlightPriorityService.deleteByAirportName(
                jsonObject.getString("airportname"),
                jsonObject.getString("userId")
        );
        return "删除成功，" + "影响" + row + "行";
    };

    /*
     * 根据机场名称和用户名插入数据
     * */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestBody Object info) {
        net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(info);
        int sum = 0;
        for (int i = 0; i < jsonArray.size(); i++) {
            net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) jsonArray.get(i);
            int row = iFlightPriorityService.insertinfo(
                    jsonObject.getString("airportname"),
                    jsonObject.getString("flighttype"),
                    jsonObject.getString("flightkey"),
                    jsonObject.getInt("prioritynum"),
                    jsonObject.getInt("sortrule"),
                    jsonObject.getString("userId"),
                    jsonObject.getString("updateTime")
            );
            sum = sum + row;
        }
        return "执行插入数据成功，" + "影响" + sum + "行";
    }

}
