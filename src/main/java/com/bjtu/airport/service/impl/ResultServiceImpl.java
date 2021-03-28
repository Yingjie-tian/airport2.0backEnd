package com.bjtu.airport.service.impl;

import com.bjtu.airport.entity.Result;
import com.bjtu.airport.entity.StatisticResult;
import com.bjtu.airport.entity.vo.FlightInfoResultVO;
import com.bjtu.airport.entity.vo.TestFlightResult;
import com.bjtu.airport.mapper.ManualResultMapper;
import com.bjtu.airport.mapper.ResultMapper;
import com.bjtu.airport.mapper.StatisticResultMapper;
import com.bjtu.airport.service.IResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */
@Service
public class ResultServiceImpl extends ServiceImpl<ResultMapper, Result> implements IResultService {

    @Autowired
    private ResultMapper ResultMapper;

    @Autowired
    private StatisticResultMapper statisticResultMapper;


    @Override
    public List<Map<String, Object>> getResults2() {
        return ResultMapper.getResultData2();
    }


    /**
     * 根据用户id从结果表中查询文件名filename
     * @param userId
     * @return
     */
    @Override
    public List<JSONObject> getFilename(String userId){

        List<JSONObject> filename = ResultMapper.getFilename(userId);

        return  filename;
    };

    @Override
    public int insertRes(List<Result> resultArray){
        int sum = 0;
        for (int i = 0; i < resultArray.size(); i++) {
//          net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) jsonArray.get(i);
            System.out.println("resultArray.get(i)"+resultArray.get(i));
            int row = ResultMapper.insert(resultArray.get(i));
            sum = sum + row;
        }
        return sum;
    };




    /**
     * 根据文件名和用户id联合查询
     * @param filename
     * @param userId
     * @return
     */
    @Override
    public List<JSONObject> getUnionResult(String filename, String userId,String model){

        List<JSONObject> unionResult = ResultMapper.getUnionResult(filename, userId,model);

        return unionResult;
    };
    @Override
    public int updateinfo(String parkinggate,Integer allostate,Integer flightId){
        int info= ResultMapper.updateinfo(parkinggate,allostate,flightId);
        return info;
    };

    @Override
    public int updateinfo2(Integer flightId){
        int info=ResultMapper.updateinfo2(flightId);
        return info;

    }

    @Override
    public List<FlightInfoResultVO> getinfo(String fileName,int model,String userId) {
        List<FlightInfoResultVO> info = ResultMapper.getinfo(fileName, String.valueOf(model),userId);
        return info;
    }


    @Override
    public int insertinfo(String parkinggate) {

        int info = ResultMapper.insertinfo(parkinggate);

        return info;
    }
    @Override
    public int insertinfo2( String boardinggate,  String planenum, String origin, String airportname,String destination,
                            Integer nation, String mdl, Integer arriveperson, Integer departperson){
        int info =ResultMapper.insertinfo2(boardinggate,planenum,origin,airportname,destination,nation,mdl,arriveperson,departperson);
        return info;
    }

    @Override
    public int airportinfo(String model,String parkinggate,String filename, Integer allostate,Integer flightId){
        int info = ResultMapper.airportinfo(model,parkinggate,filename,allostate,flightId);
        return info;
    }

    @Override
    public int airportresult(StatisticResult sr){
        int info = statisticResultMapper.insert(sr);
        return info;
    }

    @Override
    public List<TestFlightResult> getTestFlightResultList() {
        return ResultMapper.selectConnData();
    }


    @Override
    public List<Result> getResults(String model) {
        return ResultMapper.getResultData(model);
    }

    @Override
    public void insertDataList(List<Result> addList) {
        if(addList.size()>0){
            ResultMapper.insertDataList(addList);
        }

    }

    @Override
    public void updateDataList(List<Result> updateList) {
        if(updateList.size()>0){
            ResultMapper.updateDataList(updateList);
        }

    }

    /**
     * 根据文件名filename和用户id删除仿真数据
     * @param filename
     * @param userId
     * @return
     */
    @Override
    public int deleteByUserid(String filename, String userId){
        int info = ResultMapper.deleteByUserid(filename, userId);
        return info;

    };


}
