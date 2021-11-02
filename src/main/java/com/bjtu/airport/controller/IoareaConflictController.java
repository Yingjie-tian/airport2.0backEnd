package com.bjtu.airport.controller;


import com.bjtu.airport.entity.IoareaConflict;
import com.bjtu.airport.service.IIoareaConflictService;
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
@RequestMapping("/ioarea-conflict")
public class IoareaConflictController {

    @Autowired
    IIoareaConflictService iIoareaConflictService;



    /**
     * 查询IOAREA_CONFLICT表中数据
     * @param airportInfo
     * @return
     */
    @RequestMapping("/select")
    public List<IoareaConflict> getone(@RequestBody Object airportInfo){

        JSONObject jsonObject = JSONObject.fromObject(airportInfo);

        List<IoareaConflict> list = iIoareaConflictService.getRuleInfo(jsonObject.getString("airportname"),
                jsonObject.getString("userId"));

        return list;
    }

    /**
     * 查询IOAREA_CONFLICT表中数据
     * @param airportUseridInfo
     * @return
     */
    @RequestMapping("/selectByuserId")
    public List<IoareaConflict> getRuleInfoByuserId(@RequestBody Object airportUseridInfo){

        JSONObject jsonObject = JSONObject.fromObject(airportUseridInfo);

        List<IoareaConflict> list = iIoareaConflictService.getRuleInfoByuserId(jsonObject.getString("airportname"),jsonObject.getString("userId"));

        return list;
    }


    /**
     * 根据机场名称选择区域中的机位并分组
     * @param airportInfo
     * @return
     */
    @RequestMapping("/selectGroup")
    public List<IoareaConflict> getParkingGate(@RequestBody Object airportInfo){

        JSONObject jsonObject = JSONObject.fromObject(airportInfo);

        List<IoareaConflict> list = iIoareaConflictService.getParkingGate(jsonObject.getString("airportname"),jsonObject.getString("userId"));

        return list;
    }











    /**
     * 更改IOAREA_CONFLICT区域编号参数
     * @param areaInfo
     * @return
     */
    @RequestMapping(value = "/updateArea", method = RequestMethod.POST)
    public String update1(@RequestBody Object areaInfo){

        JSONObject jsonObject = JSONObject.fromObject(areaInfo);

        int row = iIoareaConflictService.updateinfo1(

                jsonObject.getInt("id"),
                jsonObject.getString("areaid"),
                jsonObject.getString("userId")
        );

        return "执行更改区域编号参数成功，" + "影响" + row + "行";
    }





    /**
     * 更改IOAREA_CONFLICT停机位参数
     * @param gateInfo
     * @return
     */
    @RequestMapping(value = "/updateGate", method = RequestMethod.POST)
    public String update2(@RequestBody Object gateInfo){

        JSONObject jsonObject = JSONObject.fromObject(gateInfo);

        int row = iIoareaConflictService.updateinfo2(

                jsonObject.getInt("id"),
                jsonObject.getString("parkinggate"),
                jsonObject.getString("userId")
        );

        return "执行更改停机位成功，" + "影响" + row + "行";
    }




    /**
     * 根据机场和停机位号码删除IOAREA_CONFLICT表中数据
     * @param parkingGateInfo 传入的json格式数据，包括机场名称和停机位号码的信息
     * @return 返回删除数据的行数
     */
    @RequestMapping(value = "deleteParkingGate", method = RequestMethod.POST)
    public String deleteParkingGate(@RequestBody Object parkingGateInfo) {

        JSONObject jsonObject = JSONObject.fromObject(parkingGateInfo);

        int row = iIoareaConflictService.deleteParkingGate(

                jsonObject.getString("airportname"),
                jsonObject.getString("parkinggate"),
                jsonObject.getString("userId")
        );

        return "执行删除停机位成功，" + "影响" + row + "行";

    };


     /**
      * @Author: 田英杰
      * @Description: 根据id删除机场区域限停机位数据
      * @Date 2020/11/6 14:19
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @RequestMapping(value = "deleteArea", method = RequestMethod.POST)
    public String deleteArea(@RequestBody Object areaidInfo) {

        JSONObject jsonObject = JSONObject.fromObject(areaidInfo);

        int row = iIoareaConflictService.deleteArea(
                jsonObject.getInt("id")
        );

        return "执行删除区域成功，" + "影响" + row + "行";

    };





    //    ****  Following is the airport2.0 code ****
     /**
      * @Author: 田英杰
      * @Description: 根据机场名称查询区域限停机位数据
      * @Date 2020/11/5 20:18
      * @Param  * @param null
      * @return 
      * @throws:
      **/
    @RequestMapping(value = "/getInfoByAN", method = RequestMethod.POST)
    public List<IoareaConflict> getInfoByAN(@RequestBody Object airportNameInfo){

        JSONObject jsonObject = JSONObject.fromObject(airportNameInfo);

        List<IoareaConflict> list = iIoareaConflictService.getInfoByAN(
                jsonObject.getString("airportname"),jsonObject.getString("userId"));

        return list;
    };


    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<IoareaConflict> getAll(){

        List<IoareaConflict> list = iIoareaConflictService.getAll();

        return list;
    };



     /**
      * @Author: 田英杰
      * @Description: 根据机场获取有几个区域
      * @Date 2020/11/8 17:45
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @RequestMapping(value = "/getAreaid", method = RequestMethod.POST)
    public List<IoareaConflict> getAreaid(@RequestBody Object airportNameInfo){

        JSONObject jsonObject = JSONObject.fromObject(airportNameInfo);

        List<IoareaConflict> list = iIoareaConflictService.getAreaid(jsonObject.getString("airportname"));

        return list;
    };


     /**
      * @Author: 田英杰
      * @Description: 根据机场名称和区域编号获取区域内有几个停机位
      * @Date 2020/11/8 18:00
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @RequestMapping(value = "/getParkinggate", method = RequestMethod.POST)
    public List<IoareaConflict> getParkinggate(@RequestBody Object airportNameInfo){

        JSONObject jsonObject = JSONObject.fromObject(airportNameInfo);

        List<IoareaConflict> list = iIoareaConflictService.getParkinggate(
                jsonObject.getString("airportname"),
                jsonObject.getString("areaid"));

        return list;
    };

     /**
      * @Author: 田英杰
      * @Description: 插入新的机场区域机位限停约束
      * @Date 2020/11/8 18:22
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insertinfo(@RequestBody Object conflictInfo) {
        try {
            net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(conflictInfo);

            int row = iIoareaConflictService.insertinfo(
                    jsonObject.getString("areaid"),
                    jsonObject.getString("parkinggate"),
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


     /**
      * @Author: 田英杰
      * @Description: 根据数据id更改机场区域限停区域和机位号
      * @Date 2020/11/8 21:37
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @RequestMapping(value = "/updateLimitInfo", method = RequestMethod.POST)
    public String updateLimitInfo(@RequestBody Object areaInfo){

        JSONObject jsonObject = JSONObject.fromObject(areaInfo);

        int row = iIoareaConflictService.updateLimitInfo(
                jsonObject.getInt("id"),
                jsonObject.getString("areaid"),
                jsonObject.getString("parkinggate"),
                jsonObject.getString("userId"),
                jsonObject.getString("updateTime"));

        return "执行更改限停区域和机位编号成功，" + "影响" + row + "行";
    }


     /**
      * @Author: 田英杰
      * @Description: 批量删除机场区域限停机位信息
      * @Date 2020/12/2 15:38
      * @Param  * @param null
      * @return
      * @throws:
      **/
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
                 int row = iIoareaConflictService.batchDelete(jsonObject.getInt("id"));
                 sum = sum + row;
             }
             return "执行批量删除机场区域限停机位信息成功，共影响" + sum + "行";
         }catch (Exception e){
             System.out.println("插入信息错误, 原因：" + e);
         }
         return null;
    };


}
