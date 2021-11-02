package com.bjtu.airport.controller;

import com.bjtu.airport.entity.EvaluationResult;
import com.bjtu.airport.entity.ScoreResult;
import net.sf.json.JSONObject;
import netscape.javascript.JSObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2020/11/17 12:57
 */
@RestController
@RequestMapping("/ScoreResult")
public class ScoreResultController {
    @Autowired
    private com.bjtu.airport.mapper.EvaluationResultMapper evaluationResultMapper;
    //2020.11.17查询SCORE_RESULT数据
    @RequestMapping("/selectScore")
    public Map<String, Integer> getone(@RequestParam(value = "userid", defaultValue = "") String userid,
                                       @RequestParam(value = "updatetime", defaultValue = "") String updatetime,
                                       @RequestParam(value = "filename", defaultValue = "") String filename){
        if (StringUtils.isEmpty(updatetime)){
            updatetime =  evaluationResultMapper.selectNewestTime();
        }

        List<EvaluationResult> temp = evaluationResultMapper.selectScore(userid,filename,updatetime);
//        System.out.println("aaa");
//        System.out.println(temp);
        Map<String,Integer> res= new HashMap<>();
        for(int i=0;i<temp.size();i++){
            String model = temp.get(i).getModel();
            Integer score = temp.get(i).getScore();
            res.put(model,score);
        }
        return res;
    }
}
