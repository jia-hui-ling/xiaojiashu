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
    public static final String XIAOJIASHU_ID_GENERATOR_KEY = "xiaojiashu.id.generator";


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
     * 用户对应的角色集合 KEY
     * @param userId
     * @return
     */
    public static String buildUserRoleKey(Long userId) {
        return USER_ROLES_KEY_PREFIX + userId;
    }


    /**
     * 角色对应的权限集合 KEY 前缀
     */
    private static final String ROLE_PERMISSIONS_KEY_PREFIX = "role:permissions:";


    /**
     * 构建角色对应的权限集合 KEY
     * @param roleId
     * @return
     */
    public static String buildRolePermissionsKey(String roleKey) {
        return ROLE_PERMISSIONS_KEY_PREFIX + roleKey;
    }
}
