package com.bjtu.airport.service.impl;

import com.bjtu.airport.entity.IoareaConflict;
import com.bjtu.airport.entity.ParkingMdl;
import com.bjtu.airport.mapper.ParkingMdlMapper;
import com.bjtu.airport.service.IParkingMdlService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 田英杰
 * @since 2020-12-03
 */
@Service
public class ParkingMdlServiceImpl extends ServiceImpl<ParkingMdlMapper, ParkingMdl> implements IParkingMdlService{

    @Autowired
    private ParkingMdlMapper parkingMdlMapper;


    @Override
    public List<ParkingMdl> getInfoByAN(String airportname, String userId){

        List<ParkingMdl> info = parkingMdlMapper.getInfoByAN(airportname, userId);

        return info;

    }

    @Override
    public int insterNewInfo(String mdl, String  parkinggate, String airportname,
                            String userId, Integer restriction, String updateTime){
        int row = parkingMdlMapper.insterNewInfo(mdl, parkinggate, airportname, userId, restriction, updateTime);

        return row;

    }


    @Override
    public int batchDelete(Integer id){

        int row = parkingMdlMapper.batchDelete(id);

        return row;
    };


    @Override
    public int updateInfo(Integer id, String mdl, String  parkinggate, String userId, String updateTime, Integer restriction){

        int row = parkingMdlMapper.updateInfo(id, mdl, parkinggate, userId, restriction, updateTime);

        return row;
    }
}
