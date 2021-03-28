package com.bjtu.airport.controller;


import com.bjtu.airport.entity.AirlineLimit;
import com.bjtu.airport.entity.ParkingMdl;
import com.bjtu.airport.service.IAirlineLimitService;
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
@RequestMapping("/airline_limit")
public class AirlineLimitController {

    @Autowired
    IAirlineLimitService iAirlineLimitService;




    /**
     * 查询AIRLINE_LIMIT表中数据
     * @param airportInfo 传入机场名称数据
     * @return
     */
    @RequestMapping("/select")
    public List<AirlineLimit> getone(@RequestBody Object airportInfo){

        JSONObject jsonObject = JSONObject.fromObject(airportInfo);

        List<AirlineLimit> list = iAirlineLimitService.getLimitInfo(
                jsonObject.getString("airportname"),jsonObject.getString("userId"));

        return list;
    }

    /**
     * @Author: 田英杰
     * @Description: 获取航司限制机位信息
     * @Date 2021/1/21 10:59
     * @Param  * @param null
     * @return
     * @throws:
     **/
    @RequestMapping(value = "/selectAirlineLimit", method = RequestMethod.POST)
    public List<AirlineLimit> getInfoByAN(@RequestBody Object airportInfo){

        try {
            JSONObject jsonObject = JSONObject.fromObject(airportInfo);

            List<AirlineLimit> infoList = iAirlineLimitService.getLimitInfo(
                    jsonObject.getString("airportname"),jsonObject.getString("userId"));

            return infoList;
        }catch (Exception e){
            System.out.println("获取航司限制机位信息异常，错误：" + e);
        }
        return null;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insertinfo(@RequestBody Object conflictInfo) {
        try {
            net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(conflictInfo);

            int row = iAirlineLimitService.insertinfo(
                    jsonObject.getString("airlineCd"),
                    jsonObject.getString("airlinename"),
                    jsonObject.getString("parkinggate"),
                    jsonObject.getInt("state"),
                    jsonObject.getString("airportname"),
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

            int row = iAirlineLimitService.updateInfo(
                    jsonObject.getInt("id"),
                    jsonObject.getString("airlineCd"),
                    jsonObject.getString("airlinename"),
                    jsonObject.getString("parkinggate"),
                    jsonObject.getInt("state"),
                    jsonObject.getString("airportname"),
                    jsonObject.getString("userId"),
                    jsonObject.getString("updateTime"));

            return "执行更改航司限制信息成功，" + "影响" + row + "行";
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
                int row = iAirlineLimitService.batchDelete(jsonObject.getInt("id"));
                sum = sum + row;
            }
            return "执行批量删除航司限制信息成功，共影响" + sum + "行";
        }catch (Exception e){
            System.out.println("删除错误, 原因：" + e);
        }
        return null;
    };
























    /**
     *
     * @param airportuserIdInfo
     * @return
     */
    @RequestMapping("/selectByUserid")
    public List<AirlineLimit> getLimitInfoByuserId(@RequestBody Object airportuserIdInfo){

        JSONObject jsonObject = JSONObject.fromObject(airportuserIdInfo);

        List<AirlineLimit> list = iAirlineLimitService.getLimitInfoByuserId(jsonObject.getString("airportname"), jsonObject.getString("userId"));

        return list;
    }






    /**
     * 向AIRLINE_LIMIT表中插入数据
     * @param limitInfo 传入需要插入的数据
     * @return
     */
//    @RequestMapping(value = "/insert", method = RequestMethod.POST)
//    public String insert(@RequestBody Object limitInfo) {
//
//        net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(limitInfo);
//        int sum = 0;
//        for (int i = 0; i < jsonArray.size(); i++) {
//            net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) jsonArray.get(i);
//
//            int row = iAirlineLimitService.insertinfo(
//                    jsonObject.getString("airline_cd"),
//                    jsonObject.getString("parkinggate"),
//                    jsonObject.getInt("state"),
//                    jsonObject.getString("airportname"),
//                    jsonObject.getString("userId"),
//                    jsonObject.getString("updateTime")
//            );
//            sum = sum + row;
//        }
//        return "执行插入数据成功，" + "影响" + sum + "行";
//
//    }




    /**
     * 更改AIRLINE_LIMIT航司二字码参数
     * @param airlineInfo
     * @return
     */
    @RequestMapping(value = "/updateAirline", method = RequestMethod.POST)
    public String update1(@RequestBody Object airlineInfo){

        JSONObject jsonObject = JSONObject.fromObject(airlineInfo);

        int row = iAirlineLimitService.updateinfo1(

                jsonObject.getInt("id"),
                jsonObject.getString("airline_cd"),
                jsonObject.getString("userId")
        );

        return "执行更改航司二字码参数成功，" + "影响" + row + "行";
    }




    /**
     * 更改AIRLINE_LIMIT停机位参数
     * @param parkingInfo
     * @return
     */
    @RequestMapping(value = "/updateGate", method = RequestMethod.POST)
    public String update2(@RequestBody Object parkingInfo){

        JSONObject jsonObject = JSONObject.fromObject(parkingInfo);

        int row = iAirlineLimitService.updateinfo2(

                jsonObject.getInt("id"),
                jsonObject.getString("parkinggate"),
                jsonObject.getString("userId")
        );

        return "执行更改停机位参数成功，" + "影响" + row + "行";
    }




    /**
     * 更改AIRLINE_LIMIT限制状态参数
     * @param stateInfo
     * @return
     */
    @RequestMapping(value = "/updateState", method = RequestMethod.POST)
    public String update3(@RequestBody Object stateInfo){

        JSONObject jsonObject = JSONObject.fromObject(stateInfo);

        int row = iAirlineLimitService.updateinfo3(

                jsonObject.getInt("id"),
                jsonObject.getInt("state"),
                jsonObject.getString("userId")
        );

        return "执行更改限制状态参数成功，" + "影响" + row + "行";
    }





    /**
     * 删除AIRLINE_LIMIT表中数据
     * @param airLimitId
     * @return
     */
    @RequestMapping(value = "/deleteAirLimit", method = RequestMethod.POST)

    public String delete(@RequestBody Object airLimitId){

        JSONObject jsonObject = JSONObject.fromObject(airLimitId);

        int row = iAirlineLimitService.deleteAirlineLimit(

                jsonObject.getInt("id")

        );
        return "执行删除航司限制数据成功，" + "影响" + row + "行";
    }

}
