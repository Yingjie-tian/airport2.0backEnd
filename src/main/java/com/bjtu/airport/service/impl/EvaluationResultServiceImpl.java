package com.bjtu.airport.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjtu.airport.component.AsyncStatisticHandler;
import com.bjtu.airport.entity.EvaluationResult;
import com.bjtu.airport.entity.Result;
import com.bjtu.airport.entity.vo.*;
import com.bjtu.airport.mapper.EvaluationResultMapper;
import com.bjtu.airport.mapper.ResultMapper;
import com.bjtu.airport.service.IEvaluationResultService;
import com.bjtu.airport.utils.CollectionUtil;
import com.bjtu.airport.utils.FakeUtil;
import com.bjtu.airport.vo.WrapEvaluationResultVo;
import static com.bjtu.airport.utils.NumberUtil.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private AsyncStatisticHandler asyncStatisticHandler;


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

    @Override
    public  R<?> getPageableInfo(Integer flightId, String date, int page, int size) {
        if (StringUtils.isEmpty(date)){
            date =  evaluationResultMapper.selectNewestTime();
        }

        Page<BridgeViewVo> pageable = new Page<>(page,size);  // 查询第1页，每页返回5条
        EvaluationResult er = new EvaluationResult();
        er.setUpdateTime(date);
        if (flightId != null){
            er.setFlightId(flightId);
        }
        IPage<BridgeViewVo> iPage = baseMapper.page(pageable, er);
        List<BridgeViewVo> records = iPage.getRecords();
        Page<WrapEvaluationResultVo> wrappedPage = new Page<>();
        wrappedPage.setTotal(iPage.getTotal());
        wrappedPage.setRecords(castRecords(records));
        wrappedPage.setCurrent(iPage.getCurrent());
        wrappedPage.setPages(iPage.getPages());
        wrappedPage.setSize(iPage.getSize());
        return R.ok(wrappedPage);
    }

    private List<WrapEvaluationResultVo> castRecords(List<BridgeViewVo> records){
        return  records.stream().map(WrapEvaluationResultVo::new).collect(Collectors.toList());
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
            vo.setConstrains(vo.getConstrains().setKey1(o.getConstraints()));
        }
        if (o.getModel().equals("1")) {
           vo.setModel2(o.getParkinggate());
            vo.setConstrain1(vo.getConstrain1().setKey2(o.getConstraint1()));
            vo.setConstrain2(vo.getConstrain2().setKey2(o.getConstraint2()));
            vo.setConstrain3(vo.getConstrain3().setKey2(o.getConstraint3()));
            vo.setConstrain4(vo.getConstrain4().setKey2(o.getConstraint4()));
            vo.setConstrain5(vo.getConstrain5().setKey2(o.getConstraint5()));
            vo.setConstrain6(vo.getConstrain6().setKey2(o.getConstraint6()));
            vo.setConstrains(vo.getConstrains().setKey2(o.getConstraints()));
        }
        if (o.getModel().equals("2")) {
            vo.setModel3(o.getParkinggate());
            vo.setConstrain1(vo.getConstrain1().setKey3(o.getConstraint1()));
            vo.setConstrain2(vo.getConstrain2().setKey3(o.getConstraint2()));
            vo.setConstrain3(vo.getConstrain3().setKey3(o.getConstraint3()));
            vo.setConstrain4(vo.getConstrain4().setKey3(o.getConstraint4()));
            vo.setConstrain5(vo.getConstrain5().setKey3(o.getConstraint5()));
            vo.setConstrain6(vo.getConstrain6().setKey3(o.getConstraint6()));
            vo.setConstrains(vo.getConstrains().setKey3(o.getConstraints()));
        }
        return vo;
    }

    @Override
    public R<?> getinfoByPageAndScore(Integer flightId, String date) {
        Map<String,Object> map=new HashMap<>();
        String sfid = null;
        if (flightId != null){
            sfid = flightId.toString();
        }
        map.put("data1",getinfoByPage(sfid,date));
        //2
        map.put("data2", getinfo());
        return R.ok(map);
    }

    @Override
    public R<?> getInfoByPageAndScore(Integer flightId, String date, int page, int size) {


        return null;
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

    // todo check statistics data
    @Override
    public R<?> getStatisticalTables(String startDate, String endDate,String fileName,String userId) {

        Map<String,Object> result = new HashMap<>();
        Future<Map<String ,Object>> brFuture = asyncStatisticHandler.getBridgeRateMap(startDate, endDate,fileName,
                userId);
        Map<String,Object> bm = null;
        try{
            bm = brFuture.get();
        } catch (InterruptedException  | ExecutionException e) {
            bm = new HashMap<>();
            if ( StringUtils.isNoneEmpty(startDate,endDate)){
                bm.put("xAxis",Collections.singletonList(new XAixsNode<>("category", FakeUtil.fakeXAxis(startDate,endDate))));
            }else {
                LocalDate today = LocalDate.now();
                bm.put("xAxis",Collections.singletonList(new XAixsNode<>("category",
                        FakeUtil.fakeXAxis(today.minusDays(30), today))));
            }
            log.error("获取靠桥率统计图失败");
        }
        result.put("bridgeRate",bm);

        Future<Map<String ,Object>> csFutureMap = asyncStatisticHandler.getConstainsCountMap(startDate, endDate,fileName,userId);
        Map<String,Object> cm = null;
        try{
            cm = csFutureMap.get();
        } catch (InterruptedException  | ExecutionException e) {
            cm = new HashMap<>();
            if ( StringUtils.isNoneEmpty(startDate,endDate)){
                cm.put("xAxis",Collections.singletonList(new XAixsNode<>("category", FakeUtil.fakeXAxis(startDate,endDate))));
            }else {
                LocalDate today = LocalDate.now();
                cm.put("xAxis",Collections.singletonList(new XAixsNode<>("category",
                        FakeUtil.fakeXAxis(today.minusDays(30), today))));
            }
           log.error("获取违反约束统计图失败");
        }
        result.put("constainsCount",cm);


        Map<String, Object> ar = null;
        Future<Map<String, Object>> arFuture = asyncStatisticHandler.getAgreementRate(startDate, endDate, fileName, userId);
        try {
            ar = arFuture.get();
        }catch (InterruptedException  | ExecutionException e) {
            ar = new HashMap<>();
            if ( StringUtils.isNoneEmpty(startDate,endDate)){
                ar.put("xAxis",Collections.singletonList(new XAixsNode<>("category", FakeUtil.fakeXAxis(startDate,endDate))));
            }else {
                LocalDate today = LocalDate.now();
                ar.put("xAxis",Collections.singletonList(new XAixsNode<>("category",
                        FakeUtil.fakeXAxis(today.minusDays(30), today))));
            }
            log.error("获取一致性统计图失败");
        }
        result.put("agreementRate",ar);

        Map<String ,Object> pcc = null;
        Future<Map<String ,Object>>  pccFuture =  asyncStatisticHandler.getPerConstrainCount(startDate, endDate, fileName, userId);
        try {
            pcc = pccFuture.get();
        }catch (InterruptedException  | ExecutionException e) {
            pcc = new HashMap<>();
            if ( StringUtils.isNoneEmpty(startDate,endDate)){
                pcc.put("xAxis",Collections.singletonList(new XAixsNode<>("category", FakeUtil.fakeXAxis(startDate,endDate))));
            }else {
                LocalDate today = LocalDate.now();
                pcc.put("xAxis",Collections.singletonList(new XAixsNode<>("category",
                        FakeUtil.fakeXAxis(today.minusDays(30), today))));
            }
            log.error("获取违反约束统计次数失败");
        }
        result.put("perConstrainCount",pcc);
        return R.ok(result);
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
    }





}
