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
    @TableName("RESULT")
    public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String filename;

    private String model;

    @TableField("flightId")
    private Integer flightId;
//    private String flightid;

    private Integer allostate;

    private String parkinggate;

    private Integer runnums;

    private String testtime;

    @TableField("userId")
    private String userId;

    @TableField("updateTime")
    private String updateTime;

//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getFilename() {
//        return filename;
//    }
//
//    public void setFilename(String filename) {
//        this.filename = filename;
//    }
//
//    public String getModel() {
//        return model;
//    }
//
//    public void setModel(String model) {
//        this.model = model;
//    }
//
//    public Integer getFlightid() {
//        return flightid;
//    }
//
//    public void setFlightid(Integer flightid) {
//        this.flightid = flightid;
//    }
//
//    public Integer getAllostate() {
//        return allostate;
//    }
//
//    public void setAllostate(Integer allostate) {
//        this.allostate = allostate;
//    }
//
//    public String getParkinggate() {
//        return parkinggate;
//    }
//
//    public void setParkinggate(String parkinggate) {
//        this.parkinggate = parkinggate;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public String getUpdateTime() {
//        return updateTime;
//    }
//
//    public void setUpdateTime(String updateTime) {
//        this.updateTime = updateTime;
//    }

}
