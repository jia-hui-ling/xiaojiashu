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
     * 小贾书全局 ID 生成器 KEY
     */
    public static final String XIAOJIASHU_ID_GENERATOR_KEY = "xiaojiashu_id_generator";


    /**
     * 用户角色数据 KEY 前缀
     */
    private static final String USER_ROLES_KEY_PREFIX = "user:roles:";


    /**
     * 构建验证码 KEY
     *
     * @param phone
     * @return
     */
    public static String buildVerificationCodeKey(String phone) {
        return VERIFICATION_CODE_KEY_PREFIX + phone;
    }

    /**
     * 构建用户-角色 Key
     *
     * @param phone
     * @return
     */
    public static String buildUserRolesKey(String phone) {
        return USER_ROLES_KEY_PREFIX + phone;
    }
}
