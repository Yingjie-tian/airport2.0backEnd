package com.bjtu.airport.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjtu.airport.entity.EvaluationResult;
import com.bjtu.airport.entity.ScoreResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2020/11/17 12:57
 */
@Mapper
@Repository
public interface ScoreResultMapper extends BaseMapper<ScoreResult> {
    //2020.11.17查询SCORE_RESULT数据
    @Select("select score,model from SCORE_RESULT")
    List<ScoreResult> getinfo() ;
}
