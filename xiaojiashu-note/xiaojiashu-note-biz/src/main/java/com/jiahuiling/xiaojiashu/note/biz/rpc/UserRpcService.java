package com.jiahuiling.xiaojiashu.note.biz.rpc;

import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.user.api.UserFeignApi;
import com.jiahuiling.xiaojiashu.user.dto.req.FindUserByIdReqDTO;
import com.jiahuiling.xiaojiashu.user.dto.resp.FindUserByIdRspDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @description: 用户服务
 **/
@Component
public class UserRpcService {
    @Resource
    private UserFeignApi userFeignApi;

    /**
     * 查询用户信息
     *
     * @param userId
     * @return
     */
    public FindUserByIdRspDTO findById(Long userId) {
        FindUserByIdReqDTO findUserByIdReqDTO = new FindUserByIdReqDTO();
        findUserByIdReqDTO.setId(userId);

        Response<FindUserByIdRspDTO> response = userFeignApi.findById(findUserByIdReqDTO);

        if (Objects.isNull(response) || !response.isSuccess()) {
            return null;
        }

        return response.getData();
    }
}
