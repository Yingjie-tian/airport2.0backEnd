package com.bjtu.airport.controller;

import com.bjtu.airport.entity.AirlineLimit;
import com.bjtu.airport.entity.TimeParameter;
import com.bjtu.airport.entity.ParkingMdl;
import com.bjtu.airport.service.IAirlineLimitService;
import com.bjtu.airport.service.ITimeParameterService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/time_parameter")
public class TimeParameterController {

    @Autowired
    ITimeParameterService iTimeParameterService;

    @RequestMapping(value = "/getTimeParameter", method = RequestMethod.POST)
    public List<TimeParameter> getInfoByAN(@RequestBody Object airportInfo){

        try {
            JSONObject jsonObject = JSONObject.fromObject(airportInfo);

            List<TimeParameter> infoList = iTimeParameterService.getParameterInfo(
                    jsonObject.getString("airportname"), jsonObject.getString("userId"));

            return infoList;
        }catch (Exception e){
            System.out.println("获取时间参数信息异常，错误：" + e);
        }
        return null;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insertinfo(@RequestBody Object conflictInfo) {
        try {
            net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(conflictInfo);

            int row = iTimeParameterService.insertinfo(
                    jsonObject.getString("airportname"),
                    jsonObject.getString("paraname"),
                    jsonObject.getDouble("warningtime1"),
                    jsonObject.getDouble("warningtime2"),
                    jsonObject.getString("userId"),
                    jsonObject.getString("updateTime")
            );

            return "执行插入时间参数成功，" + "影响" + row + "行";
        } catch (Exception e){
            System.out.println("插入信息错误：原因：" + e);
        }
        return null;
    };


    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    public String updateLimitInfo(@RequestBody Object areaInfo){
        try {
            JSONObject jsonObject = JSONObject.fromObject(areaInfo);

            int row = iTimeParameterService.updateInfo(
                    jsonObject.getInt("id"),
                    jsonObject.getString("airportname"),
                    jsonObject.getString("paraname"),
                    jsonObject.getDouble("warningtime1"),
                    jsonObject.getDouble("warningtime2"),
                    jsonObject.getString("userId"),
                    jsonObject.getString("updateTime"));

            return "执行更改时间参数成功，" + "影响" + row + "行";
        }catch (Exception e){
            System.out.println("修改信息错误：原因：" + e);
        }
        return null;
    }


    @RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
    public String batchDelete(@RequestBody Object id){
        try {
            net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(id);
            System.out.println("前端数据是：");
            System.out.println(jsonArray);
            int sum = 0;
            for (int i = 0; i < jsonArray.size(); i++) {
                net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) jsonArray.get(i);
                System.out.println(jsonObject);
                int row = iTimeParameterService.batchDelete(jsonObject.getInt("id"));
                sum = sum + row;
            }
            return "执行批量删除时间参数信息成功，共影响" + sum + "行";
        }catch (Exception e){
            System.out.println("删除错误, 原因：" + e);
        }
        return null;
    };
}
