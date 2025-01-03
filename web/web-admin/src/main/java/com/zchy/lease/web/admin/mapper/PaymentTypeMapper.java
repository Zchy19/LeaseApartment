package com.zchy.lease.web.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zchy.lease.model.entity.PaymentType;

import java.util.List;

/**
 * @author liubo
 * @description 针对表【payment_type(支付方式表)】的数据库操作Mapper
 * @createDate 2024-11-03 15:48:00
 * @Entity com.atguigu.lease.model.PaymentType
 */
public interface PaymentTypeMapper extends BaseMapper<PaymentType> {

    List<PaymentType> getByRoomId(Long id);
}




