package com.bjtu.airport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjtu.airport.entity.ManualResult;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface IManualResultService extends IService<ManualResult> {

    //改2020/3/31
    void insertinfo2( String filename, String model, Integer flightId, Integer allostate, String parkinggate, String userId, String updateTime);
    //改2020/3/31
    List<ManualResult> getResults();
    //改2020/3/31
    int updateinfo3(String parkinggate,String filename,String model,Integer flightId);



    List<Map<String, Object>> getcopyinfo();

    List<Map<String, Object>> getinfo(String userId);

    /**
     * 根据文件名和用户id联合查询
     * @param filename
     * @param userId
     * @return
     */
    List<JSONObject> getUnionResult(String filename, String userId);

    int insertinfo(String filename, Integer flightId, String parkinggate, String userId, String updateTime);

    int updateinfo(String parkinggate,Integer allostate,Integer id);
    int updateinfo2(Integer id);

    int updatecopyinfo(String parkinggate,Integer id);

    int deleteinfo();

    int deleteResultInfo(String filename, String userId);
}
