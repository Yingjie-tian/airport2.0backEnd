package com.bjtu.airport.controller;

import com.bjtu.airport.entity.*;
import com.bjtu.airport.entity.vo.FlightInfoResultVO;
import com.bjtu.airport.service.IResultService;
import com.bjtu.airport.service.ITestFlightInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.validation.OverridesAttribute;
import javax.validation.constraints.Size;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/getalldata")
public class GetDataController {

    @Autowired
    private com.bjtu.airport.service.ITestFlightInfoService ITestFlightInfoService;
    @Autowired
    private com.bjtu.airport.service.IGateInfoService IGateInfoService;
    @Autowired
    private com.bjtu.airport.service.IModeInfoService IModeInfoService;
    @Autowired
    private com.bjtu.airport.service.IRuleParameterService IRuleParameterService;
    @Autowired
    private com.bjtu.airport.service.IFlightPriorityService IFlightPriorityService;
    @Autowired
    private com.bjtu.airport.service.IAirlineLimitService IAirlineLimitService;
    @Autowired
    private com.bjtu.airport.service.IIoareaConflictService IIoareaConflictService;
    @Autowired
    ResultController resultController=new ResultController();
    @Autowired
    StatisticResultController statisticResultController=new StatisticResultController();

    //根据所选测试数据选择机位
    @RequestMapping(value = "/select_testgate", method = RequestMethod.POST)
    public List<GateInfo> getinfo2(@RequestBody Object filename){

        JSONArray jsonArray = JSONArray.fromObject(filename);
        String filename1=jsonArray.getJSONObject(0).getString("filename");
        String userId=jsonArray.getJSONObject(0).getString("userId");
        List<FlightInfo> flightinfo_list = ITestFlightInfoService.getinfo(filename1,userId);
        System.out.println("jjj  "+flightinfo_list);
        JSONArray arr1=JSONArray.fromObject(flightinfo_list);
        String airportname=arr1.getJSONObject(0).getString("airportname");
        System.out.println("jjj2  "+airportname);
        List<GateInfo> accept=IGateInfoService.getinfo2(airportname);
        System.out.println("jjj2  "+flightinfo_list);
        System.out.println("状态接收成功");
        return accept;
    }


    //查询
    @RequestMapping("/getdata")
    public String getAll(Integer modelname, String fileName,String userId) {
        try {
            String filename=fileName;
            String airportname0="丽江机场";
            /**
             * 查询所有的FlightInfo
             * 1.如果查询出来的list的几个字段都为空，保留
             * 2.如果查询出来的数据有空有非空，保留非空
             *
             */

            List<FlightInfo> flightinfo_list = ITestFlightInfoService.getinfo(filename,userId);
            flightinfo_list.toString();
            List<GateInfo> gateinfo_list = IGateInfoService.getinfo1(airportname0);
//            List<ModeInfo> modeinfo_list = IModeInfoService.getinfo();
            List<RuleParameter> rulepara_list = IRuleParameterService.getinfo(airportname0,userId);
            List<JSONObject> prio_list = IFlightPriorityService.getPriorityByAirportname(airportname0, userId);
            List<AirlineLimit> airlimit_list = IAirlineLimitService.getLimitInfo(airportname0,userId);
            List<IoareaConflict> areaconflict_list = IIoareaConflictService.getRuleInfo(airportname0,userId);
            System.out.println("wowowo"+flightinfo_list.toString());
            //将数据转为json数组
            JSONArray arr1=JSONArray.fromObject(flightinfo_list);
            JSONArray arr2=JSONArray.fromObject(gateinfo_list);
//            JSONArray arr3=JSONArray.fromObject(modeinfo_list);
            JSONArray arr4=JSONArray.fromObject(rulepara_list);
            JSONArray arr5=JSONArray.fromObject(areaconflict_list);
            JSONArray arr6=JSONArray.fromObject(airlimit_list);
            JSONArray arr7=JSONArray.fromObject(prio_list);
            //各个表格数据
            String modelstr="";
            String flightstr="";
            String gatestr="";
//            String modestr="";
            String rulestr="";
            String resultstr="";
            String statisstr="";
        //    String filePath = "C:\\Users\\wcx\\Desktop\\input.txt";
           // String filePath2 = "C:\\Users\\wcx\\Desktop\\output.txt";

            for(int i=0;i<arr1.size();i++){
                Map<String,String> map=new LinkedHashMap<>();
                String flightid=arr1.getJSONObject(i).getString("id");
                String parkinggate=arr1.getJSONObject(i).getString("parkinggate");
                String boardinggate=arr1.getJSONObject(i).getString("boardinggate");
                String planenum=arr1.getJSONObject(i).getString("planenum");
                String aflightno=arr1.getJSONObject(i).getString("aflightnum");
                String dflightno=arr1.getJSONObject(i).getString("dflightnum");
                String paras=arr1.getJSONObject(i).getString("paras");
                String atime=arr1.getJSONObject(i).getString("atime");
                String dtime=arr1.getJSONObject(i).getString("dtime");
                String nation=arr1.getJSONObject(i).getString("nation");
                String mdl=arr1.getJSONObject(i).getString("mdl");
                String business=arr1.getJSONObject(i).getString("business");
                String origin=arr1.getJSONObject(i).getString("origin");
                String destination=arr1.getJSONObject(i).getString("destination");
                String isvip=arr1.getJSONObject(i).getString("isvip");
                String arriveperson=arr1.getJSONObject(i).getString("arriveperson");
                String departperson=arr1.getJSONObject(i).getString("departperson");
                map.put("filename",filename);
                map.put("flightid",flightid);
                map.put("parkinggate",parkinggate);
                map.put("boardinggate",boardinggate);
                map.put("planenum",planenum);
                map.put("aflightno",aflightno);
                map.put("dflightno",dflightno);
                map.put("paras",paras);
                map.put("atime",atime);
                map.put("dtime",dtime);
                map.put("nation",nation);
                map.put("mdl",mdl);
                map.put("business",business);
                map.put("origin",origin);
                map.put("destination",destination);
                map.put("isvip",isvip);
                map.put("arriveperson",arriveperson);
                map.put("departperson",departperson);
                ObjectMapper json = new ObjectMapper();
                flightstr=flightstr+json.writeValueAsString(map)+",";
            }
            for(int i=0;i<arr2.size();i++){
                Map<String,String> map=new LinkedHashMap<>();
                String airportname=arr2.getJSONObject(i).getString("airportname");
                String parkinggate=arr2.getJSONObject(i).getString("parkinggate");
                String boardinggatenum=arr2.getJSONObject(i).getString("boardinggatenum");
                String state=arr2.getJSONObject(i).getString("state");
                String bridge=arr2.getJSONObject(i).getString("bridge");
                String nation=arr2.getJSONObject(i).getString("nation");
                String mdl=arr2.getJSONObject(i).getString("mdlType");
                String [] strmdl= mdl.split(",");

                List<String> mdllist= new ArrayList<>();
                for(String s:strmdl){
                    s="\""+s+"\"";
                    mdllist.add(s);
                }
                String lettersCommaSeparated = String.join(",", mdllist);
                String business=arr2.getJSONObject(i).getString("business");
                String neargate=arr2.getJSONObject(i).getString("neargate");
                map.put("airportname",airportname);
                map.put("parkinggate",parkinggate);
                map.put("boardinggatenum",boardinggatenum);
                map.put("state",state);
                map.put("bridge",bridge);
                map.put("nation",nation);
                map.put("mdl",lettersCommaSeparated);
                map.put("business",business);
                map.put("neargate",neargate);
                ObjectMapper json = new ObjectMapper();
                String sss=json.writeValueAsString(map).replace("\\","");
                sss=sss.replace(":\"\"",":[\"");
                sss=sss.replace("\"\",","\"],");
                sss=sss.replace("\"null\"","null");
                gatestr=gatestr+sss+",";


            }
//            for(int i=0;i<arr3.size();i++){
//                Map<String,String> map=new LinkedHashMap<>();
//                String type=arr3.getJSONObject(i).getString("type");
//                String mdl=arr3.getJSONObject(i).getString("mdl");
//                map.put("type",type);
//                map.put("mdl",mdl);
//
//                ObjectMapper json = new ObjectMapper();
//                modestr=modestr+json.writeValueAsString(map)+",";
//
//            }

            for(int i=0;i<5;i++){
                Map<String,String> map=new LinkedHashMap<>();
                map.put("statenum",Integer.toString(i+1));
                map.put("state","2");

                switch(i){
                    case 0:
                        for(int j=0;j<arr4.size();j++){
                            String warningtime1=Integer.toString(arr4.getJSONObject(j).getInt("warningtime1"));
                            map.put("warningtime1",warningtime1);
                        }
                        break;
                    case 1:
                        for(int j=0;j<arr4.size();j++){
                            String warningtime2=Integer.toString(arr4.getJSONObject(j).getInt("warningtime2"));
                            map.put("warningtime2",warningtime2);
                        }
                        break;
                    case 2:
//                        System.out.println("2");
                        String area_conflict="";
                        for(int j=0;j<arr5.size();j++){
                            Map<String,String> map3=new LinkedHashMap<>();
                            String areaid=arr5.getJSONObject(j).getString("areaid");
                            String parkinggate=arr5.getJSONObject(j).getString("parkinggate");
                            map3.put("areaid",areaid);
                            map3.put("parkinggate",parkinggate);
                            ObjectMapper json = new ObjectMapper();
                            area_conflict=area_conflict+json.writeValueAsString(map3)+",";
                        }
                        if(area_conflict.length()>0){
                            area_conflict=area_conflict.substring(0, area_conflict.length() - 1);
                            area_conflict='['+area_conflict+']';
                            map.put("area_conflict",area_conflict);
                        }else{
                            map.put("area_conflict",null);
                            System.out.println(area_conflict);
                        }
//                        System.out.println(area_conflict);
                        break;
                    case 3:
                        String airline_limit="";
                        for(int j=0;j<arr6.size();j++){
                            Map<String,String> map4=new LinkedHashMap<>();
                            String airline_cd=arr6.getJSONObject(j).getString("airlineCd");
                            String parkinggate=arr6.getJSONObject(j).getString("parkinggate");
                            String state=arr6.getJSONObject(j).getString("state");
                            map4.put("airline_cd",airline_cd);
                            map4.put("parkinggate",parkinggate);
                            map4.put("state",state);
                            ObjectMapper json = new ObjectMapper();
                            airline_limit=airline_limit+json.writeValueAsString(map4)+",";
                        }
                        if(airline_limit.length()>0){
                            airline_limit=airline_limit.substring(0, airline_limit.length() - 1);
                            airline_limit='['+airline_limit+']';
                            map.put("airline_limit",airline_limit);
                            System.out.println(airline_limit);
                        }else{
                            map.put("airline_limit",null);
                            System.out.println(airline_limit);
                        }


                        break;
                    case 4:
                        String flight_priority="";
                        for(int j=0;j<arr7.size();j++){

                            String aflightnum=arr7.getJSONObject(j).getString("aflightnum");
                            aflightnum="\""+aflightnum+"\"";
                            flight_priority=flight_priority+aflightnum+",";
                        }
                        System.out.println("0000"+flight_priority);
                        if(flight_priority.length()>0) {
                            flight_priority = flight_priority.substring(0, flight_priority.length() - 1);
                            flight_priority = '[' + flight_priority + ']';
                            map.put("flight_priority", flight_priority);
                        }else{
                            map.put("flight_priority",null);
                            System.out.println(flight_priority);
                        }
//                        System.out.println(flight_priority);
                        break;
                }

                ObjectMapper json = new ObjectMapper();
                String sss=json.writeValueAsString(map).replace("\\","");
                sss=sss.replace("\"[","[");
                sss=sss.replace("]\"","]");
                rulestr=rulestr+sss+",";
            }


            flightstr=flightstr.substring(0, flightstr.length() - 1);
            flightstr="\"flight\":"+'['+flightstr+']';
            gatestr=gatestr.substring(0, gatestr.length() - 1);
            gatestr="\"gate\":"+'['+gatestr+']';
//            modestr=modestr.substring(0, modestr.length() - 1);
//            modestr="\"mode_info\":"+'['+modestr+']';
            rulestr=rulestr.substring(0, rulestr.length() - 1);
            rulestr="\"rule\":"+'['+rulestr+']';
            System.out.println("rule--------:"+rulestr);
            int modelnum= modelname;
            modelstr="\"modelnum\":"+"\""+modelnum+"\"";
            String inputdata="{"+modelstr+","+flightstr+","+gatestr+","+rulestr+"}";
            return inputdata;
            //    File file = new File(filePath);
            //   PrintStream ps = new PrintStream(new FileOutputStream(file));
            //    ps.println(inputdata);// 往文件里写入字符串


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "inputdata";
    }

    //向服务器发送数据，并保存返回的数据
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public JSONObject rtPostObject(@RequestBody Object send){
        JSONArray jsonArray = JSONArray.fromObject(send);
        int modelname=Integer.parseInt(jsonArray.getJSONObject(0).getString("modelname"));
        String filename=jsonArray.getJSONObject(0).getString("fileName");
        String userId=jsonArray.getJSONObject(0).getString("userId");
        System.out.println( userId);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://47.92.202.234:8000/gateassign/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //括号里是前端发送的字段
        //TODO 修改了这个类的  getAll() 方法
        String outputdata=getAll(modelname,filename,userId);
        System.out.println("outputdata："+outputdata);
        net.sf.json.JSONObject jsonObject = JSONObject.fromObject(outputdata);
        System.out.println("jsonObject:"+jsonObject);
        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);
        System.out.println(request);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        JSONObject panda=new JSONObject();
        JSONObject body=JSONObject.fromObject(response.getBody());
        if (body.getString("state").equals("0")) {
            panda.put("state", body.getString("state"));
            if(body.getString("data").equals("分配失败，数据有误")){
                panda.put("data",body.getString("data"));
                return panda;
            }else if (body.getString("data").equals("测试模型无法计算，请选择模型二或三")){
                panda.put("data",body.getString("data"));
                return panda;
            }
        }

//        System.out.println("response：");
//        System.out.println(response.getBody());

        resultController.update(response.getBody().toString());
        System.out.println("HERE IN ONE");
        System.out.println(response.getBody().toString());
        StringBuilder sb = new StringBuilder(response.getBody());
        String userIdCharSec = ",userId:"+"\""+userId+"\"";
        sb.insert(sb.length()-1,userIdCharSec);
        resultController.airportresult(sb.toString());
         //List<Map<String, Object>>注意对应296行public后面的类型,查村哪个调用哪个层如resultController
        //TODO 修改了 ResultController的  getone() 方法
        List<FlightInfoResultVO> cheng= resultController.getone(filename,modelname,userId);
        List<StatisticResult> gong=statisticResultController.getone();
        StatisticResult statisticResult = gong.get(0);
        //JSONObject panda=new JSONObject();
        panda.put( "result",cheng);
        panda.put("statistic",statisticResult);
        return panda;





    }
}
