package com.bjtu.airport.entity;

    import com.baomidou.mybatisplus.annotation.TableField;
    import java.io.Serializable;

    import com.baomidou.mybatisplus.extension.activerecord.Model;
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
    public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String password;

    private String username;

        @TableField("regTime")
    private String regTime;

        @TableField("updateTime")
    private String updateTime;

    public static final String ID = "id";

    public static final String PASSWORD = "password";

    public static final String USERNAME = "username";

    public static final String REG_TIME = "reg_time";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
