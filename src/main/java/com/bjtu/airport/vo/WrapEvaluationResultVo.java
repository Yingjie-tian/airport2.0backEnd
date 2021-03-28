package com.bjtu.airport.vo;

import java.io.Serializable;
import java.util.Optional;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2020/12/20 17:20
 */
public class WrapEvaluationResultVo implements Serializable {
    public static class Counter {
        Integer key1;
        Integer key2;
        Integer key3;

        public Integer getKey1() {
            return key1;
        }

        public Counter setKey1(Integer key1) {
            this.key1 = key1;
            return this;
        }

        public Integer getKey2() {
            return key2;
        }

        public Counter setKey2(Integer key2) {
            this.key2 = key2;
            return this;
        }

        public Integer getKey3() {
            return key3;
        }

        public Counter setKey3(Integer key3) {
            this.key3 = key3;
            return this;
        }

        @Override
        public String toString() {
            return Optional.ofNullable(key1).orElse(0) +
                    "|" +Optional.ofNullable(key2).orElse(0) +
                    "|" + Optional.ofNullable(key3).orElse(0) ;
        }
    }

    private String flightId;
    //对应数据库的model 数据0 对应前端人工
    private String model1;
    //对应数据库的model 数据1 对应前端1
    private String model2;
    //对应数据库的model 数据2 对应前端2
    private String model3;
    private Counter constrain1;
    private Counter constrain2;
    private Counter constrain3;
    private Counter constrain4;
    private Counter constrain5;
    private Counter constrain6;
    private Counter constrain7;
    private Counter constrains;
    private String date;

    public String getFlightId() {
        return flightId;
    }

    public WrapEvaluationResultVo setFlightId(String flightId) {
        this.flightId = flightId;
        return this;
    }

    public String getModel1() {
        return model1;
    }

    public WrapEvaluationResultVo setModel1(String model1) {
        this.model1 = model1;
        return this;
    }

    public String getModel2() { return model2; }

    public WrapEvaluationResultVo setModel2(String model2) {
        this.model2 = model2;
        return this;
    }

    public String getModel3() {
        return model3;
    }

    public WrapEvaluationResultVo setModel3(String model3) {
        this.model3 = model3;
        return this;
    }

    public Counter getConstrain1() {
        return constrain1;
    }

    public WrapEvaluationResultVo setConstrain1(Counter constrain1) {
        this.constrain1 = constrain1;
        return this;
    }

    public Counter getConstrain2() {
        return constrain2;
    }

    public WrapEvaluationResultVo setConstrain2(Counter constrain2) {
        this.constrain2 = constrain2;
        return this;
    }

    public Counter getConstrain3() {
        return constrain3;
    }

    public WrapEvaluationResultVo setConstrain3(Counter constrain3) {
        this.constrain3 = constrain3;
        return this;
    }

    public Counter getConstrain4() {
        return constrain4;
    }

    public WrapEvaluationResultVo setConstrain4(Counter constrain4) {
        this.constrain4 = constrain4;
        return this;
    }

    public Counter getConstrain5() {
        return constrain5;
    }

    public WrapEvaluationResultVo setConstrain5(Counter constrain5) {
        this.constrain5 = constrain5;
        return this;
    }

    public Counter getConstrain6() {
        return constrain6;
    }

    public WrapEvaluationResultVo setConstrain6(Counter constrain6) {
        this.constrain6 = constrain6;
        return this;
    }

//    public Counter getConstrain7() {
//        return constrain7;
//    }
//
//    public WrapEvaluationResultVo setConstrain7(Counter constrain7) {
//        this.constrain7 = constrain7;
//        return this;
//    }

    public Counter getConstrains() {
        return constrains;
    }

    public WrapEvaluationResultVo setConstrains(Counter constrains) {
        this.constrains = constrains;
        return this;
    }

    public String getDate() {
        return date;
    }

    public WrapEvaluationResultVo setDate(String date) {
        this.date = date;
        return this;
    }
}
