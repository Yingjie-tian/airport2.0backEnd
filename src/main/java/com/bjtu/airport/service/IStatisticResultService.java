package com.bjtu.airport.service;

import com.bjtu.airport.entity.StatisticResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */
public interface IStatisticResultService extends IService<StatisticResult> {

    List<StatisticResult> getinfo();

    int insertinfo(Integer beallo, Integer gatenum,  Integer neargatenum,
                   Integer allostate, Integer timespend, Integer bridgerate, Integer bridgenum);

    void saveStatisticResult(StatisticResult s);

    /**
     * 根据文件名filename和用户id删除仿真数据
     * tyj   2020-4-20
     * @param filename
     * @param userId
     * @return
     */
    int deleteByUserid(String filename, String userId);


}
