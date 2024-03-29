package com.luoquan.reactserver.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * PageHelper
 *
 * @author LuoQuan
 * @date 2019/7/2 21:49
 */
@Configuration    //这个注解不能忘记
public class PageHelperConfig {

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        //数据库
        p.setProperty("helperDialect", "mysql");
        /**
         *该参数默认为false
         *设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用
         *和startPage中的pageNum效果一样
         */
        p.setProperty("offsetAsPageNum", "true");
        /**
         *该参数默认为false
         *设置为true时，使用RowBounds分页会进行count查询
         */
        p.setProperty("rowBoundsWithCount", "true");
        /**
         *3.3.0版本可用 - 分页参数合理化，默认false禁用
         *启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
         *禁用合理化时，如果pageNum<1或pageNum>pages会返回空数据
         */
        p.setProperty("reasonable", "true");
        p.setProperty("params","count=countSql");
        pageHelper.setProperties(p);
        return pageHelper;
    }

}
