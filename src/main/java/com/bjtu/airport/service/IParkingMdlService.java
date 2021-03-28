package com.bjtu.airport.service;

import com.bjtu.airport.entity.ParkingMdl;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 田英杰
 * @since 2020-12-03
 */
public interface IParkingMdlService extends IService<ParkingMdl>{

    //    ****  Following is the airport2.0 code ****

    List<ParkingMdl> getInfoByAN(String airportname, String userId);

    int insterNewInfo(String mdl, String  parkinggate, String airportname,
                     String userId, Integer restriction, String updateTime);

    int batchDelete(Integer id);

    int updateInfo(Integer id, String mdl, String  parkinggate, String userId, String updateTime, Integer restriction);
}
