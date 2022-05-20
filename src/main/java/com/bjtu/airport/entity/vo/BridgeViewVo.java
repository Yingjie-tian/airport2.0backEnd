package com.bjtu.airport.entity.vo;

import com.bjtu.airport.entity.EvaluationResult;

import java.io.Serializable;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2022/5/8 9:44
 */
public class BridgeViewVo implements Serializable {
    private Integer flightId;
    private String model;
    private String c1;
    private String c2;
    private String c3;
    private String c4;
    private String c5;
    private String c6;
    private String cs;
    private String updateTime;

    public Integer getFlightId() {
        return flightId;
    }

    public BridgeViewVo setFlightId(Integer flightId) {
        this.flightId = flightId;
        return this;
    }

    public String getModel() {
        return model;
    }

    public BridgeViewVo setModel(String model) {
        this.model = model;
        return this;
    }

    public String getC1() {
        return c1;
    }

    public BridgeViewVo setC1(String c1) {
        this.c1 = c1;
        return this;
    }

    public String getC2() {
        return c2;
    }

    public BridgeViewVo setC2(String c2) {
        this.c2 = c2;
        return this;
    }

    public String getC3() {
        return c3;
    }

    public BridgeViewVo setC3(String c3) {
        this.c3 = c3;
        return this;
    }

    public String getC4() {
        return c4;
    }

    public BridgeViewVo setC4(String c4) {
        this.c4 = c4;
        return this;
    }

    public String getC5() {
        return c5;
    }

    public BridgeViewVo setC5(String c5) {
        this.c5 = c5;
        return this;
    }

    public String getC6() {
        return c6;
    }

    public BridgeViewVo setC6(String c6) {
        this.c6 = c6;
        return this;
    }

    public String getCs() {
        return cs;
    }

    public BridgeViewVo setCs(String cs) {
        this.cs = cs;
        return this;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public BridgeViewVo setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
