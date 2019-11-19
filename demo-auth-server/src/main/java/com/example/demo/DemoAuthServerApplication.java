package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableDiscoveryClient
@SpringBootApplication
public class DemoAuthServerApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(DemoAuthServerApplication.class, args);
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
	        .inMemoryAuthentication()
	        .withUser("guest").password("guest").authorities("WRIGHT_READ")
	        .and()
	        .withUser("admin").password("admin").authorities("WRIGHT_READ", "WRIGHT_WRITE");
    }
    
    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
}
