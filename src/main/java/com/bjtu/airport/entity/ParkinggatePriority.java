package com.bjtu.airport.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ChenYakun
 * @since 2020-11-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("PARKINGGATE_PRIORITY")
public class ParkinggatePriority implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String airportname;

    private Integer groupid;

    private String flighttype;

    private String flightkey;

    private Integer template;

    private Integer businesskey;

    private String businessvalue;

    @TableField("userId")
    private String userId;

    @TableField("updateTime")
    private String updateTime;


}
