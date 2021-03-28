package com.bjtu.airport.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjtu.airport.entity.ScoreResult;
import com.bjtu.airport.mapper.ScoreResultMapper;
import com.bjtu.airport.service.IScoreResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2020/11/17 12:58
 */
@Service
public class ScoreResultServiceImpl extends ServiceImpl<ScoreResultMapper, ScoreResult> implements IScoreResultService {
    @Autowired
    ScoreResultMapper scoreResultMapper;
    //2020.11.17查询SCORE_RESULT数据
    @Override
    public List<ScoreResult> getinfo() {
        List<ScoreResult> info = scoreResultMapper.getinfo();
        return info;
    }
}
