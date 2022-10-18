package com.golforyou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.golforyou.dao.ScBoardDAO;
import com.golforyou.vo.ScboardVO;
import com.golforyou.vo.ScorecardVO;

@Service
public class ScBoardServiceImpl implements ScBoardService {
	
	@Autowired
	private ScBoardDAO scBoardDAO;

	@Override
	public int getListCount(ScboardVO sb) {
		// TODO Auto-generated method stub
		return scBoardDAO.getListCount(sb);
	}

	@Override
	public List<ScboardVO> getBoardList(ScboardVO sb) {
		// TODO Auto-generated method stub
		return scBoardDAO.getBoardList(sb);
	}

	@Transactional
	@Override
	public ScboardVO getScBoardCont(int sc_no) {
		// TODO Auto-generated method stub
		scBoardDAO.updateHit(sc_no);
		return scBoardDAO.getScBoardCont(sc_no);
	}

	@Override
	public ScboardVO getScBoardCont2(int sc_no) {
		// TODO Auto-generated method stub
		return scBoardDAO.getScBoardCont(sc_no);
	}

	@Override
	public void insertBoard(ScboardVO sb) {
		// TODO Auto-generated method stub
		scBoardDAO.insertBoard(sb);
	}

	@Transactional
	@Override
	public void replyBoard(ScboardVO sb) {
		// TODO Auto-generated method stub
		scBoardDAO.replyBoard(sb); //답변레벨 증가
		scBoardDAO.replyBoard2(sb); //답변저장
	}

	@Override
	public void updateBoard(ScboardVO sb) {
		// TODO Auto-generated method stub
		scBoardDAO.updateBoard(sb);
	}

	@Override
	public void delBoard(ScboardVO sb) {
		// TODO Auto-generated method stub
		scBoardDAO.delBoard(sb);
	}

	@Override
	public int getUpdated(ScboardVO info) {
		// TODO Auto-generated method stub
		return scBoardDAO.getUpdated(info);
	}

	@Override
	public ScboardVO getScBoardCont(ScboardVO info) {
		// TODO Auto-generated method stub
		return scBoardDAO.getScBoardCont(info);
	}

	@Override
	public void updateCard(ScorecardVO sc) {
		// TODO Auto-generated method stub
		scBoardDAO.updateCard(sc);
	}

	@Override
	public void delCard(ScorecardVO sv) {
		// TODO Auto-generated method stub
		scBoardDAO.delCard(sv);
	}

	
}
