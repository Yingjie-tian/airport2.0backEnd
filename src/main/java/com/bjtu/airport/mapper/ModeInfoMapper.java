package com.bjtu.airport.mapper;

import com.bjtu.airport.entity.ModeInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */
@Mapper
@Repository
public interface ModeInfoMapper extends BaseMapper<ModeInfo> {
    @Select("select * from MODE_INFO")
    List<ModeInfo> getinfo();


}
