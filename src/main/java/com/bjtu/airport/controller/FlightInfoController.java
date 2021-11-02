package com.bjtu.airport.controller;


import com.bjtu.airport.annotation.PassToken;
import com.bjtu.airport.entity.FlightInfo;
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
 * @since 2020-03-01
 */
@RestController
@RequestMapping("/flight-info")
public class FlightInfoController {

    @Autowired
    private IFlightInfoService IFlightInfoService;




    /**
     * 查询FLIGHT_INFO表中的数据
     * @return
     */
    @RequestMapping("/select")
    public List<FlightInfo> getone(){

        List<FlightInfo> list = IFlightInfoService.getinfo();

        return list;
    }

//    @RequestMapping("/select1")
//    public String getone1() {
//        return "111";
//    }




    /**
     * 向FLIGHT_INFO表中插入数据
     * @param flightInfo
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestBody Object flightInfo) {

        net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(flightInfo);
        int sum = 0;
        for (int i = 0; i < jsonArray.size(); i++) {
            net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) jsonArray.get(i);

            int row = IFlightInfoService.insertinfo(
                    jsonObject.getString("airportname"),
                    jsonObject.getString("parkinggate"),
                    jsonObject.getString("boardinggate"),
                    jsonObject.getString("planenum"),
                    jsonObject.getString("aflightnum"),
                    jsonObject.getString("aflightid"),
                    jsonObject.getString("dflightnum"),
                    jsonObject.getString("dflightid"),
                    jsonObject.getInt("paras"),
                    jsonObject.getString("atime"),
                    jsonObject.getString("dtime"),
                    jsonObject.getInt("nation"),
                    jsonObject.getString("mdl"),
                    jsonObject.getInt("business"),
                    jsonObject.getString("origin"),
                    jsonObject.getString("destination"),
                    jsonObject.getInt("isvip"),
                    jsonObject.getInt("arriveperson"),
                    jsonObject.getInt("departperson"),
                    jsonObject.getString("updateTime")
            );
            sum = sum + row;
        }
        return "执行插入数据成功，" + "影响" + sum + "行";
    }

    /**
     * 查询FLIGHT_INFO表中的机场名称
     * @return
     */
    @RequestMapping("/selectAirportName")
    public List<JSONObject> getName(){

        List<JSONObject> list = IFlightInfoService.getName();

        return list;
    }

    /**
     * 根据机场名称查询数据flightinfo
     * @return
     */
    @RequestMapping("/getFlightInfoByAirportname")
    public List<JSONObject> getFlightInfoByAirportname(@RequestBody Object flightinfo){

        JSONObject jsonObject = JSONObject.fromObject(flightinfo);
            List<JSONObject> list = IFlightInfoService.getFlightInfoByAirportname(jsonObject.getString("airportname"));
            System.out.println(list);
            return list;
    };

    /**
     * 测试数据保存在result表
     * @return
     */
    @RequestMapping(value = "/insertResult", method = RequestMethod.POST)
    public String insertResult(@RequestBody Object resultInfo) {
        net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(resultInfo);
        int sum = 0;
        for (int i = 0; i < jsonArray.size(); i++) {
            net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) jsonArray.get(i);
            int row = IFlightInfoService.insertResult(
                    jsonObject.getString("filename"),
                    jsonObject.getString("model"),
                    jsonObject.getInt("flightId"),
                    jsonObject.getInt("allostate"),
                    jsonObject.getString("parkinggate"),
                    jsonObject.getInt("runnums"),
                    jsonObject.getString("testtime"),
                    jsonObject.getString("userId"),
                    jsonObject.getString("updateTime")
            );
            sum = sum + row;
        }
        return "执行插入数据成功，" + "影响" + sum + "行";
    }
}
