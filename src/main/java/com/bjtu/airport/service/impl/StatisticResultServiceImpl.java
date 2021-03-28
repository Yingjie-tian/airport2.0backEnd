package com.bjtu.airport.service.impl;

import com.bjtu.airport.entity.StatisticResult;
import com.bjtu.airport.mapper.StatisticResultMapper;
import com.bjtu.airport.service.IStatisticResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */
@Service
public class StatisticResultServiceImpl extends ServiceImpl<StatisticResultMapper, StatisticResult> implements IStatisticResultService {

    @Autowired
    private StatisticResultMapper StatisticResultMapper;

    @Override
    public List<StatisticResult> getinfo() {
        List<StatisticResult> info = StatisticResultMapper.getinfo();
        return info;
    }

    @Override
    public int insertinfo(Integer beallo, Integer gatenum,  Integer neargatenum,
                          Integer allostate, Integer timespend, Integer bridgerate, Integer bridgenum)

    {

        int info = StatisticResultMapper.insertinfo( beallo,gatenum,neargatenum,allostate,timespend,bridgerate,bridgenum);
        return info;

    }

    @Override
    public void saveStatisticResult(StatisticResult s){
        if(s!=null){
            StatisticResultMapper.insert(s);
        }
    }

    /**
     * 根据文件名filename和用户id删除仿真数据
     * tyj   2020-4-20
     * @param filename
     * @param userId
     * @return
     */
    @Override
    public int deleteByUserid(String filename, String userId){
        int info = StatisticResultMapper.deleteByUserid(filename,userId);
        return info;
    };
}


