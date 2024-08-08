package com.zchy.lease.common.utils;

import java.util.Random;

/**
 * @projectName: lease
 * @package: com.zchy.lease.common.utils
 * @className: VerifyCodeUtil
 * @author: ZCH
 * @description:
 * @date: 8/8/2024 8:25 PM
 * @version: 1.0
 */
public class VerifyCodeUtil {
    public static String getCode(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }
}
