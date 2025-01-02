package com.zchy.lease.web.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zchy.lease.model.entity.FeeKey;
import com.zchy.lease.web.admin.vo.fee.FeeKeyVo;

import java.util.List;

/**
* @author liubo
* @description 针对表【fee_key(杂项费用名称表)】的数据库操作Mapper
* @createDate 2024-11-03 15:48:00
* @Entity com.atguigu.lease.model.FeeKey
*/
public interface FeeKeyMapper extends BaseMapper<FeeKey> {

    List<FeeKeyVo> getAllList();
}




