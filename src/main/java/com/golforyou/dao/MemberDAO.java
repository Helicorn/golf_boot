package com.golforyou.dao;

import com.golforyou.vo.MemberVO;

public interface MemberDAO {

	MemberVO Login(String m_id);
	void insertMember(MemberVO m);
	MemberVO idCheck(String m_id);
	String getSaltById(String m_id);
	MemberVO getMember(String id);

}
