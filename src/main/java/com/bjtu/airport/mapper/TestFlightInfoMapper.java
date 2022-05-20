package com.bjtu.airport.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjtu.airport.entity.FlightInfo;
import com.bjtu.airport.entity.TestFlight;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface TestFlightInfoMapper extends BaseMapper<FlightInfo> {


    /**
     * todo 增加查询索引  KEY `idx_fid_model_uid` (`flightId`,`filename`,`userId`) USING BTREE
     * CREATE INDEX idx_fid_model_uid ON TEST_FLIGHT(`flightId`,`filename`,`userId`) USING BTREE
     * @param filename
     * @param userId
     * @return
     */
    @Select("select  t2.* " +
            "from TEST_FLIGHT t1" +
            "inner join FLIGHT_INFO t2" +
            "on t1.flightId=t2.id" +
            "where t1.filename= #{filename} " +
            "and  t1.userId=#{userId} ")
    List<FlightInfo> getinfo(@Param("filename") String filename,@Param("userId")String userId);


    @Select("select test_flight.* from test_flight where test_flight.atime < #{end} and test_flight.atime > #{start}")
    List<TestFlight> gettime(@Param("end") String end, @Param("start") String start);


}
