package com.bjtu.airport.controller;

import com.bjtu.airport.entity.ParkingMdl;
import com.bjtu.airport.service.IParkingMdlService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 田英杰
 * @since 2020-12-03
 */

@RestController
@RequestMapping("/parking-mdl")
public class ParkingMdlController {

    //    ****  Following is the airport2.0 code ****

    @Autowired
    IParkingMdlService iParkingMdlService;


    @RequestMapping(value = "/getInfoByAN", method = RequestMethod.POST)
    public List<ParkingMdl> getInfoByAN(@RequestBody Object airportNameInfo){

        try {
            JSONObject jsonObject = JSONObject.fromObject(airportNameInfo);

            List<ParkingMdl> infoList = iParkingMdlService.getInfoByAN(
                    jsonObject.getString("airportname"),jsonObject.getString("userId"));

            return infoList;
        }catch (Exception e){
            System.out.println("获取机位机位信息异常，错误：" + e);
        }
        return null;
    }


    @RequestMapping(value = "/insterNewInfo", method = RequestMethod.POST)
    public String insterNewInfo(@RequestBody Object NewInfo){
        try {
            JSONObject jsonObject = JSONObject.fromObject(NewInfo);

            int row = iParkingMdlService.insterNewInfo(jsonObject.getString("mdl"), jsonObject.getString("parkinggate"),
                    jsonObject.getString("airportname"), jsonObject.getString("userId"), jsonObject.getInt("restriction"),
                    jsonObject.getString("updateTime"));
            return "成功插入" + row + "条数据";
        }catch (Exception e){
            System.out.println("插入机位机位信息异常，错误：" + e);
        }
        return null;
    }

    @RequestMapping(value = "/batchDeleteInfo", method = RequestMethod.POST)
    public String batchDelete(@RequestBody Object idInfo){
        try{
            net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(idInfo);
            int sum = 0;
            for (int i = 0; i < jsonArray.size(); i++) {
                net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) jsonArray.get(i);
                int row = iParkingMdlService.batchDelete(jsonObject.getInt("id"));
                sum = sum + row;
            }
            return "执行批量删除机位机型限制信息成功，共影响" + sum + "行";
        }catch (Exception e){
            System.out.println("删除机位机位信息异常，错误：" + e);
        }
        return null;
    };


    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    public String updateInfo(@RequestBody Object Info){
        try {
            JSONObject jsonObject = JSONObject.fromObject(Info);
            int row  = iParkingMdlService.updateInfo(jsonObject.getInt("id"),
                    jsonObject.getString("mdl"), jsonObject.getString("parkinggate"),
                    jsonObject.getString("userId"), jsonObject.getString("updateTime"),
                    jsonObject.getInt("restriction"));
            return "执行修改机位机型限制信息成功，共影响" + row + "行";
        }catch (Exception e){
            System.out.println("修改机位机位信息异常，错误：" + e);
        }
        return null;
    }
}
