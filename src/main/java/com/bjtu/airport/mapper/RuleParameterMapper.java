package com.bjtu.airport.mapper;

import com.bjtu.airport.entity.RuleParameter;
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
public interface RuleParameterMapper extends BaseMapper<RuleParameter> {

    @Select("select * from RULE_PARAMETER")
    List<RuleParameter> getinfo();


    @Select({"SELECT * FROM RULE_PARAMETER where airportname = #{airportname}"})
    List<RuleParameter> getinfo(@Param("airportname") String airportname,@Param("userId")String userId);


    /**
     * 根据机场名称和用户id查询规则参数
     * @param airportname
     * @param userId
     * @return
     */
    @Select({"SELECT * FROM RULE_PARAMETER where airportname = #{airportname} AND userId = #{userId}"})
    List<RuleParameter> getParameter(@Param("airportname") String airportname, @Param("userId") String userId);



    @Insert("insert into RULE_PARAMETER(warningtime1, warningtime2, airportname, userId, updateTime) " +
            "values(#{warningtime1},#{warningtime2},#{airportname},#{userId},#{updateTime})")

    int insertinfo(@Param("warningtime1") Double warningtime1,
                   @Param("warningtime2") Double warningtime2,
                   @Param("airportname") String airportname,
                   @Param("userId") String userId,
                   @Param("updateTime") String updateTime);


    /**
     * 更改规则参数
     * @param id
     * @param warningtime1
     * @param userId
     * @return
     */
    @Update({"update RULE_PARAMETER set warningtime1 = #{warningtime1} where id = #{id} AND RULE_PARAMETER.userId = #{userId}"})
    int updateinfo1(@Param("id") Integer id,
                    @Param("warningtime1") Double warningtime1,
                    @Param("userId") String userId);


    /**
     * 更改规则参数
     * @param id
     * @param warningtime2
     * @param userId
     * @return
     */
    @Update({"update RULE_PARAMETER set warningtime2 = #{warningtime2} where id = #{id} AND RULE_PARAMETER.userId = #{userId}"})
    int updateinfo2(@Param("id") Integer id,
                    @Param("warningtime2") Double warningtime2,
                    @Param("userId") String userId);

}
