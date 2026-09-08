package com.jiahuiling.xiaojiashu.auth.constant;

/**
 * @Author 贾慧玲
 * @Date 2026/9/4 12:36
 * @Description TODO
 */
public class RedisKeyConstants {
    /**
     * 验证码 KEY 前缀
     */
    public static final String VERIFICATION_CODE_KEY_PREFIX = "verification_code:";


    /**
     * 构建验证码 KEY
     *
     * @param phone
     * @return
     */
    public static String buildVerificationCodeKey(String phone) {
        return VERIFICATION_CODE_KEY_PREFIX + phone;
    }
}
