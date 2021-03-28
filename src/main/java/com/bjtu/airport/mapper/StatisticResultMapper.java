package com.bjtu.airport.mapper;

import com.bjtu.airport.entity.StatisticResult;
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
public interface StatisticResultMapper extends BaseMapper<StatisticResult> {

    //查询STATISTIC_RESULT的数据

    @Select("select beallo,gatenum,neargatenum,allostate,timespend,bridgerate,bridgenum from STATISTIC_RESULT order by id desc")
    List<StatisticResult> getinfo();

    //向STATISTIC_RESULT中插入数据
    @Insert("insert into STATISTIC_RESULT(beallo, gatenum, neargatenum, allostate, timespend, bridgerate,bridgenum)" +
            "values(#{beallo},#{gatenum},#{neargatenum},#{ allostate},#{timespend},#{bridgerate},#{bridgenum})")
    int insertinfo(@Param("beallo") Integer beallo, @Param("gatenum") Integer gatenum,
                   @Param("neargatenum") Integer neargatenum, @Param("allostate") Integer allostate,
                   @Param("timespend") Integer timespend, @Param("bridgerate")  Integer bridgerate,
                   @Param("bridgenum") Integer bridgenum);

    /**
     * 根据文件名filename和用户id删除仿真数据
     * tyj   2020-4-20
     * @param filename
     * @param userId
     * @return
     */
    @Delete("DELETE FROM STATISTIC_RESULT WHERE inputname = #{filename} and userId = #{userId}")
    int deleteByUserid(@Param("filename") String filename, @Param("userId") String userId);

}
