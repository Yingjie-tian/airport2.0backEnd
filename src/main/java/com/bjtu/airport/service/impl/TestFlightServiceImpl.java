package com.bjtu.airport.service.impl;

import com.bjtu.airport.entity.TestFlight;
import com.bjtu.airport.mapper.FlightInfoMapper;
import com.bjtu.airport.mapper.TestFlightMapper;
import com.bjtu.airport.service.ITestFlightService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */
@Service
public class TestFlightServiceImpl extends ServiceImpl<TestFlightMapper, TestFlight> implements ITestFlightService {

    @Autowired
    private TestFlightMapper testFlightMapper;

    /**
     * 根据用户id查询TEST_FLIGHT表中的数据
     * @return
     */
    @Override
    public List<TestFlight> getinfo(String userId,String filename){
        List<TestFlight> info = testFlightMapper.getinfo(userId,filename);
        return info;
    }

    /**
     * 根据用户id、atime、dtime更新TEST_FLIGHT表中的数据，产生随机延误
     * @return
     */
    @Override
    public int updateinfo(Integer id,String atime,String dtime) {

        int info = testFlightMapper.updateinfo(id,atime,dtime);
        return info;
    }


    /**
     * 根据用户id从test flight中查找filename
     * @return
     */
    @Override
    public List<JSONObject> getFilename(String userId){

        List<JSONObject> filenameInfo = testFlightMapper.getFilename(userId);

        return filenameInfo;
    };


    /**
     * 根据文件名userId查找航班数据flightinfo
     * @param userId
     * @return
     */
    @Override
    public List<JSONObject> getFlightInfoByUserId(String userId){

        List<JSONObject> flightInfo = testFlightMapper.getFlightInfoByUserId(userId);

        return flightInfo;
    };


    /**
     * 根据文件名filename和用户id查找航班数据flightinfo
     * @param filename
     * @return
     */
    @Override
    public List<JSONObject> getFlightInfoByFilename(String filename, String userId){

        List<JSONObject> flightInfo = testFlightMapper.getFlightInfoByFilename(filename, userId);

        return flightInfo;
    };


    @Override
    public List<Map<String, Object>> getUnioninfo() {
        List<Map<String, Object>> info = testFlightMapper.getUnionInfo();
        return info;
    }




    @Override
    public int insertinfo(String filename,Integer flightId,  String userId, String updateTime) {

        int info = testFlightMapper.insertinfo( filename,flightId, userId, updateTime);

        return info;
    }


    /**
     * 根据文件名filename和用户id删除仿真数据
     * @param filename
     * @return
     */
    @Override
    public int deleteFilenameInfo(String filename, String userId){

        int info = testFlightMapper.deleteFilenameInfo(filename, userId);

        return info;
    };

}
