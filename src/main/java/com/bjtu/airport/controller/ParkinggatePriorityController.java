package com.bjtu.airport.controller;


import com.bjtu.airport.entity.ParkinggatePriority;
import com.bjtu.airport.service.IParkinggatePriorityService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ChenYakun
 * @since 2020-11-07
 */
@RestController
@RequestMapping("/parkinggate-priority")
public class ParkinggatePriorityController {
    @Autowired
    IParkinggatePriorityService iParkinggatePriorityService;

    /* **
     * @Author: chenyakun
     * @Description: 根据用户名和机场名称请求航班类型
     * @Date 2020/11/9 14:13
     * @Param info
     * @return java.util.List<net.sf.json.JSONObject>
     * @throws:
     **/
    @RequestMapping("/selectType")
    public List<JSONObject> selectType(@RequestBody Object info){
        JSONObject jsonObject = JSONObject.fromObject(info);
        List<JSONObject> list = iParkinggatePriorityService.selectType(jsonObject.getString("airportname"),jsonObject.getString("userId"));
        return list;
    }

    /* **
     * @Author: lixiang
     * @Description: 根据用户名和机场名请求数据
     * @Date 2020/11/9 14:13
     * @Param info
     * @return java.util.List<net.sf.json.JSONObject>
     * @throws:
     **/
    @RequestMapping("/select")
    public List<JSONObject> select(@RequestBody Object info){
        JSONObject jsonObject = JSONObject.fromObject(info);
        List<JSONObject> list = iParkinggatePriorityService.select22(jsonObject.getString("airportname"),jsonObject.getString("userId"));
        return list;
    }
    
    /* **
     * @Author: chenyakun
     * @Description: 根据用户名、机场名称、航班类型查询数据
     * @Date 2020/11/9 14:11
     * @Param null
     * @return 
     * @throws:
     **/
    @RequestMapping("/selectByAirportnameAndUserIdAndType")
    public List<JSONObject> selectByAirportnameAndUserIdAndType(@RequestBody Object info){
        JSONObject jsonObject = JSONObject.fromObject(info);
        List<JSONObject> list = iParkinggatePriorityService.selectByAirportnameAndUserIdAndType(
                jsonObject.getString("airportname"),
                jsonObject.getString("flighttype"),
                jsonObject.getString("userId"));
        return list;
    }

    /* **
     * @Author: chenyakun
     * @Description: 根据机场名称查询该机场有哪些机位号
     * @Date 2020/11/10 10:20
     * @Param null
     * @return
     * @throws:
     **/
    @RequestMapping("/selectParkinggate")
    public List<JSONObject> selectParkinggate(@RequestBody Object info){
        JSONObject jsonObject = JSONObject.fromObject(info);
        List<JSONObject> list = iParkinggatePriorityService.selectParkinggate(jsonObject.getString("airportname"));
        return list;
    }
    
    /* **
     * @Author: chenyakun
     * @Description: 根据groupid、航班类型、机场名称、用户名删除数据
     * @Date 2020/11/10 19:30
     * @Param null
     * @return 
     * @throws:
     **/
    @RequestMapping(value = "/deleteParkinggate", method = RequestMethod.POST)
    public String insert(@RequestBody Object info) {
        net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(info);
        int sum = 0;
        for (int i = 0; i < jsonArray.size(); i++) {
            net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) jsonArray.get(i);
            int row = iParkinggatePriorityService.deleteParkinggate(
                    jsonObject.getString("airportname"),
                    jsonObject.getInt("groupid"),
                    jsonObject.getString("flighttype"),
                    jsonObject.getString("userId"));
            sum = sum + row;
        }
        return "执行成功，" + "影响" + sum + "行";
    }


}
