package com.golforyou.service;

import com.golforyou.vo.MemberVO;

public interface MemberService {

	MemberVO Login(String m_id);
	void insertMember(MemberVO ab);
	MemberVO idCheck(String m_id);
	String getSaltById(String m_id);
	MemberVO getMember(String id);

}
