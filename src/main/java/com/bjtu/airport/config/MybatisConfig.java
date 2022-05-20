package com.bjtu.airport.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2022/5/8 11:59
 */
@Configuration
public class MybatisConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }

}
