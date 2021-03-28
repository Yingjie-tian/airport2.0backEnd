package com.bjtu.airport.entity;

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
    public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

        @TableField("userId")
    private String userId;

        @TableField("roleId")
    private String roleId;

        @TableField("updateTime")
    private String updateTime;


}
