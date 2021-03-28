package com.bjtu.airport.entity.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2021/3/20 11:32
 */
public class MenuVo implements Serializable {
    private static final long serialVersionUID = -5204511659226127015L;
    private String icon;
    private String index;
    private String title;
    private Integer parentId;
    private List<MenuVo> subs;

    public MenuVo() {
    }

    public MenuVo(String icon, String index, String title, List<MenuVo> subs) {
        this.icon = icon;
        this.index = index;
        this.title = title;
        this.subs = subs;
    }

    public MenuVo(String icon, String index, String title,Integer parentId) {
        this.icon = icon;
        this.index = index;
        this.title = title;
        this.parentId = parentId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public MenuVo setParentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public MenuVo setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public String getIndex() {
        return index;
    }

    public MenuVo setIndex(String index) {
        this.index = index;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MenuVo setTitle(String title) {
        this.title = title;
        return this;
    }

    public List<MenuVo> getSubs() {
        return subs;
    }

    public MenuVo setSubs(List<MenuVo> subs) {
        this.subs = subs;
        return this;
    }
}
