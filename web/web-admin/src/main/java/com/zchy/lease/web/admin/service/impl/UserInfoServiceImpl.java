package com.zchy.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zchy.lease.model.entity.UserInfo;
import com.zchy.lease.web.admin.service.UserInfoService;
import com.zchy.lease.web.admin.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author liubo
* @description 针对表【user_info(用户信息表)】的数据库操作Service实现
* @createDate 2024-11-03 15:48:00
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

}




