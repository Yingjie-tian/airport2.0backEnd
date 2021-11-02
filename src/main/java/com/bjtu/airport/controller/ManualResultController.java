package com.bjtu.airport.controller;


import com.bjtu.airport.entity.ManualResult;
import com.bjtu.airport.service.IManualResultService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manual-result")
public class ManualResultController {

    @Autowired
    private com.bjtu.airport.service.IManualResultService IManualResultService;

    @Autowired
    private com.bjtu.airport.service.IResultService IResultService;

    //*************插入****用于同步result表**************//
    @RequestMapping(value = "/insertallresult", method = RequestMethod.POST)
    public String insertresult(@RequestBody Object allResult) {
        JSONArray jsonArray = JSONArray.fromObject(allResult);
        List<ManualResult> dataList = new ArrayList<>();
        for (int i=0;i<jsonArray.size();i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            ManualResult r = (ManualResult)JSONObject.toBean(obj,ManualResult.class);
            dataList.add(r);
        }
        System.out.println("hhhhhhh:"+dataList);
        Map<String,ManualResult> infoMap = new HashMap<>();
        for(ManualResult r:dataList){
            String key = ""+r.getFilename()+":"+r.getModel()+":"+r.getFlightId()+":"+r.getUserId();
            infoMap.put(key,r);
        }
//        System.out.println("hhhhhhh1:"+infoMap);
        //查库 全表扫描
        List<ManualResult> results = IManualResultService.getResults();
        Map<String,ManualResult> dbMap = new HashMap<>();
        for(ManualResult r:results){
            String key = ""+r.getFilename()+":"+r.getModel()+":"+r.getFlightId()+":"+r.getUserId();
            dbMap.put(key,r);
        }
        List<ManualResult> addList = new ArrayList<>();
        List<ManualResult> updateList = new ArrayList<>();
        for(String s:infoMap.keySet()){
            if(dbMap.containsKey(s)){
                //update操作
                ManualResult addRes = infoMap.get(s);
                JSONArray arr = net.sf.json.JSONArray.fromObject(addRes);
                for (int i = 0; i < arr.size(); i++) {
                    net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) arr.get(i);
                    int accept=IManualResultService.updateinfo3(
                            jsonObject.getString("parkinggate"),
                            jsonObject.getString("filename"),
                            jsonObject.getString("model"),
                            jsonObject.getInt("flightId")
                    );
                }
            }else {
                ManualResult addRes = infoMap.get(s);
                //insert操作
                JSONArray arr = net.sf.json.JSONArray.fromObject(addRes);
                for (int i = 0; i < arr.size(); i++) {
                    net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) arr.get(i);
                    IManualResultService.insertinfo2(
                            jsonObject.getString("filename"),
                            jsonObject.getString("model"),
                            jsonObject.getInt("flightId"),
                            jsonObject.getInt("allostate"),
                            jsonObject.getString("parkinggate"),
                            jsonObject.getString("userId"),
                            jsonObject.getString("updateTime")
                    );
                }
            }
        }
        return "插入成功";
    }
    //***************插入(结束)****************//


    //查询MANUAL_RESULT_copy的数据
    @RequestMapping("/selectcopy")
    public List<Map<String, Object>> getcopyone(){
        List<Map<String, Object>> list = IManualResultService.getcopyinfo();
        return list;
    }



    /**
     * //查询MANUAL_RESULT的数据
     * @param userIdInfo
     * @return
     */
    @RequestMapping("/select")
    public List<Map<String, Object>> getone(@RequestBody Object userIdInfo){
        JSONObject jsonObject = JSONObject.fromObject(userIdInfo);
        List<Map<String, Object>> list = IManualResultService.getinfo(jsonObject.getString("userId"));
        return list;
    }


    /**
     * 根据文件名和用户id联合查询
     * @param filenameUseridInfo
     * @return
     */
    @RequestMapping("/getUnionResult")
    public List<JSONObject> getUnionResult(@RequestBody Object filenameUseridInfo){

        try {
            JSONObject jsonObject = JSONObject.fromObject(filenameUseridInfo);

            List<JSONObject> resultList = IManualResultService.getUnionResult(jsonObject.getString("filename"),jsonObject.getString("userId"));

            return resultList;
        } catch (Exception e){
            System.out.println(e);
        }return null;
    };


    //向MANUAL_RESULT中插入数据
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestBody Object manualResult) {

        net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(manualResult);
        int sum = 0;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);

            int row = IManualResultService.insertinfo(
                    jsonObject.getString("filename"),
                    jsonObject.getInt("flightId"),
                    jsonObject.getString("parkinggate"),
                    jsonObject.getString("userId"),
                    jsonObject.getString("updateTime")
            );
            sum = sum + row;
        }
        return "插入成功，" + "影响" + sum + "行";
    }

    //删除MANUAL_RESULT表中数据
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(){
//        JSONObject jsonObject = JSONObject.fromObject(ruleId);
        int row = IManualResultService.deleteinfo();
        return "执行删除规则数据成功，" + "影响" + row + "行";
    }

    //更改机位开放状态(parkinggate=你所选的值&id=你选的值）
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public int update(@RequestBody Object manualResult){
        System.out.println(manualResult);
        JSONObject jsonObject = JSONObject.fromObject(manualResult);
        int accept=IManualResultService.updateinfo(
                jsonObject.getString("parkinggate"),
                jsonObject.getInt("allostate"),
                jsonObject.getInt("id")
        );
        System.out.println("状态接收成功");
        return accept ;
    }
    @RequestMapping(value = "/update2", method = RequestMethod.POST)
    public int update2(@RequestBody Object manualResult){
        System.out.println(manualResult);
        JSONObject jsonObject = JSONObject.fromObject(manualResult);
        int accept=IManualResultService.updateinfo2(jsonObject.getInt("id"));
        System.out.println("状态接收成功");
        return accept ;
    }


    @RequestMapping(value = "/updatecopy", method = RequestMethod.POST)
    public int updatecopy(@RequestBody Object manualResult){
        System.out.println(manualResult);
        JSONObject jsonObject = JSONObject.fromObject(manualResult);
        int accept=IManualResultService.updatecopyinfo(
                jsonObject.getString("parkinggate"),
                jsonObject.getInt("id")
        );
        System.out.println("状态接收成功");
        return accept ;
    }


    /**
     * 根据文件名filename和用户id删除数据
     * @param filenameUseridInfo
     * @return
     */
    @RequestMapping("/deleteResultByFilename")
    public String deleteByFilename(@RequestBody Object filenameUseridInfo){

        JSONObject jsonObject = JSONObject.fromObject(filenameUseridInfo);

        int row = IManualResultService.deleteResultInfo(jsonObject.getString("filename"), jsonObject.getString("userId"));

        return "删除数据成功，影响" + row + "行";
    };
}
