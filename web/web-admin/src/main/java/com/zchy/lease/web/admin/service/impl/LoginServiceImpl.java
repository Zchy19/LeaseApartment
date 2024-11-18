package com.zchy.lease.web.admin.service.impl;

import com.wf.captcha.SpecCaptcha;
import com.zchy.lease.common.constant.RedisConstant;
import com.zchy.lease.common.exception.LeaseException;
import com.zchy.lease.common.login.LoginUser;
import com.zchy.lease.common.login.LoginUserHolder;
import com.zchy.lease.common.result.ResultCodeEnum;
import com.zchy.lease.common.utils.JwtUtil;
import com.zchy.lease.model.entity.SystemUser;
import com.zchy.lease.model.enums.BaseStatus;
import com.zchy.lease.web.admin.mapper.SystemUserMapper;
import com.zchy.lease.web.admin.service.LoginService;
import com.zchy.lease.web.admin.vo.login.CaptchaVo;
import com.zchy.lease.web.admin.vo.login.LoginVo;
import com.zchy.lease.web.admin.vo.system.user.SystemUserInfoVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private SystemUserMapper systemUserMapper;

    @Override
    public CaptchaVo getCaptcha() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        specCaptcha.setCharType(SpecCaptcha.TYPE_DEFAULT);

        String code = specCaptcha.text().toLowerCase();
        String key = RedisConstant.ADMIN_LOGIN_PREFIX + UUID.randomUUID().toString();
        String image = specCaptcha.toBase64();
        redisTemplate.opsForValue().set(key, code, RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC, TimeUnit.MINUTES);

        return new CaptchaVo(image, key);
    }

    @Override
    public String login(LoginVo loginVo) {
        //验证码校验
        checkCAPTCHA(loginVo.getCaptchaKey(), loginVo.getCaptchaCode());
        //密码校验
        Long id = checkPassword(loginVo.getUsername(), loginVo.getPassword());
        //生成JWT
        return JwtUtil.createToken(id, loginVo.getUsername());
    }

    @Override
    public SystemUserInfoVo getinfo() {
        LoginUser loginUser = LoginUserHolder.getLoginUser();
        SystemUser systemUser = systemUserMapper.selectById(loginUser.getId());
        SystemUserInfoVo systemUserInfoVo = new SystemUserInfoVo();
        systemUserInfoVo.setName(systemUser.getName());
        systemUserInfoVo.setAvatarUrl(systemUser.getAvatarUrl());
        return systemUserInfoVo;
    }

    private Long checkPassword(String username, String password) {
        SystemUser systemUser = systemUserMapper.getPasswordByUserName(username);
        if (systemUser == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);
        }
        if(systemUser.getStatus() == BaseStatus.DISABLE){
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_DISABLED_ERROR);
        }

        if (!systemUser.getPassword().equals(DigestUtils.md5Hex(password))) {
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_ERROR);
        }
        return systemUser.getId();
    }

    private void checkCAPTCHA(String captchaKey, String captchaCode) {
        if (captchaCode == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_NOT_FOUND);
        }
        String code = redisTemplate.opsForValue().get(captchaKey);
        if (code == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_EXPIRED);
        }
        if (!code.equals(captchaCode.toLowerCase())) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);
        }
    }
}
