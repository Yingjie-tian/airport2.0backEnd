package com.bjtu.airport.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bjtu.airport.entity.EvaluationResult;
import com.bjtu.airport.entity.StatisticResult;
import com.bjtu.airport.vo.WrapEvaluationResultVo;
import org.apache.ibatis.annotations.Param;

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

    R<?> getPageableInfo(Integer flightId, String date,int page,int size);

    R<?> getinfoByPageAndScore(Integer flightId, String date);

    /**
     * 分页查询
     * @param flightId
     * @param date
     * @param page
     * @param size
     * @return
     */
    R<?> getInfoByPageAndScore(Integer flightId, String date,int page,int size);
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
