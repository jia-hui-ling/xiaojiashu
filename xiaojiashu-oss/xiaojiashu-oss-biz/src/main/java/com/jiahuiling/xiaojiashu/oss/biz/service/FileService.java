package com.jiahuiling.xiaojiashu.oss.biz.service;

import com.jiahuiling.framework.common.response.Response;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author 贾慧玲
 * @Date 2026/9/5 19:00
 * @Description TODO
 */
public interface FileService {
    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    Response<String> uploadFile(MultipartFile file);
}
