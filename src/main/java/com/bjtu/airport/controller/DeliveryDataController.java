package com.bjtu.airport.controller;

import com.bjtu.airport.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.bjtu.airport.entity.*;
import com.bjtu.airport.entity.vo.FlightInfoResultVO;
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
@RequestMapping("/getmodeldata")
/**
 * @ClassName: ModelDataController
 * @Description:
 * @Author Lx
 * @Date 2021/1/21 20:28
 */
public class DeliveryDataController {
    @Autowired
    private com.bjtu.airport.service.ITestFlightService ITestFlightService;
    @Autowired
    private com.bjtu.airport.service.IGateInfoService IGateInfoService;
    @Autowired
    private com.bjtu.airport.service.IIoareaConflictService IIoareaConflictService;
    @Autowired
    private com.bjtu.airport.service.IAirlineLimitService IAirlineLimitService;
    @Autowired
    private com.bjtu.airport.service.IFlightPriorityService IFlightPriorityService;
    @Autowired
    private com.bjtu.airport.service.IAirlineBridgeRateService IAirlineBridgeRateService;
    @Autowired
    private com.bjtu.airport.service.ITimeParameterService ITimeParameterService;
    @Autowired
    private com.bjtu.airport.service.IParkingMdlService IParkingMdlService;
    @Autowired
    private com.bjtu.airport.service.IParkinggatePriorityService IParkinggatePriorityService;
    @Autowired
    private com.bjtu.airport.service.IResultService IResultService;
    @Autowired
    private com.bjtu.airport.service.IEvaluationResultService IEvaluationResultService;
    @Autowired
    private RestTemplate restTemplate;


    //查询
    @PostMapping(value = "/getdata")
    public Boolean getAll(@RequestBody JSONObject human) {
        try {
            String filename = human.getString("filename");
            String userid = human.getString("userId");
            String airportname0= human.getString("airportname");
//            System.out.println("aaaa");
//            System.out.println(human.toString());
            /**
             * 查询所有的FlightInfo
             * 1.如果查询出来的list的几个字段都为空，保留
             * 2.如果查询出来的数据有空有非空，保留非空
             *
             */
//            List<FlightInfo> flightinfo_list = ITestFlightInfoService.getinfo(filename, userid);
            List<JSONObject>flightinfo_list= ITestFlightService.getFlightInfoByFilename(filename,userid);

//           String str1= flightinfo_list.toString().replace("(","{");
//           str1=str1.replace(")","}");
//           str1=str1.replace("FlightInfo","");
            //将数据转为json数组
            JSONArray arr1=JSONArray.fromObject(flightinfo_list);
            System.out.println("!!!"+arr1);
//            表格数据
            String flightstr="";
            String gatestr="";
            String areaconflictstr="";
            String airlimitstr="";
            String priostr="";
            String airlinebridgeratestr="";
            String timeparameterstr="";
            String parkingmdlstr="";
            String ParkinggatePrioritystr="";
            for(int i=0;i<arr1.size();i++){
                Map<String,String> map=new LinkedHashMap<>();
                String filghtId=arr1.getJSONObject(i).getString("id");
                String filename1=arr1.getJSONObject(i).getString("filename");
                String airportname=arr1.getJSONObject(i).getString("airportname");
                String planenum=arr1.getJSONObject(i).getString("planenum");
                String mdl=arr1.getJSONObject(i).getString("mdl");
                String aflightnum=arr1.getJSONObject(i).getString("aflightnum");
                String dflightnum=arr1.getJSONObject(i).getString("dflightnum");
                String airlinename=arr1.getJSONObject(i).getString("airlinename");
                String nation=arr1.getJSONObject(i).getString("nation");
                String pass=arr1.getJSONObject(i).getString("pass");
                String paras=arr1.getJSONObject(i).getString("paras");
                String business=arr1.getJSONObject(i).getString("business");
                String isvip=arr1.getJSONObject(i).getString("isvip");
                String atime=arr1.getJSONObject(i).getString("atime");
                String dtime=arr1.getJSONObject(i).getString("dtime");
                String origin=arr1.getJSONObject(i).getString("origin");
                String destination=arr1.getJSONObject(i).getString("destination");
                String userId=arr1.getJSONObject(i).getString("userId");
                String updateTime=human.getJSONArray("data").getJSONObject(0).getString("updateTime");



                map.put("flightId",filghtId);
                map.put("filename",filename1);
                map.put("airportname",airportname);
                map.put("planenum",planenum);
                map.put("mdl",mdl);
                map.put("aflightnum",aflightnum);
                map.put("dflightnum",dflightnum);
                map.put("airlinename",airlinename);
                map.put("nation",nation);
                map.put("pass",pass);
                map.put("paras",paras);
                map.put("business",business);
                map.put("isvip",isvip);
                map.put("atime",atime);
                map.put("dtime",dtime);
                map.put("origin",origin);
                map.put("destination",destination);
                map.put("userId",userId);
                map.put("updateTime",updateTime);
//                map.put("updateTime",updateTime);


                ObjectMapper json = new ObjectMapper();
                flightstr=flightstr+json.writeValueAsString(map)+",";
            }
//            flightstr=flightstr.replace("\"null\"","");
            flightstr=flightstr.substring(0,flightstr.length()-1);
            flightstr='['+flightstr+']';
            flightstr="\"flight\":"+flightstr;

            List<GateInfo> gateinfo_list = IGateInfoService.getinfo1(airportname0);
//           String str2=gateinfo_list.toString().replace("(","{");
//            str2=str2.replace(")","}");
//            str2=str2.replace("GateInfo","");
            JSONArray arr2=JSONArray.fromObject(gateinfo_list);
            for(int i=0;i<arr2.size();i++){
                Map<String,String> map=new LinkedHashMap<>();
                String id=arr2.getJSONObject(i).getString("id");
                String airportname=arr2.getJSONObject(i).getString("airportname");
                String parkinggate=arr2.getJSONObject(i).getString("parkinggate");
                String state=arr2.getJSONObject(i).getString("state");
                String endtime=arr2.getJSONObject(i).getString("endtime");
                String bridge=arr2.getJSONObject(i).getString("bridge");
                String nation=arr2.getJSONObject(i).getString("nation");
                String updateTime=arr2.getJSONObject(i).getString("updateTime");
                map.put("id",id);
                map.put("airportname",airportname);
                map.put("parkinggate",parkinggate);
                map.put("state",state);
                map.put("endtime",endtime);
                map.put("bridge",bridge);
                map.put("nation",nation);
                map.put("updateTime",updateTime);
                ObjectMapper json = new ObjectMapper();
                String sss=json.writeValueAsString(map);
                gatestr=gatestr+sss+",";
            }
            gatestr=gatestr.substring(0,gatestr.length()-1);
            gatestr='['+gatestr+']';
            gatestr="\"gate\":"+gatestr;

            List<IoareaConflict> areaconflict_list = IIoareaConflictService.getRuleInfo(airportname0,userid);
//            String str3=areaconflict_list.toString().replace("(","{");
//            str3=str3.replace(")","}");
//            str3=str3.replace("IoareaConflict","");
            JSONArray arr3=JSONArray.fromObject(areaconflict_list);
            for(int i=0;i<arr3.size();i++){
                Map<String,String> map=new LinkedHashMap<>();
                String id=arr3.getJSONObject(i).getString("id");
                String areaid=arr3.getJSONObject(i).getString("areaid");
                String parkinggate=arr3.getJSONObject(i).getString("parkinggate");
                String airportname=arr3.getJSONObject(i).getString("airportname");
                String userid1=arr3.getJSONObject(i).getString("userId");
                String updateTime=arr2.getJSONObject(i).getString("updateTime");
                map.put("id",id);
                map.put("areaid",areaid);
                map.put("parkinggate",parkinggate);
                map.put("airportname",airportname);
                map.put("userId",userid1);
                map.put("updateTime",updateTime);
                ObjectMapper json = new ObjectMapper();
                String sss=json.writeValueAsString(map);
                areaconflictstr=areaconflictstr+sss+",";
            }
            areaconflictstr=areaconflictstr.substring(0,areaconflictstr.length()-1);
            areaconflictstr='['+areaconflictstr+']';
            areaconflictstr="\"ioAreaConflict\":"+areaconflictstr;


            List<AirlineLimit> airlimit_list = IAirlineLimitService.getLimitInfo(airportname0,userid);
//            String str4=airlimit_list.toString().replace("(","{");
//            str4=str4.replace(")","}");
//            str4=str4.replace("AirlineLimit","");
            JSONArray arr4=JSONArray.fromObject(airlimit_list);
            for(int i=0;i<arr4.size();i++){
                Map<String,String> map=new LinkedHashMap<>();
                String id=arr4.getJSONObject(i).getString("id");
                String airportname=arr4.getJSONObject(i).getString("airportname");
                String airline_cd=arr4.getJSONObject(i).getString("airlineCd");
                String airlinename=arr4.getJSONObject(i).getString("airlinename");
                String parkinggate=arr4.getJSONObject(i).getString("parkinggate");
                String state=arr4.getJSONObject(i).getString("state");
                String userid6=arr4.getJSONObject(i).getString("userId");
                String updateTime=arr4.getJSONObject(i).getString("updateTime");
                map.put("id",id);
                map.put("airportname",airportname);
                map.put("airlineCd",airline_cd);
                map.put("airlinename",airlinename);
                map.put("parkinggate",parkinggate);
                map.put("state",state);
                map.put("userId",userid6);
                map.put("updateTime",updateTime);
                ObjectMapper json = new ObjectMapper();
                String sss=json.writeValueAsString(map);
                airlimitstr=airlimitstr+sss+",";
            }
            airlimitstr=airlimitstr.substring(0,airlimitstr.length()-1);
            airlimitstr='['+airlimitstr+']';
            airlimitstr="\"airlineLimit\":"+airlimitstr;
            airlimitstr=airlimitstr.replace("airlineCd","airline_cd");


            List<JSONObject> prio_list = IFlightPriorityService.getPriorityByAirportname(airportname0, userid);
//            String str5=prio_list.toString().replace("(","{");
//            str5=str5.replace(")","}");
            JSONArray arr5=JSONArray.fromObject(prio_list);
            for(int i=0;i<arr5.size();i++){
                Map<String,String> map=new LinkedHashMap<>();
                String id=arr5.getJSONObject(i).getString("id");
                String airportname=arr5.getJSONObject(i).getString("airportname");
                String flighttype=arr5.getJSONObject(i).getString("flighttype");
                String flightkey=arr5.getJSONObject(i).getString("flightkey");
                String prioritynum=arr5.getJSONObject(i).getString("prioritynum");
                String sortrule=arr5.getJSONObject(i).getString("sortrule");
                String userid3=arr5.getJSONObject(i).getString("userId");
                String updateTime=arr5.getJSONObject(i).getString("updateTime");
                map.put("id",id);
                map.put("airportname",airportname);
                map.put("flighttype",flighttype);
                map.put("flightkey",flightkey);
                map.put("prioritynum",prioritynum);
                map.put("sortrule",sortrule);
                map.put("userId",userid3);
                map.put("updateTime",updateTime);
                ObjectMapper json = new ObjectMapper();
                String sss=json.writeValueAsString(map);
                priostr=priostr+sss+",";
            }
            priostr=priostr.substring(0,priostr.length()-1);
            priostr='['+priostr+']';
            priostr="\"flightPriority\":"+priostr;

            List<AirlineBridgeRate> airlinebridgerate_list = IAirlineBridgeRateService.getBridgeRateInfo(airportname0, userid);
//            String str6=airlinebridgerate_list.toString().replace("(","{");
//            str6=str6.replace(")","}");
//            str6=str6.replace("AirlineBridgeRate","");
            JSONArray arr6=JSONArray.fromObject(airlinebridgerate_list);
            for(int i=0;i<arr6.size();i++){
                Map<String,String> map=new LinkedHashMap<>();
                String id=arr6.getJSONObject(i).getString("id");
                String airportname=arr6.getJSONObject(i).getString("airportname");
                String airline_cd=arr6.getJSONObject(i).getString("airlineCd");
                String airlinename=arr6.getJSONObject(i).getString("airlinename");
                String bridgerate=arr6.getJSONObject(i).getString("bridgerate");
                String userid3=arr6.getJSONObject(i).getString("userId");
                String updateTime=arr6.getJSONObject(i).getString("updateTime");
                map.put("id",id);
                map.put("airportname",airportname);
                map.put("airlineCd",airline_cd);
                map.put("airlinename",airlinename);
                map.put("bridgerate",bridgerate);
                map.put("userId",userid3);
                map.put("updateTime",updateTime);
                ObjectMapper json = new ObjectMapper();
                String sss=json.writeValueAsString(map);
                airlinebridgeratestr=airlinebridgeratestr+sss+",";
            }
            airlinebridgeratestr=airlinebridgeratestr.substring(0,airlinebridgeratestr.length()-1);
            airlinebridgeratestr='['+airlinebridgeratestr+']';
            airlinebridgeratestr="\"airlineBridgeRate\":"+airlinebridgeratestr;
            airlinebridgeratestr=airlinebridgeratestr.replace("airlineCd","airline_cd");

            List<TimeParameter> timeparameter_list = ITimeParameterService.getParameterInfo(airportname0, userid);
//            String str7=timeparameter_list.toString().replace("(","{");
//            str7=str7.replace(")","}");
//            str7=str7.replace("TimeParameter","");
            JSONArray arr7=JSONArray.fromObject(timeparameter_list);
            for(int i=0;i<arr7.size();i++){
                Map<String,String> map=new LinkedHashMap<>();
                String id=arr7.getJSONObject(i).getString("id");
                String airportname=arr7.getJSONObject(i).getString("airportname");
                String paraname=arr7.getJSONObject(i).getString("paraname");
                String warningtime1=arr7.getJSONObject(i).getString("warningtime1");
                String warningtime2=arr7.getJSONObject(i).getString("warningtime2");
                String userid3=arr7.getJSONObject(i).getString("userId");
                String updateTime=arr7.getJSONObject(i).getString("updateTime");
                map.put("id",id);
                map.put("airportname",airportname);
                map.put("paraname",paraname);
                map.put("warningtime1",warningtime1);
                map.put("warningtime2",warningtime2);
                map.put("userId",userid3);
                map.put("updateTime",updateTime);
                ObjectMapper json = new ObjectMapper();
                String sss=json.writeValueAsString(map);
                timeparameterstr=timeparameterstr+sss+",";
            }
            timeparameterstr=timeparameterstr.substring(0,timeparameterstr.length()-1);
            timeparameterstr='['+timeparameterstr+']';
            timeparameterstr="\"timeParameter\":"+timeparameterstr;
            List<ParkingMdl> parkingmdl_list = IParkingMdlService.getInfoByAN(airportname0, userid);
//            String str8=parkingmdl_list.toString().replace("(","{");
//            str8=str8.replace(")","}");
//            str8=str8.replace("ParkingMdl","");
            JSONArray arr8=JSONArray.fromObject(parkingmdl_list);
            for(int i=0;i<arr8.size();i++){
                Map<String,String> map=new LinkedHashMap<>();
                String id=arr8.getJSONObject(i).getString("id");
                String airportname=arr8.getJSONObject(i).getString("airportname");
                String mdl=arr8.getJSONObject(i).getString("mdl");
                String parkinggate=arr8.getJSONObject(i).getString("parkinggate");
                String restriction=arr8.getJSONObject(i).getString("restriction");
                String userid3=arr8.getJSONObject(i).getString("userId");
                String updateTime=arr8.getJSONObject(i).getString("updateTime");
                map.put("id",id);
                map.put("airportname",airportname);
                map.put("mdl",mdl);
                map.put("parkinggate",parkinggate);
                map.put("restriction",restriction);
                map.put("userId",userid3);
                map.put("updateTime",updateTime);
                ObjectMapper json = new ObjectMapper();
                String sss=json.writeValueAsString(map);
                parkingmdlstr=parkingmdlstr+sss+",";
            }
            parkingmdlstr=parkingmdlstr.substring(0,parkingmdlstr.length()-1);
            parkingmdlstr='['+parkingmdlstr+']';
            parkingmdlstr="\"parkingMdl\":"+parkingmdlstr;

            List<JSONObject> gate_list = IParkinggatePriorityService.select22(airportname0, userid);
//            String str9=gate_list.toString().replace("(","{");
//            str9=str9.replace(")","}");
            JSONArray arr9=JSONArray.fromObject(gate_list);
            for(int i=0;i<arr9.size();i++){
                Map<String,String> map=new LinkedHashMap<>();
                String id=arr9.getJSONObject(i).getString("id");
                String airportname=arr9.getJSONObject(i).getString("airportname");
                String groupid=arr9.getJSONObject(i).getString("groupid");
                String flighttype=arr9.getJSONObject(i).getString("flighttype");
                String flightkey=arr9.getJSONObject(i).getString("flightkey");
                String template=arr9.getJSONObject(i).getString("template");
                String businesskey=arr9.getJSONObject(i).getString("businesskey");
                String businessvalue=arr9.getJSONObject(i).getString("businessvalue");
                String userid3=arr9.getJSONObject(i).getString("userId");
                String updateTime=arr9.getJSONObject(i).getString("updateTime");
                map.put("id",id);
                map.put("airportname",airportname);
                map.put("groupid",groupid);
                map.put("flighttype",flighttype);
                map.put("flightkey",flightkey);
                map.put("template",template);
                map.put("businesskey",businesskey);
                map.put("businessvalue",businessvalue);
                map.put("userId",userid3);
                map.put("updateTime",updateTime);
                ObjectMapper json = new ObjectMapper();
                String sss=json.writeValueAsString(map);
                ParkinggatePrioritystr=ParkinggatePrioritystr+sss+",";
            }
            ParkinggatePrioritystr=ParkinggatePrioritystr.substring(0,ParkinggatePrioritystr.length()-1);
            ParkinggatePrioritystr='['+ParkinggatePrioritystr+']';
            ParkinggatePrioritystr="\"gatePriority\":"+ParkinggatePrioritystr;

            //拼接字符串
            String rulestr=areaconflictstr+","+parkingmdlstr+","+airlimitstr+","+airlinebridgeratestr+","+timeparameterstr+","+priostr+","+ParkinggatePrioritystr;
            String rule="\"rule\":"+"{"+rulestr+"}";
            String str="{"+"\"success\":"+"true"+","+"\"code\":"+"\"1\","+"\"msg\":"+"\"数据获取成功!\","+"\"data\":"+"{"+"\"paramtype\":"+"\"默认\","+flightstr+","+gatestr+","+rule+"}"+"}";

            //模型一
            String url1 = "http://localhost:8000/model1/";
            String res1 = restTemplate.postForObject(url1,str.getBytes("UTF-8"),String.class);
            JSONObject json1 = JSONObject.fromObject(res1);
            List<Result> ResultArray1 = json1.getJSONObject("result").getJSONArray("data");
            System.out.println("ResultArray1"+ResultArray1);
            int Ressum1 = IResultService.insertRes(ResultArray1);
            System.out.println("Ressum1"+Ressum1);
            System.out.println(json1.getJSONObject("evaluation"));
            List<EvaluationResult> EvaluationResultArray1 = json1.getJSONObject("evaluation").getJSONArray("data");
            int Evasum1 = IEvaluationResultService.insertEva(EvaluationResultArray1);

            //模型二
            String url2 = "http://localhost:8000/model2/";
            String res2 = restTemplate.postForObject(url2,str.getBytes("UTF-8"),String.class);
            JSONObject json2 = JSONObject.fromObject(res2);
            List<Result> ResultArray2 = json2.getJSONObject("result").getJSONArray("data");
            int Ressum2 = IResultService.insertRes(ResultArray2);
            System.out.println(json2.getJSONObject("evaluation"));
            List<EvaluationResult> EvaluationResultArray2 = json2.getJSONObject("evaluation").getJSONArray("data");
            System.out.println("-------------------------------------------------------------");
            int Evasum2 = IEvaluationResultService.insertEva(EvaluationResultArray2);

            //人工分配评价
            String url3 = "http://localhost:8000/eval/";
            List<Result> ResultArray3 = human.getJSONArray("data");
            int Ressum3 = IResultService.insertRes(ResultArray3);
//            返回的停机位不能是null
            JSONObject res = new JSONObject();
            res.put("success", true);
            res.put("code", "1");
            res.put("msg", "机位分配成功!");
            res.put("data", ResultArray3);
            JSONObject js = new JSONObject();
            js.put("res",res);
            js.put("data",str);
            String res3 = restTemplate.postForObject(url3,js.toString().getBytes("UTF-8"),String.class);
            JSONObject json3 = JSONObject.fromObject(res3);
            List<EvaluationResult> EvaluationResultArray3 = json3.getJSONArray("data");
            int Evasum3 = IEvaluationResultService.insertEva(EvaluationResultArray3);
            if (Ressum3>0){
                return true;
            }else {
                return false;
            }
//            return Evasum3;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

