package com.bjtu.airport.service;

import com.bjtu.airport.entity.ModeInfo;
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
public interface IModeInfoService extends IService<ModeInfo> {
    List<ModeInfo> getinfo();

}
