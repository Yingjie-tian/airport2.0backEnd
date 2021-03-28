package com.bjtu.airport.entity.vo;

import java.io.Serializable;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2021/3/21 10:09
 */
public class ConstrainsStatistic implements Serializable {
    private static final long serialVersionUID = 3782815502536802261L;

    private Integer constrainsCount;
    private String model;
    private String timePoint;

    public ConstrainsStatistic() {
    }

    public ConstrainsStatistic(Integer constrainsCount, String model, String timePoint) {
        this.constrainsCount = constrainsCount;
        this.model = model;
        this.timePoint = timePoint;
    }

    public Integer getConstrainsCount() {
        return constrainsCount;
    }

    public ConstrainsStatistic setConstrainsCount(Integer constrainsCount) {
        this.constrainsCount = constrainsCount;
        return this;
    }

    public String getModel() {
        return model;
    }

    public ConstrainsStatistic setModel(String model) {
        this.model = model;
        return this;
    }

    public String getTimePoint() {
        return timePoint;
    }

    public ConstrainsStatistic setTimePoint(String timePoint) {
        this.timePoint = timePoint;
        return this;
    }
}
