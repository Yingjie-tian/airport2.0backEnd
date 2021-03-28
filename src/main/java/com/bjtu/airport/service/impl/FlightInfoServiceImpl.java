package com.bjtu.airport.service.impl;

import com.bjtu.airport.entity.FlightInfo;
import com.bjtu.airport.mapper.FlightInfoMapper;
import com.bjtu.airport.service.IFlightInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */
@Service
public class FlightInfoServiceImpl extends ServiceImpl<FlightInfoMapper, FlightInfo> implements IFlightInfoService {

    @Autowired
    private FlightInfoMapper FlightInfoMapper;

    @Override
    public List<FlightInfo> getinfo() {
        List<FlightInfo> info = FlightInfoMapper.getinfo();
        return info;
    }


    @Override
    public int insertinfo(String airportname,String parkinggate, String boardinggate,  String planenum,
                          String aflightnum, String aflightid, String dflightnum, String dflightid,
                          Integer paras ,  String atime, String dtime,  Integer nation,
                          String mdl,  Integer business, String origin,  String destination,
                          Integer isvip,  Integer arriveperson, Integer departperson,  String updateTime) {

        int info = FlightInfoMapper.insertinfo(airportname,parkinggate,  boardinggate,   planenum,
                aflightnum,  aflightid,  dflightnum,  dflightid, paras ,   atime,  dtime,   nation,
                mdl,   business,  origin,   destination, isvip, arriveperson,  departperson,   updateTime);
        return info;
    }

    @Override
    public List<JSONObject> getName() {
        List<JSONObject> info = FlightInfoMapper.getName();
        return info;
    }

    @Override
    public List<JSONObject> getFlightInfoByAirportname(String airportname){

        List<JSONObject> flightInfo = FlightInfoMapper.getFlightInfoByAirportname(airportname);

        return flightInfo;
    };

    @Override
    public int insertResult(String filename,String model,Integer flightId, Integer allostate,
                            String parkinggate,Integer runnums,String testtime,String userId,String updateTime) {

        int info = FlightInfoMapper.insertResult(filename,model,flightId,allostate,
              parkinggate,runnums,testtime,userId,updateTime);
        System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
        return info;
    }
}
