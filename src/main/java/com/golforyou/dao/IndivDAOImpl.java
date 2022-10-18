package com.golforyou.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.golforyou.vo.ScboardVO;
import com.golforyou.vo.ScorecardVO;

@Repository
public class IndivDAOImpl implements IndivDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public String getPoint(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("rank_loginpoint", id);
	}

	@Override
	public String getHandicap(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("sc_handicap", id);
	}

	@Override
	public List<Integer> getPutting(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("sc_putting", id);
	}

	@Override
	public int monthCount(ScorecardVO sv) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("sc_graph", sv);
	}

	@Override
	public List<String> getRowNum(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("sc_row", id);
	}

	@Override
	public List<String> getDate(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("sc_date", id);
	}

	@Override
	public List<Integer> getBestScore(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("sc_bestscore", id);
	}

	@Override
	public List<String> getLocation(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("sc_location", id);
	}

	@Override
	public List<Integer> getRange(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("sc_range", id);
	}

	@Override
	public int getSumPoint(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("sc_sumpoint", id);
	}

	@Override
	public void autoInsert(ScboardVO sb) {
		// TODO Auto-generated method stub
		sqlSession.insert("sc_auto", sb);
	}

	@Override
	public String makeDate(ScboardVO sb) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("sc_makedate", sb);
	}

	@Override
	public void sortDate(ScboardVO sb) {
		// TODO Auto-generated method stub
		sqlSession.update("sc_sort", sb);
	}
	
}
