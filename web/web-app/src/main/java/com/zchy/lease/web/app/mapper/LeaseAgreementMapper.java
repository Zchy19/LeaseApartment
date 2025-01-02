package com.zchy.lease.web.app.mapper;

import com.zchy.lease.model.entity.LeaseAgreement;
import com.zchy.lease.web.app.vo.agreement.AgreementItemVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface LeaseAgreementMapper extends BaseMapper<LeaseAgreement> {

    List<AgreementItemVo> listItemByPhone(String phone);
}




