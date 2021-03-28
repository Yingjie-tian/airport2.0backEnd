package com.bjtu.airport.vo;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2020/12/20 17:20
 */
public class WrapEvaluationPoiResultVo implements Serializable {

    @ExcelProperty("航班号")
    private Integer flightId;
    //对应数据库的model 数据0 对应前端人工
    @ExcelProperty("模型一")
    private String model1;
    //对应数据库的model 数据1 对应前端1
    @ExcelProperty("模型二")
    private String model2;
    @ExcelProperty("模型三")
    //对应数据库的model 数据2 对应前端2
    private String model3;
    @ExcelProperty("约束1")
    private String constrain1;
    @ExcelProperty("约束2")
    private String constrain2;
    @ExcelProperty("约束3")
    private String constrain3;
    @ExcelProperty("约束4")
    private String constrain4;
    @ExcelProperty("约束5")
    private String constrain5;
    @ExcelProperty("约束6")
    private String constrain6;
//    @ExcelProperty("约束7")
//    private String constrain7;
    @ExcelProperty("合计")
    private String constrains;
    @ExcelProperty("日期")
    private String date;

    public WrapEvaluationPoiResultVo() {
    }

//    public WrapEvaluationPoiResultVo(Integer flightId, String model1, String model2, String model3, String constrain1, String constrain2, String constrain3, String constrain4, String constrain5, String constrain6, String constrain7, String constrains, String date) {
    public WrapEvaluationPoiResultVo(Integer flightId, String model1, String model2, String model3, String constrain1, String constrain2, String constrain3, String constrain4, String constrain5, String constrain6, String constrains, String date) {
        this.flightId = flightId;
        this.model1 = model1;
        this.model2 = model2;
        this.model3 = model3;
        this.constrain1 = constrain1;
        this.constrain2 = constrain2;
        this.constrain3 = constrain3;
        this.constrain4 = constrain4;
        this.constrain5 = constrain5;
        this.constrain6 = constrain6;
//        this.constrain7 = constrain7;
        this.constrains = constrains;
        this.date = date;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public WrapEvaluationPoiResultVo setFlightId(Integer flightId) {
        this.flightId = flightId;
        return this;
    }

    public String getModel1() {
        return model1;
    }

    public WrapEvaluationPoiResultVo setModel1(String model1) {
        this.model1 = model1;
        return this;
    }

    public String getModel2() {
        return model2;
    }

    public WrapEvaluationPoiResultVo setModel2(String model2) {
        this.model2 = model2;
        return this;
    }

    public String getModel3() {
        return model3;
    }

    public WrapEvaluationPoiResultVo setModel3(String model3) {
        this.model3 = model3;
        return this;
    }

    public String getConstrain1() {
        return constrain1;
    }

    public WrapEvaluationPoiResultVo setConstrain1(String constrain1) {
        this.constrain1 = constrain1;
        return this;
    }

    public String getConstrain2() {
        return constrain2;
    }

    public WrapEvaluationPoiResultVo setConstrain2(String constrain2) {
        this.constrain2 = constrain2;
        return this;
    }

    public String getConstrain3() {
        return constrain3;
    }

    public WrapEvaluationPoiResultVo setConstrain3(String constrain3) {
        this.constrain3 = constrain3;
        return this;
    }

    public String getConstrain4() {
        return constrain4;
    }

    public WrapEvaluationPoiResultVo setConstrain4(String constrain4) {
        this.constrain4 = constrain4;
        return this;
    }

    public String getConstrain5() {
        return constrain5;
    }

    public WrapEvaluationPoiResultVo setConstrain5(String constrain5) {
        this.constrain5 = constrain5;
        return this;
    }

    public String getConstrain6() {
        return constrain6;
    }

    public WrapEvaluationPoiResultVo setConstrain6(String constrain6) {
        this.constrain6 = constrain6;
        return this;
    }

//    public String getConstrain7() {
//        return constrain7;
//    }
//
//    public WrapEvaluationPoiResultVo setConstrain7(String constrain7) {
//        this.constrain7 = constrain7;
//        return this;
//    }

    public String getConstrains() {
        return constrains;
    }

    public WrapEvaluationPoiResultVo setConstrains(String constrains) {
        this.constrains = constrains;
        return this;
    }

    public String getDate() {
        return date;
    }

    public WrapEvaluationPoiResultVo setDate(String date) {
        this.date = date;
        return this;
    }

    public static List<WrapEvaluationPoiResultVo> transfer(List<WrapEvaluationResultVo> inputList) {
        if (inputList == null || inputList.isEmpty()) {
            return null;
        } else {
          return  inputList.stream().map(e ->
                    new WrapEvaluationPoiResultVo(Integer.valueOf(e.getFlightId()),
                            e.getModel1(),
                            e.getModel2(),
                            e.getModel3(),
                            e.getConstrain1().toString(),
                            e.getConstrain2().toString(),
                            e.getConstrain3().toString(),
                            e.getConstrain4().toString(),
                            e.getConstrain5().toString(),
                            e.getConstrain6().toString(),
//                            e.getConstrain7().toString(),
                            e.getConstrains().toString(),
                            e.getDate())
                    ).collect(Collectors.toList());
        }
    }
}
