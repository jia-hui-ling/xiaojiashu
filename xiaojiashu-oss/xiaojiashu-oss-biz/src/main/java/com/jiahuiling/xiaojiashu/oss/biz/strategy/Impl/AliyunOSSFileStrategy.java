package com.jiahuiling.xiaojiashu.oss.biz.strategy.Impl;

import com.jiahuiling.xiaojiashu.oss.biz.strategy.FileStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author 贾慧玲
 * @Date 2026/9/5 18:54
 * @Description TODO
 */
@Slf4j
public class AliyunOSSFileStrategy implements FileStrategy {

    @Override
    public String uploadFile(MultipartFile file, String bucketName) {
        log.info("## 上传文件至阿里云 OSS ...");
        return null;
    }
}
