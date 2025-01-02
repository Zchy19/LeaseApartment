package com.zchy.lease.web.app.mapper;

import com.zchy.lease.model.entity.LeaseTerm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【lease_term(租期)】的数据库操作Mapper
* @createDate 2024-11-03 11:12:39
* @Entity com.zchy.lease.model.entity.LeaseTerm
*/
public interface LeaseTermMapper extends BaseMapper<LeaseTerm> {

    List<LeaseTerm> selectListByRoomId(Long id);
}




