package com.zchy.lease.web.admin.service;

import com.zchy.lease.web.admin.vo.login.CaptchaVo;
import com.zchy.lease.web.admin.vo.login.LoginVo;
import com.zchy.lease.web.admin.vo.system.user.SystemUserInfoVo;

public interface LoginService {

    CaptchaVo getCaptcha();

    String login(LoginVo loginVo);

    SystemUserInfoVo getinfo();
}
