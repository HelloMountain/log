package tqs.log.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tqs.log.dao.UserMapper;

import java.util.HashSet;
import java.util.Set;

@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限验证");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String username = (String)SecurityUtils.getSubject().getPrincipal();
        String role = userMapper.getRoleByName(username);
        Set<String> set = new HashSet<>();
        set.add(role);
        simpleAuthorizationInfo.addRoles(set);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("身份验证");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String password = userMapper.getPasswordByName(token.getUsername());
        if (password == null){
            throw new AccountException("用户名不正确");
        }else if (!password.equals(token.getCredentials().toString())){
            throw new AccountException("密码不正确");
        }
        return new SimpleAuthenticationInfo(token.getPrincipal(), password, getName());
    }
}
