package com.zchy.lease.web.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zchy.lease.model.entity.AttrKey;
import com.zchy.lease.web.admin.vo.attr.AttrKeyVo;

import java.util.List;

/**
* @author liubo
* @description 针对表【attr_key(房间基本属性表)】的数据库操作Mapper
* @createDate 2024-11-03 15:48:00
* @Entity com.atguigu.lease.model.AttrKey
*/
public interface AttrKeyMapper extends BaseMapper<AttrKey> {

    List<AttrKeyVo> getAllList();
}




