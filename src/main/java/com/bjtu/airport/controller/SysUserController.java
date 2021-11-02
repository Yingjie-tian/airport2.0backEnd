package com.bjtu.airport.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.bjtu.airport.contant.Result;
import com.bjtu.airport.contant.ResultCode;
import com.bjtu.airport.entity.SysUser;
import com.bjtu.airport.service.ISysUserService;
import com.bjtu.airport.utils.JWTUtil;
import com.bjtu.airport.vo.LoginVo;
import com.bjtu.airport.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WuZhen
 * @since 2020-03-01
 */

@RestController
@RequestMapping("/user")
@Api(tags = "用户注册登录API")
@Slf4j
public class SysUserController {

    @Autowired
    private ISysUserService userService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public Result login(@RequestParam(value = "username", required = true) String userName,
                        @RequestParam(value = "password", required = true) String password,
                        HttpServletRequest request, HttpServletResponse response) {

//      验证逻辑
        SysUser query_user = userService.getUserByName(userName);
        if (query_user == null) {
            return ResultVo.error(ResultCode.USERNAME_PASSWORD_ERROR);
        }
        ;
        //验证密码
        boolean is_password = (password != null && password.equals(query_user.getPassword()));
        if (!is_password) {
            //密码错误，返回提示
            return ResultVo.error(ResultCode.USERNAME_PASSWORD_ERROR);
        }
        String jwtToken = JWTUtil.createToken(userName);
        response.setHeader("Authorization", jwtToken);
        List<String> roleNames = userService.getRoleNamesByUserId(query_user.getUsername());
        return ResultVo.ok(new LoginVo(jwtToken, query_user, roleNames));
    }


    /**
     * <p>
     * 用来查询后台所有账号
     * </p>
     *
     * @author zhang xiaofeng
     * @since 2019-06-25
     */
    @GetMapping(value = "/unauthorized")
    public Result unauthorized() {
        System.out.println("hahahaha");
        List<String> kk = userService.getusername();

        return ResultVo.ok(kk);
    }

    /**
     * <p>
     * 用来查询costomer账号
     * </p>
     *
     * @author zhang xiaofeng
     * @since 2019-06-25
     */

    @GetMapping(value = "/sel")
    public Result getStuList(@RequestParam(value = "username", required = true) String userName) {
        SysUser query_user = userService.getUserByName(userName);
        return ResultVo.ok(query_user);
    }

    @GetMapping(value = "/del")
    public Result delStuList(@RequestParam(value = "username", required = true) String userName) {

        String userid = userService.getUserId(userName);
        userService.delUserByUserid(userid);
        userService.delUserByName(userName);
        System.out.println("hahasadas");
        System.out.println(userid);
        return ResultVo.ok();
    }
//    @GetMapping(value = "/add")
//    public Result addStuList(@RequestBody Object data_new) {
//        System.out.println(data_new);
//        System.out.println("dogdog");
//        return ResultVo.ok();
//    }

    /**
     * <p>
     * 用来查询User_Role customer账号
     * </p>
     *
     * @author zhang xiaofeng
     * @since 2019-06-25
     */
    @GetMapping("/sura")
    public List<String> list1() {
        List<String> userInfos = userService.list1();
        return userInfos;
    }

    /**
     * <p>
     * 用来查询User_Role admin账号
     * </p>
     *
     * @author zhang xiaofeng
     * @since 2019-06-25
     */
    @GetMapping("/surc")
    public Result list2() {
        List<String> userInfos = userService.list2();
        List<SysUser> userInfo = userService.list(null);
        List temp = new ArrayList();
        String[] role = new String[userInfo.size()];
        for (int i = 0; i < userInfo.size(); i++) {
            for (int ii = 0; ii < userInfos.size(); ii++) {
                if (userInfo.get(i).getId().equals(userInfos.get(ii))) {
                    role[i] = "admin";
                    break;
                } else {
                    role[i] = "customer";
                }
            }
            temp.add(userInfo.get(i).getId());
            temp.add(userInfo.get(i).getUsername());
            temp.add(userInfo.get(i).getPassword());
            temp.add(role[i]);
        }


        return ResultVo.ok(temp);
    }

    /**
     * <p>
     * 增加一个账号
     * </p>
     *
     * @author zhang xiaofeng
     * @since 2019-06-25
     */
    @GetMapping(value = "/add")
    public Result addStuList(@RequestParam(value = "userId", required = true) String userId,
                             @RequestParam(value = "userName", required = true) String userName,
                             @RequestParam(value = "password", required = true) String password,
                             @RequestParam(value = "role", required = true) String role) {

        Timestamp ctime = new java.sql.Timestamp(new java.util.Date().getTime());
        System.out.println(ctime);
        String sql = "INSERT INTO SYS_USER (id,username,password) VALUES (?,?,?)";
        this.jdbcTemplate.update(sql, userId, password, ctime);
        int num_id = userService.list3();

        String sql1 = "INSERT INTO SYS_USER_ROLE (id,user_id,role_id) VALUES (?,?,?)";
        this.jdbcTemplate.update(sql1, num_id + 1, userId, "2");
        return ResultVo.ok();
    }


    /**
     * 用户管理，查询所有的用户及权限
     */
    @GetMapping("/selectAllUsers")
    public  List<JSONObject> selectAllUsers(){
        try {
            List<JSONObject> userList = userService.selectAllUsers();
            return userList;
        }catch (Exception e){
            log.info("查询用户错误是:{}", e);
        };

        return null;
    };


    /**
     * 用户管理，用来删除用户
     */
    @PostMapping("/deleteUser")
    public String deleteUser(@RequestBody Object userIdInfo){
        JSONObject jsonObject = JSONObject.fromObject(userIdInfo);
        int numOfDelete = userService.deleteUser(jsonObject.getInt("id"));
        return "成功删除" + numOfDelete + "名用户";
    };
    /**
     * 用户管理，用来批量删除用户2020.11.3
     */
    @PostMapping("/condeleteUser")
    public String condeleteUser(@RequestBody Object userIdInfo){
        net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(userIdInfo);
        int sum = 0;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            int row2 = userService.delinfo(jsonObject.getInt("id")
            );
        }
        return "成功删除" ;
    };

    /**
     * 用户管理，用来更改用户密码
     */
    @PostMapping("updatePS")
    public String updatePS(@RequestBody Object passwordInfo){
        JSONObject jsonObject = JSONObject.fromObject(passwordInfo);
        int row = userService.updatePS(jsonObject.getString("password"), jsonObject.getInt("id"));
        return "更改" + row + "名用户密码成功";
    };

    /**
     * 用户管理，用来添加用户
     */
    @PostMapping("/insertUser")
    public String insertUser(@RequestBody Object userInfo){
        JSONObject jsonObject = JSONObject.fromObject(userInfo);
        int row1 = userService.insertUser(jsonObject.getString("password").trim(), jsonObject.getString("username").trim());
        Integer userId = userService.selectUseridByUsername(jsonObject.getString("username").trim().trim());
        Integer roleId = userService.selectRoleidByName(jsonObject.getString("name").trim());
        System.out.println(userId + "==============" + roleId);
        int row2 = userService.insertUserRole(userId, roleId);
        System.out.println(row2);
        return "添加用户成功";
    }

    @GetMapping("/getMenuList")
    public Result getMenuList(@RequestParam("role") String role){
        //todo 前端src\components\common\Sidebar.vue
        return ResultVo.ok(userService.getMenuList(role));
    }

    @GetMapping("/checkUserExist")
    public R<?> checkUserExist(@RequestParam("username") String username){
        if (StringUtils.isEmpty(username)){
            return R.failed("用户名不能为空");
        }
        if (username.trim().length()<2 || username.trim().length()>16){
            return R.failed("用户名长度应在2-16");
        }
        return userService.checkUserExist(username.trim());
    }
}
