package com.zchy.lease.web.admin.custom.converter;

import com.zchy.lease.model.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * @projectName: lease
 * @package: com.zchy.lease.web.admin.custom.converter
 * @className: StringToBaseEnumConverter
 * @author: ZCH
 * @description:
 * @date: 8/3/2024 8:41 PM
 * @version: 1.0
 */
@Component
public class StringToBaseEnumConverter implements ConverterFactory<String, BaseEnum> {
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new Converter<String, T>() {
            @Override
            public T convert(String source) {
                for (T enumConstant : targetType.getEnumConstants()) {
                    if (enumConstant.getCode().toString().equals(source)){
                        return enumConstant;
                    }
                }
                throw new IllegalArgumentException("枚举类型不正确" + source);
            }
        };
    }
}
