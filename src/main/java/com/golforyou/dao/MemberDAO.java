package com.golforyou.dao;

import com.golforyou.vo.GolforyouMemberNEW;

public interface MemberDAO {

	GolforyouMemberNEW Login(String m_id);
	void insertMember(GolforyouMemberNEW m);
	GolforyouMemberNEW idCheck(String m_id);
	String getSaltById(String m_id);
	GolforyouMemberNEW getMember(String id);

}
