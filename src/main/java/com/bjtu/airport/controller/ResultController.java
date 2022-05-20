package com.bjtu.airport.controller;


import com.bjtu.airport.entity.Result;
import com.bjtu.airport.entity.StatisticResult;
import com.bjtu.airport.entity.vo.FlightInfoResultVO;
import com.bjtu.airport.entity.vo.TestFlightResult;
import com.bjtu.airport.service.IManualResultService;
import com.bjtu.airport.service.IStatisticResultService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */
@RestController
@RequestMapping("/result")
@Slf4j
public class ResultController {
    @Autowired
    private com.bjtu.airport.service.IResultService IResultService;

    @Autowired
    private IStatisticResultService statisticResultService;

    //查询结果表RESULT全部内容(2020/3/31)
    @RequestMapping("/selectallresult")
    public List<Map<String, Object>> getallresult(){
        List<Map<String, Object>> results = IResultService.getResults2();
        return results;
    }

    /**
     * 根据用户id从结果表中查询文件名filename
     * @param UseridInfo
     * @return
     */
    @RequestMapping("/getFilename")
    public List<JSONObject> getFilename(@RequestBody Object UseridInfo){

        try{JSONObject jsonObject = JSONObject.fromObject(UseridInfo);

            List<JSONObject> filenameList = IResultService.getFilename(jsonObject.getString("userId"));

            return filenameList;

        } catch(Exception e ){

            System.out.println(e);
        };
        return null;
    }


    /**01/16
     * 根据文件名和用户id和model联合查询
     * @param filenameUseridInfo
     * @return
     */
//    @RequestMapping("/getUnionResult")
//    public List<JSONObject> getUnionResult(@RequestBody Object filenameUseridInfo){
//
//        try {
//            JSONObject jsonObject = JSONObject.fromObject(filenameUseridInfo);
//
//            List<JSONObject> resultList = IResultService.getUnionResult(jsonObject.getString("filename"), jsonObject.getString("userId"),jsonObject.getString("model"));
//            System.out.println(resultList);
//            return resultList;
//        } catch (Exception e){
//            System.out.println(e);
//        }return null;
//    };
    // todo
    @RequestMapping("/getUnionResult")
    public List<JSONObject> getUnionResult(@RequestBody Object filenameUseridInfo){

        try {
            JSONObject jsonObject = JSONObject.fromObject(filenameUseridInfo);

            List<JSONObject> resultList = IResultService.getUnionResult(jsonObject.getString("filename"), jsonObject.getString("userId"),jsonObject.getString("model"));
            return resultList;
        } catch (Exception e){
            System.out.println(e);
        }return null;
    };

    //更改机位开放状态(parkinggate=你所选的值&id=你选的值）
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public int update(@RequestBody Object Result){
        System.out.println(Result);
        JSONObject jsonObject = JSONObject.fromObject(Result);
        int accept= IResultService.updateinfo(
                jsonObject.getString("parkinggate"),
                jsonObject.getInt("allostate"),
                jsonObject.getInt("flightId")
        );
        System.out.println("状态接收成功");
        return accept ;
    }

    @RequestMapping(value = "/update2", method = RequestMethod.POST)
    public int update2(@RequestBody Object Result){
        System.out.println(Result);
        JSONObject jsonObject = JSONObject.fromObject(Result);
        int accept=IResultService.updateinfo2(jsonObject.getInt("flightId"));
        System.out.println("状态接收成功");
        return accept ;
    }


    //同时查询测试结果表RESULT和航班数据表FLIGHT_INFO中需要的数据
    @RequestMapping("/select")
    public List<FlightInfoResultVO> getone(String fileName,int model,String userId){
        List<FlightInfoResultVO> list = IResultService.getinfo(fileName,model,userId);
        return list;
    }
    //直接向测试结果表RESULT和航班数据表FLIGHT_INFO中插入数据
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestBody Object result) {

        net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(result);
        int sum = 0;
        for (int i = 0; i < jsonArray.size(); i++) {
            net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) jsonArray.get(i);

            int row1 = IResultService.insertinfo(
                    jsonObject.getString("parkinggate")
            );
            String insert2=jsonObject.getString("flightline");
            String[] shuzu=insert2.split("\\-");
            int row = IResultService.insertinfo2(

                    jsonObject.getString("boardinggate"),
                    jsonObject.getString("planenum"),
                    shuzu[0],shuzu[1],shuzu[2],
                    jsonObject.getInt("nation"),
                    jsonObject.getString("mdl"),
                    jsonObject.getInt("arriveperson"),
                    jsonObject.getInt("departperson")
            );

            sum = sum + row+row1;
        }
        System.out.println(jsonArray.getJSONObject(0).getString("parkinggate"));
        System.out.println(jsonArray);
        System.out.println("接收成功啦");
        return "执行插入数据成功，" + "影响" + sum + "行";
    }


       //保存服务器返回的数据到result数据库
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String update(@RequestBody String result) {
        JSONObject dada=JSONObject.fromObject(result);
        //得到的是返回数据中的data部分
        net.sf.json.JSONObject data = net.sf.json.JSONObject.fromObject(dada.getJSONObject("data"));
        //有更新没有插入
        String res = data.getString("result");
//        if(StringUtils.isBlank(res)){
//            //如果字符串为 "" 直接返回
//            return "no result data";
  //      }

        log.info(res);
        JSONArray jsonArray=JSONArray.fromObject(res);

        //json转换成list
        List<Result> dataList = new ArrayList<>();
        for (int i=0;i<jsonArray.size();i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            Result r = (Result)JSONObject.toBean(obj,Result.class);
            dataList.add(r);
        }
        String model = dataList.get(0).getModel();
//        测试传回来的数据
//        dataList.forEach(System.out::println);
        Map<String,Result> infoMap = new HashMap<>(96);
        for(Result r:dataList){
            String key = ""+r.getFilename()+":"+r.getModel()+":"+r.getFlightId();
            infoMap.put(key,r);
        }

        //查库 全表扫描
        List<Result> results = IResultService.getResults(model);

        List<TestFlightResult> testFlightResultList = compareFlightIdAndFileName(dataList,IResultService.getTestFlightResultList());
        Map<String,TestFlightResult> testFlightResultMap = new HashMap<>(16);
        for(TestFlightResult t:testFlightResultList){
            testFlightResultMap.put(t.getFileName()+":"+t.getFlightId(),t);
        }
//        测试查表查到的数据
//        results.forEach(System.out::println);
//      Filename:Model:Flightid
        Map<String,Result> dbMap = new HashMap<>(96);
        for(Result r:results){
            String key = r.getFilename()+":"+r.getModel()+":"+r.getFlightId();
            dbMap.put(key,r);
        }

        List<Result> addList = new ArrayList<>();
        List<Result> updateList = new ArrayList<>();
        for(String s:infoMap.keySet()){
            if(dbMap.containsKey(s)){
                //更新该Result数据表
                Result updateRes = dbMap.get(s);
                updateRes.setAllostate(infoMap.get(s).getAllostate());
                updateRes.setParkinggate(infoMap.get(s).getParkinggate());
                updateRes.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                if(testFlightResultMap.containsKey(updateRes.getFilename()+":"+updateRes.getFlightId())){
                    updateRes.setUserId(testFlightResultMap.get(updateRes.getFilename()+":"+updateRes.getFlightId()).getUserId());
                }

                //添加到addList
                updateList.add(updateRes);
            }else {
                Result addRes = infoMap.get(s);
                addRes.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                if(testFlightResultMap.containsKey(addRes.getFilename()+":"+addRes.getFlightId())){
                    addRes.setUserId(testFlightResultMap.get(addRes.getFilename()+":"+addRes.getFlightId()).getUserId());
                }
                addList.add(addRes);
            }
        }
        //插入没有问题
        log.info("将会插入"+addList.size()+"条数据");
        IResultService.insertDataList(addList);

        log.info("将会更新"+updateList.size()+"条数据");
//        测试更新的数据
//        updateList.forEach(System.out::println);
        IResultService.updateDataList(updateList);
        return "success";
    }




//    //保存服务器返回的数据到result数据库
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public String update(@RequestBody String result) {
//        JSONObject dada=JSONObject.fromObject(result);
//        net.sf.json.JSONObject data = net.sf.json.JSONObject.fromObject(dada.getJSONObject("data"));//得到的是返回数据中的data部分
//        JSONArray jsonArray=JSONArray.fromObject(data.getJSONArray("result"));//有更新没有插入
//        System.out.println(jsonArray );
//
//        int sum = 0;
//        System.out.println(jsonArray.size());
//
//        for (int i = 0; i < jsonArray.size(); i++) {
//            net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) jsonArray.get(i);
//            int row = IResultService.airportinfo(
//                    jsonObject.getString("model"),
//                    jsonObject.getString("parkinggate"),
//                    jsonObject.getString("filename"),
//                    Integer.parseInt(jsonObject.getString("allostate")),
//                    Integer.parseInt(jsonObject.getString("flightid"))
//            );
//            System.out.println("----");
//            sum = sum + row;
//
//        }
//        System.out.println(sum);
//        return "插入成功，" + "影响" + sum + "行";
//
//    }

        public static List<TestFlightResult> compareFlightIdAndFileName(List<Result> res,List<TestFlightResult> tfRes){
        //        List<TestFlightResult> tfResult = new ArrayList<>();
        //        for(Result r:res){
        //            for(TestFlightResult tf:tfRes){
        //                if(r.getFlightId().toString().equals(tf.getFlightId())&&r.getFilename().equals(tf.getFileName())){
        //                    tfResult.add(tf);
        //                    break;
        //                }
        //            }
        //        }
        //        return tfResult;
            //运用的方法循环遍历和流操作2020.4.19

            tfRes.stream().map(t->res.stream()
                    .filter(r-> Objects.equals(t.getFlightId(),r.getFlightId().toString()))
                    .filter(r-> Objects.equals(t.getFileName(),t.getFileName()))).close();
            return tfRes;
        }


    //保存服务器返回的数据到statistic数据库
    //20-3-28-14:42 封装对象，改动了IStatisticResultService
    @RequestMapping(value = "/airportresult", method = RequestMethod.POST)
    public String airportresult(@RequestBody String result) {
        JSONObject dada = JSONObject.fromObject(result);
        //得到服务器返回的数据data

        net.sf.json.JSONObject data = net.sf.json.JSONObject.fromObject(dada.getJSONObject("data"));

        //得到服务器返回的数据statistic
        String userId = dada.getString("userId");
        System.out.println(userId);

        JSONObject jsonObject = data.getJSONObject("statistic");

        StatisticResult sr = new StatisticResult();

        Integer neargatenum=Integer.parseInt(jsonObject.getString("neargatenum"));
        Integer timespend=Integer.parseInt(jsonObject.getString("timespend"));
        Integer bridgenum=Integer.parseInt(jsonObject.getString("bridgenum"));
        String model=jsonObject.getString("model");
        Integer flightnum=Integer.parseInt(jsonObject.getString("flightnum"));
        Integer haveallo=Integer.parseInt(jsonObject.getString("haveallo"));
        Integer gatenum=Integer.parseInt(jsonObject.getString("gatenum"));
        Integer beallo=Integer.parseInt(jsonObject.getString("beallo"));
        String bridgerate=jsonObject.getString("bridgerate");
        String input = jsonObject.getString("filename");
        sr.setNeargatenum(neargatenum);
        sr.setTimespend(timespend);
        sr.setBridgenum(bridgenum);
        sr.setModel(model);
        sr.setFlightnum(flightnum);
        sr.setBeallo(beallo);
        sr.setGatenum(gatenum);
        sr.setHaveallo(haveallo);
        sr.setBridgerate(bridgerate);
        sr.setInputname(input);
        sr.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        sr.setUserid(userId);
        IResultService.airportresult(sr);
        return jsonObject.toString();
    }

}
