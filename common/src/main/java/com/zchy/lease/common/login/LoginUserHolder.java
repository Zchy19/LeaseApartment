package com.zchy.lease.common.login;

/**
 * @projectName: lease
 * @package: com.zchy.lease.common.login
 * @className: LoginUserHolder
 * @author: ZCH
 * @description:
 * @date: 8/7/2024 3:54 PM
 * @version: 1.0
 */
public class LoginUserHolder {
    public static final ThreadLocal<LoginUser> threadLocal = new ThreadLocal<>();
    public static void setLoginUser(LoginUser loginUser) {
        threadLocal.set(loginUser);
    }
    public static LoginUser getLoginUser() {
        return threadLocal.get();
    }
    public static void removeLoginUser() {
        threadLocal.remove();
    }
}
