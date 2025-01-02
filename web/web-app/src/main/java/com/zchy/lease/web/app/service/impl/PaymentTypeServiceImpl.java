package com.zchy.lease.web.app.service.impl;

import com.zchy.lease.model.entity.PaymentType;
import com.zchy.lease.web.app.mapper.PaymentTypeMapper;
import com.zchy.lease.web.app.service.PaymentTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【payment_type(支付方式表)】的数据库操作Service实现
* @createDate 2024-11-03 11:12:39
*/
@Service
public class PaymentTypeServiceImpl extends ServiceImpl<PaymentTypeMapper, PaymentType>
    implements PaymentTypeService{

    @Autowired
    private PaymentTypeMapper paymentTypeMapper;

    @Override
    public List<PaymentType> listByRoomId(Long id) {
        return paymentTypeMapper.selectListByRoomId(id);
    }
}




