package com.bjtu.airport.service;

import com.bjtu.airport.entity.FlightInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */
public interface IFlightInfoService extends IService<FlightInfo> {
    List<FlightInfo> getinfo();

    int insertinfo(String airportname,String parkinggate, String boardinggate,  String planenum,
                   String aflightnum, String aflightid, String dflightnum, String dflightid,
                   Integer paras ,  String atime, String dtime,  Integer nation,
                   String mdl,  Integer business, String origin,  String destination,
                   Integer isvip,  Integer arriveperson, Integer departperson,  String updateTime);

    List<JSONObject> getName();

    List<JSONObject> getFlightInfoByAirportname(String airportname);

    int insertResult(String filename,String model,Integer flightId, Integer allostate,
                     String parkinggate,Integer runnums,String testtime,String userId,String updateTime);
}
