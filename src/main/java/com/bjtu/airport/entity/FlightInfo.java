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
* @author WuZhen
* @since 2020-03-01
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("FLIGHT_INFO")
    public class FlightInfo implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String airportname;
    private String planenum;
    private String mdl;
    private String aflightnum;
    private String dflightnum;
    private String airlinename;
    private Integer nation;
    private Integer pass;
    private Integer paras;
    private Integer business;
    private Integer isvip;
    private String atime;
    private String dtime;
    private String origin;
    private String destination;
        @TableField("updateTime")
    private String updateTime;


}
