package com.bjtu.airport.service.impl;

import com.bjtu.airport.entity.IoareaConflict;
import com.bjtu.airport.mapper.IoareaConflictMapper;
import com.bjtu.airport.service.IIoareaConflictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class IoareaConflictServiceImpl extends ServiceImpl<IoareaConflictMapper, IoareaConflict> implements IIoareaConflictService {
    @Autowired
    private IoareaConflictMapper ioareaConflictMapper;

    @Override
    public List<IoareaConflict> getinfo() {
        List<IoareaConflict> info = ioareaConflictMapper.getinfo();
        return info;

    }


    @Override
    public List<IoareaConflict> getRuleInfo(String airportname,String useId){

        List<IoareaConflict> allInfo = ioareaConflictMapper.getRuleInfo(airportname,useId);

        return allInfo;
    }

    @Override
    public List<IoareaConflict> getRuleInfoByuserId(String airportname, String userId){

        List<IoareaConflict> allInfo = ioareaConflictMapper.getRuleInfoByuserId(airportname,userId);

        return allInfo;
    }


    @Override
    public List<IoareaConflict> getParkingGate(String airportname, String userId){

        List<IoareaConflict> parkingGateInfo = ioareaConflictMapper.getParkingGate(airportname, userId);

        return parkingGateInfo;

    }






    @Override
    public int updateinfo1(Integer id, String areaid, String userId){

        int info = ioareaConflictMapper.updateinfo1(id,areaid, userId);

        return info;
    }


    @Override
    public int updateinfo2(Integer id, String parkinggate, String userId){

        int info = ioareaConflictMapper.updateinfo2(id,parkinggate, userId);

        return info;
    }

    @Override
    public int deleteParkingGate(String airportname, String parkinggate, String userId){

        int info = ioareaConflictMapper.deleteParkingGate(airportname, parkinggate, userId);

        return info;
    };


    @Override
    public int deleteArea(Integer id){

        int info = ioareaConflictMapper.deleteArea(id);

        return info;
    }

//    ****  Following is the airport2.0 code ****
    
     /**
      * @Author: 田英杰
      * @Description: 
      * @Date 2020/11/5 20:18
      * @Param  * @param null
      * @return 
      * @throws:
      **/
    @Override
    public List<IoareaConflict> getInfoByAN(String airportname, String userId) {
        List<IoareaConflict> info = ioareaConflictMapper.getInfoByAN(airportname, userId);
        return info;
    };


    @Override
    public List<IoareaConflict> getAll() {
        List<IoareaConflict> info = ioareaConflictMapper.getAll();
        return info;
    };

    @Override
    public List<IoareaConflict> getAreaid(String airportname) {
        List<IoareaConflict> info = ioareaConflictMapper.getAreaid(airportname);
        return info;
    };

    @Override
    public List<IoareaConflict> getParkinggate(String airportname, String areaid) {
        List<IoareaConflict> info = ioareaConflictMapper.getParkinggate(airportname, areaid);
        return info;
    };

    @Override
    public int insertinfo(String areaid, String parkinggate,
                          String airportname,  String userId,
                          String updateTime) {

        int info = ioareaConflictMapper.insertinfo(areaid, parkinggate, airportname,userId, updateTime);

        return info;
    };

    @Override
    public int updateLimitInfo(Integer id,
                               String areaid, String parkinggate,
                               String userId, String updateTime){

        int info = ioareaConflictMapper.updateLimitInfo(id, areaid, parkinggate, userId, updateTime);

        return info;
    }


    @Override
    public int batchDelete(Integer id){

        int info = ioareaConflictMapper.batchDelete(id);

        return info;

    }
}
