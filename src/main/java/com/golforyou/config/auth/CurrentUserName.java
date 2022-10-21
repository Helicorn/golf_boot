//package com.golforyou.config.auth;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//
//public class CurrentUserName {
//	
//	 public String currentUserName() {
//
//	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	        User user = (User) authentication.getPrincipal();
//	        System.out.println("authentication"+user.getUsername());
//	        return user.getUsername();
//
//	    }
//	 
//}
