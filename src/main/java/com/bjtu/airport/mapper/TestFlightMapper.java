package com.bjtu.airport.mapper;

import com.bjtu.airport.entity.TestFlight;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */
@Repository
public interface TestFlightMapper extends BaseMapper<TestFlight> {

    /**
     * 根据用户id查询TEST_FLIGHT表中的数据
     * @return
     */
    @Select("select * from TEST_FLIGHT WHERE `TEST_FLIGHT`.userId = #{userId} AND `TEST_FLIGHT`.filename = #{filename}")
    List<TestFlight> getinfo(@Param("userId") String userId,@Param("filename") String filename);

//    2020/12/11


    /**
     * 根据用户id从test_flight中查找有几个仿真数据，即查找filename
     * @return
     */
    @Select("SELECT DISTINCT filename FROM `TEST_FLIGHT` WHERE `TEST_FLIGHT`.userId = #{userId}  ")
    List<JSONObject> getFilename(@Param("userId") String userId);


    /**
     * 根据文件名userId查询航班数据flightinfo
     * @param  userId
     * @return
     */
    @Select("SELECT FLIGHT_INFO.*, TEST_FLIGHT.* FROM TEST_FLIGHT, FLIGHT_INFO\n" +
            "where TEST_FLIGHT.userId = #{userId} AND TEST_FLIGHT.flightId = FLIGHT_INFO.id ")
    List<JSONObject> getFlightInfoByUserId(@Param("userId") String userId);



    /**
     * 根据文件名filename和用户id查询航班数据flightinfo
     * @param filename
     * @return
     */
    @Select("SELECT FLIGHT_INFO.*, TEST_FLIGHT.*, concat(FLIGHT_INFO.aflightnum,'/',FLIGHT_INFO.dflightnum) as flightnum, concat(FLIGHT_INFO.origin,'-',FLIGHT_INFO.airportname,'-',FLIGHT_INFO.destination) as flightline FROM TEST_FLIGHT, FLIGHT_INFO\n" +
            "where TEST_FLIGHT.userId = #{userId} and TEST_FLIGHT.filename = #{filename} AND TEST_FLIGHT.flightId = FLIGHT_INFO.id ")
    List<JSONObject> getFlightInfoByFilename(@Param("filename") String filename, @Param("userId") String userId);

//    @Select("SELECT FLIGHT_INFO.id ,FLIGHT_INFO.isvip,FLIGHT_INFO.pass,FLIGHT_INFO.business,FLIGHT_INFO.paras,FLIGHT_INFO.atime,FLIGHT_INFO.dtime,FLIGHT_INFO.nation,FLIGHT_INFO.mdl,FLIGHT_INFO.planenum,FLIGHT_INFO.aflightnum,FLIGHT_INFO.dflightnum,FLIGHT_INFO.origin,FLIGHT_INFO.airportname,FLIGHT_INFO.destination,concat(FLIGHT_INFO.aflightnum,'/',FLIGHT_INFO.dflightnum) as flightnum ,concat(FLIGHT_INFO.origin,'-',FLIGHT_INFO.airportname,'-',FLIGHT_INFO.destination) as flightline, TEST_FLIGHT.* FROM TEST_FLIGHT, FLIGHT_INFO\n" +
//            "where TEST_FLIGHT.userId = #{userId} and TEST_FLIGHT.filename = #{filename} AND TEST_FLIGHT.flightId = FLIGHT_INFO.id ")
//    List<JSONObject> getFlightInfoByFilename(@Param("filename") String filename, @Param("userId") String userId);


    /**
     * 同时查询测试结果表RESULT和航班数据表FLIGHT_INFO中需要的数据
     * @return
     */
    @Select( "select TEST_FLIGHT.id,TEST_FLIGHT.flightId,TEST_FLIGHT.filename,TEST_FLIGHT.parkinggate,FLIGHT_INFO.atime,FLIGHT_INFO.dtime,FLIGHT_INFO.boardinggate,FLIGHT_INFO.planenum,FLIGHT_INFO.aflightnum,FLIGHT_INFO.dflightnum,FLIGHT_INFO.origin,FLIGHT_INFO.airportname,FLIGHT_INFO.destination," + "concat(FLIGHT_INFO.aflightnum,'/',FLIGHT_INFO.dflightnum) as flightnum" +
            ",concat(FLIGHT_INFO.origin,'-',FLIGHT_INFO.airportname,'-',FLIGHT_INFO.destination) as flightline" +
            ",FLIGHT_INFO.nation,FLIGHT_INFO.mdl,FLIGHT_INFO.arriveperson,FLIGHT_INFO.departperson, FLIGHT_INFO.parkinggate from TEST_FLIGHT,FLIGHT_INFO where TEST_FLIGHT.flightId=FLIGHT_INFO.id")
    List<Map<String, Object>> getUnionInfo();



    /**
     * 根据用户id、atime、dtime更新TEST_FLIGHT表中的数据，产生随机延误
     * @return
     */
    @Update({"update TEST_FLIGHT set atime= #{atime},dtime=#{dtime} where TEST_FLIGHT.id= #{id}"})
    int updateinfo(@Param("id") Integer id,
                   @Param("atime") String atime,
                   @Param("dtime") String dtime);

    @Insert("insert into TEST_FLIGHT(filename,flightId, userId,updateTime) " +
            "values(#{filename},#{flightId},#{userId},#{updateTime})")
    int insertinfo(@Param("filename") String filename,
                   @Param("flightId") Integer flightId,
                   @Param("userId") String userId,
                   @Param("updateTime") String updateTime);

    /**
     * 根据文件名filenamehe和用户id删除仿真数据
     * @param filename
     * @return
     */
    @Delete("delete from TEST_FLIGHT where filename = #{filename} AND `TEST_FLIGHT`.userId = #{userId}")
    int deleteFilenameInfo(@Param("filename") String filename, @Param("userId") String userId);

}
