package com.bjtu.airport.controller;


import com.bjtu.airport.entity.GateInfo;
import com.bjtu.airport.service.IGateInfoService;
import net.sf.json.JSONArray;
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
@RequestMapping("/gate-info")
public class GateInfoController {
    @Autowired
    private com.bjtu.airport.service.IGateInfoService IGateInfoService;


    //查询GATE_INFO的数据
    @RequestMapping("/select")
    public List<GateInfo> getone(){
        List<GateInfo> list = IGateInfoService.getinfo();
        return list;
    }
    //根据机场名称查询GATE_INFO的数据
    @RequestMapping("/getGateInfoByAirportname")
    public List<GateInfo> getGateInfoByAirportname(@RequestBody Object gateinfo){

        JSONObject jsonObject = JSONObject.fromObject(gateinfo);
        List<GateInfo> list = IGateInfoService.getinfo1(jsonObject.getString("airportname"));
        System.out.println(list);
        return list;
    };
    //向GATE_INFO中插入数据
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestBody Object gateInfo) {

        net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(gateInfo);
        int sum = 0;
        for (int i = 0; i < jsonArray.size(); i++) {
            net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) jsonArray.get(i);

            int row = IGateInfoService.insertinfo(
                    jsonObject.getString("airportname"),
                    jsonObject.getString("parkinggate"),
                    jsonObject.getString("state"),
                    jsonObject.getString("endtime"),//不能为空值
                    jsonObject.getString("bridge"),
                    jsonObject.getString("nation"),
                    jsonObject.getString("updateTime")
            );
            sum = sum + row;
        }
        return "插入成功，" + "影响" + sum + "行";
    }

    //更改机位开放状态(它的路径为localhost:8067/gate-info/update?parkinggate=你所选的值&state=你选的值）
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public int update(@RequestBody Object gateInfo){
        System.out.println(gateInfo);
        JSONObject jsonObject = JSONObject.fromObject(gateInfo);
        int accept=IGateInfoService.updateinfo(
                jsonObject.getString("parkinggate"),
                jsonObject.getString("state")
        );
        System.out.println("状态接收成功");
        return accept ;
    }

    @RequestMapping(value = "/update2", method = RequestMethod.POST)
    public int update2(@RequestBody Object gateInfo){
        System.out.println(gateInfo);
        JSONObject jsonObject = JSONObject.fromObject(gateInfo);
        int accept=IGateInfoService.update2(
                jsonObject.getString("state"),
                jsonObject.getString("endtime"),
                jsonObject.getString("bridge"),
                jsonObject.getString("nation"),
                jsonObject.getString("parkinggate")
        );
        System.out.println(jsonObject.getInt("bridge") + "jjjjjjjjjjjjjjjjjjjjj");
        System.out.println("状态接收成功");
        return accept ;
    }

    @RequestMapping("/delete")
    public String deleteBygate(@RequestBody Object gateInfo){
//        JSONObject jsonObject = JSONObject.fromObject(gateInfo);
//        int row = IGateInfoService.deleteone(jsonObject.getString("parkinggate"));
        net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(gateInfo);
        int sum = 0;
        for (int i = 0; i < jsonArray.size(); i++) {
            net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) jsonArray.get(i);

            int row = IGateInfoService.deleteone(
                    jsonObject.getString("parkinggate")
            );
            sum = sum + row;
        }
        return "删除数据成功，影响" + sum + "行";
    };


}
