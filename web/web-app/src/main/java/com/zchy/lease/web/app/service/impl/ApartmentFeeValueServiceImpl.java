package com.zchy.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zchy.lease.model.entity.ApartmentFeeValue;
import com.zchy.lease.web.app.service.ApartmentFeeValueService;
import com.zchy.lease.web.app.mapper.ApartmentFeeValueMapper;
import org.springframework.stereotype.Service;

/**
* @author liubo
* @description 针对表【apartment_fee_value(公寓&杂费关联表)】的数据库操作Service实现
* @createDate 2024-11-03 11:12:39
*/
@Service
public class ApartmentFeeValueServiceImpl extends ServiceImpl<ApartmentFeeValueMapper, ApartmentFeeValue>
    implements ApartmentFeeValueService{
}




