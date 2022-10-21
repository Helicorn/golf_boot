package com.golforyou.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.golforyou.vo.GolforyouMemberNEW;

@Repository
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public GolforyouMemberNEW Login(String m_id) {	
		return this.sqlSession.selectOne("member_read",m_id);
	}

	@Override
	public void insertMember(GolforyouMemberNEW m) {
		this.sqlSession.insert("member_in",m);
		
	}

	@Override
	public GolforyouMemberNEW idCheck(String m_id) { 
		return this.sqlSession.selectOne("id_check",m_id);
		
	}//아이디 중복 검사

	@Override
	public String getSaltById(String m_id) {		
		return this.sqlSession.selectOne("salt_check",m_id);
	}

	@Override
	public GolforyouMemberNEW getMember(String id) {	
		return this.sqlSession.selectOne("getMember",id);
	}//회원정보 수정을 위한 아이디 받아오기
}
