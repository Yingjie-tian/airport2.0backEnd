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
    public class SysRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

        @TableField("roleId")
    private String roleId;

        @TableField("permissionId")
    private String permissionId;

        @TableField("updateTime")
    private String updateTime;


}
