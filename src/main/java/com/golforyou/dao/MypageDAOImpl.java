package com.golforyou.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.golforyou.vo.GolforyouMemberNEW;
import com.golforyou.vo.RankingVO;

@Repository
public class MypageDAOImpl implements MypageDAO{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void updateMember(GolforyouMemberNEW id) {
		this.sqlSession.update("update_member", id);
		
	}

	@Override
	public void updateProvince(RankingVO id) {
		this.sqlSession.update("update_province",id);
		
	}

	@Override
	public void withdrawal(String m_pw) {
		this.sqlSession.update("withdrawal",m_pw);
		
	}
	

	
}
