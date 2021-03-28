package com.bjtu.airport.service.impl;

import com.bjtu.airport.entity.ModeInfo;
import com.bjtu.airport.mapper.ModeInfoMapper;
import com.bjtu.airport.service.IModeInfoService;
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
public class ModeInfoServiceImpl extends ServiceImpl<ModeInfoMapper, ModeInfo> implements IModeInfoService {
    @Autowired
    private ModeInfoMapper ModeInfoMapper;

    @Override
    public List<ModeInfo> getinfo() {
        List<ModeInfo> info = ModeInfoMapper.getinfo();
        return info;
    }
}
