package com.htxx.config;

import com.htxx.entity.User;
import com.htxx.service.PermissionServiceI;
import com.htxx.service.RoleServiceI;
import com.htxx.service.UserServiceI;
import com.htxx.service.impl.UserServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {


    @Autowired
    UserServiceI userServiceI;
    @Autowired
    RoleServiceI roleServiceI;
    @Autowired
    PermissionServiceI permissionServiceI;
    {
        super.setName("yx");
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        Set<String> roles = getRolesByUsername(username);
        Set<String> permission = getPermissinByUsername(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if(null != permission){
            simpleAuthorizationInfo.addStringPermissions(permission);
        }
        if( null != roles){
            simpleAuthorizationInfo.addRoles(roles);
        }
        return simpleAuthorizationInfo;
    }

    private Set<String> getPermissinByUsername(String username) {
        Set<String> permission = permissionServiceI.getPermissionByUsername(username);
        return permission;
    }

    private Set<String> getRolesByUsername(String username) {
        Set<String> roles =roleServiceI.getRoleByUsername(username);
        return roles;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //1.从主体传过来的认证信息中获得用户名
        String username = (String) authenticationToken.getPrincipal();
        //2.通过用户名到数据库中获取凭证
        String password = userServiceI.selectPasswordByUsername(username);
        if (password == null ){
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,password,"yx");

        return simpleAuthenticationInfo;
    }

    /**
     * 从数据库中取得数据
     * @param username
     * @return
     */
    private String getPasswordByUsername(String username) {
        return userServiceI.selectPasswordByUsername(username);
    }
}
