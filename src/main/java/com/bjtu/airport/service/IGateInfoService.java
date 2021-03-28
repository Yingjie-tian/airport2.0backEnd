package com.bjtu.airport.service;

import com.bjtu.airport.entity.GateInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */
public interface IGateInfoService extends IService<GateInfo> {
    List<GateInfo> getinfo();

    List<GateInfo> getinfo1(String airportname);

    int insertinfo( String airportname, String parkinggate, String state, String endtime,
                    String bridge, String nation, String updateTime);

    int updateinfo(String parkinggate,String state);

    int update2(String state,String endtime,String bridge,String nation,String parkinggate);

    List<GateInfo> getinfo2(String airportname);

    int deleteone(String parkinggate);
}
