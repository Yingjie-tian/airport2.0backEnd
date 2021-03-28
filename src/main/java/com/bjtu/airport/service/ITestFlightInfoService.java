package com.bjtu.airport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjtu.airport.entity.FlightInfo;
import com.bjtu.airport.entity.StatisticResult;
import com.bjtu.airport.entity.TestFlight;
import com.bjtu.airport.entity.vo.TestFlightResult;
import net.sf.json.JSONObject;

import java.util.Date;
import java.util.List;

public interface ITestFlightInfoService extends IService<FlightInfo> {
        List<FlightInfo> getinfo(String filename,String userId);

        List<TestFlight> gettime(String end, String start);

        }
