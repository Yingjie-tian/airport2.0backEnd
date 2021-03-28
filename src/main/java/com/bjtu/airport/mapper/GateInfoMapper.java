package com.bjtu.airport.mapper;

import com.bjtu.airport.entity.GateInfo;
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

@Repository
public interface GateInfoMapper extends BaseMapper<GateInfo> {
    //查询GATE_INFO表中所有数据
    @Select("select * from GATE_INFO")
    List<GateInfo> getinfo();
    //为服务器提供数据
    @Select("select * from GATE_INFO where airportname= #{airportname}")
    List<GateInfo> getinfo1(@Param("airportname") String airportname);

    //向GATE_INFO表中插入数据
    @Insert("insert into GATE_INFO(airportname, parkinggate, state, endtime, bridge, nation, updateTime)" +
            "values(#{airportname},#{parkinggate},#{state},#{endtime},#{bridge},#{nation},#{updateTime})")
    int insertinfo(@Param("airportname") String airportname,
                   @Param("parkinggate") String parkinggate,
                   @Param("state") String state,
                   @Param("endtime") String endtime,
                   @Param("bridge") String bridge,
                   @Param("nation") String nation,
                   @Param("updateTime") String updateTime);

    @Update({"update GATE_INFO set state = #{state} where parkinggate= #{parkinggate}"})
    int updateinfo(@Param("parkinggate") String parkinggate,
                   @Param("state") String state);

    @Update({"update GATE_INFO set state = #{state},endtime=#{endtime},bridge=#{bridge},nation=#{nation} where parkinggate= #{parkinggate}"})
    int update2(@Param("state") String state,
                   @Param("endtime") String endtime,
                   @Param("bridge") String bridge,
                   @Param("nation") String nation,
                   @Param("parkinggate") String parkinggate);

    //根据所选测试数据筛选机位
    @Select("select *  from GATE_INFO where airportname= #{airportname}")
    List<GateInfo> getinfo2(@Param("airportname") String airportname);


    @Delete("delete from GATE_INFO where parkinggate = #{parkinggate}")
    int deleteone(@Param("parkinggate") String parkinggate);



}
