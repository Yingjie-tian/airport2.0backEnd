package com.bjtu.airport.controller;

import com.bjtu.airport.entity.AirlineBridgeRate;
import com.bjtu.airport.service.IAirlineBridgeRateService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/airline_bridgeRate")
public class AirlineBridgeRateController {

    @Autowired
    IAirlineBridgeRateService iAirlineBridgeRateService;

    @RequestMapping(value = "/getBridgeRate", method = RequestMethod.POST)
    public List<AirlineBridgeRate> getInfoByAN(@RequestBody Object airportInfo){

        try {
            JSONObject jsonObject = JSONObject.fromObject(airportInfo);

            List<AirlineBridgeRate> infoList = iAirlineBridgeRateService.getBridgeRateInfo(
                    jsonObject.getString("airportname"), jsonObject.getString("userId"));

            return infoList;
        }catch (Exception e){
            System.out.println("获取航司靠桥率信息异常，错误：" + e);
        }
        return null;
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insertinfo(@RequestBody Object conflictInfo) {
        try {
            net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(conflictInfo);

            int row = iAirlineBridgeRateService.insertinfo(
                    jsonObject.getString("airportname"),
                    jsonObject.getString("airlineCd"),
                    jsonObject.getString("airlinename"),
                    jsonObject.getString("bridgerate"),
                    jsonObject.getString("userId"),
                    jsonObject.getString("updateTime")
            );

            return "执行插入数据成功，" + "影响" + row + "行";
        } catch (Exception e){
            System.out.println("插入信息错误：原因：" + e);
        }
        return null;
    };


    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    public String updateLimitInfo(@RequestBody Object areaInfo){
        try {
            JSONObject jsonObject = JSONObject.fromObject(areaInfo);

            int row = iAirlineBridgeRateService.updateInfo(
                    jsonObject.getInt("id"),
                    jsonObject.getString("bridgerate"),
                    jsonObject.getString("airlinename"),
                    jsonObject.getString("airlineCd"),
                    jsonObject.getString("userId"),
                    jsonObject.getString("updateTime"));

            return "执行更改限停区域和机位编号成功，" + "影响" + row + "行";
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
                int row = iAirlineBridgeRateService.batchDelete(jsonObject.getInt("id"));
                sum = sum + row;
            }
            return "执行批量删除靠桥率信息成功，共影响" + sum + "行";
        }catch (Exception e){
            System.out.println("删除错误, 原因：" + e);
        }
        return null;
    };
}
