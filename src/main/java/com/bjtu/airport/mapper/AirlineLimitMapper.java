package com.bjtu.airport.mapper;

import com.bjtu.airport.entity.AirlineLimit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
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
@Mapper
@Repository
public interface AirlineLimitMapper extends BaseMapper<AirlineLimit> {

    @Select("select * from AIRLINE_LIMIT")
    List<AirlineLimit> getinfo();

    @Select({"SELECT * FROM AIRLINE_LIMIT where airportname = #{airportname} and userId = #{userId}"})
    List<AirlineLimit> getLimitInfo(@Param("airportname") String airportname, @Param("userId") String userId);


    @Insert("insert into `AIRLINE_LIMIT`(airline_cd, airlinename, parkinggate, state, airportname, userId, updateTime) " +
            "values(#{airline_cd}, #{airlinename}, #{parkinggate},#{state},#{airportname},#{userId},#{updateTime})")
    int insertinfo(@Param("airline_cd") String airlineCd,
                   @Param("airlinename") String airlinename,
                   @Param("parkinggate") String  parkinggate,
                   @Param("state") Integer state,
                   @Param("airportname") String airportname,
                   @Param("userId") String userId,
                   @Param("updateTime") String updateTime);


    @Update({"update AIRLINE_LIMIT set " +
            "parkinggate = #{parkinggate}, " +
            "airlinename = #{airlinename}," +
            "airline_cd = #{airline_cd}, " +
            "state = #{state}," +
            "airportname = #{airportname}," +
            "userId = #{userId}, " +
            "updateTime = #{updateTime} " +
            "where id = #{id}"})
    int updateInfo(@Param("id") Integer id,
                   @Param("airline_cd") String airlineCd,
                   @Param("airlinename") String airlinename,
                   @Param("parkinggate") String  parkinggate,
                   @Param("state") Integer state,
                   @Param("airportname") String airportname,
                   @Param("userId") String userId,
                   @Param("updateTime") String updateTime);


    @Delete({"DELETE FROM AIRLINE_LIMIT WHERE id = #{id}"})
    int batchDelete(@Param("id") Integer id);






    /**
     * 根据用户id和机场名称查询航司限制表中的数据
     * @param airportname
     * @param userId
     * @return
     */
    @Select({"SELECT * FROM AIRLINE_LIMIT where airportname = #{airportname} AND userId = #{userId}"})
    List<AirlineLimit> getLimitInfoByuserId(@Param("airportname") String airportname,@Param("userId") String userId);




    @Update({"update AIRLINE_LIMIT set airline_cd = #{airline_cd} where id = #{id} AND userId = #{userId}"})
    int updateinfo1(@Param("id") Integer id,
                    @Param("airline_cd") String airline_cd,
                    @Param("userId") String userId);


    @Update({"update AIRLINE_LIMIT set parkinggate = #{parkinggate} where id = #{id} AND userId = #{userId}"})
    int updateinfo2(@Param("id") Integer id,
                    @Param("parkinggate") String parkinggate,
                    @Param("userId") String userId);


    @Update({"update AIRLINE_LIMIT set state = #{state} where id = #{id} AND userId = #{userId}"})
    int updateinfo3(@Param("id") Integer id,
                    @Param("state") Integer state,
                    @Param("userId") String userId);


    @Delete({"delete from AIRLINE_LIMIT where id = #{id}"})
    int deleteAirlineLimit(@Param("id") Integer id) ;



}
