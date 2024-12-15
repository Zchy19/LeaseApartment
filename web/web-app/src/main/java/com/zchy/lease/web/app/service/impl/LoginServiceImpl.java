package com.zchy.lease.web.app.service.impl;


import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zchy.lease.common.constant.RedisConstant;
import com.zchy.lease.common.exception.LeaseException;
import com.zchy.lease.common.login.LoginUser;
import com.zchy.lease.common.login.LoginUserHolder;
import com.zchy.lease.common.result.ResultCodeEnum;
import com.zchy.lease.common.utils.JwtUtil;
import com.zchy.lease.common.utils.VerifyCodeUtil;
import com.zchy.lease.model.entity.UserInfo;
import com.zchy.lease.model.enums.BaseStatus;
import com.zchy.lease.web.app.service.LoginService;
import com.zchy.lease.web.app.service.UserInfoService;
import com.zchy.lease.web.app.vo.user.LoginVo;
import com.zchy.lease.web.app.vo.user.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private Client client;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserInfoService userInfoService;

    @Override
    public void getCode(String phone) {
        if(!StringUtils.hasText(phone)) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_PHONE_EMPTY);
        }
        //查询Redis判断发送间隔
        String key = RedisConstant.APP_LOGIN_PREFIX + phone;
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey) {
            Long expire = redisTemplate.getExpire(key);
            if(RedisConstant.APP_LOGIN_CODE_TTL_SEC - expire < RedisConstant.APP_LOGIN_CODE_RESEND_TIME_SEC) {
                throw new LeaseException(ResultCodeEnum.APP_SEND_SMS_TOO_OFTEN);
            }
        }
        String VerifyCode = VerifyCodeUtil.getCode(6);
        //sendCode(phone, VerifyCode);
        redisTemplate.opsForValue().set(key, VerifyCode, RedisConstant.APP_LOGIN_CODE_TTL_SEC, TimeUnit.MINUTES);
    }

    @Override
    public String login(LoginVo loginVo) {
        if(loginVo.getPhone() == null) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_PHONE_EMPTY);
        } else if (loginVo.getCode() == null) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_EMPTY);
        }
        String key = RedisConstant.APP_LOGIN_PREFIX + loginVo.getPhone();
        boolean hasKey = redisTemplate.hasKey(key);
        if (!hasKey) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_EXPIRED);
        }
        String code = (String) redisTemplate.opsForValue().get(key);
        if (!loginVo.getCode().equals(code)) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_ERROR);
        }
        redisTemplate.delete(key);
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getPhone, loginVo.getPhone());
        UserInfo userInfo = userInfoService.getOne(queryWrapper);
        if(userInfo == null) {
            userInfo = new UserInfo();
            userInfo.setPhone(loginVo.getPhone());
            userInfo.setStatus(BaseStatus.ENABLE);
            userInfo.setNickname("用户-" + loginVo.getPhone().substring(6));
            userInfoService.save(userInfo);
        }
        if(userInfo.getStatus().equals(BaseStatus.DISABLE)) {
            throw new LeaseException(ResultCodeEnum.APP_ACCOUNT_DISABLED_ERROR);
        }
        UserInfo userInfo1 = userInfoService.getOne(queryWrapper);
        String token = JwtUtil.createToken(userInfo1.getId(), loginVo.getPhone());
        return token;
    }

    @Override
    public UserInfoVo getUserInfoById() {
        LoginUser loginUser = LoginUserHolder.getLoginUser();
        UserInfo userInfo = userInfoService.getById(loginUser.getId());
        return new UserInfoVo(userInfo.getNickname(), userInfo.getAvatarUrl());
    }

    private void sendCode(String phone, String code) {
        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        sendSmsRequest.setPhoneNumbers(phone);
        sendSmsRequest.setSignName("阿里云短信测试");
        sendSmsRequest.setTemplateCode("SMS_154950909");
        sendSmsRequest.setTemplateParam("{\"code\":\"" + code + "\"}");
        try {
            client.sendSms(sendSmsRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
