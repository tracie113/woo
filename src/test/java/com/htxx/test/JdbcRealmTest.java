package com.htxx.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class JdbcRealmTest {
    DruidDataSource druidDataSource = new DruidDataSource();
    {
        druidDataSource.setUrl(" jdbc:mysql://localhost:3306/woo");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("aisino2017");
    }

    @Test
    public void testAuthentication(){
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(druidDataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);

        String sql = "select password from userinfo where username = ? ";
        jdbcRealm.setAuthenticationQuery(sql);

        String rolesql = "select name from roleinfo where username = ?  ";
        jdbcRealm.setUserRolesQuery(rolesql);

        String permisssql = " select name from authority where rolename = ? ";
        jdbcRealm.setPermissionsQuery(permisssql);


        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("yanxin","1");
        subject.login(token);
        System.out.println("是否认证："+subject.isAuthenticated());
        subject.checkRole("admin");
//        subject.checkPermission("user:delete");
        subject.logout();
        System.out.println("退出登陆后的认证："+subject.isAuthenticated());
    }
}
