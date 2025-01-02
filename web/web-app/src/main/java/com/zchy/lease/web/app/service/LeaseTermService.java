package com.zchy.lease.web.app.service;

import com.zchy.lease.model.entity.LeaseTerm;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author liubo
* @description 针对表【lease_term(租期)】的数据库操作Service
* @createDate 2024-11-03 11:12:39
*/
public interface LeaseTermService extends IService<LeaseTerm> {
    List<LeaseTerm> listByRoomId(Long id);
}
