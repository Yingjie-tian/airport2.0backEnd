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
    @TableName("FLIGHT_PRIORITY")
    public class FlightPriority implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String airportname;

    private String flighttype;

    private String flightkey;

    private Integer prioritynum;

    private Integer sortrule;

    private String userId;

        @TableField("updateTime")
    private String updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAirportname() {
        return airportname;
    }

    public void setAirportname(String airportname) {
        this.airportname = airportname;
    }

    public String getFlightkey() { return flightkey; }

    public void setFlightkey(String flightkey) {
        this.flightkey = flightkey;
    }

    public String getFlighttype() {
        return flighttype;
    }

    public void setFlighttype(String flighttype) {
        this.flighttype = flighttype;
    }

    public Integer getPrioritynum() {
        return prioritynum;
    }

    public void setPrioritynum(Integer prioritynum) {
        this.prioritynum = prioritynum;
    }

    public Integer getSortrule() {
        return sortrule;
    }

    public void setSortrule(Integer sortrule) {
        this.sortrule = sortrule;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }


}
