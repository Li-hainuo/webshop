package com.lihainuo.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lihainuo
 * @description: MyBatisPlus 配置类
 */
@Configuration
@MapperScan("com.lihainuo.common.mapper")
public class MyBatisPlusConfig {

}
