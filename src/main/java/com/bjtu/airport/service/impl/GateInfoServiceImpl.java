package com.bjtu.airport.service.impl;

import com.bjtu.airport.entity.GateInfo;
import com.bjtu.airport.mapper.GateInfoMapper;
import com.bjtu.airport.service.IGateInfoService;
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
public class GateInfoServiceImpl extends ServiceImpl<GateInfoMapper, GateInfo> implements IGateInfoService {
    @Autowired
    private GateInfoMapper GateInfoMapper;
    @Override
    public List<GateInfo> getinfo() {
        List<GateInfo> info = GateInfoMapper.getinfo();
        return info;
    }
    @Override
    public List<GateInfo> getinfo1(String airportname) {
        List<GateInfo> info = GateInfoMapper.getinfo1(airportname);
        return info;
    }

    @Override
    public int insertinfo(String airportname, String parkinggate, String state, String endtime,
                          String bridge, String nation, String updateTime)

    {

        int info = GateInfoMapper.insertinfo(airportname,parkinggate,state,endtime,bridge,nation,updateTime);
        return info;

    }
    @Override
    public int updateinfo(String parkinggate,String state){
        int info=GateInfoMapper.updateinfo(parkinggate ,state);
        return info;
    }
    @Override
    public int update2(String state,String endtime,String bridge,String nation,String parkinggate){
        int info=GateInfoMapper.update2(state,endtime,bridge,nation,parkinggate);
        return info;
    }

    @Override
    public List<GateInfo> getinfo2(String airportname){
        List<GateInfo> info = GateInfoMapper.getinfo2(airportname);
        return info;

    }

    @Override
    public int deleteone(String parkinggate){
        int info=GateInfoMapper.deleteone(parkinggate);
        return info;
    }
}
