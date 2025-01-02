package com.zchy.lease.web.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zchy.lease.model.entity.RoomInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zchy.lease.web.app.vo.room.RoomDetailVo;
import com.zchy.lease.web.app.vo.room.RoomItemVo;
import com.zchy.lease.web.app.vo.room.RoomQueryVo;

/**
* @author liubo
* @description 针对表【room_info(房间信息表)】的数据库操作Service
* @createDate 2024-11-03 11:12:39
*/
public interface RoomInfoService extends IService<RoomInfo> {
    IPage<RoomItemVo> pageItem(Page<RoomItemVo> page, RoomQueryVo queryVo);

    RoomDetailVo getDetailById(Long id);

    IPage<RoomItemVo> pageItemByApartmentId(Page<RoomItemVo> page, Long id);
}
