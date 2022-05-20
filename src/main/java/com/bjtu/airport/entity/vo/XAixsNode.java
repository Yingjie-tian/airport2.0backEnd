package com.bjtu.airport.entity.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2022/5/14 10:43
 */
public class XAixsNode<T> {

    private String type;
    private List<T> data;

    public XAixsNode(String type) {
        this.type = type;
        this.data = new ArrayList<>();

    }

    public XAixsNode(String type,List<T> data) {
        this.type = type;
        this.data = data;

    }

    public String getType() {
        return type;
    }

    public List<T> getData() {
        return data;
    }

    public void addData(T e){
        this.data.add(e);
    }
}
