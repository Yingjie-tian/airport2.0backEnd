package com.bjtu.airport.utils;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * token
 *
 * @ClassName: JWTToken
 * @Author liuyi
 * @Date 2019/6/20 15:57
 */
public class JWTToken implements AuthenticationToken {
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
