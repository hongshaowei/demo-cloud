package com.example.demo.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class SecurityConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/**").authenticated()
			.antMatchers(HttpMethod.GET, "/luckyWord")
			.hasAuthority("WRIGHT_READ")
			.and()
			.csrf()
			.disable();
	}
 
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources
			.resourceId("WRIGHT")
			.tokenStore(jwtTokenStore());
	}
 
	@Bean
	protected JwtAccessTokenConverter jwtTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("springcloud13");
		return converter;
	}
 
	@Bean
	public TokenStore jwtTokenStore() {
		return new JwtTokenStore(jwtTokenConverter());
	}
}
