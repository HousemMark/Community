package com.xq.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xq.pojo.Guest;
import com.xq.pojo.Manager;
import com.xq.pojo.User;
import com.xq.service.LoginService;

public class MyShiroRealm extends AuthorizingRealm{

	@Reference(check=false)
	LoginService loginService;

	/**
	 * 完成认证信息的获取以及封装
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken upToken = (UsernamePasswordToken)token;
		Guest guest = loginService.findGuestByName(upToken.getUsername());
		Manager manager = loginService.findManagerByName(upToken.getUsername());
		SimpleAuthenticationInfo info = null;
		//登陆用户是住客
		if(guest != null) {
			info = getInfo(guest);
		}
		//登陆用户是管理员
		if(manager != null) {
			info = getInfo(manager);
		}
		System.out.println(info);
		return info;
	}
	
	/*封装方法*/
	public SimpleAuthenticationInfo getInfo(User user) {
		ByteSource credentialsSalt=
				ByteSource.Util.bytes(user.getSalt());

		SimpleAuthenticationInfo info=
				new SimpleAuthenticationInfo(
						user,//principal 身份 user
						user.getPassword(),//hashedCredentials 已加密的凭证的信息
						credentialsSalt,//credentialsSalt 盐值
						getName());//realmName
		return info;//返回给调用者(SecurityManager)
	}
	
	/*权限认证功能*/
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 设置凭证匹配器,告诉认证管理器使用什么
	 * 加密算法对用户输入的密码进行加密操作
	 */
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		//构建凭证匹配对象
		HashedCredentialsMatcher cMatcher=
				new HashedCredentialsMatcher();
		//设置加密算法
		cMatcher.setHashAlgorithmName("MD5");
		//设置加密次数
		cMatcher.setHashIterations(1);
		super.setCredentialsMatcher(cMatcher);
	}
}
