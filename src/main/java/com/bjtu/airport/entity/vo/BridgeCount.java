package com.bjtu.airport.entity.vo;

import java.io.Serializable;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2021/3/21 15:22
 */
public class BridgeCount implements Serializable {

    private static final long serialVersionUID = 7819473816562078150L;

    private String timePoint;
    private Integer allLines;
    private Integer bridgeLines;
    private String model;

    public BridgeCount() {
    }

    public BridgeCount(String timePoint, Integer allLines, Integer bridgeLines, String model) {
        this.timePoint = timePoint;
        this.allLines = allLines;
        this.bridgeLines = bridgeLines;
        this.model = model;
    }

    public String getTimePoint() {
        return timePoint;
    }

    public BridgeCount setTimePoint(String timePoint) {
        this.timePoint = timePoint;
        return this;
    }

    public Integer getAllLines() {
        return allLines;
    }

    public BridgeCount setAllLines(Integer allLines) {
        this.allLines = allLines;
        return this;
    }

    public Integer getBridgeLines() {
        return bridgeLines;
    }

    public BridgeCount setBridgeLines(Integer bridgeLines) {
        this.bridgeLines = bridgeLines;
        return this;
    }

    public String getModel() {
        return model;
    }

    public BridgeCount setModel(String model) {
        this.model = model;
        return this;
    }
}
