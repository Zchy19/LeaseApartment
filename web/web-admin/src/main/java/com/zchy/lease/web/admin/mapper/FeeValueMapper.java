package com.zchy.lease.web.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zchy.lease.model.entity.FeeValue;
import com.zchy.lease.web.admin.vo.fee.FeeValueVo;

import java.util.List;

/**
* @author liubo
* @description 针对表【fee_value(杂项费用值表)】的数据库操作Mapper
* @createDate 2024-11-03 15:48:00
* @Entity com.atguigu.lease.model.FeeValue
*/
public interface FeeValueMapper extends BaseMapper<FeeValue> {

    List<FeeValueVo> getByApartmentId(Long id);
}




