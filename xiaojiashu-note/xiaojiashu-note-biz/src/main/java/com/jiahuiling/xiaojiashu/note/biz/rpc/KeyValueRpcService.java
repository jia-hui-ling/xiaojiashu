package com.jiahuiling.xiaojiashu.note.biz.rpc;

import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.kv.api.KeyValueFeignApi;
import com.jiahuiling.xiaojiashu.kv.dto.req.AddNoteContentReqDTO;
import com.jiahuiling.xiaojiashu.kv.dto.req.DeleteNoteContentReqDTO;
import com.jiahuiling.xiaojiashu.kv.dto.req.FindNoteContentReqDTO;
import com.jiahuiling.xiaojiashu.kv.dto.rsp.FindNoteContentRspDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class KeyValueRpcService {
    @Resource
    private KeyValueFeignApi keyValueFeignApi;


    /**
     * 保存笔记内容
     *
     * @param uuid
     * @param content
     * @return
     */
    public boolean saveNoteContent(String uuid, String content) {
        AddNoteContentReqDTO addNoteContentReqDTO = new AddNoteContentReqDTO();
        addNoteContentReqDTO.setUuid(uuid);
        addNoteContentReqDTO.setContent(content);

        Response<?> response = keyValueFeignApi.addNoteContent(addNoteContentReqDTO);
        if (Objects.isNull(response) || !response.isSuccess()) {
            return false;
        }
        return true;
    }

    /**
     * 删除笔记内容
     *
     * @param uuid
     * @return
     */

    public boolean deleteNoteContent(String uuid) {
        DeleteNoteContentReqDTO deleteNoteContentReqDTO = new DeleteNoteContentReqDTO();
        deleteNoteContentReqDTO.setUuid(uuid);
        Response<?> response = keyValueFeignApi.deleteNoteContent(deleteNoteContentReqDTO);
        if (Objects.isNull(response) || !response.isSuccess()) {
            return false;
        }
        return true;
    }

    /**
     * 查询笔记内容
     *
     * @param uuid
     * @return
     */
    public String findNoteContent(String uuid) {
        FindNoteContentReqDTO findNoteContentReqDTO = new FindNoteContentReqDTO();
        findNoteContentReqDTO.setUuid(uuid);

        Response<FindNoteContentRspDTO> response = keyValueFeignApi.findNoteContent(findNoteContentReqDTO);

        if (Objects.isNull(response) || !response.isSuccess()) {
            return null;
        }

        return response.getData().getContent();

    }
}
