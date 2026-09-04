package com.jiahuiling.xiaojiashu.auth.service.Impl;

import cn.hutool.core.util.RandomUtil;
import com.jiahuiling.framework.common.exception.BizException;
import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.auth.constant.RedisKeyConstants;
import com.jiahuiling.xiaojiashu.auth.enums.ResponseCodeEnum;
import com.jiahuiling.xiaojiashu.auth.model.VO.veriticationcode.SendVeriticationCodeReqVO;
import com.jiahuiling.xiaojiashu.auth.service.VerificationCodeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author 贾慧玲
 * @Date 2026/9/4 12:42
 * @Description TODO
 */
@Service
@Slf4j
public class VerificationCodeServiceImpl implements VerificationCodeService {
    @Resource
    RedisTemplate redisTemplate;
    @Override
    public Response<?> send(SendVeriticationCodeReqVO sendVerificationCodeReqVO) {
        String phone = sendVerificationCodeReqVO.getPhone();
        String key = RedisKeyConstants.buildVerificationCodeKey(phone);
        Boolean isSent = redisTemplate.hasKey(key);
        if (isSent) {
            throw new BizException(ResponseCodeEnum.VERIFICATION_CODE_SEND_FREQUENTLY);

        }

        String verificationCode = RandomUtil.randomNumbers(6);

        log.info("==> 手机号: {}, 已发送验证码：【{}】", phone, verificationCode);

        redisTemplate.opsForValue().set(key,verificationCode,3, TimeUnit.MINUTES);

        return Response.success();
    }


}
