package com.devfortech.HelloWord.components;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.devfortech.HelloWord.entities.Clients;
import com.devfortech.HelloWord.repository.ClientsRepository;

@SuppressWarnings("deprecation")
public class JwtTokenEnhancer implements TokenEnhancer {
	
	@Autowired
	private ClientsRepository repository;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Clients client = repository.findByEmail(authentication.getName());
		Map<String, Object> map = new HashMap<>();
		map.put("clientName", client.getName());
		map.put("clientId", client.getId());
		
		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
		return accessToken;
	}
}