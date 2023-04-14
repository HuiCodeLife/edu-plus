package com.xuecheng.ucenter.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 验证码服务远程接口
 *
 * @author: Lin
 * @since: 2023-04-13
 */
@FeignClient(value = "checkcode", fallbackFactory = CheckCodeClientFactory.class)
public interface CheckCodeClient {


    @PostMapping(value = "/verify")
    public Boolean verify(@RequestParam("key") String key, @RequestParam("key") String code);
}
