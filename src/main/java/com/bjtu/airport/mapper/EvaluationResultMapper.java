package com.bjtu.airport.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjtu.airport.entity.EvaluationResult;
import com.bjtu.airport.entity.StatisticResult;
import com.bjtu.airport.entity.vo.ConstrainsStatistic;
import com.bjtu.airport.entity.vo.ConstraintSum;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2020/11/17 10:40
 */
@Mapper
@Repository
public interface EvaluationResultMapper extends BaseMapper<EvaluationResult> {
    //2020.11.17 查询EVALUATION_RESULT的数据
    List<EvaluationResult> getinfo() ;

    List<EvaluationResult> getInfoWithParams(@Param("date")String date);

    List<String> getFlightIdList(@Param("page")int page,@Param("size")int size);

    @Select(value ="select distinct testtime from xx" )
    List<String> getExistedDate();


    @Select(value = "select distinct runtime from xx ")
    List<Integer> getExistedRunTime();

    int deleteByPrimaryKey(Integer id);

    // int insert(EvaluationResult record);

    int insertSelective(EvaluationResult record);

    EvaluationResult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EvaluationResult record);

    int updateByPrimaryKey(EvaluationResult record);

    @Select(value = "select distinct updateTime from EVALUATION_RESULT order by updateTime")
    List<String> getTimePoint();


    List<ConstrainsStatistic> getConstainsCount(@Param("startDate") String startDate,
                                                @Param("endDate")String endDate,
                                                @Param("fileName")String fileName,
                                                @Param("userId")String userId);

    List<ConstraintSum> getFlightConstraintSum(@Param("startDate") String startDate,
                                         @Param("endDate")String endDate,
                                         @Param("fileName")String fileName,
                                         @Param("userId")String userId);
    //2021.3.21
    @Select(value = "select distinct updateTime from EVALUATION_RESULT order by updateTime desc limit 1")
    String selectNewestTime();

    @Select(value = "select DISTINCT(score),model from EVALUATION_RESULT where userId=#{userid} and filename=#{filename} and updateTime=#{updatetime} ORDER BY model")
    List<EvaluationResult> selectScore(@Param("userid") String userid, @Param("filename") String filename, @Param("updatetime") String updatetime);
}
