package com.bjtu.airport.service;

import com.bjtu.airport.entity.TestFlight;
import com.baomidou.mybatisplus.extension.service.IService;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */
public interface ITestFlightService extends IService<TestFlight> {

    /**
     * 根据用户id查询TEST_FLIGHT表中的数据
     * @param userId
     * @return
     */
    List<TestFlight> getinfo(String userId,String filename);

    /**
     * 根据用户id、atime、dtime更新TEST_FLIGHT表中的数据，产生随机延误
     * @return
     */

    int updateinfo(Integer id,String atime, String dtime);

    /**
     * 根据用户id从test flight中查找filename
     * @return
     */
    List<JSONObject> getFilename(String userId);

    /**
     * 根据文件名userId查找航班数据flightinfo
     * @param userId
     * @return
     */
    List<JSONObject> getFlightInfoByUserId(String userId);


    /**
     * 根据文件名filename和用户id查找航班数据flightinfo
     * @param filename
     * @return
     */
    List<JSONObject> getFlightInfoByFilename(String filename, String userId);

    List<Map<String, Object>> getUnioninfo();

    /**
     * 向测试数据表中插入数据
     * @param filename
     * @param flightId
     * @param userId
     * @param updateTime
     * @return
     */
    int insertinfo(String filename, Integer flightId,  String userId, String updateTime);

    /**
     * 根据文件名filename删除仿真数据
     * @param filename
     * @return
     */
    int deleteFilenameInfo(String filename, String userId);

}
