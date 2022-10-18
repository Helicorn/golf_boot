package com.golforyou.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.golforyou.vo.ScorecardVO;

@Repository
public class RankingDAOImpl implements RankingDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int memberCount() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("rank_count");
	}

	@Override
	public int memberCount(String prov) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("rank_count2", prov);
	}

	@Override
	public List<String> getId() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("rank_id");
	}

	@Override
	public int playCount(String r_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("s_count",r_id);
	}

	@Override
	public List<Integer> getRankPoint() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("rank_point");
	}

	@Override
	public List<Integer> getBestRange() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("rank_range");
	}

	@Override
	public List<Integer> getProvince() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("rank_prov");
	}

	@Override
	public void updateAvgScore(ScorecardVO sc) {
		// TODO Auto-generated method stub
		sqlSession.update("rank_editAvg", sc);
	}
	
	
}
