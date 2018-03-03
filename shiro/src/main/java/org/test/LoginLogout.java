package org.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import junit.framework.Assert;

public class LoginLogout {
	@Test
	public void testHelloWorld() 
	{
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		
		SecurityManager securityManeger = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManeger);
		
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO: handle exception
		}
		
		Assert.assertEquals(true, subject.isAuthenticated());
		
		subject.logout();
	}
	
	@Test
	public void testAllSuccessfulStrategyWithSuccess() 
	{
		login("classpath:shiro-authenticator-all-success.ini");
		Subject l_Subject = SecurityUtils.getSubject();
		
		PrincipalCollection l_Pc = l_Subject.getPrincipals();
		Assert.assertEquals(2, l_Pc.asList().size());
	}
	
	private void login(String p_ConfigFile) 
	{
		Factory<SecurityManager> l_Factory = new IniSecurityManagerFactory(p_ConfigFile);
		SecurityManager l_SecurityManager = l_Factory.getInstance();
		SecurityUtils.setSecurityManager(l_SecurityManager);
		Subject l_Subject = SecurityUtils.getSubject();
		UsernamePasswordToken l_Token = new UsernamePasswordToken("zhang", "123");
		l_Subject.login(l_Token);	
	}
	
}
