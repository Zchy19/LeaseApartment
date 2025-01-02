package com.zchy.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zchy.lease.model.entity.AttrKey;
import com.zchy.lease.web.app.service.AttrKeyService;
import com.zchy.lease.web.app.mapper.AttrKeyMapper;
import org.springframework.stereotype.Service;

/**
* @author liubo
* @description 针对表【attr_key(房间基本属性表)】的数据库操作Service实现
* @createDate 2024-11-03 11:12:39
*/
@Service
public class AttrKeyServiceImpl extends ServiceImpl<AttrKeyMapper, AttrKey>
    implements AttrKeyService{

}




