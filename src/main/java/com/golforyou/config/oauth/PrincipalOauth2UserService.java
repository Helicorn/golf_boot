package com.golforyou.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.golforyou.config.auth.PrincipalDetails;
import com.golforyou.controller.UserRepository;
import com.golforyou.vo.GolforyouMemberNEW;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private UserRepository userRepository;
	
	//구글에서 받은 userRequest데이터에 대한 후처리되는 함수
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("getClientRegistration:"+userRequest.getClientRegistration());//registrationId로 어떤 OAuth로 로그인 했는지 확인
		System.out.println("getAccessToken:"+userRequest.getAccessToken().getTokenValue());
		OAuth2User oauth2User = super.loadUser(userRequest);
		//구글 로그인 버튼 클릭 > 구글로그인 창 > 로그인 완료 > code리턴 (OAuth-Client라이브러리) > accessToken 요청 
		//userRequest 정보 > loadUser함수 호출 > 구글로부터 회원 프로필 받아줌. 
		System.out.println("getAttributes:"+super.loadUser(userRequest).getAttributes());
	
		String mProvider = userRequest.getClientRegistration().getClientId();//google
		String mProviderid = oauth2User.getAttribute("sub");
		String username = mProvider+"_"+mProviderid; //google_***
		String password = bCryptPasswordEncoder.encode("겟인데어");
		String mEmail = oauth2User.getAttribute("email");
		String mRole = "ROLE_USER";
		
		GolforyouMemberNEW userEntity = userRepository.findByUsername(username);

		if(userEntity == null) {
			System.out.println("구글로그인이 최초입니다.");
			userEntity = GolforyouMemberNEW.builder()
					.username(username)
					.password(password)
					.mEmail(mEmail)
					.mRole(mRole)
					.mProvider(mProvider)
					.mProviderid(mProviderid)
					.build();
		}else {
			System.out.println("구글가입이 이미 되어있습니다.");
		}
		
		
		return new PrincipalDetails(userEntity,oauth2User.getAttributes());
		//authentication 객채로 들어감
		//Oauth2로 로그인 하면, Map을 같이 가지고 있음
	}
}
