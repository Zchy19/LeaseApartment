package com.zchy.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.zchy.lease.common.exception.LeaseException;
import com.zchy.lease.common.result.ResultCodeEnum;
import com.zchy.lease.model.entity.*;
import com.zchy.lease.model.enums.ItemType;
import com.zchy.lease.web.admin.mapper.*;
import com.zchy.lease.web.admin.service.*;
import com.zchy.lease.web.admin.vo.apartment.ApartmentDetailVo;
import com.zchy.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.zchy.lease.web.admin.vo.apartment.ApartmentQueryVo;
import com.zchy.lease.web.admin.vo.apartment.ApartmentSubmitVo;
import com.zchy.lease.web.admin.vo.fee.FeeValueVo;
import com.zchy.lease.web.admin.vo.graph.GraphVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liubo
 * @description 针对表【apartment_info(公寓信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class ApartmentInfoServiceImpl extends ServiceImpl<ApartmentInfoMapper, ApartmentInfo> implements ApartmentInfoService {

    @Autowired
    private ApartmentInfoMapper apartmentInfoMapper;
    @Autowired
    private GraphInfoMapper graphInfoMapper;
    @Autowired
    private FacilityInfoMapper facilityInfoMapper;
    @Autowired
    private LabelInfoMapper labelInfoMapper;
    @Autowired
    private FeeValueMapper feeValueMapper;
    @Autowired
    private RoomInfoMapper roomInfoMapper;

    @Autowired
    private GraphInfoService graphInfoService;
    @Autowired
    private ApartmentFacilityService apartmentFacilityService;
    @Autowired
    private ApartmentLabelService apartmentLabelService;
    @Autowired
    private ApartmentFeeValueService apartmentFeeValueService;

    @Override
    public void saveOrUpdateApartment(ApartmentSubmitVo apartmentSubmitVo) {
        boolean isUpdate = false;
        if (apartmentSubmitVo.getId() != null) {
            isUpdate = true;
        }
        super.saveOrUpdate(apartmentSubmitVo);
        if (isUpdate) {
            removeRelatedEntity(apartmentSubmitVo.getId());
        }
        saveRelatedEntity(apartmentSubmitVo);
    }

    @Override
    public IPage<ApartmentItemVo> getListByPage(long current, long size, ApartmentQueryVo queryVo) {
        IPage<ApartmentItemVo> page = new Page<>(current, size);
        List<ApartmentItemVo> list = apartmentInfoMapper.getListByPage(page, queryVo);
        page.setRecords(list);
        return page;
    }

    @Override
    public ApartmentDetailVo getDetailById(Long id) {
        //1.查询公寓信息
        ApartmentInfo apartmentInfo = super.getById(id);
        //2.查询图片信息
        List<GraphVo> graphVoList = graphInfoMapper.getByItemIdAndType(id, ItemType.APARTMENT);
        //3.查询配套信息
        List<FacilityInfo> facilityInfoList = facilityInfoMapper.getByApartmentId(id);
        //4.查询标签信息
        List<LabelInfo> labelInfoList = labelInfoMapper.getByApartmentId(id);
        //5.查询杂费信息
        List<FeeValueVo> feeValueList = feeValueMapper.getByApartmentId(id);
        //6.封装返回
        ApartmentDetailVo apartmentDetailVo = new ApartmentDetailVo();
        BeanUtils.copyProperties(apartmentInfo,apartmentDetailVo);
        apartmentDetailVo.setGraphVoList(graphVoList);
        apartmentDetailVo.setFacilityInfoList(facilityInfoList);
        apartmentDetailVo.setLabelInfoList(labelInfoList);
        apartmentDetailVo.setFeeValueVoList(feeValueList);
        return apartmentDetailVo;
    }

    @Override
    public void removeByApartmentId(Long id) {
        Long count = roomInfoMapper.selectCount(new LambdaQueryWrapper<RoomInfo>().eq(RoomInfo::getApartmentId, id));
        if (count > 0) {
            throw new LeaseException(ResultCodeEnum.ADMIN_APARTMENT_DELETE_ERROR);
        }
        removeById(id);
        removeRelatedEntity(id);
    }

    private void removeRelatedEntity(Long id) {
        //1.删除公寓配套
        LambdaQueryWrapper<ApartmentFacility> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ApartmentFacility::getApartmentId, id);
        apartmentFacilityService.remove(wrapper);
        //2.删除公寓标签
        LambdaQueryWrapper<ApartmentLabel> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.eq(ApartmentLabel::getApartmentId, id);
        apartmentLabelService.remove(wrapper2);
        //3.删除公寓杂费
        LambdaQueryWrapper<ApartmentFeeValue> wrapper3 = new LambdaQueryWrapper<>();
        wrapper3.eq(ApartmentFeeValue::getApartmentId, id);
        apartmentFeeValueService.remove(wrapper3);
        //4.删除公寓图片
        LambdaQueryWrapper<GraphInfo> wrapper4 = new LambdaQueryWrapper<>();
        wrapper4.eq(GraphInfo::getItemId, id);
        wrapper4.eq(GraphInfo::getItemType, ItemType.APARTMENT);
        graphInfoService.remove(wrapper4);
    }


    private void saveRelatedEntity(ApartmentSubmitVo apartmentSubmitVo) {
        //1.插入图片列表
        List<GraphVo> graphVoList = apartmentSubmitVo.getGraphVoList();
        if (!CollectionUtils.isEmpty(graphVoList)){
            ArrayList<GraphInfo> graphInfoList = new ArrayList<>();
            for (GraphVo graphVo : graphVoList) {
                GraphInfo graphInfo = new GraphInfo();
                graphInfo.setItemType(ItemType.APARTMENT);
                graphInfo.setItemId(apartmentSubmitVo.getId());
                graphInfo.setName(graphVo.getName());
                graphInfo.setUrl(graphVo.getUrl());
                graphInfoList.add(graphInfo);
            }
            graphInfoService.saveBatch(graphInfoList);
        }


        //2.插入配套列表
        List<Long> facilityInfoIdList = apartmentSubmitVo.getFacilityInfoIds();
        if (!CollectionUtils.isEmpty(facilityInfoIdList)){
            ArrayList<ApartmentFacility> facilityList = new ArrayList<>();
            for (Long facilityId : facilityInfoIdList) {
                ApartmentFacility apartmentFacility = ApartmentFacility.builder().build();
                apartmentFacility.setApartmentId(apartmentSubmitVo.getId());
                apartmentFacility.setFacilityId(facilityId);
                facilityList.add(apartmentFacility);
            }
            apartmentFacilityService.saveBatch(facilityList);
        }


        //3.插入标签列表
        List<Long> labelIds = apartmentSubmitVo.getLabelIds();
        if (!CollectionUtils.isEmpty(labelIds)) {
            List<ApartmentLabel> apartmentLabelList = new ArrayList<>();
            for (Long labelId : labelIds) {
                ApartmentLabel apartmentLabel = ApartmentLabel.builder().build();
                apartmentLabel.setApartmentId(apartmentSubmitVo.getId());
                apartmentLabel.setLabelId(labelId);
                apartmentLabelList.add(apartmentLabel);
            }
            apartmentLabelService.saveBatch(apartmentLabelList);
        }


        //4.插入杂费列表
        List<Long> feeValueIds = apartmentSubmitVo.getFeeValueIds();
        if (!CollectionUtils.isEmpty(feeValueIds)) {
            ArrayList<ApartmentFeeValue> apartmentFeeValueList = new ArrayList<>();
            for (Long feeValueId : feeValueIds) {
                ApartmentFeeValue apartmentFeeValue = ApartmentFeeValue.builder().build();
                apartmentFeeValue.setApartmentId(apartmentSubmitVo.getId());
                apartmentFeeValue.setFeeValueId(feeValueId);
                apartmentFeeValueList.add(apartmentFeeValue);
            }
            apartmentFeeValueService.saveBatch(apartmentFeeValueList);
        }
    }
}

