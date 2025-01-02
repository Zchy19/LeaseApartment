package com.zchy.lease.web.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zchy.lease.model.entity.GraphInfo;
import com.zchy.lease.model.enums.ItemType;
import com.zchy.lease.web.admin.vo.graph.GraphVo;

import java.util.List;

/**
* @author liubo
* @description 针对表【graph_info(图片信息表)】的数据库操作Mapper
* @createDate 2024-11-03 15:48:00
* @Entity com.atguigu.lease.model.GraphInfo
*/
public interface GraphInfoMapper extends BaseMapper<GraphInfo> {

    List<GraphVo> getByItemIdAndType(Long id, ItemType itemType);
}




