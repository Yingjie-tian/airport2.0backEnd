package com.bjtu.airport.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bjtu.airport.entity.EvaluationResult;
import com.bjtu.airport.entity.StatisticResult;
import com.bjtu.airport.vo.WrapEvaluationResultVo;

import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2020/11/17 10:42
 */
public interface IEvaluationResultService extends IService<EvaluationResult> {
    //2020.11.17 查询EVALUATION_RESULT的数据
    List<EvaluationResult> getinfo();

    List<WrapEvaluationResultVo> getinfoByPage(String flightId, String date);

    R<?> getinfoByPageAndScore(String flightId, String date);

    R<?> getTimePoint();

    /**
     * 获取统计图表
     * @param startDate
     * @param endDate
     * @return
     */
    R<?> getStatisticalTables(String startDate, String endDate,String fileName,String userId);

    int insertEva(List<EvaluationResult> evaluationResultArray);
}
