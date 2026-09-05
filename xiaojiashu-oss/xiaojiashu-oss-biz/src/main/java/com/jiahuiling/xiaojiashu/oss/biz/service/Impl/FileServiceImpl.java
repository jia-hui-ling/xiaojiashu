package com.jiahuiling.xiaojiashu.oss.biz.service.Impl;

import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.oss.biz.service.FileService;
import com.jiahuiling.xiaojiashu.oss.biz.strategy.FileStrategy;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author 贾慧玲
 * @Date 2026/9/5 19:01
 * @Description TODO
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Resource
    private FileStrategy fileStrategy;

    @Override
    public Response<String> uploadFile(MultipartFile file) {
        // 上传文件到
        String url = fileStrategy.uploadFile(file, "xiaojiashu");

        return Response.success(url);
    }
}
