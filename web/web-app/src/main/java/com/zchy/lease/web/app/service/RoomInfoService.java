package com.zchy.lease.web.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zchy.lease.model.entity.RoomInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zchy.lease.web.app.vo.room.RoomDetailVo;
import com.zchy.lease.web.app.vo.room.RoomItemVo;
import com.zchy.lease.web.app.vo.room.RoomQueryVo;

/**
* @author liubo
* @description 针对表【room_info(房间信息表)】的数据库操作Service
* @createDate 2023-07-26 11:12:39
*/
public interface RoomInfoService extends IService<RoomInfo> {
    IPage<RoomItemVo> getAllList(long current, long size, RoomQueryVo queryVo);

    RoomDetailVo getDetaiById(Long id);
}
