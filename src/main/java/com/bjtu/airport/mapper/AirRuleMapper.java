package com.bjtu.airport.mapper;

import com.bjtu.airport.entity.AirRule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
 * @since 2020-03-17
 */
@Mapper
@Repository
public interface AirRuleMapper extends BaseMapper<AirRule> {

    /**
     * getAirport()用于查询AIR_RULE表中的机场名称（去重）.
     * @return 返回值为List<AirRule>（AirRule类）.
     */
    @Select("SELECT DISTINCT airportname FROM GATE_INFO")
    List<JSONObject> getAirport();


    /**
     * 想AIR_RULE表中插入数据.
     * @param airportname
     * @param statenum
     * @param state
     * @param userId
     * @param updateTime
     * @return
     */
    @Insert("insert into AIR_RULE(`airportname`, `statenum`, `state`, `userId`, `updateTime`) " +
            "values(#{airportname},#{statenum},#{state},#{userId},#{updateTime})")
    int insertAirRule(@Param("airportname") String airportname,
                      @Param("statenum") Integer statenum,
                      @Param("state") Integer state,
                      @Param("userId") String userId,
                      @Param("updateTime") String updateTime);


    /**
     * 根据主键id在AIR_RULE表中更改状态信息.
     * @param id 主键
     * @param state 状态信息
     * @return
     */
    @Update({"update AIR_RULE set state = #{state} where id = #{id} AND AIR_RULE.userId = #{userId}"})
    int updateState(@Param("id") Integer id,
                    @Param("state") Integer state,
                    @Param("userId") String userId);


    /**
     * 根据主键删除AIR_RULE表中某条信息.
     * @param id
     * @return
     */
    @Delete({"delete from AIR_RULE where id = #{id} AND AIR_RULE.userId = #{userId}"})
    int deleteAirRule(@Param("id") Integer id, @Param("userId") String userId) ;

}
