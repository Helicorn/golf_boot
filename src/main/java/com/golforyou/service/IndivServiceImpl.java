package com.golforyou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golforyou.dao.IndivDAO;
import com.golforyou.vo.ScboardVO;
import com.golforyou.vo.ScorecardVO;

@Service
public class IndivServiceImpl implements IndivService {
	
	@Autowired
	private IndivDAO indivDAO;

	@Override
	public String getPoint(String id) {
		// TODO Auto-generated method stub
		return indivDAO.getPoint(id);
	}

	@Override
	public String getHandicap(String id) {
		// TODO Auto-generated method stub
		return indivDAO.getHandicap(id);
	}

	@Override
	public List<Integer> getPutting(String id) {
		// TODO Auto-generated method stub
		return indivDAO.getPutting(id);
	}

	@Override
	public int monthCount(ScorecardVO sv) {
		// TODO Auto-generated method stub
		return indivDAO.monthCount(sv);
	}

	@Override
	public List<String> getRowNum(String id) {
		// TODO Auto-generated method stub
		return indivDAO.getRowNum(id);
	}

	@Override
	public List<String> getDate(String id) {
		// TODO Auto-generated method stub
		return indivDAO.getDate(id);
	}

	@Override
	public List<Integer> getBestScore(String id) {
		// TODO Auto-generated method stub
		return indivDAO.getBestScore(id);
	}

	@Override
	public List<String> getLocation(String id) {
		// TODO Auto-generated method stub
		return indivDAO.getLocation(id);
	}

	@Override
	public List<Integer> getRange(String id) {
		// TODO Auto-generated method stub
		return indivDAO.getRange(id);
	}

	@Override
	public int getSumPoint(String id) {
		// TODO Auto-generated method stub
		return indivDAO.getSumPoint(id);
	}

	@Override
	public void autoInsert(ScboardVO sb) {
		// TODO Auto-generated method stub
		indivDAO.autoInsert(sb);
	}

	@Override
	public String makeDate(ScboardVO sb) {
		// TODO Auto-generated method stub
		return indivDAO.makeDate(sb);
	}

	@Override
	public void sortDate(ScboardVO sb) {
		// TODO Auto-generated method stub
		indivDAO.sortDate(sb);
	}
	
}
