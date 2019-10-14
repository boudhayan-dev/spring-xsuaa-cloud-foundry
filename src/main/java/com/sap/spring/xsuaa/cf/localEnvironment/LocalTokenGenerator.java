package com.sap.spring.xsuaa.cf.localEnvironment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.cloud.security.xsuaa.XsuaaServiceConfiguration;
import com.sap.cloud.security.xsuaa.test.JwtGenerator;

@ConditionalOnProperty(name="controller.LocalTokenGenerator.active", havingValue="true")
@RestController
@RequestMapping("local")
public class LocalTokenGenerator {
	
	@Autowired
	 private XsuaaServiceConfiguration xsuaaServiceConfiguration;
	
	@GetMapping("getLocalToken")
	public String getLocalToken() {
		String token = new JwtGenerator().setUserName("boudhayan.dev@example.com")
				.addScopes(getGlobalScope("Manager"),getGlobalScope("Employee"))
				.getTokenForAuthorizationHeader();
		
		return token;
	}
	
	private String getGlobalScope(String localScope) {
		return xsuaaServiceConfiguration.getAppId() + "." + localScope;
	}
}
