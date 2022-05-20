package com.bjtu.airport.mapper;

import com.bjtu.airport.entity.AirlineBridgeRate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjtu.airport.entity.AirlineLimit;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface AirlineBridgeRateMapper extends BaseMapper<AirlineBridgeRate>{



    @Select({"SELECT * FROM AIRLINE_BRIDGERATE where airportname = #{airportname} and userId = #{userId}"})
    List<AirlineBridgeRate> getBridgeRateInfo(@Param("airportname") String airportname, @Param("userId") String userId);

    @Insert({"insert into AIRLINE_BRIDGERATE(airportname, airline_cd, airlinename, bridgerate, userId, updateTime) " +
            "values(#{airportname}, #{airline_cd},#{airlinename},#{bridgerate},#{userId},#{updateTime})"})
    int insertinfo(@Param("airportname") String airportname,
                   @Param("airline_cd") String airlineCd,
                   @Param("airlinename") String  airlinename,
                   @Param("bridgerate") String bridgerate,
                   @Param("userId") String userId,
                   @Param("updateTime") String updateTime);


    @Update({"update AIRLINE_BRIDGERATE set bridgerate = #{bridgerate}, airlinename = #{airlinename},airline_cd = #{airline_cd}, userId = #{userId}, updateTime = #{updateTime} where id = #{id}"})
    int updateInfo(@Param("id") Integer id,
                   @Param("bridgerate") String  bridgerate,
                        @Param("airlinename") String airlinename,
                        @Param("airline_cd") String airlineCd,
                        @Param("userId") String userId,
                        @Param("updateTime") String updateTime);


    @Delete({"DELETE FROM AIRLINE_BRIDGERATE WHERE id = #{id}"})
    int batchDelete(@Param("id") Integer id);
}
