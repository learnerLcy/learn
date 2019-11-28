package com.learn.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName:ShiroConfig
 * @Description: shrio的配置信息
 * @Author:lvchunyang
 * @Date:16:16
 **/
@Configuration
public class ShiroConfig {
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher =
                new HashedCredentialsMatcher();
        //指定加密方式为MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        credentialsMatcher.setHashIterations(1024);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }
    /**
     * 注入 securityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(
            HashedCredentialsMatcher hashedCredentialsMatcher) {

        DefaultWebSecurityManager securityManager =
                new DefaultWebSecurityManager();
        // 关联realm.
        securityManager.setRealm(userRealm(hashedCredentialsMatcher));
        return securityManager;
    }

    @Bean("userRealm")
    public UserRealm userRealm(@Qualifier("hashedCredentialsMatcher")HashedCredentialsMatcher matcher) {

        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(matcher);
        return userRealm;
    }

    @Bean
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager")
                                                     DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置 SecurityManager
        bean.setSecurityManager(securityManager);
        // 设置登录成功跳转Url
        bean.setSuccessUrl("/main");
        // 设置登录跳转Url
        bean.setLoginUrl("/index/toLogin");
        // 设置未授权提示Url
        bean.setUnauthorizedUrl("/error/unAuth");

        /**
         * anon：匿名用户可访问
         * authc：认证用户可访问
         * user：使用rememberMe可访问
         * perms：对应权限可访问
         * role：对应角色权限可访问
         **/
        Map<String, String> filterMap = new LinkedHashMap<>();
        /* 登陆登出放行 */
        filterMap.put("/index/login","anon");
        filterMap.put("/index/logout", "logout");
        //filterMap.put("/user/index","authc");
        //filterMap.put("/vip/index","roles[vip]");

        /* 不能直接写/static/**,会被拦截 */
        filterMap.put("/common/**","anon");
        filterMap.put("/layui/**","anon");
        filterMap.put("/jquery-3.3.1/**","anon");
        filterMap.put("/JsSequenceDiagrams/**","anon");
        /* 其他需要登陆认证 */
        filterMap.put("/**","authc");

        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }
}
