package com.zchy.lease.web.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zchy.lease.model.entity.LeaseAgreement;
import com.zchy.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.zchy.lease.web.admin.vo.agreement.AgreementVo;

import java.util.List;

/**
* @author liubo
* @description 针对表【lease_agreement(租约信息表)】的数据库操作Mapper
* @createDate 2024-11-03 15:48:00
* @Entity com.atguigu.lease.model.LeaseAgreement
*/
public interface LeaseAgreementMapper extends BaseMapper<LeaseAgreement> {

    List<AgreementVo> getAllList(IPage<AgreementVo> page, AgreementQueryVo queryVo);
}




