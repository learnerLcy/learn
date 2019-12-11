package com.learn.config.shiro;

import com.learn.Constants.Constants;
import com.learn.PoJo.customCenter.User;
import com.learn.PoJo.customCenter.UserRole;
import com.learn.service.customCenter.UserRoleService;
import com.learn.service.customCenter.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName:UserRealm
 * @Description: 自定义shiro的realm
 * @Author:lvchunyang
 * @Date:15:54
 **/
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    //授权使用
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        if(user != null) {
            String username = (String) principals.getPrimaryPrincipal();
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            //在数据库中查询用户拥有的角色/权限
            user = userService.select(user).get(0);

            UserRole userRole = new UserRole();
            userRole.setUserId(user.getUserId());

            authorizationInfo.setRoles(userRoleService.getUserRoles(userRole));
            //authorizationInfo.setStringPermissions(userService.findPermissions(username));
            return authorizationInfo;
        }
        return null;
    }

    //验证使用
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = new User();
        user.setLoginname(username);
        List<User> userList = userService.select(user);
        if(userList.size()==0){
            throw new UnknownAccountException(); //没找到账号
        }
        user = userList.get(0);
       /* if(Boolean.TRUE.equals(user.getLocked())){
            throw new LockedAccountException(); //账号被锁定
        }*/
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getLoginname(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                getName());
        SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_LOGINUSER,user);
        return authenticationInfo;
    }


    // 模拟Shiro用户加密，假设用户密码为123456
    public static void main(String[] args){
        // 用户名
        String username = "2";
        // 用户密码
        String password = "2";
        // 用户密码
        String salt = "2";
        // 加密方式
        String hashAlgorithName = "MD5";
        // 加密次数
        int hashIterations = 1;
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        Object obj = new SimpleHash(hashAlgorithName, password,
                credentialsSalt, hashIterations);
        System.out.println(obj);
    }
}
