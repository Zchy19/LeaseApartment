package com.zchy.lease.web.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zchy.lease.model.entity.RoomInfo;
import com.zchy.lease.web.admin.vo.room.RoomItemVo;
import com.zchy.lease.web.admin.vo.room.RoomQueryVo;

import java.util.List;

/**
* @author liubo
* @description 针对表【room_info(房间信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.atguigu.lease.model.RoomInfo
*/
public interface RoomInfoMapper extends BaseMapper<RoomInfo> {

    List<RoomItemVo> getListByPage(Page<RoomItemVo> page, RoomQueryVo queryVo);
}




