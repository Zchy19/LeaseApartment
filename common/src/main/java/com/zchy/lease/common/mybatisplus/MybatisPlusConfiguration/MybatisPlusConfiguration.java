package com.zchy.lease.common.mybatisplus.MybatisPlusConfiguration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.zchy.lease.web.*.mapper")
public class MybatisPlusConfiguration {
  
}