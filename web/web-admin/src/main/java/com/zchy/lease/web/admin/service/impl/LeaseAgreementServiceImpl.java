package com.zchy.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zchy.lease.model.entity.*;
import com.zchy.lease.model.enums.LeaseStatus;
import com.zchy.lease.web.admin.mapper.LeaseAgreementMapper;
import com.zchy.lease.web.admin.service.*;
import com.zchy.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.zchy.lease.web.admin.vo.agreement.AgreementVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author liubo
 * @description 针对表【lease_agreement(租约信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class LeaseAgreementServiceImpl extends ServiceImpl<LeaseAgreementMapper, LeaseAgreement>
        implements LeaseAgreementService {

    @Autowired
    private LeaseAgreementMapper leaseAgreementMapper;

    @Autowired
    private ApartmentInfoService apartmentInfoService;
    @Autowired
    private RoomInfoService roomInfoService;
    @Autowired
    private PaymentTypeService paymentTypeService;
    @Autowired
    private LeaseTermService leaseTermService;


    @Override
    public IPage<AgreementVo> getAllList(long current, long size, AgreementQueryVo queryVo) {
        IPage<AgreementVo> page = new Page<>(current, size);
        List<AgreementVo> list = leaseAgreementMapper.getAllList(page, queryVo);
        page.setRecords(list);
        return page;
    }

    @Override
    public AgreementVo getByAgreementId(Long id) {
        LeaseAgreement leaseAgreement = getById(id);
        ApartmentInfo apartmentInfo = apartmentInfoService.getById(leaseAgreement.getApartmentId());
        RoomInfo roomInfo = roomInfoService.getById(leaseAgreement.getRoomId());
        PaymentType paymentType = paymentTypeService.getById(leaseAgreement.getPaymentTypeId());
        LeaseTerm leaseTerm = leaseTermService.getById(leaseAgreement.getLeaseTermId());
        AgreementVo agreementVo = new AgreementVo();
        BeanUtils.copyProperties(leaseAgreement, agreementVo);
        agreementVo.setApartmentInfo(apartmentInfo);
        agreementVo.setRoomInfo(roomInfo);
        agreementVo.setPaymentType(paymentType);
        agreementVo.setLeaseTerm(leaseTerm);
        return agreementVo;
    }

    @Override
    public void updateStatus() {
        LambdaUpdateWrapper<LeaseAgreement> wrapper = new LambdaUpdateWrapper<>();
        wrapper.in(LeaseAgreement::getStatus, LeaseStatus.SIGNED, LeaseStatus.WITHDRAWING);
        wrapper.le(LeaseAgreement::getLeaseEndDate, new Date());
        wrapper.set(LeaseAgreement::getStatus, LeaseStatus.EXPIRED);

        update(wrapper);
    }
}




