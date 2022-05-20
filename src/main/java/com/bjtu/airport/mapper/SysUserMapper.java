package com.bjtu.airport.mapper;

import com.bjtu.airport.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */
@Mapper
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("SELECT PASSWORD FROM sys_user WHERE userName = #{username}")
    String getPwdByUserName(String username);

    @Select("SELECT r.`name` FROM sys_user u, sys_role r, sys_user_role ur WHERE u.`id`=ur.`userId` AND r.`id`=ur.`roleId` AND u.`username`=#{username} ")
    List<String> getRoleByUserName(String username);

//    @Select("SELECT * FROM SYS_USER")
//    List<SysUser> getRoleByUserName(String username);

    @Select("SELECT p.`name` FROM sys_role r, sys_permission p, sys_role_permission rp WHERE r.`id`=rp.`role_id` AND p.`id`=rp.`permission_id` AND r.`name` = #{roleName} AND p.`type`=2")
    List<String> getRolePermissionByRoleName(String roleName);

    @Select("SELECT u.`username`  FROM sys_role r, sys_user u, sys_user_role ur WHERE u.`id`=ur.`user_id` AND r.`id`=ur.`role_id`AND r.`id` = 2 ")
    List<String> getusername();
//    String getPermissionByUserName(String username);

    //@Delete("delete from SYS_USER u, SYS_USER_ROLE ur, SYS_ROLE_PERMISSION rp where u.`id`=ur.`user_id` AND u.`id`=rp.`user_id` AND u.`username` = #{userName}")
    @Delete("delete from sys_user WHERE username = #{username}")
    public void delUserByName(String username);
    @Select("select id from sys_user WHERE username = #{username}")
    public String getUserId(String username);
    @Delete("delete from sys_user_role WHERE user_id = #{userid}")
    public void delUserByUserid (String userid);
    @Select("select u.`username, u.`password` FROM sys_role r, sys_user u, sys_user_role ur WHERE r.`id`=ur.`role_id`AND r.`id` = 2 ")
    public String selall();
    @Select("SELECT user_id FROM sys_user_role WHERE role_id=2")
    List<String> list1();
    @Select("SELECT user_id FROM sys_user_role WHERE role_id=1")
    List<String> list2();
    @Select("SELECT COUNT(id) FROM sys_user_role")
    int list3();

    /**
     * 用户管理，查询所有的用户及权限
     */
    @Select("SELECT sys_user.id, sys_user.username,sys_user.`password`, sys_role.`name` FROM sys_user,sys_user_role,sys_role \n" +
            "where sys_user.id = sys_user_role.userId AND sys_user_role.roleId = sys_role.id")
    List<JSONObject> selectAllUsers();

    /**
     * 用户管理，用来删除用户
     */
    @Delete("DELETE from sys_user WHERE id = #{id}")
    int deleteUSer(@Param("id") Integer id);
    /**
     * 用户管理，用来批量删除用户2020.11.3
     */
    @Delete("DELETE from sys_user WHERE id = #{id}")
    int delinfo(@Param("id") Integer id);


    /**
     * 用户管理，用来更改用户密码
     */
    @Update("UPDATE sys_user SET `password` = #{password} where id = #{id}")
    int updatePS(@Param("password") String password, @Param("id") Integer id);

    /**
     * 用户管理，用来添加用户
     */
    @Insert("INSERT INTO sys_user (`password`, username) VALUES(#{password}, #{username})")
    int insertUser(@Param("password") String password, @Param("username") String username);

    @Select("SELECT id FROM sys_user WHERE username = #{username}")
    Integer selectUseridByUsername(@Param("username") String username);

    @Select("SELECT id FROM sys_role WHERE name = #{name}")
    Integer selectRoleidByName(@Param("name") String name);

    @Insert("INSERT INTO sys_user_role (`userId`, `roleId`) VALUES (#{userId},#{roleId})")
    int insertUserRole(@Param("userId") Integer userId, @Param("roleId") Integer  roleId);

    @Select(value = "select username from sys_user where username= #{username}")
    List<String> selectUsernameList(@Param("username")String username);
}
