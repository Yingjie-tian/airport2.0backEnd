package com.bjtu.airport.entity.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2021/3/21 12:08
 */
public class LinePointNode<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String type;
    private String stack;
    private List<T> data;

    public LinePointNode() {
    }

    public LinePointNode(String name) {
        this.name = name;
        this.type = "line";
        this.stack = "总量";
        this.data = new ArrayList<>();
    }

    public LinePointNode(String name,List<T> data) {
        this.name = name;
        this.type = "line";
        this.stack = "总量";
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public LinePointNode<T> setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public LinePointNode<T> setType(String type) {
        this.type = type;
        return this;
    }

    public String getStack() {
        return stack;
    }

    public LinePointNode<T> setStack(String stack) {
        this.stack = stack;
        return this;
    }

    public List<T> getData() {
        return data;
    }

    public LinePointNode<T> setData(List<T> data) {
        this.data = data;
        return this;
    }
}
