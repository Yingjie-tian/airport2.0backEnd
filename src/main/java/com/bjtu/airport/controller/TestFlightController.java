package com.bjtu.airport.controller;


import com.bjtu.airport.entity.TestFlight;
import com.bjtu.airport.service.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */
@RestController
@RequestMapping("/test-flight")
public class TestFlightController {

    @Autowired
    ITestFlightService iTestFlightService;

    @Autowired
    ITestFlightInfoService iTestFlightInfoService;

    /**
     * 根据用户id查询TEST_FLIGHT表中的数据
     * @return
     */
    @RequestMapping("/select")
    public List<TestFlight> getone(@RequestBody Object userIdInfo){
        JSONObject jsonObject = JSONObject.fromObject(userIdInfo);
        List<TestFlight> list = iTestFlightService.getinfo(jsonObject.getString("userId"),jsonObject.getString("filename"));
        return list;
    }

//
//    /**
//     * 根据用户id和对应时间段查询TEST_FLIGHT表中的数据
//     * @return
//     */
//    @RequestMapping("/selecttime")
//    public List<TestFlight> getone2(@RequestBody Object userIdInfo){
//        JSONObject jsonObject = JSONObject.fromObject(userIdInfo);
//        List<TestFlight> list = iTestFlightService.getinfo(jsonObject.getString("userId"));
//        return list;
//    }
    /**
     * 根据用户id、atime、dtime更新TEST_FLIGHT表中的数据，产生随机延误
     * @return
     * 李想
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody Object TestFlight) {
        net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(TestFlight);
        int sum = 0;
        for (int i = 0; i < jsonArray.size(); i++) {
            net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) jsonArray.get(i);

            int row = iTestFlightService.updateinfo(
                    jsonObject.getInt("id"),
                    jsonObject.getString("atime"),
                    jsonObject.getString("dtime")
            );
            sum = sum + row;
        }
        return "执行插入数据成功，" + "影响" + sum + "行";
    }


    // 根据前端发的时间段更改数据库 产生随机延误
    //根据用户id和对应时间段查询TEST_FLIGHT表中的数据
    @RequestMapping(value = "/selecttime", method = RequestMethod.POST)
    public void selecttime(@RequestBody Object result) throws ParseException {
//      public List<TestFlight> gettime(@RequestBody Object result){
        try {
            JSONObject jsonObject = JSONObject.fromObject(result);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////            Date end = simpleDateFormat.parse(jsonObject.getString("end"));
//            Date end = simpleDateFormat.parse("2020-10-01 01:43:45");
//
//            Date start = simpleDateFormat.parse(jsonObject.getString("start"));
            List<TestFlight> list = iTestFlightInfoService.gettime(
                    jsonObject.getString("end"), jsonObject.getString("start"));
            System.out.println("我啊啊啊啊啊"+result);
        }catch (Exception e){
            System.out.println("error: " + e.getMessage());
        }
    }



    /**
     * 根据用户id从test flight中查找filename，查有几个仿真数据
     * @return
     */
    @RequestMapping("/getFilename")
    public List<JSONObject> getFilename(@RequestBody Object userIdInfo){

        JSONObject jsonObject = JSONObject.fromObject(userIdInfo);

        List<JSONObject> filenameInfo = iTestFlightService.getFilename(jsonObject.getString("userId"));

        return filenameInfo;
    };

    /**
     * 根据文件名userid查找航班数据flightinfo
     * @param  userIdInfo
     * @return
     */
    @RequestMapping("/getFlightInfoByUserId")
    public List<JSONObject> getFlightInfoByUserId(@RequestBody Object userIdInfo){

        JSONObject jsonObject = JSONObject.fromObject(userIdInfo);

        try {
            List<JSONObject> list = iTestFlightService.getFlightInfoByUserId(jsonObject.getString("userId"));

//            System.out.println(list);

            return list;
        }catch (Exception e){
            System.out.println("========================================="+e);
        };
        return null;
    };


    /**
     * 根据文件名filename和用户id查找航班数据flightinfo
     * @param filenameInfo
     * @return
     */
    @RequestMapping("/getFlightInfoByFilename")
    public List<JSONObject> getFlightInfoByFilename(@RequestBody Object filenameInfo){

        JSONObject jsonObject = JSONObject.fromObject(filenameInfo);

        try {
            List<JSONObject> list = iTestFlightService.getFlightInfoByFilename(jsonObject.getString("filename"), jsonObject.getString("userId"));

            System.out.println(list);

            return list;
        }catch (Exception e){
            System.out.println("========================================="+e);
        };
        return null;
    };

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public List<JSONObject> test(){

        String s = "{\"filename\":\"ff\"}";

        List<JSONObject> result = this.getFlightInfoByFilename(s);

        System.out.println("=============================================");

        System.out.println(result);

        return result;
    };



    /**
     * 同时查询测试结果表RESULT和航班数据表FLIGHT_INFO中需要的数据
     * @return
     */
    @RequestMapping("/selectUnion")
    public List<Map<String, Object>> getones(){

        List<Map<String, Object>> list = iTestFlightService.getUnioninfo();

        return list;
    }



    /**
     * 向测试数据表中插入数据
     * @param testFlightInfo
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestBody Object testFlightInfo) {

        net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(testFlightInfo);
        int sum = 0;
        for (int i = 0; i < jsonArray.size(); i++) {
            net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) jsonArray.get(i);
            String newuserId;
            if (jsonObject.getString("userId") == "null" || jsonObject.getString("userId") == null){
                 newuserId = "";
            }else{
                newuserId = jsonObject.getString("userId");
            };
            int row = iTestFlightService.insertinfo(
                    jsonObject.getString("filename"),
                    jsonObject.getInt("flightId"),
                    newuserId,
                    jsonObject.getString("updateTime")
            );
            sum = sum + row;
        }
        return "执行插入测试航班数据成功，" + "影响" + sum + "行";


//        JSONObject jsonobject = JSONObject.fromObject(flightInfo);
//
//        int info = ITestFlightService.insertinfo(
//                jsonobject.getString("filename"),
//                jsonobject.getString("airportname"),
//                jsonobject.getString("aflightnum"),
//                jsonobject.getString("dflightnum"),
//                jsonobject.getString("atime"),
//                jsonobject.getString("dtime"),
//                jsonobject.getString("parkinggate"),
//                jsonobject.getString("updateTime")
//        );
//
//        return ResultVo.ok(info);
//    }

    }


    /**
     * 根据文件名filename和用户id删除仿真数据
     * @param filenameUserInfo
     * @return
     */
    @Autowired
    IStatisticResultService iStatisticResultService;
    @Autowired
    IResultService iResultService;
    @RequestMapping("/deleteByFilename")
    public String deleteByFilename(@RequestBody Object filenameUserInfo){

        JSONObject jsonObject = JSONObject.fromObject(filenameUserInfo);

        int row1 = iTestFlightService.deleteFilenameInfo(jsonObject.getString("filename"), jsonObject.getString("userId"));
        int row3 = iResultService.deleteByUserid(jsonObject.getString("filename"), jsonObject.getString("userId"));
        int row2 = iStatisticResultService.deleteByUserid(jsonObject.getString("filename"), jsonObject.getString("userId"));

        int row = row1 + row2 + row3;

        return "删除仿真数据成功，共影响" + row + "行";
    };






}
