package com.golforyou.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.golforyou.config.oauth.PrincipalOauth2UserService;

import lombok.AllArgsConstructor;

@Configuration //@EnableWebSecurity //스프링 시큐리티 필터가 스프링 필터체인에 등록됨, SecurityAutoConfigurationd에서 자동 추가된다고 해서 잠시 주석처리함
@EnableWebSecurity 
@EnableGlobalMethodSecurity(securedEnabled=true,prePostEnabled=true) //secure 애노테이션 활성화, preAuthorize 어노테이션 활성화 
@ConditionalOnDefaultWebSecurity
@AllArgsConstructor 
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SecurityConfig{
	
	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;
	
	
	//해당 메서드의 리턴되는 오브젝트 IoC로 등록해준다.
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//1. 코드 받기(인증), 2.액세스 토큰 받기(권한) 3.사용자 프로필 정보를 가져와서 4.정보를 토대로 회원가입을 자동으로 진행
	
	@Bean
	@Order(0)
	@Primary
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

		http.csrf().disable();
		http
		.authorizeRequests()
			.antMatchers("/user/**").authenticated() //인증만 되면 들어갈 수 있는 주소
			.antMatchers("/manager/**").access("hasRole('ROLE_ADMIN')or hasRole('ROLE_MANAGER')")
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll()	
			.and()
		.formLogin()
			.loginPage("/login")
			//.usernameParameter("mId")
			.loginProcessingUrl("/loginOk") // /login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해줌 
			.defaultSuccessUrl("/test/login")
			.failureUrl("/access_denied")
			.and()			
		.logout() // 로그아웃 기능 작동함
			.logoutUrl("/logout") // 로그아웃 처리 URL, default: /logout, 원칙적으로 post 방식만 지원
			.logoutSuccessUrl("/") // 로그아웃 성공 후 이동페이지
			.deleteCookies("JSESSIONID", "remember-me") // 로그아웃 후 쿠키 삭제
			.and()
		.oauth2Login()
			.loginPage("/login")	
			.defaultSuccessUrl("/test/oauth/login")
			.userInfoEndpoint()
			.userService(principalOauth2UserService);
//			.and()
		//구글 로그인이 완료된 뒤의 후처리가 필요함 . 코드 X, (액세스 토큰+사용자 프로필 정보 O)
//		.rememberMe()
//			.key("rememberKey")
//		.rememberMeCookieName("rememberMeCookieName")
//		.rememberMeParameter("remember-me")
//		.tokenValiditySeconds(60 * 60 * 24 * 7)
//		.userDetailsService(principalDetailsService);
		
		return http.build();
		
	}
	 @Bean
	    public WebSecurityCustomizer webSecurityCustomizer() throws Exception{
	        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
	    }
    

	 
	 
}
