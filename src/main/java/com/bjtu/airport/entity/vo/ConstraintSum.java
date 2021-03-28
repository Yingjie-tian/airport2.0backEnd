package com.bjtu.airport.entity.vo;

import java.io.Serializable;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2021/3/21 13:24
 */
public class ConstraintSum implements Serializable {

    private static final long serialVersionUID = -1295160561867240599L;
    private Integer constraint1;
    private Integer constraint2;
    private Integer constraint3;
    private Integer constraint4;
    private Integer constraint5;
    private Integer constraint6;
    private String model;

    public ConstraintSum() {
    }

    public ConstraintSum(Integer constraint1, Integer constraint2, Integer constraint3, Integer constraint4,
                         Integer constraint5, Integer constraint6,String model) {
        this.constraint1 = constraint1;
        this.constraint2 = constraint2;
        this.constraint3 = constraint3;
        this.constraint4 = constraint4;
        this.constraint5 = constraint5;
        this.constraint6 = constraint6;
        this.model = model;
    }

    public Integer getConstraint1() {
        return constraint1;
    }

    public ConstraintSum setConstraint1(Integer constraint1) {
        this.constraint1 = constraint1;
        return this;
    }

    public Integer getConstraint2() {
        return constraint2;
    }

    public ConstraintSum setConstraint2(Integer constraint2) {
        this.constraint2 = constraint2;
        return this;
    }

    public Integer getConstraint3() {
        return constraint3;
    }

    public ConstraintSum setConstraint3(Integer constraint3) {
        this.constraint3 = constraint3;
        return this;
    }

    public Integer getConstraint4() {
        return constraint4;
    }

    public ConstraintSum setConstraint4(Integer constraint4) {
        this.constraint4 = constraint4;
        return this;
    }

    public Integer getConstraint5() {
        return constraint5;
    }

    public ConstraintSum setConstraint5(Integer constraint5) {
        this.constraint5 = constraint5;
        return this;
    }

    public Integer getConstraint6() {
        return constraint6;
    }

    public ConstraintSum setConstraint6(Integer constraint6) {
        this.constraint6 = constraint6;
        return this;
    }

    public String getModel() {
        return model;
    }

    public ConstraintSum setModel(String model) {
        this.model = model;
        return this;
    }
}
