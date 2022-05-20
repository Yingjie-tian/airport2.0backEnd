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
public class EchartsNode<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String type;
    private String stack;
    private List<T> data;

    public EchartsNode() {
    }

    public EchartsNode(String name,String type,String stack) {
        this.name = name;
        this.type = type;
        this.stack = stack;
        this.data = new ArrayList<>();
    }

    public EchartsNode(String name,String type,String stack, List<T> data) {
        this.name = name;
        this.type = type;
        this.stack = stack;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public EchartsNode<T> setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public EchartsNode<T> setType(String type) {
        this.type = type;
        return this;
    }

    public String getStack() {
        return stack;
    }

    public EchartsNode<T> setStack(String stack) {
        this.stack = stack;
        return this;
    }

    public List<T> getData() {
        return data;
    }

    public EchartsNode<T> setData(List<T> data) {
        this.data = data;
        return this;
    }

    public void addData(T o){
        this.data.add(o);
    }
}
