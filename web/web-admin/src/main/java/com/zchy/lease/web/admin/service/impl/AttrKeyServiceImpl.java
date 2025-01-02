package com.zchy.lease.web.admin.service.impl;

import com.zchy.lease.model.entity.AttrKey;
import com.zchy.lease.web.admin.mapper.AttrKeyMapper;
import com.zchy.lease.web.admin.service.AttrKeyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zchy.lease.web.admin.vo.attr.AttrKeyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【attr_key(房间基本属性表)】的数据库操作Service实现
* @createDate 2024-11-03 15:48:00
*/
@Service
public class AttrKeyServiceImpl extends ServiceImpl<AttrKeyMapper, AttrKey>
    implements AttrKeyService{

    @Autowired
    private AttrKeyMapper attrKeyMapper;

    @Override
    public List<AttrKeyVo> getAllList() {
        return attrKeyMapper.getAllList();
    }
}




