package com.bjtu.airport.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2020/11/17 12:49
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("SCORE_RESULT")
public class ScoreResult implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type= IdType.AUTO)
    private Integer id;

    private String filename;

    private String model;

    private Integer constraint1;

    private Integer constraint2;

    private Integer constraint3;

    private Integer constraint4;

    private Integer constraint5;

    private Integer constraint6;

    private Integer constraint7;

    private Integer constraints;

    private String score;

    private Integer runnums;

    private String userId;


    @TableField("updateTime")
    private String updateTime;
    @TableField("testtime")
    private String testtime;

}
