package com.bjtu.airport.service;

import com.bjtu.airport.entity.IoareaConflict;
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
public interface IIoareaConflictService extends IService<IoareaConflict> {
    List<IoareaConflict> getinfo();


    List<IoareaConflict> getRuleInfo(String airportname,String useId);

    List<IoareaConflict> getRuleInfoByuserId(String airportname,  String userId);


    List<IoareaConflict> getParkingGate(String airportname,  String userId);





    int updateinfo1(Integer id, String areaid,  String userId);


    int updateinfo2(Integer id, String parkinggate,  String userId);


    int deleteParkingGate(String airportname, String parkinggate,  String userId);


    int deleteArea(Integer id);


//    ****  Following is the airport2.0 code ****

     /**
      * @Author: 田英杰
      * @Description: 
      * @Date 2020/11/5 20:18
      * @Param  * @param null
      * @return 
      * @throws:
      **/
    List<IoareaConflict> getInfoByAN(String airportname, String userId);


    List<IoareaConflict> getAll();

    List<IoareaConflict> getAreaid(String airportname);

    List<IoareaConflict> getParkinggate(String airportname, String areaid);

    int insertinfo(String areaid, String parkinggate,
                   String airportname,  String userId,
                   String updateTime);

    int updateLimitInfo(Integer id,
                        String areaid, String parkinggate,
                        String userId, String updateTime);

    int batchDelete(Integer id);
}

