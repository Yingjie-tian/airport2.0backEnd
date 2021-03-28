package com.bjtu.airport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.bjtu.airport.entity.SysMenu;
import com.bjtu.airport.entity.SysUser;
import com.bjtu.airport.entity.vo.MenuVo;
import com.bjtu.airport.mapper.SysMenuMapper;
import com.bjtu.airport.mapper.SysUserMapper;
import com.bjtu.airport.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    public SysUser getUserByName(String userName) {
        return getOne(new QueryWrapper<SysUser>().eq(SysUser.USERNAME, userName));
    }

    @Override
    public List<String> getRoleNamesByUserId(String username) {
        List<String> roleNames = baseMapper.getRoleByUserName(username);
        return new ArrayList(new HashSet(roleNames));
    }
//    @Override
//    public List<SysUser> getRoleNamesByUserId(String username) {
//        List<SysUser> roleNames = baseMapper.getRoleByUserName(username);
//        return new ArrayList(new HashSet(roleNames));
//    }

    @Override
    public List<String> getusername() {
        List<String> Info =userMapper.getusername();
        return Info;
    }

    @Override
    public void delUserByName(String userName) {
        userMapper.delUserByName(userName);
    }

    @Override
    public String getUserId(String userName) {
        String userid = userMapper.getUserId(userName);
        return userid;
    }
    @Override
    public List<String> list1(){
        List<String> Info =userMapper.list1();
        return Info;
    }
    @Override
    public List<String> list2(){
        List<String> Info =userMapper.list2();
        return Info;
    }

    @Override
    public void delUserByUserid (String userid){
        userMapper.delUserByUserid(userid);
    }
    @Override
    public String selall(){
        String info= userMapper.selall();
        return info;
    }
    @Override
    public int list3(){
        int Info =userMapper.list3();
        return Info;
    }

    @Override
    /**
     * 用户管理，查询所有的用户及权限
     */
    public List<JSONObject> selectAllUsers(){
        List<JSONObject> userList = userMapper.selectAllUsers();
        return userList;
    };

    /**
     * 用户管理，用来删除用户
     */
    @Override
    public int deleteUser(Integer id){
        int numOfDelete = userMapper.deleteUSer(id);
        return numOfDelete;
    };
    /**
     * 用户管理，用来批量删除用户2020.11.03
     */
    @Override
    public int delinfo(Integer id){
        int info = userMapper.delinfo(id);
        return info;
    };
    /**
     * 用户管理，用来更改用户密码
     */
    @Override
    public int updatePS(String password, Integer id){
        int info = userMapper.updatePS(password, id);
        return info;
    };

    /**
     * 用户管理，用来添加用户
     */
    @Override
    public int insertUser(String password, String username){
        int info = userMapper.insertUser(password, username);
        return info;
    };
    @Override
    public Integer selectUseridByUsername(String username){
        Integer uid = userMapper.selectUseridByUsername(username);
        return uid;
    };
    @Override
    public Integer selectRoleidByName(String name){
        Integer rid = userMapper.selectRoleidByName(name);
        return rid;
    };
    @Override
    public int insertUserRole(Integer userId, Integer roleId){
        int info = userMapper.insertUserRole(userId, roleId);
        return info;
    }

    @Override
    public List<MenuVo> getMenuList(String role) {
        //先查出该角色所有的菜单
        List<SysMenu> sysMenus = sysMenuMapper.selectMenuByRoleName(role);
        //转换为menuvo
        List<MenuVo> menus = new ArrayList<>();
//                collect = sysMenus.stream().map(e -> new MenuVo(e.getIcon(), e.getOrderNum().toString(), e.getMenuName(),e.getParentId())).collect(Collectors.toList());
        for (SysMenu m:sysMenus){
            if (m.getParentId() == null){
                MenuVo parent = new MenuVo().setIcon(m.getIcon())
                        .setIndex(m.getOrderNum().toString())
                        .setTitle(m.getMenuName())
                        .setParentId(m.getParentId());
                for (SysMenu child : sysMenus){
                    if (child.getParentId()!=null && child.getParentId().equals(m.getId())){
                        if (parent.getSubs() == null) {
                            parent.setSubs(new ArrayList<MenuVo>());
                        }
                        parent.getSubs().add(
                                new MenuVo()
                                .setIcon(child.getIcon())
                                .setIndex(child.getOrderNum().toString())
                                .setTitle(child.getMenuName())
                                .setParentId(child.getParentId())
                        );
                    }
                }
                menus.add(parent);
            }
        }
        return menus;

    }

    @Override
    public R<?> checkUserExist(String username) {
        List<String> list = userMapper.selectUsernameList(username);
        if (list == null || list.isEmpty()){
            return R.ok(false);
        }else {
            return R.ok(true);
        }
    }


}
