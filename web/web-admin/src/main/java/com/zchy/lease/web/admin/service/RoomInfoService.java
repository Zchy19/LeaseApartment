package com.zchy.lease.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zchy.lease.model.entity.RoomInfo;
import com.zchy.lease.web.admin.vo.room.RoomDetailVo;
import com.zchy.lease.web.admin.vo.room.RoomItemVo;
import com.zchy.lease.web.admin.vo.room.RoomQueryVo;
import com.zchy.lease.web.admin.vo.room.RoomSubmitVo;

/**
* @author liubo
* @description 针对表【room_info(房间信息表)】的数据库操作Service
* @createDate 2024-11-03 15:48:00
*/
public interface RoomInfoService extends IService<RoomInfo> {


    void saveOrUpdateRoom(RoomSubmitVo roomSubmitVo);

    IPage<RoomItemVo> getListByPage(long current, long size, RoomQueryVo queryVo);

    RoomDetailVo getDetailById(Long id);

    void removeRoomById(Long id);
}
