package com.zchy.lease.web.admin.service;

import com.zchy.lease.model.entity.SystemUser;
import com.zchy.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.zchy.lease.web.admin.vo.system.user.SystemUserQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author liubo
* @description 针对表【system_user(员工信息表)】的数据库操作Service
* @createDate 2024-11-03 15:48:00
*/
public interface SystemUserService extends IService<SystemUser> {

    IPage<SystemUserItemVo> getAllList(long current, long size, SystemUserQueryVo queryVo);

    SystemUserItemVo getByUserId(Long id);
}
