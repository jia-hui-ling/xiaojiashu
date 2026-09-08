package com.jiahuiling.framework.common.util;

import java.util.regex.Pattern;

/**
 * @Author 贾慧玲
 * @Date 2026/9/8 10:53
 * @Description 参数条件工具
 */
public final class ParamUtils {
    // ============================== 校验昵称 ==============================
    // 定义昵称长度范围
    private static final int NICK_NAME_MIN_LENGTH = 2;
    private static final int NICK_NAME_MAX_LENGTH = 24;

    // 定义特殊字符的正则表达式
    private static final String NICK_NAME_REGEX = "[!@#$%^&*(),.?\":{}|<>]";

    /**
     * 昵称校验
     *
     * @param nickName
     * @return
     */
    public static boolean checkNickName(String nickName) {
        if (nickName.length() < NICK_NAME_MIN_LENGTH || nickName.length() > NICK_NAME_MAX_LENGTH) {
            return false;
        }
        Pattern pattern = Pattern.compile(NICK_NAME_REGEX);
        return !pattern.matcher(nickName).find();
    }

    // ============================== 校验小哈书号 ==============================
    // 定义 ID 长度范围
    private static final int ID_MIN_LENGTH = 6;
    private static final int ID_MAX_LENGTH = 15;

    // 定义正则表达式
    private static final String ID_REGEX = "^[a-zA-Z0-9_]+$";

    /**
     * 小哈书 ID 校验
     *
     * @param xiaohashuId
     * @return
     */
    public static boolean checkXiaohashuId(String xiaohashuId) {
        // 检查长度
        if (xiaohashuId.length() < ID_MIN_LENGTH || xiaohashuId.length() > ID_MAX_LENGTH) {
            return false;
        }
        // 检查格式
        Pattern pattern = Pattern.compile(ID_REGEX);
        return pattern.matcher(xiaohashuId).matches();
    }

    /**
     * 字符串长度校验
     *
     * @param str
     * @param length
     * @return
     */
    public static boolean checkLength(String str, int length) {
        // 检查长度
        if (str.isEmpty() || str.length() > length) {
            return false;
        }
        return true;
    }
}
