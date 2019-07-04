package com.yang.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.yang.doman.po.Role;
import com.yang.doman.po.User;
import com.yang.service.UserService;
import com.yang.shiro.token.JWTToken;
import com.yang.util.JWTUtil;

public class JWTRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	
	//需要重写这个方法判断token
	@Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		String userName=JWTUtil.getUsername(principals.toString());
		Set<Role> sets=userService.findRoleByUserName(userName);
		Set<String> set=new HashSet<>();
		for(Role role : sets) {
			set.add(role.getRole());
		}
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		info.addRoles(set);
		info.addStringPermissions(userService.findPermissionByUserName(userName));
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		String mytoken = (String) token.getCredentials();
		String userName = JWTUtil.getUsername(mytoken);
		if(userName==null || userName.trim()=="") {
			throw new AuthenticationException("token无效");
		}
		User user=userService.findUserByUserName(userName);
		if(user==null) {
			throw new AuthenticationException("用户不存在");
		}
		if(!JWTUtil.verificationJWT(mytoken, userName, user.getPassWord())) {
			throw new AuthenticationException("用户名或者密码错误");
		}
		return new SimpleAuthenticationInfo(mytoken, mytoken, "jwt_realm");
	}

}
