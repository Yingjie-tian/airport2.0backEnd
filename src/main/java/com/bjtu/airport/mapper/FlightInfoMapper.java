package com.bjtu.airport.mapper;

import com.bjtu.airport.entity.FlightInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
public interface FlightInfoMapper extends BaseMapper<FlightInfo> {




    /**
     * 查询FLIGHT_INFO表中所有数据
     * @return
     */
    @Select("select * from FLIGHT_INFO")
    List<FlightInfo> getinfo();



    /**
     * 向FLIGHT_INFO表中插入数据
     * @param airportname
     * @param parkinggate
     * @param boardinggate
     * @param planenum
     * @param aflightnum
     * @param aflightid
     * @param dflightnum
     * @param dflightid
     * @param paras
     * @param atime
     * @param dtime
     * @param nation
     * @param mdl
     * @param business
     * @param origin
     * @param destination
     * @param isvip
     * @param arriveperson
     * @param departperson
     * @param updateTime
     * @return
     */
    @Insert("insert into FLIGHT_INFO(airportname, parkinggate, boardinggate, planenum, aflightnum, aflightid ,dflightnum ,dflightid, paras, atime, dtime, nation, mdl, business, origin, destination, isvip, arriveperson, departperson, updateTime)" +
            "values(#{airportname},#{parkinggate},#{boardinggate},#{planenum},#{aflightnum},#{aflightid},#{dflightnum},#{dflightid},#{paras},#{atime},#{dtime},#{nation},#{mdl},#{business},#{origin},#{destination},#{isvip},#{arriveperson},#{departperson},#{updateTime})")
    int insertinfo(@Param("airportname") String airportname, @Param("parkinggate") String parkinggate,
                   @Param("boardinggate") String boardinggate, @Param("planenum") String planenum,
                   @Param("aflightnum") String aflightnum, @Param("aflightid") String aflightid,
                   @Param("dflightnum") String dflightnum, @Param("dflightid") String dflightid,
                   @Param("paras") Integer paras , @Param("atime") String atime,
                   @Param("dtime") String dtime, @Param("nation") Integer nation,
                   @Param("mdl") String mdl, @Param("business") Integer business,
                   @Param("origin") String origin, @Param("destination") String destination,
                   @Param("isvip") Integer isvip, @Param("arriveperson") Integer arriveperson,
                   @Param("departperson") Integer departperson, @Param("updateTime") String updateTime);


    /**
     * 查询FLIGHT_INFO表的机场名称
     * @return
     */
    @Select("SELECT DISTINCT FLIGHT_INFO.airportname FROM FLIGHT_INFO")
    List<JSONObject> getName();

    /**
     * 查询FLIGHT_INFO表的机场机场数据-根据机场名称
     * @return
     */
    @Select("SELECT * FROM `FLIGHT_INFO` WHERE FLIGHT_INFO.airportname = #{airportname}")
    List<JSONObject> getFlightInfoByAirportname( @Param("airportname") String airportname);


    /**
     * 测试数据保存在result表
     * @return
     */
    @Insert("insert into result(filename, model, flightId, allostate, parkinggate, runnums ,testtime ,userId, updateTime) values(#{filename},#{model},#{flightId},#{allostate},#{parkinggate},#{runnums},#{testtime},#{userId},#{updateTime})")
    int insertResult(@Param("filename") String filename,
                     @Param("model") String model,
                     @Param("flightId") Integer flightId,
                     @Param("allostate") Integer allostate,
                     @Param("parkinggate") String parkinggate,
                     @Param("runnums") Integer runnums,
                     @Param("testtime") String testtime,
                     @Param("userId") String userId,
                     @Param("updateTime") String updateTime);


}
