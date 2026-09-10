package com.jiahuiling.xiaojiashu.kv.api;

import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.kv.constant.ApiConstants;
import com.jiahuiling.xiaojiashu.kv.dto.req.AddNoteContentReqDTO;
import com.jiahuiling.xiaojiashu.kv.dto.req.DeleteNoteContentReqDTO;
import com.jiahuiling.xiaojiashu.kv.dto.req.FindNoteContentReqDTO;
import com.jiahuiling.xiaojiashu.kv.dto.rsp.FindNoteContentRspDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = ApiConstants.SERVICE_NAME)
public interface KeyValueFeignApi {
    String PREFIX = "/kv";

    @PostMapping(value = PREFIX + "/note/content/add")
    Response<?> addNoteContent(@RequestBody AddNoteContentReqDTO addNoteContentReqDTO);

    @PostMapping(value = PREFIX + "/note/content/find")
    Response<FindNoteContentRspDTO> findNoteContent(@RequestBody FindNoteContentReqDTO findNoteContentReqDTO);

    @PostMapping(value = PREFIX + "/note/content/delete")
    Response<?> deleteNoteContent(@RequestBody DeleteNoteContentReqDTO deleteNoteContentReqDTO);

}
