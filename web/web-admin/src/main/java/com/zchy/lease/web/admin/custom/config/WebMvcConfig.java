package com.zchy.lease.web.admin.custom.config;

import com.zchy.lease.web.admin.custom.converter.StringToBaseEnumConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @projectName: lease
 * @package: com.zchy.lease.web.admin.custom.config
 * @className: WebMvcConfig
 * @author: ZCH
 * @description:
 * @date: 8/3/2024 8:38 PM
 * @version: 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private StringToBaseEnumConverter stringToBaseEnumConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(stringToBaseEnumConverter);
    }
}
