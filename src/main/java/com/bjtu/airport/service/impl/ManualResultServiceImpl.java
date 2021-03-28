package com.bjtu.airport.service.impl;

import com.bjtu.airport.entity.ManualResult;
import com.bjtu.airport.mapper.ManualResultMapper;
import com.bjtu.airport.service.IManualResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ManualResultServiceImpl extends ServiceImpl<ManualResultMapper, ManualResult> implements IManualResultService {
    @Autowired
    private ManualResultMapper ManualResultMapper;
    @Override
    public List<Map<String, Object>> getcopyinfo() {
        List<Map<String, Object>> info = ManualResultMapper.getcopyinfo();
        return info;
    }
    //改2020/3/31
    @Override
    public void insertinfo2(String filename, String model, Integer flightId, Integer allostate, String parkinggate,String userId,String updateTime)
    {
//        int info = ManualResultMapper.insertinfo2(filename,model,flightId,allostate,parkinggate,userId,updateTime);
//        return info;
        ManualResultMapper.insertinfo2(filename,model,flightId,allostate,parkinggate,userId,updateTime);
    }
    //改2020/3/31
    @Override
    public List<ManualResult> getResults() {
        return ManualResultMapper.getResultData();
    }
    //改2020/3/31
    @Override
    public int updateinfo3(String parkinggate,String filename,String model,Integer flightId){
        int info=ManualResultMapper.updateinfo3(parkinggate,filename,model,flightId);
        return info;
    }


    @Override
    public List<Map<String, Object>> getinfo(String userId) {
        List<Map<String, Object>> info = ManualResultMapper.getinfo(userId);
        return info;
    }

    /**
     * 根据文件名和用户id联合查询
     * @param filename
     * @param userId
     * @return
     */
    @Override
    public List<JSONObject> getUnionResult(String filename,String userId){

        List<JSONObject> unionResult = ManualResultMapper.getUnionResult(filename, userId);

        return unionResult;
    };


    @Override
    public int insertinfo(String filename, Integer flightId, String parkinggate, String userId, String updateTime)
    {
        int info = ManualResultMapper.insertinfo(filename,flightId,parkinggate,userId,updateTime);
        return info;
    }
    @Override
    public int updateinfo(String parkinggate,Integer allostate,Integer id){
        int info=ManualResultMapper.updateinfo(parkinggate,allostate,id);
        return info;
    }
    @Override
    public int updateinfo2(Integer id){
        int info=ManualResultMapper.updateinfo2(id);
        return info;

    }

    @Override
    public int updatecopyinfo(String parkinggate,Integer id){
        int info=ManualResultMapper.updatecopyinfo(parkinggate ,id);
        return info;
    }

    @Override
    public int deleteinfo(){
        int info = ManualResultMapper.deleteinfo();
        return info;
    }


    /**
     * 根据文件名filename和用户id删除数据
     * @param filename
     * @param userId
     * @return
     */
    @Override
    public int deleteResultInfo(String filename, String userId){

        int info = ManualResultMapper.deleteResultInfo(filename,userId);

        return info;
    };
}
