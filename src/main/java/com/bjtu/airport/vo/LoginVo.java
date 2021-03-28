package com.bjtu.airport.vo;


import com.bjtu.airport.entity.SysUser;
import lombok.Data;

import java.util.List;

/**
 * @author: LiuYi
 * @date: 2019/4/20 13:41
 */
@Data

public class LoginVo {

    String token;

    String userName;

    String userId;

    String role;

    public LoginVo(String token, SysUser user, List<String> roleNames) {
        this.token = token;
        this.userName = user.getUsername();
        this.userId = user.getId();
        StringBuilder sb = new StringBuilder();
        if (roleNames != null) {
            for (String roleName : roleNames) {
                sb.append(roleName).append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        this.role = sb.toString();
    }
}
