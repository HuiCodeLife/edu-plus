package com.xuecheng.auth.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.auth.service.AuthService;
import com.xuecheng.auth.service.WxAuthService;
import com.xuecheng.ucenter.mapper.XcUserMapper;
import com.xuecheng.ucenter.mapper.XcUserRoleMapper;
import com.xuecheng.ucenter.model.dto.AuthParamsDto;
import com.xuecheng.ucenter.model.dto.XcUserExt;
import com.xuecheng.ucenter.model.po.XcUser;
import com.xuecheng.ucenter.model.po.XcUserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * @author: Lin
 * @since: 2023-04-14
 */
@Slf4j
@Service("wx_authservice")
public class WxAuthServiceImpl implements AuthService, WxAuthService {
    @Autowired
    XcUserMapper xcUserMapper;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    XcUserRoleMapper xcUserRoleMapper;


    @Autowired
    WxAuthServiceImpl currentProxy;
    @Value("${weixin.appid}")
    String appid;
    @Value("${weixin.secret}")
    String secret;



    @Override
    public XcUserExt execute(AuthParamsDto authParamsDto) {
        //账号
        String username = authParamsDto.getUsername();
        XcUser user = xcUserMapper.selectOne(new LambdaQueryWrapper<XcUser>().eq(XcUser::getUsername, username));
        if(user==null){
            //返回空表示用户不存在
            throw new RuntimeException("账号不存在");
        }
        XcUserExt xcUserExt = new XcUserExt();
        BeanUtils.copyProperties(user,xcUserExt);
        return xcUserExt;

    }

    @Override
    public XcUser wxAuth(String code) {

        //收到code调用微信接口申请access_token
        Map<String, String> accessTokenMap = getAccess_token(code);
        if(accessTokenMap==null){
            return null;
        }
        log.info("accessTokenMap:{}",accessTokenMap);
        String openid = accessTokenMap.get("openid");
        String accessToken = accessTokenMap.get("access_token");
        //拿access_token查询用户信息
        Map<String, String> userinfo = getUserinfo(accessToken, openid);
        if(userinfo==null){
            return null;
        }
        //添加用户到数据库 解决事务问题
        return currentProxy.addWxUser(userinfo);
    }

    /**
     * 申请访问令牌
     * @param code 授权码
     * @return
     */
    private Map<String,String> getAccess_token(String code) {
        String wxUrlTemplate = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
        //请求微信地址
        String wxUrl = String.format(wxUrlTemplate, appid, secret, code);

        log.info("调用微信接口申请access_token, url:{}", wxUrl);

        ResponseEntity<String> exchange = restTemplate.exchange(wxUrl, HttpMethod.POST, null, String.class);
        String result = exchange.getBody();

        log.info("调用微信接口申请access_token: 返回值:{}", result);
        Map<String,String> resultMap = JSON.parseObject(result, Map.class);
        return resultMap;

    }

    private Map<String,String> getUserinfo(String access_token,String openid) {

        String wxUrlTemplate = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";
        //请求微信地址
        String wxUrl = String.format(wxUrlTemplate, access_token,openid);
        log.info("调用微信接口申请access_token, url:{}", wxUrl);
        ResponseEntity<String> exchange = restTemplate.exchange(wxUrl, HttpMethod.POST, null, String.class);
        //防止乱码进行转码
        String result = new     String(exchange.getBody().getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
        log.info("调用微信接口申请access_token: 返回值:{}", result);
        Map<String,String> resultMap = JSON.parseObject(result, Map.class);
        return resultMap;
    }

    @Transactional(rollbackFor = Exception.class)
    public XcUser addWxUser(Map<String,String> userInfoMap){
        String unionid = userInfoMap.get("unionid");
        //根据unionid查询数据库
        XcUser xcUser = xcUserMapper.selectOne(new LambdaQueryWrapper<XcUser>().eq(XcUser::getWxUnionid, unionid));
        if(xcUser!=null){
            return xcUser;
        }
        String userId = UUID.randomUUID().toString();
        xcUser = new XcUser();
        xcUser.setId(userId);
        xcUser.setWxUnionid(unionid);
        //记录从微信得到的昵称
        xcUser.setNickname(userInfoMap.get("nickname").toString());
        xcUser.setUserpic(userInfoMap.get("headimgurl").toString());
        xcUser.setName(userInfoMap.get("nickname").toString());
        xcUser.setUsername(unionid);
        xcUser.setPassword(unionid);
        //学生类型
        xcUser.setUtype("101001");
        //用户状态
        xcUser.setStatus("1");
        xcUser.setCreateTime(LocalDateTime.now());
        xcUserMapper.insert(xcUser);
        XcUserRole xcUserRole = new XcUserRole();
        xcUserRole.setId(UUID.randomUUID().toString());
        xcUserRole.setUserId(userId);
        //学生角色
        xcUserRole.setRoleId("17");
        xcUserRoleMapper.insert(xcUserRole);
        return xcUser;
    }


}
