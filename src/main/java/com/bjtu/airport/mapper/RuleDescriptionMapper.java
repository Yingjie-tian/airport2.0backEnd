package com.bjtu.airport.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjtu.airport.entity.RuleDescription;
import net.sf.json.JSONObject;
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
public interface RuleDescriptionMapper extends BaseMapper<RuleDescription> {


    @Select("SELECT * FROM RULE_DESCRIPTION")
    List<RuleDescription>getinfo();


    /**
     * 根据机场名称和用户id查找相应规则
     * @param airportname
     * @param userId
     * @return
     */
    @Select("SELECT `AIR_RULE`.*, `RULE_DESCRIPTION`.description, `RULE_DESCRIPTION`.corrtablename, `RULE_DESCRIPTION`.paraname,  `RULE_DESCRIPTION`.parastate FROM `RULE_DESCRIPTION`, `AIR_RULE`" +
            " WHERE AIR_RULE.statenum = RULE_DESCRIPTION.id AND AIR_RULE.airportname = #{airportname} AND AIR_RULE.userId = #{userId};")
    List<JSONObject>getByAirportname(@Param("airportname") String airportname, @Param("userId") String userId);


    @Insert("insert into RULE_STATE(`state`, `describe`, `parastate`, `corrtablename`, `paraname`, `airportname`, `userId`, `updateTime`) " +
            "values(#{state},#{describe},#{parastate},#{corrtablename},#{paraname},#{airportname},#{userId},#{updateTime})")

    int insertinfo(@Param("state") Integer state,
                   @Param("describe") String describe,
                   @Param("parastate") Integer parastate,
                   @Param("corrtablename") String corrtablename,
                   @Param("paraname") String paraname,
                   @Param("airportname") String airportname,
                   @Param("userId") String userId,
                   @Param("updateTime") String updateTime);


    @Delete({"delete from RULE_STATE where id = #{id}"})
    int deleteinfo(@Param("id") Integer id) ;



    @Update({"update RULE_STATE set state = #{state} where id = #{id}"})
    int updateinfo(@Param("id") Integer id,
                   @Param("state") Integer state);
}



