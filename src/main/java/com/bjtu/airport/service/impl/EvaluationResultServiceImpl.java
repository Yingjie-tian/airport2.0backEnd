package com.bjtu.airport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjtu.airport.entity.EvaluationResult;
import com.bjtu.airport.entity.Result;
import com.bjtu.airport.entity.vo.BridgeCount;
import com.bjtu.airport.entity.vo.ConstrainsStatistic;
import com.bjtu.airport.entity.vo.ConstraintSum;
import com.bjtu.airport.entity.vo.LinePointNode;
import com.bjtu.airport.mapper.EvaluationResultMapper;
import com.bjtu.airport.mapper.ResultMapper;
import com.bjtu.airport.service.IEvaluationResultService;
import com.bjtu.airport.utils.CollectionUtil;
import com.bjtu.airport.vo.WrapEvaluationResultVo;
import static com.bjtu.airport.utils.NumberUtil.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2020/11/17 10:41
 */
@Slf4j
@Service
public class EvaluationResultServiceImpl extends ServiceImpl<EvaluationResultMapper, EvaluationResult> implements IEvaluationResultService {

    @Autowired
    private EvaluationResultMapper evaluationResultMapper;

    @Autowired
    private ResultMapper resultMapper;
    //2020.11.17 查询EVALUATION_RESULT的数据
    @Override
    public List<EvaluationResult> getinfo() {
        List<EvaluationResult> info = evaluationResultMapper.getinfo();
        return info;
    }

    @Override
    public  List<WrapEvaluationResultVo> getinfoByPage(String flightId,String date) {

        if (StringUtils.isEmpty(date)){
            date =  evaluationResultMapper.selectNewestTime();
        }
        List<EvaluationResult> list = evaluationResultMapper.getInfoWithParams(date);
        if (list == null || list.isEmpty()){
            return null;
        }
        Collection<List<EvaluationResult>> values =
                list.stream().filter(e->{
                    String fid =  e.getFlightId().toString();
                    if (StringUtils.isNotEmpty(flightId)){
                        return fid.contains(flightId);
                    }else {
                        return StringUtils.isNotEmpty(fid);
                    }
                }).collect(Collectors.groupingBy(EvaluationResult::getFlightId)).values();
        List<WrapEvaluationResultVo> voList = new ArrayList<>();
        //2021.3.26改动
        values.forEach(e -> {
            WrapEvaluationResultVo vo = new WrapEvaluationResultVo();
            e.forEach(o -> {
                handleResolveEvaluationResult(o,vo);
            });
            voList.add(vo);
        });
        return voList;
    }

    private WrapEvaluationResultVo handleResolveEvaluationResult(EvaluationResult o,WrapEvaluationResultVo vo){
        //2021.3.26改动
//        WrapEvaluationResultVo vo = new WrapEvaluationResultVo();
        if(vo.getFlightId() == null){
            vo.setFlightId(o.getFlightId().toString());
        }
        if (vo.getConstrain1() == null) {
            vo.setConstrain1(new WrapEvaluationResultVo.Counter());
        }
        if (vo.getConstrain2() == null) {
            vo.setConstrain2(new WrapEvaluationResultVo.Counter());
        }
        if (vo.getConstrain3() == null) {
            vo.setConstrain3(new WrapEvaluationResultVo.Counter());
        }
        if (vo.getConstrain4() == null) {
            vo.setConstrain4(new WrapEvaluationResultVo.Counter());
        }
        if (vo.getConstrain5() == null) {
            vo.setConstrain5(new WrapEvaluationResultVo.Counter());
        }
        if (vo.getConstrain6() == null) {
            vo.setConstrain6(new WrapEvaluationResultVo.Counter());
        }
//                if (vo.getConstrain7() == null) {
//                    vo.setConstrain7(new WrapEvaluationResultVo.Counter());
//                }
        if (vo.getConstrains() == null) {
            vo.setConstrains(new WrapEvaluationResultVo.Counter());
        }
        vo.setDate(o.getUpdateTime());
        if (o.getModel().equals("0")) {
           vo.setModel1(o.getParkinggate());
//            下面的代码是把结果评价表里的nonegate转为0，对应的还有NumberUtil注释掉的代码31行
//            vo.setModel1(getIntFromString(o.getParkinggate()).toString());
            vo.setConstrain1(vo.getConstrain1().setKey1(o.getConstraint1()));
            vo.setConstrain2(vo.getConstrain2().setKey1(o.getConstraint2()));
            vo.setConstrain3(vo.getConstrain3().setKey1(o.getConstraint3()));
            vo.setConstrain4(vo.getConstrain4().setKey1(o.getConstraint4()));
            vo.setConstrain5(vo.getConstrain5().setKey1(o.getConstraint5()));
            vo.setConstrain6(vo.getConstrain6().setKey1(o.getConstraint6()));
//          vo.setConstrain7(vo.getConstrain7().setKey1(o.getConstraint7()));
            vo.setConstrains(vo.getConstrains().setKey1(o.getConstraints()));
        }
        if (o.getModel().equals("1")) {
           vo.setModel2(o.getParkinggate());
//            vo.setModel2(getIntFromString(o.getParkinggate()).toString());
            vo.setConstrain1(vo.getConstrain1().setKey2(o.getConstraint1()));
            vo.setConstrain2(vo.getConstrain2().setKey2(o.getConstraint2()));
            vo.setConstrain3(vo.getConstrain3().setKey2(o.getConstraint3()));
            vo.setConstrain4(vo.getConstrain4().setKey2(o.getConstraint4()));
            vo.setConstrain5(vo.getConstrain5().setKey2(o.getConstraint5()));
            vo.setConstrain6(vo.getConstrain6().setKey2(o.getConstraint6()));
//                    vo.setConstrain7(vo.getConstrain7().setKey2(o.getConstraint7()));
            vo.setConstrains(vo.getConstrains().setKey2(o.getConstraints()));
        }
        if (o.getModel().equals("2")) {
            vo.setModel3(o.getParkinggate());
//            vo.setModel3(getIntFromString(o.getParkinggate()).toString());
            vo.setConstrain1(vo.getConstrain1().setKey3(o.getConstraint1()));
            vo.setConstrain2(vo.getConstrain2().setKey3(o.getConstraint2()));
            vo.setConstrain3(vo.getConstrain3().setKey3(o.getConstraint3()));
            vo.setConstrain4(vo.getConstrain4().setKey3(o.getConstraint4()));
            vo.setConstrain5(vo.getConstrain5().setKey3(o.getConstraint5()));
            vo.setConstrain6(vo.getConstrain6().setKey3(o.getConstraint6()));
//                    vo.setConstrain7(vo.getConstrain7().setKey3(o.getConstraint7()));
            vo.setConstrains(vo.getConstrains().setKey3(o.getConstraints()));
        }
        return vo;
    }

    @Override
    public R<?> getinfoByPageAndScore(String flightId, String date) {
        Map<String,Object> map=new HashMap<>();
        map.put("data1",getinfoByPage(flightId,date));
        map.put("data2", getinfo());
        return R.ok(map);
    }

    @Override
    public R<?> getTimePoint() {
//        Map<String,List<String>> result = new HashMap<>();
        Map<String,Object> result = new HashMap<>();
        result.put("dateList",new ArrayList<>());
//        result.put("timeList",new ArrayList<>());
        List<String> timePointList = evaluationResultMapper.getTimePoint();
        System.out.println(timePointList);
        if (timePointList.isEmpty()){
            return R.ok(result);
        }
        List<String> dateList = timePointList.stream().filter(StringUtils::isNotEmpty).map(e -> {
            if (e.length()>15){
                return e.split(" ")[0];
            }
            return null;
        }).filter(StringUtils::isNotEmpty).distinct().collect(Collectors.toList());
        result.put("dateList",dateList);

        Map<String,List<String>> timeDict = new HashMap<>();
        System.out.println("jjj");
        System.out.println(timePointList);
        for (int i=0;i<timePointList.size();i++){
            System.out.println("kkk"+timePointList.get(i));
            if (timePointList.get(i).length()>15){
                String k = timePointList.get(i).split(" ")[0];

                if (timeDict.containsKey(k)){
                    List temp = timeDict.get(k);
                    temp.add(timePointList.get(i).split(" ")[1]);
                    timeDict.put(k,temp);
                }else {
                    List temp = new ArrayList();
                    temp.add(timePointList.get(i).split(" ")[1]);
                    timeDict.put(k,temp);
                }
                System.out.println("yyyy");
                System.out.println(timeDict);
                System.out.println(k);
            }
        }

        result.put("timeLimit",Arrays.asList(dateList.get(0),dateList.get(dateList.size()-1)));
        result.put("timeDict",timeDict);
        System.out.println("iiiiii"+result);
        System.out.println("timeDict"+timeDict);
        return R.ok(result);
    }

    @Override
    public R<?> getStatisticalTables(String startDate, String endDate,String fileName,String userId) {

        Map<String,Object> result = new HashMap<>();
        Future<List<Object[]>> bridgeRateFuture = getBridgeRate(startDate, endDate,fileName,userId);
        List<Object[]> br = null;
        try{
            br = bridgeRateFuture.get();
        } catch (InterruptedException  | ExecutionException e) {
            br = new ArrayList<>();
            log.error("获取靠桥率统计图失败");
        }
        result.put("bridgeRate",br);


        List<Object[]> csList = null;
        Future<List<Object[]>> csFuture = getConstainsCount(startDate, endDate, fileName, userId);
        try{
            csList = csFuture.get();
        } catch (InterruptedException  | ExecutionException e) {
            csList = new ArrayList<>();
           log.error("获取违反约束统计图失败");
        }
        result.put("constainsCount",csList);


        Map<String, Object> ar = null;
        Future<Map<String, Object>> arFuture = getAgreementRate(startDate, endDate, fileName, userId);
        try {
            ar = arFuture.get();
        }catch (InterruptedException  | ExecutionException e) {
            ar = new HashMap<>();
            log.error("获取一致性统计图失败");
        }
        result.put("agreementRate",ar);

        Map<String ,Object> pcc = null;
        Future<Map<String ,Object>>  pccFuture =  getPerConstrainCount(startDate, endDate, fileName, userId);
        try {
            pcc = pccFuture.get();
        }catch (InterruptedException  | ExecutionException e) {
            pcc = new HashMap<>();
            log.error("获取违反约束统计次数失败");
        }
        result.put("perConstrainCount",pcc);
        return R.ok(result);
    }



    @Async
    Future<List<Object[]>> getBridgeRate(String startDate, String endDate,String fileName,String userId){
        List<BridgeCount> countList =  resultMapper.selectBridgeData(startDate,endDate,fileName,userId);
        if (countList.isEmpty()){
            return  new AsyncResult<>(new ArrayList<>());
        }
        Map<String, List<BridgeCount>> collect = countList.stream().collect(Collectors.groupingBy(BridgeCount::getTimePoint, LinkedHashMap::new,
                Collectors.toList()));
        List<Object[]> dataList = new ArrayList<>();
        dataList.add(new Object[]{"product", "human", "model1", "model2"});
        collect.forEach((k,v)->{
            Object[] arr = getNewObjectArray();
            arr[0]=k;
            v.forEach(e->{
                if (e.getModel().equals("0")){

                    arr[1] = save2Decimal(gdd(e.getBridgeLines())/gdd(e.getAllLines()));
                }
                if (e.getModel().equals("1")){
                    arr[2] = save2Decimal(gdd(e.getBridgeLines())/gdd(e.getAllLines()));
                }
                if (e.getModel().equals("2")){
                    arr[3] = save2Decimal(gdd(e.getBridgeLines())/gdd(e.getAllLines()));
                }
            });
            dataList.add(arr);
        });
        return new AsyncResult<>(dataList);
    }

    private Double save2Decimal(double val){
       return Double.parseDouble(String.format("%.2f", val));
    }
    @Async
    Future<List<Object[]>> getConstainsCount(String startDate, String endDate,String fileName,String userId){
        //todo 表updateTime不能为空
        List<ConstrainsStatistic> list = evaluationResultMapper.getConstainsCount(startDate,endDate,fileName,userId);
        if (list.isEmpty()){
            return new AsyncResult<>(new ArrayList<>());
        }
        //x轴
        Map<String, List<ConstrainsStatistic>> collect = list
                .stream()
                .collect(Collectors.groupingBy(ConstrainsStatistic::getTimePoint,LinkedHashMap::new, Collectors.toList()));
        List<Object[]> dataList = new ArrayList<>();
        dataList.add(new Object[]{"product", "human", "model1", "model2"});
        collect.forEach((k,v)->{
            Object[] arr = getNewObjectArray();
            arr[0]=k;
            v.forEach(e->{
                if (e.getModel().equals("0")){
                    arr[1] = e.getConstrainsCount();
                }
                if (e.getModel().equals("1")){
                    arr[2] = e.getConstrainsCount();
                }
                if (e.getModel().equals("2")){
                    arr[3] = e.getConstrainsCount();
                }
            });
            dataList.add(arr);
        });
        return new AsyncResult<>(dataList);
    }
    private Object[] getNewObjectArray(){
        return new Object[]{"",0,0,0};
    }

    @Async
    Future<Map<String,Object>> getAgreementRate(String startDate, String endDate,String fileName,String userId){
        //todo 每条数据的 flightid  与 parkinggate 都不能为空
        //todo 表updateTime不能为空
        Map<String,Object> result = new HashMap<>();
        result.put("xAxis",new ArrayList<>());
        result.put("series",new ArrayList<>());
        List<Result> resultList =  resultMapper.getResultForAgreementRate(startDate,endDate,fileName,userId);
        if (resultList.isEmpty()){
            return new AsyncResult<>(result);
        }
        //此处获取updateTime为空  抛出异常
        Map<String, List<Result>> collect = resultList
                .stream()
                //2021.3.23过滤掉空数据  这样不会产生异常  但是数据会少，如果数据无误可以屏蔽掉下一行
                .filter(e->StringUtils.isNotEmpty(e.getUpdateTime()))
                .collect(Collectors.groupingBy(Result::getUpdateTime,LinkedHashMap::new, Collectors.toList()));
        Set<String> xAxis = collect.keySet();
        result.put("xAxis",xAxis);
        LinePointNode<Double> node1 = new LinePointNode<>("model1");
        LinePointNode<Double> node2 = new LinePointNode<>("model2");
        collect.forEach((k,v)->{
            Set<String> collect0 = v.stream()
                    .filter(e -> e.getModel().equals("0"))
                    .map(e-> e.getFlightId() + ":" + e.getParkinggate())
                    .collect(Collectors.toSet());
            Set<String> collect1 = v.stream()
                    .filter(e -> e.getModel().equals("1"))
                    .map(e-> e.getFlightId() + ":" + e.getParkinggate())
                    .collect(Collectors.toSet());
            Set<String> collect2 = v.stream()
                    .filter(e -> e.getModel().equals("2"))
                    .map(e-> e.getFlightId() + ":" + e.getParkinggate())
                    .collect(Collectors.toSet());
            if (collect0.isEmpty()){
                node1.getData().add(0d);
                node2.getData().add(0d);
            }else {
                //比较0  1
                if (!collect1.isEmpty()){
                    double v1 = CollectionUtil.compareSame(collect0, collect1);
                    node1.getData().add(v1);
                }else {
                    node1.getData().add(0d);
                }
                //比较0  2
                if (!collect2.isEmpty()){
                    double v2 = CollectionUtil.compareSame(collect0, collect2);
                    node2.getData().add(v2);
                }else {
                    node2.getData().add(0d);
                }
            }
        });

        result.put("series",Arrays.asList(node1,node2));
        return new AsyncResult<>(result);
    }

    @Async
    Future<Map<String ,Object>> getPerConstrainCount(String startDate, String endDate,String fileName,String userId){
        //todo 表updateTime不能为空
        Map<String ,Object> result = new HashMap<>();
        List<ConstraintSum> sumList  = evaluationResultMapper.getFlightConstraintSum(startDate,endDate,fileName,userId);
        result.put("series",transferObj2IntArrSeries(sumList));
        if (sumList.isEmpty()){
            return new AsyncResult<>(result);
        }
        List<LinePointNode<Integer>> series = transferObj2IntArrSeries(sumList);
        result.put("series",series);
        return new AsyncResult<>(result);
    }


    private List<LinePointNode<Integer>>  transferObj2IntArrSeries( List<ConstraintSum>  list){
        List<LinePointNode<Integer>>  resList = new ArrayList<>();
        for (int i = 0;i<3;i++){
            List<Integer> intList = new ArrayList<>();
            LinePointNode<Integer> node = null;
            if (i == 0){
                node = new LinePointNode<>("human");
            }
            if (i == 1){
                node = new LinePointNode<>("model1");
            }
            if (i == 2){
                node = new LinePointNode<>("model2");
            }
            try {
                ConstraintSum o = list.get(i);
                intList.add(o.getConstraint1());
                intList.add(o.getConstraint2());
                intList.add(o.getConstraint3());
                intList.add(o.getConstraint4());
                intList.add(o.getConstraint5());
                intList.add(o.getConstraint6());
                node.setData(intList);
            } catch (Exception e){
                node.setData(Arrays.asList(0,0,0,0,0,0));
            }
            resList.add(node);
        }
        return resList;
    }

    @Override
    public int insertEva(List<EvaluationResult> evaluationResultArray){
        int sum = 0;
        for (int i = 0; i < evaluationResultArray.size(); i++) {
//          net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) jsonArray.get(i);
            System.out.println("wangfujun");
            System.out.println(evaluationResultArray.get(i));
            int row = evaluationResultMapper.insert(evaluationResultArray.get(i));
            sum = sum + row;
        }
        return sum;
    };


}
