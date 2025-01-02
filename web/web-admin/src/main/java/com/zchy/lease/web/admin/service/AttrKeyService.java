package com.zchy.lease.web.admin.service;

import com.zchy.lease.model.entity.AttrKey;
import com.zchy.lease.web.admin.vo.attr.AttrKeyVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author liubo
* @description 针对表【attr_key(房间基本属性表)】的数据库操作Service
* @createDate 2024-11-03 15:48:00
*/
public interface AttrKeyService extends IService<AttrKey> {

    List<AttrKeyVo> getAllList();
}
