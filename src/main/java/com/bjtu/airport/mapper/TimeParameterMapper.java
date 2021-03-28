package com.bjtu.airport.mapper;

import com.bjtu.airport.entity.AirlineLimit;
import com.bjtu.airport.entity.TimeParameter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TimeParameterMapper extends BaseMapper<TimeParameter> {

    @Select({"SELECT * FROM TIME_PARAMETER where airportname = #{airportname} and userId = #{userId}"})
    List<TimeParameter> getParameterInfo(@Param("airportname") String airportname, @Param("userId") String userId);

    @Insert({"insert into TIME_PARAMETER(airportname, paraname, warningtime1, warningtime2, userId, updateTime) " +
            "values(#{airportname},#{paraname},#{warningtime1}, #{warningtime2}, #{userId},#{updateTime})"})
    int insertinfo(@Param("airportname") String airportname,
                   @Param("paraname") String  paraname,
                   @Param("warningtime1") Double warningtime1,
                   @Param("warningtime2") Double warningtime2,
                   @Param("userId") String userId,
                   @Param("updateTime") String updateTime);


    @Update({"update TIME_PARAMETER set airportname = #{airportname}, paraname = #{paraname},warningtime1 = #{warningtime1}," +
            " warningtime2 = #{warningtime2}, userId = #{userId}, updateTime = #{updateTime} where id = #{id}"})
    int updateInfo(@Param("id") Integer id,
                   @Param("airportname") String airportname,
                   @Param("paraname") String  paraname,
                   @Param("warningtime1") Double warningtime1,
                   @Param("warningtime2") Double warningtime2,
                   @Param("userId") String userId,
                   @Param("updateTime") String updateTime);


    @Delete({"DELETE FROM TIME_PARAMETER WHERE id = #{id}"})
    int batchDelete(@Param("id") Integer id);
}
