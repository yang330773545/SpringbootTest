package com.vinzor.shiro;



import java.util.HashSet;
import java.util.Set;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.vinzor.mysql.dao.RoleDao;
import com.vinzor.mysql.dao.UseDao;
import com.vinzor.mysql.entity.UserEntity;
import com.vinzor.mysql.service.UserService;

public class MyRealm extends AuthorizingRealm{

	@Autowired(required=true)
	UserService userService;
	@Autowired
	UseDao useDao;
	@Autowired
	RoleDao roleDao;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		//UserEntity userEntity=(UserEntity) SecurityUtils.getSubject().getPrincipal();
		UserEntity userEntity=(UserEntity) principals.fromRealm(getName()).iterator().next();
		User user=useDao.findUserById(userEntity.getId());
		Set<String> use=new HashSet<>();
		use.add(user.getUser1());
		use.add(user.getUser2());
		Role role=roleDao.findRoleById(userEntity.getId());
		Set<String> rol=new HashSet<>();
		rol.add(role.getRole1());
		rol.add(role.getRole2());
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();	
		authorizationInfo.setRoles(rol);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		String userName= (String) token.getPrincipal();
		UserEntity userEntity=userService.selectUserByUserName(userName);
		if(userEntity==null) throw new UnknownAccountException();
		//SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(userEntity, userEntity.getPassWord(), ByteSource.Util.bytes("123"),getName());
		SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(userEntity, userEntity.getPassWord(),getName());
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute("userSession", userEntity);

		return authenticationInfo;
	}

}
