package com.zchy.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zchy.lease.model.entity.*;
import com.zchy.lease.model.enums.ItemType;
import com.zchy.lease.web.admin.mapper.RoomInfoMapper;
import com.zchy.lease.web.admin.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zchy.lease.web.admin.vo.room.RoomSubmitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * @description 针对表【room_info(房间信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo>
        implements RoomInfoService {

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




