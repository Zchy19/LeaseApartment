package com.zchy.lease.web.app.service;

import com.zchy.lease.model.entity.PaymentType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface PaymentTypeService extends IService<PaymentType> {
    List<PaymentType> listByRoomId(Long id);
}
