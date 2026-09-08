package com.jiahuiling.xiaojiashu.user.biz.rpc;

import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.oss.api.FileFeignApi;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author 贾慧玲
 * @Date 2026/9/8 19:07
 * @Description TODO
 */
@Component
public class OssRpcService {
    @Resource
    private FileFeignApi fileFeignApi;

    public String uploadFile(MultipartFile file) {
        // 调用对象存储服务上传文件
        Response<?> response = fileFeignApi.uploadFile(file);
        if (!response.isSuccess()) {
            return null;
        }
        // 返回图片访问链接
        return (String) response.getData();

    }
}
