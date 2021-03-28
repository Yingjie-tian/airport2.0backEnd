//package com.bjtu.airport;
//
//import com.alibaba.excel.EasyExcel;
//import com.bjtu.airport.service.IEvaluationResultService;
//import com.bjtu.airport.vo.WrapEvaluationPoiResultVo;
//import com.bjtu.airport.vo.WrapEvaluationResultVo;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@SpringBootTest
//class AirportApplicationTests {
//
//    @Autowired
//    private IEvaluationResultService evaluationResultService;
//
//    @Test
//    void contextLoads() {
//        String fileName = "D:\\test\\" + "excludeOrIncludeWrite" + System.currentTimeMillis() + ".xlsx";
//
//        // 根据用户传入字段 假设我们要忽略 date
//        Set<String> excludeColumnFiledNames = new HashSet<String>();
//        excludeColumnFiledNames.add("date");
//        // 这里需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
//        //todo  1.注入获取那个图表接口的service
//        List<WrapEvaluationResultVo> voList = evaluationResultService.getinfoByPage(null,null,null);
//        // 2.转换数据对象
//        List<WrapEvaluationPoiResultVo> transfer = WrapEvaluationPoiResultVo.transfer(voList);
//        // 3.将数据放到doWrite里面测试
//        EasyExcel.write(fileName, WrapEvaluationPoiResultVo.class).sheet("模板").doWrite(transfer);
//
//    }
//
//}
