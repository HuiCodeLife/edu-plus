package com.xuecheng.auth.config;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.auth.service.AuthService;
import com.xuecheng.ucenter.mapper.XcUserMapper;
import com.xuecheng.ucenter.model.dto.AuthParamsDto;
import com.xuecheng.ucenter.model.dto.XcUserExt;
import com.xuecheng.ucenter.model.po.XcUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author: Lin
 * @since: 2023-04-13
 */
@Slf4j
@Service
public class UserServiceImpl implements UserDetailsService {


    @Autowired
    ApplicationContext applicationContext;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthParamsDto authParamsDto = null;
        try {
            //将认证参数转为AuthParamsDto类型
            authParamsDto = JSON.parseObject(username, AuthParamsDto.class);
        } catch (Exception e) {
            log.info("认证请求不符合项目要求:{}",username);
            throw new RuntimeException("认证请求数据格式不对");
        }
        if (authParamsDto == null) {
            log.info("认证请求不符合项目要求:{}",username);
            throw new RuntimeException("认证请求数据格式不对");
        }
        //认证方法
        String authType = authParamsDto.getAuthType();

        AuthService authService = applicationContext.getBean(authType + "_authservice", AuthService.class);

        //开始认证
        XcUserExt user = authService.execute(authParamsDto);

        return getUserPrincipal(user);


    }

    /**
     * 查询用户信息
     */
    public UserDetails getUserPrincipal(XcUserExt user){
        //用户权限,如果不加报Cannot pass a null GrantedAuthority collection
        String[] authorities = {"p1"};
        String password = user.getPassword();
        //为了安全在令牌中不放密码
        user.setPassword(null);
        //将user对象转json
        String userString = JSON.toJSONString(user);
        //创建UserDetails对象
        return User.withUsername(userString).password(password ).authorities(authorities).build();
    }

}
