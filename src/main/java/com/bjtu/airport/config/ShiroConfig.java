package com.bjtu.airport.config;



import com.bjtu.airport.component.JWTFilter;
import com.bjtu.airport.utils.CustomRealm;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ShiroConfig
 * @Author liuyi
 * @Date 2019/6/13 19:44
 */
@Configuration
public class ShiroConfig {
    /**
     * 先走 filter ，然后 filter 如果检测到请求头存在 token，则用 token 去 login，走 Realm 去验证
     */
    @Bean
    public ShiroFilterFactoryBean factory(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<>();
        //设置我们自定义的JWT过滤器
        filterMap.put("jwt", new JWTFilter());
        factoryBean.setFilters(filterMap);
        factoryBean.setSecurityManager(securityManager);

        // 拦截器.
//        Map<String, String> filterChainDefinitionMap = new HashMap<>();
//
//        filterChainDefinitionMap.put("/**", "jwt");
//
//        // 配置不会被拦截的链接 顺序判断
//        filterChainDefinitionMap.put("/css/**", "anon");
//        filterChainDefinitionMap.put("/fonts/**", "anon");
//        filterChainDefinitionMap.put("/img/**", "anon");
//        filterChainDefinitionMap.put("/js/**", "anon");
//        // 访问 /unauthorized/** 不通过JWTFilter
////        filterChainDefinitionMap.put("/unauthorized/**", "anon");
//
//        filterChainDefinitionMap.put("/", "anon");
////        filterChainDefinitionMap.put("/user/login", "anon");
//
//        // 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
//        filterChainDefinitionMap.put("/user/logout", "logout");
//
//
//        // 设置无权限时跳转的 url;
//        factoryBean.setUnauthorizedUrl("/user/unauthorized");
//        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);


        Map<String, String> filterRuleMap = new HashMap<>();
        // 所有请求通过我们自己的JWT Filter
        // 配置不进行拦截的 url 请求
//        filterRuleMap.put("/user/login", "anon");
//        filterRuleMap.put("/index.html", "anon");
//        filterRuleMap.put("/index.html#", "anon");
////        filterRuleMap.put("/intakePlantInfo/hello", "anon");
//        // 剩余全部请求 拦截
//
////        filterRuleMap.put("/**", "jwt");
////        filterRuleMap.put("/**", "anon");
//        // 访问 /unauthorized/** 不通过JWTFilter
//        filterRuleMap.put("/user/unauthorized", "anon");
//        filterRuleMap.put("/flight-info/**","anon");
        filterRuleMap.put("/user/**","anon");
//        filterRuleMap.put("/flight-info/**","anon");
//        filterRuleMap.put("/**", "jwt");
        filterRuleMap.put("/rule-description/**", "anon");
        filterRuleMap.put("/flight-info/**", "anon");
        filterRuleMap.put("/air-rule/**", "anon");
        filterRuleMap.put("/gate-info/**", "anon");
        filterRuleMap.put("/getalldata/**", "anon");
        filterRuleMap.put("/result/**", "anon");
        filterRuleMap.put("/statistic-result/**", "anon");



        factoryBean.setFilterChainDefinitionMap(filterRuleMap);

        return factoryBean;
    }

    /**
     * 注入 securityManager
     */
    @Bean
    public SecurityManager securityManager(CustomRealm customRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        /*
         * 关闭shiro自带的session，详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        securityManager.setAuthenticator(authenticator(customRealm));
        securityManager.setAuthorizer(authorizer(customRealm));

        // 设置自定义 realm.
        securityManager.setRealm(customRealm);

        return securityManager;
    }

    /**
     * 添加注解支持
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public Authorizer authorizer(CustomRealm myRealm) {
        ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
        Collection<Realm> crealms = new ArrayList<>();
        crealms.add(myRealm);
        authorizer.setRealms(crealms);
        return authorizer;
    }

    @Bean
    public Authenticator authenticator(CustomRealm myRealm) {
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        Collection<Realm> crealms = new ArrayList<>();
        crealms.add(myRealm);
        authenticator.setRealms(crealms);
        return authenticator;
    }
}