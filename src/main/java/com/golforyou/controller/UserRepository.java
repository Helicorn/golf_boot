package com.golforyou.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import com.golforyou.vo.GolforyouMemberNEW;

public interface UserRepository extends JpaRepository<GolforyouMemberNEW, Integer> {

	
	//public GolforyouMemberNEW findBymId(String mId);
	//public GolforyouMemberNEW findByUsername(String username);
	//public GolforyouMemberNEW findBymId(String username);
	//public GolforyouMemberNEW findByUsername(String username);
	
	public GolforyouMemberNEW findByUsername(String username); //JPA Query method() 

}
