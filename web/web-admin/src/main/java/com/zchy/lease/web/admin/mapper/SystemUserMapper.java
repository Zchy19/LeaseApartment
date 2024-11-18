package com.zchy.lease.web.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zchy.lease.model.entity.SystemUser;
import com.zchy.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.zchy.lease.web.admin.vo.system.user.SystemUserQueryVo;

import java.util.List;

/**
* @author liubo
* @description 针对表【system_user(员工信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.atguigu.lease.model.SystemUser
*/
public interface SystemUserMapper extends BaseMapper<SystemUser> {

    List<SystemUserItemVo> getAllList(Page<SystemUser> systemUserPage, SystemUserQueryVo queryVo);

    SystemUser getPasswordByUserName(String username);
}




