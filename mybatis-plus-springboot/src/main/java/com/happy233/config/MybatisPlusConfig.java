package com.happy233.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注册分页插件
 *
 * @author zy136
 * @date 2021/12/19 17:38
 */

@Configuration
public class MybatisPlusConfig {


    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        //注册分页插件 使用 mybatis plus 内置的分页插件
        PaginationInnerInterceptor pageInterceptor = new PaginationInnerInterceptor();
        //单页分页条数限制 -1L=不设限制
        pageInterceptor.setMaxLimit(-1L);
        //溢出总页数后是否进行处理
        pageInterceptor.setOverflow(true);
        //生成 countSql 优化掉 join 现在只支持 left join
        pageInterceptor.setOptimizeJoin(true);
        //设置数据库类型
        pageInterceptor.setDbType(DbType.MYSQL);
        //添加分页拦截器
        mybatisPlusInterceptor.addInnerInterceptor(pageInterceptor);

        return mybatisPlusInterceptor;

    }
}
