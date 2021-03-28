package com.bjtu.airport.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjtu.airport.entity.FlightInfo;
import com.bjtu.airport.entity.TestFlight;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TestFlightInfoMapper extends BaseMapper<FlightInfo> {

    //    @Select( "select FLIGHT_INFO.* from TEST_FLIGHT,FLIGHT_INFO where TEST_FLIGHT.flightId=FLIGHT_INFO.id and TEST_FLIGHT.filename='仿真数据一'")
//    @Select( "select fi.* from TEST_FLIGHT tf,FLIGHT_INFO fi where tf.flightId=fi.id and " +
//            "tf.filename= #{filename} and  tf.userId=#{userId}")

    /**
     * 4-21 吴卓霖改    4.30王春霞解决了数据重复 加了DISTINCT
     *
     * @param filename
     * @param userId
     * @return
     */
//    @Select( "select DISTINCT FLIGHT_INFO.* from MANUAL_RESULT ,FLIGHT_INFO where MANUAL_RESULT.flightId=FLIGHT_INFO" +
//            ".id and MANUAL_RESULT.filename= #{filename} and  MANUAL_RESULT.userId=#{userId}  ")
//    List<FlightInfo> getinfo(@Param("filename") String filename,@Param("userId")String userId);

    @Select( "select DISTINCT FLIGHT_INFO.* from TEST_FLIGHT ,FLIGHT_INFO where TEST_FLIGHT.flightId=FLIGHT_INFO" +
            ".id and TEST_FLIGHT.filename= #{filename} and  TEST_FLIGHT.userId=#{userId} ")
    List<FlightInfo> getinfo(@Param("filename") String filename,@Param("userId")String userId);

//    @Insert("insert into TEST_FLIGHT(parkinggate) values(#{parkinggate})")
//    int insertinfo(@Param("parkinggate") String parkinggate);
//
//    @Update({"update TEST_FLIGHT set parkinggate= #{parkinggate} where flightId= #{flightId}"})
//    int updateinfo(@Param("parkinggate") String parkinggate,
//                   @Param("flightId") Integer flightId);

    @Select("select test_flight.* from test_flight where test_flight.atime < #{end} and test_flight.atime > #{start}")
    List<TestFlight> gettime(@Param("end") String end, @Param("start") String start);


}
