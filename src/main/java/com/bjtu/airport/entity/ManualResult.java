package com.bjtu.airport.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

    @Data
    @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("MANUAL_RESULT")
    public class ManualResult implements Serializable {

        private static final long serialVersionUID = 1L;

        @TableId(value = "id", type = IdType.AUTO)
        private Integer id;

        private String filename;

        private String model;

        private Integer flightId;

        private Integer allostate;

        private String parkinggate;

        private String userId;

        @TableField("updateTime")
        private String updateTime;


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public Integer getFlightId() {
            return flightId;
        }

        public void setFlightId(Integer flightId) {
            this.flightId = flightId;
        }

        public Integer getAllostate() {
            return allostate;
        }

        public void setAllostate(Integer allostate) {
            this.allostate = allostate;
        }

        public String getParkinggate() {
            return parkinggate;
        }

        public void setParkinggate(String parkinggate) {
            this.parkinggate = parkinggate;
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
