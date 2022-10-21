//package com.golforyou.config.auth;
//
//import javax.security.sasl.AuthenticationException;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import lombok.RequiredArgsConstructor;
//
//@Component
//@RequiredArgsConstructor
//public class CustomAuthenticationManager implements AuthenticationManager {
//
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    private final PrincipalDetailsService principalDetailsService;
//
//   
//    @Override
//    public Authentication authenticate(Authentication authentication) {
//        PrincipalDetails principalDetails = (PrincipalDetails) principalDetailsService.loadUserByUsername(authentication.getName());
//
//        if(!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), principalDetails.getPassword())){
//           throw new BadCredentialsException("Wrong password!");
//        }
//        
//        return new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
//    }
//}
