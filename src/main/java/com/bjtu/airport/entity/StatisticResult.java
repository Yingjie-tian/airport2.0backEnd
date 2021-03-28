package com.bjtu.airport.entity;

    import java.math.BigDecimal;
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
* @author WuZhen
* @since 2020-03-01
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("STATISTIC_RESULT")
    public class StatisticResult implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "id", type = IdType.AUTO)

    private Integer id;
    private String inputname;
    private String model;
    private Integer flightnum;

    private Integer beallo;

    private Integer gatenum;

    private Integer neargatenum;

    private Integer allostate;

    private Integer timespend;

    private Integer haveallo;

    private String bridgerate;

    private Integer bridgenum;
    private String userid;

        @TableField("updateTime")
    private String updateTime;


}
