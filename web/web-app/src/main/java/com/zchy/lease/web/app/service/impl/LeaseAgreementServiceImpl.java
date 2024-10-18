package com.zchy.lease.web.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zchy.lease.model.entity.GraphInfo;
import com.zchy.lease.model.entity.LeaseAgreement;
import com.zchy.lease.web.app.mapper.LeaseAgreementMapper;
import com.zchy.lease.web.app.service.*;
import com.zchy.lease.web.app.vo.agreement.AgreementDetailVo;
import com.zchy.lease.web.app.vo.agreement.AgreementItemVo;
import com.zchy.lease.web.app.vo.graph.GraphVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liubo
 * @description 针对表【lease_agreement(租约信息表)】的数据库操作Service实现
 * @createDate 2023-07-26 11:12:39
 */
@Service
public class LeaseAgreementServiceImpl extends ServiceImpl<LeaseAgreementMapper, LeaseAgreement>
        implements LeaseAgreementService {
    @Autowired
    private GraphInfoService graphInfoService;
    @Autowired
    private ApartmentInfoService apartmentInfoService;
    @Autowired
    private RoomInfoService roomInfoService;
    @Autowired
    private PaymentTypeService paymentTypeService;
    @Autowired
    private LeaseTermService leaseTermService;

    /**
     * 根据id获取租约详细信息
     * @param id
     * @return AgreementDetailVo
     */
    @Override
    public AgreementDetailVo getDetailById(Long id) {
        LeaseAgreement leaseAgreement = getById(id);
        AgreementDetailVo agreementDetailVo = new AgreementDetailVo();
        BeanUtils.copyProperties(leaseAgreement, agreementDetailVo);
        // 获取公寓图片
        LambdaQueryWrapper<GraphInfo> graphInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        graphInfoLambdaQueryWrapper.eq(GraphInfo::getId, leaseAgreement.getApartmentId());
        List<GraphInfo> apartmentGraph = graphInfoService.list(graphInfoLambdaQueryWrapper);
        List<GraphVo> apartmentGraphVoList = apartmentGraph.stream().map(graphInfo -> {
            GraphVo graphVo = new GraphVo();
            graphVo.setName(graphInfo.getName());
            graphVo.setUrl(graphInfo.getUrl());
            return graphVo;
        }).collect(Collectors.toList());
        agreementDetailVo.setApartmentGraphVoList(apartmentGraphVoList);
        graphInfoLambdaQueryWrapper.clear();
        // 获取房间图片
        graphInfoLambdaQueryWrapper.eq(GraphInfo::getId, leaseAgreement.getRoomId());
        List<GraphInfo> roomGraph = graphInfoService.list(graphInfoLambdaQueryWrapper);
        List<GraphVo> roomGraphVoList = roomGraph.stream().map(graphInfo -> {
            GraphVo graphVo = new GraphVo();
            graphVo.setName(graphInfo.getName());
            graphVo.setUrl(graphInfo.getUrl());
            return graphVo;
        }).collect(Collectors.toList());
        agreementDetailVo.setRoomGraphVoList(roomGraphVoList);
        //获取公寓名称
        agreementDetailVo.setApartmentName(apartmentInfoService.getById(leaseAgreement.getApartmentId()).getName());
        //获取房间号
        agreementDetailVo.setRoomNumber(roomInfoService.getById(leaseAgreement.getRoomId()).getRoomNumber());
        //获取支付方式
        agreementDetailVo.setPaymentTypeName(paymentTypeService.getById(leaseAgreement.getPaymentTypeId()).getName());
        //获取租期月数
        agreementDetailVo.setLeaseTermMonthCount(leaseTermService.getById(leaseAgreement.getLeaseTermId()).getMonthCount());
        //获取租期单位
        agreementDetailVo.setLeaseTermUnit(leaseTermService.getById(leaseAgreement.getLeaseTermId()).getUnit());

        return agreementDetailVo;

    }

    /**
     * 获取个人租约基本信息列表
     * @return List<AgreementItemVo>
     */
    @Override
    public List<AgreementItemVo> listItem() {
        
    }
}




