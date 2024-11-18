package com.zchy.lease.common.login;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @projectName: lease
 * @package: com.zchy.lease.common.login
 * @className: LoginUser
 * @author: ZCH
 * @description:
 * @date: 8/7/2024 3:55 PM
 * @version: 1.0
 */
@Data
@AllArgsConstructor
public class LoginUser {
    private Long id;
    private String username;
}
