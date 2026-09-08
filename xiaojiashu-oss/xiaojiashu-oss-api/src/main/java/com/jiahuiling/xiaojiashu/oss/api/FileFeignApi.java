package com.jiahuiling.xiaojiashu.oss.api;

import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.oss.config.FeignFormConfig;
import com.jiahuiling.xiaojiashu.oss.constant.ApiConstants;
import org.bouncycastle.asn1.its.IValue;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = ApiConstants.SERVICE_NAME,configuration = FeignFormConfig.class)
public interface FileFeignApi {
    String PREFIX = "/file";

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping(value = PREFIX + "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Response<?> uploadFile(@RequestPart(value = "file") MultipartFile file);
}
