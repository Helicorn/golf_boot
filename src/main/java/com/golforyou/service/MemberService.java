package com.golforyou.service;

import com.golforyou.vo.GolforyouMemberNEW;

public interface MemberService {

	GolforyouMemberNEW Login(String m_id);
	void insertMember(GolforyouMemberNEW ab);
	GolforyouMemberNEW idCheck(String m_id);
	String getSaltById(String m_id);
	GolforyouMemberNEW getMember(String id);

}
