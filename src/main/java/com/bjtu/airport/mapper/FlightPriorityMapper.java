package com.bjtu.airport.mapper;

import com.bjtu.airport.entity.FlightInfo;
import com.bjtu.airport.entity.FlightPriority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */
@Mapper
@Repository
public interface FlightPriorityMapper extends BaseMapper<FlightPriority> {

    @Select({"SELECT * FROM FLIGHT_PRIORITY WHERE airportname = #{airportname} AND userId = #{userId}"})
    List<JSONObject> getPriorityByAirportname(@Param("airportname") String airportname,
                                              @Param("userId") String userId);

    @Update({"update FLIGHT_PRIORITY set sortrule = #{sortrule} where id = #{id}"})
    int updatesortrule(@Param("id") Integer id,
                       @Param("sortrule") Integer sortrule);

    @Delete("DELETE FROM FLIGHT_PRIORITY WHERE airportname = #{airportname} AND userId = #{userId}")
    int deleteByAirportName(@Param("airportname") String airportname,
                            @Param("userId") String userId);

    @Insert({"insert into FLIGHT_PRIORITY(airportname, flighttype, flightkey,prioritynum,sortrule, userId, updateTime) " +
            "values(#{airportname},#{flighttype},#{flightkey},#{prioritynum},#{sortrule},#{userId},#{updateTime})"})
    int insertinfo(@Param("airportname") String airportname,
               @Param("flighttype") String flighttype,
               @Param("flightkey") String flightkey,
               @Param("prioritynum") Integer prioritynum,
               @Param("sortrule") Integer sortrule,
               @Param("userId") String userId,
               @Param("updateTime") String updateTime);

}
