package com.zchy.lease.web.admin.service;

import com.zchy.lease.model.entity.RoomInfo;
import com.zchy.lease.web.admin.vo.room.RoomDetailVo;
import com.zchy.lease.web.admin.vo.room.RoomItemVo;
import com.zchy.lease.web.admin.vo.room.RoomQueryVo;
import com.zchy.lease.web.admin.vo.room.RoomSubmitVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author liubo
* @description 针对表【room_info(房间信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface RoomInfoService extends IService<RoomInfo> {


    void saveOrUpdateRoom(RoomSubmitVo roomSubmitVo);
}
