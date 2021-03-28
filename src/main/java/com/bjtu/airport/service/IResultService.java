package com.bjtu.airport.service;

import com.bjtu.airport.entity.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bjtu.airport.entity.StatisticResult;
import com.bjtu.airport.entity.vo.FlightInfoResultVO;
import com.bjtu.airport.entity.vo.TestFlightResult;
import net.sf.json.JSONObject;

import java.util.Map;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */
public interface IResultService extends IService<Result> {
    //2020/3/31
    List<Map<String, Object>> getResults2();
    List<Result> getResults(String model);

    /**
     * 根据用户id从结果表中查询文件名filename
     * @param userId
     * @return
     */
    List<JSONObject> getFilename(String userId);

    /**
     * 根据文件名和用户id联合查询
     * @param filename
     * @param userId
     * @return
     */
    List<JSONObject> getUnionResult(String filename,String userId,String model);

    List<FlightInfoResultVO> getinfo(String fileName, int model, String userId);

    int insertinfo(String parkinggate);
    int insertRes(List<Result> resultArray);

    int insertinfo2(String boardinggate, String planenum, String origin, String airportname, String destination,
                    Integer nation, String mdl, Integer arriveperson, Integer departperson);

    int airportinfo(String model,String parkinggate, String filename, Integer allostate, Integer flightId);

    int airportresult(StatisticResult sr);
    int updateinfo(String parkinggate,Integer allostate,Integer flightId);
    int updateinfo2(Integer flightId);

    /**
     * 通过filename，flightid 获取获取关联信息
     * @return
     */
    List<TestFlightResult> getTestFlightResultList();



    /**
     * 插入新增数据列表
     * @param addList 要插入的列表
     */
    void insertDataList(List<Result> addList);

    /**
     * 更新列表
     * @param updateList  要更新的列表
     */

    void updateDataList(List<Result> updateList);

    /**
     * 根据文件名filename和用户id删除仿真数据
     * tyj  2020-4-20
     * @param filename
     * @param userId
     * @return
     */
    int deleteByUserid(String filename, String userId);

}
