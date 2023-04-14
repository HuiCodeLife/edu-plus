package com.xuecheng.auth.service;

import com.xuecheng.ucenter.model.po.XcUser;

/**
 * 微信认证接口
 * @author: Lin
 * @since: 2023-04-14
 */
public interface WxAuthService {
    /**
     * 微信认证
     * @param code 授权码
     * @return 结果
     */
    public XcUser wxAuth(String code);
}
