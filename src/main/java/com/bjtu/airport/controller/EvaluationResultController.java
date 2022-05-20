package com.bjtu.airport.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.api.R;
import com.bjtu.airport.service.IEvaluationResultService;
import com.bjtu.airport.vo.WrapEvaluationPoiResultVo;
import com.bjtu.airport.vo.WrapEvaluationResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.*;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2020/11/17 10:31
 */
@RestController
@RequestMapping("/EvaluationResult")
public class EvaluationResultController {
    @Autowired
    private IEvaluationResultService evaluationResultService;

    //2020.11.17 查询EVALUATION_RESULT的数据

    //todo 分页
    @GetMapping("/test")
    public R<?> test(@RequestParam(value = "flightId", required = false) Integer flightId,
                     @RequestParam(value = "date", defaultValue = "") String date,
                     @RequestParam(value = "page",defaultValue = "1")Integer page,
                     @RequestParam(value = "size",defaultValue = "10")Integer size) {
        return evaluationResultService.getPageableInfo(flightId, date,page,size);
    }


    @GetMapping("/getTimePoint")
    public R<?> getTimePoint() {
        //获取有时间的updatetime
        return evaluationResultService.getTimePoint();
    }

    @GetMapping("/testDownload")
    public void templateExcelDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //文件目录+名字
        String filePath = "/test/";
        String fileName = "excludeOrIncludeWrite" + System.currentTimeMillis() + ".xlsx";
        //获取请求所需参数  这里有空指针异常
        String flightId = request.getParameter("flightId");
        String date = request.getParameter("date");
        String runtime = request.getParameter("runtime");
        Integer temp = null;
        if (runtime != null) {
            temp = Integer.parseInt(runtime);
        }
        // 数据写出到excel表中
        List<WrapEvaluationResultVo> voList = evaluationResultService.getinfoByPage(flightId, date);
        List<WrapEvaluationPoiResultVo> transfer = WrapEvaluationPoiResultVo.transfer(voList);
        EasyExcel.write(filePath + fileName, WrapEvaluationPoiResultVo.class).sheet("sheet1").doWrite(transfer);
        //读取文件流
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath + fileName));
        //转码，免得文件名中文乱码
        fileName = URLEncoder.encode(fileName, "UTF-8");
        //设置文件下载头
        byte[] buf = new byte[1024];
        int len = 0;
        response.reset(); // 非常重要
        // 纯下载方式
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        OutputStream out = response.getOutputStream();
        while ((len = bis.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        bis.close();
        out.close();
    }

    @GetMapping("/getStatisticalTables")
    public R<?> getStatisticalTables(@RequestParam(value = "startDate",defaultValue = "") String startDate,
                                     @RequestParam(value = "endDate",defaultValue = "")  String endDate,
                                     @RequestParam(value = "filename",required = false,defaultValue = "")String fileName,
                                     @RequestParam(value = "userid",required = false,defaultValue = "")String userId) {
        return evaluationResultService.getStatisticalTables(startDate, endDate,fileName,userId);
    }


}
