package com.bjtu.airport.mapper;

import com.bjtu.airport.entity.Result;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjtu.airport.entity.vo.BridgeCount;
import com.bjtu.airport.entity.vo.FlightInfoResultVO;
import com.bjtu.airport.entity.vo.TestFlightResult;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Map;


import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */
@Repository
public interface ResultMapper extends BaseMapper<Result> {
    //查询result表(改2020/3/31)
    @Select("select filename,model,flightId,allostate,parkinggate,userId,updateTime from RESULT;")
    List<Map<String, Object>> getResultData2();


    /**
     * 根据用户id从结果表中查询文件名filename
     * @param userId
     * @return
     */
    @Select("SELECT DISTINCT filename FROM  RESULT WHERE RESULT.userId = #{userId}")
    List<JSONObject> getFilename(@Param("userId") String userId);


    /**
     * 根据文件名和用户id联合查询
     * @param filename
     * @param userId
     * @return
     */
//    @Select("SELECT FLIGHT_INFO.id,RESULT.filename,RESULT.model,RESULT.flightId,RESULT.allostate,RESULT.parkinggate,RESULT.userId, RESULT.updateTime,FLIGHT_INFO.isvip,FLIGHT_INFO.business,FLIGHT_INFO.paras,FLIGHT_INFO.atime,FLIGHT_INFO.dtime,FLIGHT_INFO.nation,FLIGHT_INFO.mdl,FLIGHT_INFO.planenum,FLIGHT_INFO.aflightnum,FLIGHT_INFO.dflightnum,FLIGHT_INFO.origin,FLIGHT_INFO.airportname,FLIGHT_INFO.destination,concat(FLIGHT_INFO.aflightnum,'/',FLIGHT_INFO.dflightnum) as flightnum ,concat(FLIGHT_INFO.origin,'-',FLIGHT_INFO.airportname,'-',FLIGHT_INFO.destination) as flightline  FROM RESULT,FLIGHT_INFO \n" +
//            "WHERE FLIGHT_INFO.id = RESULT.flightId AND RESULT.filename = #{filename} AND RESULT.userId = #{userId} AND RESULT.model = #{model}")
//    List<JSONObject> getUnionResult(@Param("filename") String filename, @Param("userId") String userId,@Param("model") String model);
    List<JSONObject> getUnionResult(@Param("filename") String filename,
                                    @Param("userId") String userId,
                                    @Param("model") String model);

    //同时查询测试结果表RESULT和航班数据表FLIGHT_INFO中需要的数据

//    @Select( "select RESULT.parkinggate,FLIGHT_INFO.origin,FLIGHT_INFO.airportname,FLIGHT_INFO.destination,FLIGHT_INFO.aflightnum,FLIGHT_INFO.dflightnum,FLIGHT_INFO.atime,FLIGHT_INFO.dtime,FLIGHT_INFO.boardinggate,FLIGHT_INFO.planenum," +
//            "concat(FLIGHT_INFO.origin,'-',FLIGHT_INFO.airportname,'-',FLIGHT_INFO.destination) as flightline" +
//            ",FLIGHT_INFO.nation,FLIGHT_INFO.mdl,FLIGHT_INFO.arriveperson,FLIGHT_INFO.departperson from RESULT,FLIGHT_INFO where RESULT.flightId=FLIGHT_INFO.id")
//
//    List<Map<String, Object>> getinfo();
    @Select("select r.parkinggate,f.origin, f.airportname,f.destination,f.aflightnum,f.dflightnum," +
            "f.atime,f.dtime,f.boardinggate,f.planenum,concat(f.origin,'-',f.airportname,'-',f.destination) as flightline," +
            "f.nation,f.mdl,f.arriveperson,f.departperson from RESULT r,FLIGHT_INFO f " +
            "where  r.flightId=f.id and r.filename=#{fileName} and r.model=#{model}and r.userId=#{userId}")
    List<FlightInfoResultVO> getinfo(@Param("fileName") String fileName, @Param("model") String model, @Param("userId")
            String userId );

    //查询某一时间段的进港时间
    @Select("select * from RESULT where  RESULT.flightId=FLIGHT_INFO.id and FLIGHT_INFO.atime>#{start} and FLIGHT_INFO.atime<#{end} ")

    //向测试结果表RESULT和航班数据表FLIGHT_INFO中插入数据

    @Insert("insert into RESULT(parkinggate)values(#{parkinggate})")
    int insertinfo(@Param("parkinggate") String parkinggate);
    @Insert( "insert into FLIGHT_INFO(boardinggate,planenum,origin,airportname,destination,nation,mdl,arriveperson,departperson) values(#{boardinggate},(#{planenum}),(#{origin}),(#{airportname}),(#{destination}),(#{nation}),(#{mdl}),(#{arriveperson}),(#{departperson}))")
    int insertinfo2(@Param("boardinggate") String boardinggate, @Param("planenum") String planenum,
                    @Param("origin") String origin, @Param("airportname") String airportname,
                    @Param("destination") String destination, @Param("nation") Integer nation, @Param("mdl") String mdl,
                    @Param("arriveperson") Integer arriveperson, @Param("departperson") Integer departperson);

    //保存服务器返回的数据

    @Insert("insert into RESULT(model,parkinggate,filename,allostate,flightId) values(#{model},#{parkinggate},#{filename},#{allostate},#{flightId})")
    int airportinfo(@Param("model") String model, @Param("parkinggate") String parkinggate,
                    @Param("filename") String filename, @Param("allostate") Integer allostate,@Param("flightId") Integer flightId);
    @Insert("insert into STATISTIC_RESULT(neargatenum,timespend,bridgenum,model,flightnum,haveallo,gatenum,beallo,bridgerate) values(#{neargatenum},(#{timespend}),(#{bridgenum}),(#{model}),(#{flightnum}),(#{haveallo}),(#{gatenum}),(#{beallo}),(#{bridgerate}))")
    int  airportresult(@Param("neargatenum") Integer neargatenum, @Param("timespend") Integer timespend,
                       @Param("bridgenum") Integer bridgenum,@Param("model") String model,
                       @Param("flightnum") Integer flightnum,@Param("haveallo") Integer haveallo,
                       @Param("gatenum") Integer gatenum,@Param("beallo") Integer beallo,
                       @Param("bridgerate") String bridgerate);

    @Select("select * from RESULT where model=#{model}")
    List<Result> getResultData(@Param("model") String model);

    @Update({"update RESULT set parkinggate= #{parkinggate},allostate=#{allostate} where flightId= #{flightId}"})
    int updateinfo(@Param("parkinggate") String parkinggate,
                   @Param("allostate") Integer allostate,
                   @Param("flightId") Integer flightId);


    @Update({"update RESULT set parkinggate= NULL,allostate=0 where flightId= #{flightId}"})
    int updateinfo2(@Param("flightId") Integer flightId);

    /**
     * 插入列表
     * @param addList 要插入的列表
     */
    void insertDataList(List<Result> addList);

    /**
     * 更新列表
     * @param updateList 要更新的列表
     */
    void updateDataList(List<Result> updateList);

    @Select("select tf.filename,tf.flightId,tf.userId from TEST_FLIGHT tf")
	List<TestFlightResult> selectConnData();

    /**
     * 根据用户ID个文件名删除结果表中数据
     * @param filename
     * @param userId
     * @return
     */
    @Delete("DELETE FROM RESULT WHERE filename = #{filename} and userId = #{userId}")
    int deleteByUserid(@Param("filename") String filename, @Param("userId") String userId);

    List<Result> getResultForAgreementRate(@Param("startDate") String startDate,
                                           @Param("endDate") String endDate,
                                           @Param("fileName") String fileName,
                                           @Param("userId") String userId);

    List<BridgeCount> selectBridgeData(@Param("startDate") String startDate,
                                       @Param("endDate") String endDate,
                                       @Param("fileName") String fileName,
                                       @Param("userId") String userId);

}
