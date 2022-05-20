package com.bjtu.airport.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjtu.airport.entity.ManualResult;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ManualResultMapper extends BaseMapper<ManualResult> {

    @Select("select * from MANUAL_RESULT;")
    List<ManualResult> getResultData();


    //查询MANUAL_RESULT_copy表中所有数据
    @Select( "select MANUAL_RESULT_copy.id,MANUAL_RESULT_copy.parkinggate,FLIGHT_INFO.atime,FLIGHT_INFO.dtime,FLIGHT_INFO.planenum,FLIGHT_INFO.aflightnum,FLIGHT_INFO.dflightnum,FLIGHT_INFO.origin,FLIGHT_INFO.airportname,FLIGHT_INFO.destination," + "concat(FLIGHT_INFO.aflightnum,'/',FLIGHT_INFO.dflightnum) as flightnum" +
            ",concat(FLIGHT_INFO.origin,'-',FLIGHT_INFO.airportname,'-',FLIGHT_INFO.destination) as flightline" +
            " from MANUAL_RESULT_copy,FLIGHT_INFO where MANUAL_RESULT_copy.flightId=FLIGHT_INFO.id")
    List<Map<String, Object>> getcopyinfo();

    //改2020/3/31
    @Select( "select MANUAL_RESULT.id,MANUAL_RESULT.model,MANUAL_RESULT.filename,MANUAL_RESULT.parkinggate,FLIGHT_INFO.atime,FLIGHT_INFO.dtime,FLIGHT_INFO.boardinggate,FLIGHT_INFO.planenum,FLIGHT_INFO.aflightnum,FLIGHT_INFO.dflightnum,FLIGHT_INFO.origin,FLIGHT_INFO.airportname,FLIGHT_INFO.destination," + "concat(FLIGHT_INFO.aflightnum,'/',FLIGHT_INFO.dflightnum) as flightnum" +
            ",concat(FLIGHT_INFO.origin,'-',FLIGHT_INFO.airportname,'-',FLIGHT_INFO.destination) as flightline" +
            ",FLIGHT_INFO.nation,FLIGHT_INFO.mdl,FLIGHT_INFO.arriveperson,FLIGHT_INFO.departperson from MANUAL_RESULT,FLIGHT_INFO where MANUAL_RESULT.flightId=FLIGHT_INFO.id AND MANUAL_RESULT.userId = #{userId}")
    List<Map<String, Object>> getinfo(@Param("userId") String userId);

    //向MANUAL_RESULT表中插入(改2020/3/31)
    @Insert("insert into MANUAL_RESULT(filename, model, flightId, allostate, parkinggate,userId,updateTime) values(#{filename},#{model},#{flightId},#{allostate},#{parkinggate},#{userId},#{updateTime})")
    void insertinfo2(@Param("filename") String filename,
                     @Param("model") String model,
                     @Param("flightId") Integer flightId,
                     @Param("allostate") Integer allostate,
                     @Param("parkinggate") String parkinggate,
                     @Param("userId") String userId,
                     @Param("updateTime") String updateTime);

    //改2020/3/31
    @Update({"update MANUAL_RESULT set parkinggate= #{parkinggate} where filename= #{filename} and model=#{model} and flightId=#{flightId}"})
    int updateinfo3(@Param("parkinggate") String parkinggate,
                    @Param("filename") String filename,
                    @Param("model") String model,
                    @Param("flightId") Integer flightId);


    /**
     * 根据文件名和用户id联合查询
     * @param filename
     * @param userId
     * @return
     */
    @Select("SELECT MANUAL_RESULT.*, FLIGHT_INFO.* FROM MANUAL_RESULT,FLIGHT_INFO \n" +
            "WHERE FLIGHT_INFO.id = MANUAL_RESULT.flightId AND MANUAL_RESULT.filename = #{filename} AND MANUAL_RESULT.userId = #{userId}")
    List<JSONObject> getUnionResult(@Param("filename") String filename, @Param("userId") String userId);


    //向MANUAL_RESULT表中插入数据
    @Insert("insert into MANUAL_RESULT(filename, flightId, parkinggate, userId, updateTime) values(#{filename},#{flightId},#{parkinggate}, #{userId}, #{updateTime})")
    int insertinfo(@Param("filename") String filename,
                   @Param("flightId") Integer flightId,
                   @Param("parkinggate") String parkinggate,
                   @Param("userId") String userId,
                   @Param("updateTime") String updateTime);

    @Update({"update MANUAL_RESULT set parkinggate= #{parkinggate},allostate=#{allostate} where id= #{id}"})
    int updateinfo(@Param("parkinggate") String parkinggate,
                   @Param("allostate") Integer allostate,
                   @Param("id") Integer id);

    @Update({"update MANUAL_RESULT set parkinggate= NULL,allostate=0 where id= #{id}"})
    int updateinfo2(@Param("id") Integer id);
    @Update({"update MANUAL_RESULT_copy set parkinggate= #{parkinggate} where id= #{id}"})
    int updatecopyinfo(@Param("parkinggate") String parkinggate,
                       @Param("id") Integer id);


    @Delete({"delete from MANUAL_RESULT_copy"})
    int deleteinfo() ;


    /**
     * 根据文件名filename和用户id删除数据
     * @param filename
     * @param userId
     * @return
     */
    @Delete("delete from MANUAL_RESULT where filename = #{filename} AND userId = #{userId}")
    int deleteResultInfo(@Param("filename") String filename, @Param("userId") String userId);

}
