package com.golforyou.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.golforyou.vo.GolforyouMemberNEW;

import lombok.Data;

//시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다. 
//로그인을 진행이 완료가 되면 session을 만들어줍니다. (Security ContextHolder)
//오브젝트 => Authentication 타입 객체
//Authentication 안에 User정보가 있어야 됨.
//User오브젝트타입 => UserDetails 타입 객체 

//Security Session => Authentication => UserDetails(PrincipalDetails) 
@SuppressWarnings("serial")
@Data
public class PrincipalDetails implements UserDetails, OAuth2User{

	private GolforyouMemberNEW member; //콤포지션 
	public PrincipalDetails(GolforyouMemberNEW member) {
		this.member=member;

	}
	
	//해당 User의 권한을 리턴하는 곳! 
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Collection<GrantedAuthority> collect = new ArrayList<>();
//		collect.add(new GrantedAuthority() {
//			
//			@Override
//			public String getAuthority() {				
//				return member.getMRole();
//			}
//		});
//		return collect;
//	}
//	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collet = new ArrayList<GrantedAuthority>();
		collet.add(()->{ return member.getMRole();});
		return collet;
	}

	@Override
	public String getPassword() {		
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		
		return member.getUsername();
	}

	
	//아래 설정은 추기 기능 필요시 진헹

	
	@Override
	public boolean isAccountNonExpired() {		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		//1년동안 접속 안하면 휴면 계정으로 하기로 한 경우 
		//현재 시간 - 로그인 시간으로 > 1년을 초과하면 return을 false로 한다. 
		return true;
	}
	
	//Oauth
	@Override
	public Map<String, Object> getAttributes() {
	
		return null;
	}
	
	//Oauth
	@Override
	public String getName() {
		
		return null;
	}

}
