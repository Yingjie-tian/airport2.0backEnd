package com.bjtu.airport.component;

import com.bjtu.airport.entity.Result;
import com.bjtu.airport.entity.vo.*;
import com.bjtu.airport.mapper.EvaluationResultMapper;
import com.bjtu.airport.mapper.ResultMapper;
import com.bjtu.airport.utils.CollectionUtil;
import com.bjtu.airport.utils.FakeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.bjtu.airport.utils.NumberUtil.gdd;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2022/5/14 11:41
 */
@Component
public class AsyncStatisticHandler {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Autowired
    private EvaluationResultMapper evaluationResultMapper;

    @Autowired
    private ResultMapper resultMapper;
    private Function<Integer,List<Object>> func = in ->{
        List<Object> dl = new ArrayList<>();
        for (int i=0;i < in;i++){
            dl.add(0);
        }
        return dl;
    };
    //todo no xais
    @Async
    public Future<List<Object[]>> getBridgeRate(String startDate, String endDate, String fileName, String userId){

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

    @Async
    public Future<Map<String,Object>> getBridgeRateMap(String startDate, String endDate,String fileName,String userId){
        Map<String,Object> result = new HashMap<>();
        result.put("xAxis",new ArrayList<>());
        result.put("series",new ArrayList<>());
        List<BridgeCount> countList =  resultMapper.selectBridgeData(startDate,endDate,fileName,userId);
        if (countList.isEmpty()){
            if ( StringUtils.isNoneEmpty(startDate,endDate)){
                List<String> fakeXAxis = FakeUtil.fakeXAxis(startDate, endDate);
                result.put("xAxis",Collections.singletonList(new XAixsNode<>("category", fakeXAxis)));
                // 这里  计算出 FakeUtil.fakeXAxis(startDate,endDate) 列表的size
                EchartsNode<Object> n0 = new EchartsNode<>("human","bar",null,func.apply(fakeXAxis.size()));
                EchartsNode<Object> n1 = new EchartsNode<>("model1","bar",null,func.apply(fakeXAxis.size()));
                EchartsNode<Object> n2 = new EchartsNode<>("model2","bar",null,func.apply(fakeXAxis.size()));
                result.put("series",Arrays.asList(n0,n1,n2));
            }else {
                LocalDate today = LocalDate.now();
                List<String> fakeXAxis = FakeUtil.fakeXAxis(today.minusDays(30), today);
                result.put("xAxis",Collections.singletonList(new XAixsNode<>("category",fakeXAxis)));
                // 这里  计算出 FakeUtil.fakeXAxis(startDate,endDate) 列表的size
                EchartsNode<Object> n0 = new EchartsNode<>("human","bar",null,func.apply(fakeXAxis.size()));
                EchartsNode<Object> n1 = new EchartsNode<>("model1","bar",null,func.apply(fakeXAxis.size()));
                EchartsNode<Object> n2 = new EchartsNode<>("model2","bar",null,func.apply(fakeXAxis.size()));
                result.put("series",Arrays.asList(n0,n1,n2));
            }
            return new AsyncResult<>(result);
        }
        Map<String, List<BridgeCount>> collect = countList.stream().collect(Collectors.groupingBy(BridgeCount::getTimePoint, LinkedHashMap::new,
                Collectors.toList()));
        Set<String> keySet = collect.keySet();
        List<String> collect3 = keySet.stream().map(e -> LocalDate.parse(e, formatter))
                .sorted()
                .map(e -> e.format(formatter))
                .collect(Collectors.toList());
        result.put("xAxis",Collections.singletonList(new XAixsNode<>("category",collect3)));
        EchartsNode<Double> node0 = new EchartsNode<>("human","bar",null);
        EchartsNode<Double> node1 = new EchartsNode<>("model1","bar",null);
        EchartsNode<Double> node2 = new EchartsNode<>("model2","bar",null);

        collect.forEach((k,v)->{
            // 根据时间遍历
            int j = 0;
            // 拿列表遍历,如果有的加入data list 没有加0
            for (int i =0;i<v.size();i++){
                BridgeCount e = v.get(i);
                if (e.getModel().equals("0")){
                    j = j|4;
                    node0.addData(save2Decimal(gdd(e.getBridgeLines())/gdd(e.getAllLines())));
                }
                if (e.getModel().equals("1")){
                    j = j|2;
                    node1.addData(save2Decimal(gdd(e.getBridgeLines())/gdd(e.getAllLines())));
                }
                if (e.getModel().equals("2")){
                    j = j|1;
                    node2.addData(save2Decimal(gdd(e.getBridgeLines())/gdd(e.getAllLines())));
                }
            }
            if ((j&4)==0){
                node0.addData(0d);
            }
            if ((j&2)==0){
                node1.addData(0d);
            }
            if ((j&1)==0){
                node2.addData(0d);
            }
        });
        result.put("series",Arrays.asList(node0,node1,node2));
        return new AsyncResult<>(result);
    }

    private Double save2Decimal(double val){
        return Double.parseDouble(String.format("%.2f", val));
    }

    @Async
    public Future<Map<String ,Object>> getConstainsCountMap(String startDate, String endDate,String fileName,String userId){
        Map<String,Object> result = new HashMap<>();
        result.put("xAxis",new ArrayList<>());
        result.put("series",new ArrayList<>());
        List<ConstrainsStatistic> list = evaluationResultMapper.getConstainsCount(startDate,endDate,fileName,userId);
        if (list.isEmpty()){
            if ( StringUtils.isNoneEmpty(startDate,endDate)){
                List<String> fakeXAxis = FakeUtil.fakeXAxis(startDate, endDate);
                result.put("xAxis",Collections.singletonList(new XAixsNode<>("category", fakeXAxis)));
                // 这里  计算出 FakeUtil.fakeXAxis(startDate,endDate) 列表的size
                EchartsNode<Object> n0 = new EchartsNode<>("human","bar",null,func.apply(fakeXAxis.size()));
                EchartsNode<Object> n1 = new EchartsNode<>("model1","bar",null,func.apply(fakeXAxis.size()));
                EchartsNode<Object> n2 = new EchartsNode<>("model2","bar",null,func.apply(fakeXAxis.size()));
                result.put("series",Arrays.asList(n0,n1,n2));
            }else {
                LocalDate today = LocalDate.now();
                List<String> fakeXAxis = FakeUtil.fakeXAxis(today.minusDays(30), today);
                result.put("xAxis",Collections.singletonList(new XAixsNode<>("category",fakeXAxis)));
                // 这里  计算出 FakeUtil.fakeXAxis(startDate,endDate) 列表的size
                EchartsNode<Object> n0 = new EchartsNode<>("human","bar",null,func.apply(fakeXAxis.size()));
                EchartsNode<Object> n1 = new EchartsNode<>("model1","bar",null,func.apply(fakeXAxis.size()));
                EchartsNode<Object> n2 = new EchartsNode<>("model2","bar",null,func.apply(fakeXAxis.size()));
                result.put("series",Arrays.asList(n0,n1,n2));
            }
            return new AsyncResult<>(result);
        }
        Map<String, List<ConstrainsStatistic>> collect = list
                .stream()
                .collect(Collectors.groupingBy(ConstrainsStatistic::getTimePoint,LinkedHashMap::new, Collectors.toList()));
        Set<String> keySet = collect.keySet();
        List<String> collect3 = keySet.stream().map(e -> LocalDate.parse(e, formatter))
                .sorted()
                .map(e -> e.format(formatter))
                .collect(Collectors.toList());
        result.put("xAxis",Collections.singletonList(new XAixsNode<>("category",collect3)));
        EchartsNode<Integer> node0 = new EchartsNode<>("human","bar",null);
        EchartsNode<Integer> node1 = new EchartsNode<>("model1","bar",null);
        EchartsNode<Integer> node2 = new EchartsNode<>("model2","bar",null);
        collect.forEach((k,v)->{
            // 根据时间遍历
            int j = 0;
            // 拿列表遍历,如果有的加入data list 没有加0
            for (int i =0;i<v.size();i++){
                ConstrainsStatistic e = v.get(i);
                if (e.getModel().equals("0")){
                    j = j|4;
                    node0.addData(e.getConstrainsCount());
                }
                if (e.getModel().equals("1")){
                    j = j|2;
                    node1.addData(e.getConstrainsCount());
                }
                if (e.getModel().equals("2")){
                    j = j|1;
                    node2.addData(e.getConstrainsCount());
                }
            }
            if ((j&4)==0){
                node0.addData(0);
            }
            if ((j&2)==0){
                node1.addData(0);
            }
            if ((j&1)==0){
                node2.addData(0);
            }
        });
        result.put("series",Arrays.asList(node0,node1,node2));
        return new AsyncResult<>(result);
    }
    @Async
    public Future<List<Object[]>> getConstainsCount(String startDate, String endDate,String fileName,String userId){
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
    public Future<Map<String,Object>> getAgreementRate(String startDate, String endDate,String fileName,String userId){
        //todo 每条数据的 flightid  与 parkinggate 都不能为空
        //todo 表updateTime不能为空
        Map<String,Object> result = new HashMap<>();
        result.put("xAxis",new ArrayList<>());
        result.put("series",new ArrayList<>());
        List<Result> resultList =  resultMapper.getResultForAgreementRate(startDate,endDate,fileName,userId);
        if (resultList.isEmpty()){
            if ( StringUtils.isNoneEmpty(startDate,endDate)){
                List<String> fakeXAxis = FakeUtil.fakeXAxis(startDate, endDate);
                result.put("xAxis",Collections.singletonList(new XAixsNode<>("category", fakeXAxis)));
                // 这里  计算出 FakeUtil.fakeXAxis(startDate,endDate) 列表的size
                EchartsNode<Object> n0 = new EchartsNode<>("human","bar",null,func.apply(fakeXAxis.size()));
                EchartsNode<Object> n1 = new EchartsNode<>("model1","bar",null,func.apply(fakeXAxis.size()));
                EchartsNode<Object> n2 = new EchartsNode<>("model2","bar",null,func.apply(fakeXAxis.size()));
                result.put("series",Arrays.asList(n0,n1,n2));
            }else {
                LocalDate today = LocalDate.now();
                List<String> fakeXAxis = FakeUtil.fakeXAxis(today.minusDays(30), today);
                result.put("xAxis",Collections.singletonList(new XAixsNode<>("category",fakeXAxis)));
                // 这里  计算出 FakeUtil.fakeXAxis(startDate,endDate) 列表的size
                EchartsNode<Object> n0 = new EchartsNode<>("human","bar",null,func.apply(fakeXAxis.size()));
                EchartsNode<Object> n1 = new EchartsNode<>("model1","bar",null,func.apply(fakeXAxis.size()));
                EchartsNode<Object> n2 = new EchartsNode<>("model2","bar",null,func.apply(fakeXAxis.size()));
                result.put("series",Arrays.asList(n0,n1,n2));
            }
            return new AsyncResult<>(result);
        }
        //此处获取updateTime为空  抛出异常
        Map<String, List<Result>> collect = resultList
                .stream()
                //2021.3.23过滤掉空数据  这样不会产生异常  但是数据会少，如果数据无误可以屏蔽掉下一行
                .filter(e->StringUtils.isNotEmpty(e.getUpdateTime()))
                .collect(Collectors.groupingBy(Result::getUpdateTime,LinkedHashMap::new, Collectors.toList()));
        Set<String> keySet = collect.keySet();
        List<String> collect3 = keySet.stream().map(e -> LocalDate.parse(e, formatter))
                .sorted()
                .map(e -> e.format(formatter))
                .collect(Collectors.toList());
        result.put("xAxis",Collections.singletonList(new XAixsNode<>("category",collect3)));
        EchartsNode<Double> node1 = new EchartsNode<>("model1","line",null);
        EchartsNode<Double> node2 = new EchartsNode<>("model2","line",null);
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
    public Future<Map<String ,Object>> getPerConstrainCount(String startDate, String endDate,String fileName,
                                                            String userId){
        //todo 表updateTime不能为空
        Map<String ,Object> result = new HashMap<>();
        List<ConstraintSum> sumList  = evaluationResultMapper.getFlightConstraintSum(startDate,endDate,fileName,userId);
        result.put("series",transferObj2IntArrSeries(sumList));
        if (sumList.isEmpty()){
            return new AsyncResult<>(result);
        }
        List<EchartsNode<Integer>> series = transferObj2IntArrSeries(sumList);
        result.put("series",series);
        return new AsyncResult<>(result);
    }


    private List<EchartsNode<Integer>>  transferObj2IntArrSeries(List<ConstraintSum>  list){
        List<EchartsNode<Integer>>  resList = new ArrayList<>();
        for (int i = 0;i<3;i++){
            List<Integer> intList = new ArrayList<>();
            EchartsNode<Integer> node = null;
            if (i == 0){
                node = new EchartsNode<>("human","line",null);
            }
            if (i == 1){
                node = new EchartsNode<>("model1","line",null);
            }
            if (i == 2){
                node = new EchartsNode<>("model2","line",null);
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
}
