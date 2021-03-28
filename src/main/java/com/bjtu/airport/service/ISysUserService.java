package com.bjtu.airport.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.bjtu.airport.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bjtu.airport.entity.vo.MenuVo;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */
public interface ISysUserService extends IService<SysUser> {
    List<String> getusername();
    SysUser getUserByName(String userName);
    public void delUserByName (String userName);
    List<String> getRoleNamesByUserId(String username);
//List<SysUser> getRoleNamesByUserId(String username);
    String  getUserId(String userName);
    public void delUserByUserid (String userid);
    public String selall();
    public List<String> list1();
    public List<String> list2();
    int list3();


    /**
     * 用户管理，查询所有的用户及权限
     */
    List<JSONObject> selectAllUsers();

    /**
     * 用户管理，用来删除用户
     */
    int deleteUser(Integer id);
    /**
     * 用户管理，用来批量删除用户2020.11.03
     */
    int delinfo(Integer id);
    /**
     * 用户管理，用来更改用户密码
     */
    int updatePS(String password, Integer id);

    /**
     * 用户管理，用来添加用户
     */
    int insertUser(String password, String username);
    Integer selectUseridByUsername(String username);
    Integer selectRoleidByName(String name);
    int insertUserRole(Integer userId, Integer roleId);

    List<MenuVo> getMenuList(String role);

    R<?> checkUserExist(String username);
}
