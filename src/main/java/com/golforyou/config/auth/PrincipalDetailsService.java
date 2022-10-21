package com.golforyou.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.golforyou.controller.UserRepository;
import com.golforyou.vo.GolforyouMemberNEW;



//시큐리티 설정에서 loginProcessingUrl("/login");
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어있는 loadUserByUsername 함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	// private HttpServletResponse response;
	// private HttpSession session;
	
	
	//시큐리티 session(내부 Authentication(내부 UserDetails)) 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				
		GolforyouMemberNEW userEntity = userRepository.findByUsername(username);				
		System.out.println(userEntity);
		
			if(userEntity == null) {
				 throw new UsernameNotFoundException(username);
				
				
			}else {
				
				System.out.println(userEntity.getUsername()+userEntity.getPassword()+userEntity.getMRole().toString());

				return new PrincipalDetails(userEntity);
				

			}
	
	}
	
}