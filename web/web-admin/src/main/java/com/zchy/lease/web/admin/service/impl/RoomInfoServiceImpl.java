package com.zchy.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zchy.lease.model.entity.*;
import com.zchy.lease.model.enums.ItemType;
import com.zchy.lease.web.admin.mapper.*;
import com.zchy.lease.web.admin.service.*;
import com.zchy.lease.web.admin.vo.attr.AttrValueVo;
import com.zchy.lease.web.admin.vo.graph.GraphVo;
import com.zchy.lease.web.admin.vo.room.RoomDetailVo;
import com.zchy.lease.web.admin.vo.room.RoomItemVo;
import com.zchy.lease.web.admin.vo.room.RoomQueryVo;
import com.zchy.lease.web.admin.vo.room.RoomSubmitVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liubo
 * @description 针对表【room_info(房间信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo>
        implements RoomInfoService {

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
    private AttrValueMapper attrValueMapper;
    @Autowired
    private PaymentTypeMapper paymentTypeMapper;
    @Autowired
    private LeaseTermMapper leaseTermMapper;

    @Autowired
    private RoomPaymentTypeService roomPaymentTypeService;
    @Autowired
    private RoomLeaseTermService roomLeaseTermService;
    @Autowired
    private RoomFacilityService roomFacilityService;
    @Autowired
    private RoomLabelService roomLabelService;
    @Autowired
    private RoomAttrValueService roomAttrValueService;
    @Autowired
    private GraphInfoService graphInfoService;

    @Override
    public void saveOrUpdateRoom(RoomSubmitVo roomSubmitVo) {
        Boolean isUpdate = false;
        if(roomSubmitVo.getId() != null){
            isUpdate = true;
        }
        super.saveOrUpdate(roomSubmitVo);
        if(isUpdate){
            removeRelatedData(roomSubmitVo.getId());
        }
        //updateRelatedData(roomSubmitVo);
    }

    @Override
    public IPage<RoomItemVo> getListByPage(long current, long size, RoomQueryVo queryVo) {
        Page<RoomItemVo> page = new Page<>(current, size);
        List<RoomItemVo> list = roomInfoMapper.getListByPage(page, queryVo);
        page.setRecords(list);
        return page;
    }

    @Override
    public RoomDetailVo getDetailById(Long id) {
        //1.查询RoomInfo
        RoomInfo roomInfo = roomInfoMapper.selectById(id);

        //2.查询所属公寓信息
        ApartmentInfo apartmentInfo = apartmentInfoMapper.selectById(roomInfo.getApartmentId());

        //3.查询graphInfoList
        List<GraphVo> graphVoList = graphInfoMapper.getByItemIdAndType(id, ItemType.ROOM);

        //4.查询attrValueList
        List<AttrValueVo> attrvalueVoList = attrValueMapper.getByRoomId(id);

        //5.查询facilityInfoList
        List<FacilityInfo> facilityInfoList = facilityInfoMapper.getByRoomId(id);

        //6.查询labelInfoList
        List<LabelInfo> labelInfoList = labelInfoMapper.getByRoomId(id);

        //7.查询paymentTypeList
        List<PaymentType> paymentTypeList = paymentTypeMapper.getByRoomId(id);

        //8.查询leaseTermList
        List<LeaseTerm> leaseTermList = leaseTermMapper.getByRoomId(id);


        RoomDetailVo adminRoomDetailVo = new RoomDetailVo();
        BeanUtils.copyProperties(roomInfo, adminRoomDetailVo);

        adminRoomDetailVo.setApartmentInfo(apartmentInfo);
        adminRoomDetailVo.setGraphVoList(graphVoList);
        adminRoomDetailVo.setAttrValueVoList(attrvalueVoList);
        adminRoomDetailVo.setFacilityInfoList(facilityInfoList);
        adminRoomDetailVo.setLabelInfoList(labelInfoList);
        adminRoomDetailVo.setPaymentTypeList(paymentTypeList);
        adminRoomDetailVo.setLeaseTermList(leaseTermList);

        return adminRoomDetailVo;
    }

    @Override
    public void removeRoomById(Long id) {
        //1.删除RoomInfo
        super.removeById(id);

        //2.删除graphInfoList
        LambdaQueryWrapper<GraphInfo> graphQueryWrapper = new LambdaQueryWrapper<>();
        graphQueryWrapper.eq(GraphInfo::getItemType, ItemType.ROOM);
        graphQueryWrapper.eq(GraphInfo::getItemId, id);
        graphInfoService.remove(graphQueryWrapper);

        //3.删除attrValueList
        LambdaQueryWrapper<RoomAttrValue> attrQueryWrapper = new LambdaQueryWrapper<>();
        attrQueryWrapper.eq(RoomAttrValue::getRoomId, id);
        roomAttrValueService.remove(attrQueryWrapper);

        //4.删除facilityInfoList
        LambdaQueryWrapper<RoomFacility> facilityQueryWrapper = new LambdaQueryWrapper<>();
        facilityQueryWrapper.eq(RoomFacility::getRoomId, id);
        roomFacilityService.remove(facilityQueryWrapper);

        //5.删除labelInfoList
        LambdaQueryWrapper<RoomLabel> labelQueryWrapper = new LambdaQueryWrapper<>();
        labelQueryWrapper.eq(RoomLabel::getRoomId, id);
        roomLabelService.remove(labelQueryWrapper);

        //6.删除paymentTypeList
        LambdaQueryWrapper<RoomPaymentType> paymentQueryWrapper = new LambdaQueryWrapper<>();
        paymentQueryWrapper.eq(RoomPaymentType::getRoomId, id);
        roomPaymentTypeService.remove(paymentQueryWrapper);

        //7.删除leaseTermList
        LambdaQueryWrapper<RoomLeaseTerm> termQueryWrapper = new LambdaQueryWrapper<>();
        termQueryWrapper.eq(RoomLeaseTerm::getRoomId, id);
        roomLeaseTermService.remove(termQueryWrapper);
    }

    private void removeRelatedData(Long id) {
        //删除支付方式列表
        LambdaQueryWrapper<RoomPaymentType> paymentTypeQueryWrapper = new LambdaQueryWrapper<>();
        paymentTypeQueryWrapper.eq(RoomPaymentType::getRoomId, id);
        roomPaymentTypeService.remove(paymentTypeQueryWrapper);
        //删除可选租期列表
        LambdaQueryWrapper<RoomLeaseTerm> leaseTermQueryWrapper = new LambdaQueryWrapper<>();
        leaseTermQueryWrapper.eq(RoomLeaseTerm::getRoomId, id);
        roomLeaseTermService.remove(leaseTermQueryWrapper);
        //1.删除公寓配套
        LambdaQueryWrapper<RoomFacility> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RoomFacility::getRoomId, id);
        roomFacilityService.remove(wrapper);
        //2.删除公寓标签
        LambdaQueryWrapper<RoomLabel> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.eq(RoomLabel::getRoomId, id);
        roomLabelService.remove(wrapper2);
        //删除公寓属性
        LambdaQueryWrapper<RoomAttrValue> wrapper3 = new LambdaQueryWrapper<>();
        wrapper3.eq(RoomAttrValue::getRoomId, id);
        roomAttrValueService.remove(wrapper3);
        //4.删除公寓图片
        LambdaQueryWrapper<GraphInfo> wrapper4 = new LambdaQueryWrapper<>();
        wrapper4.eq(GraphInfo::getItemId, id);
        wrapper4.eq(GraphInfo::getItemType, ItemType.ROOM);
        graphInfoService.remove(wrapper4);
    }
}




