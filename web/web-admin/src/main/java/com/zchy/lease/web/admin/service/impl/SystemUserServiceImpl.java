package com.zchy.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zchy.lease.model.entity.SystemPost;
import com.zchy.lease.model.entity.SystemUser;
import com.zchy.lease.web.admin.mapper.SystemUserMapper;
import com.zchy.lease.web.admin.service.SystemPostService;
import com.zchy.lease.web.admin.service.SystemUserService;
import com.zchy.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.zchy.lease.web.admin.vo.system.user.SystemUserQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liubo
 * @description 针对表【system_user(员工信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser>
        implements SystemUserService {

    @Autowired
    private SystemUserMapper systemUserMapper;

    @Autowired
    private SystemPostService systemPostService;

    @Override
    public IPage<SystemUserItemVo> getAllList(long current, long size, SystemUserQueryVo queryVo) {
        Page<SystemUser> systemUserPage = new Page<>(current, size);
        List<SystemUserItemVo> list = systemUserMapper.getAllList(systemUserPage, queryVo);
        IPage<SystemUserItemVo> systemUserItemVoIPage = new Page<>();
        systemUserItemVoIPage.setRecords(list);
        return systemUserItemVoIPage;
    }

    @Override
    public SystemUserItemVo getByUserId(Long id) {
        SystemUser systemUser = getById(id);
        SystemPost systemPost = systemPostService.getById(systemUser.getPostId());
        SystemUserItemVo systemUserItemVo = new SystemUserItemVo();
        BeanUtils.copyProperties(systemUser, systemUserItemVo);
        systemUserItemVo.setPostName(systemPost.getName());
        return systemUserItemVo;
    }


}




