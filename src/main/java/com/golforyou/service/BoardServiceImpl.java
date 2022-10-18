package com.golforyou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.golforyou.dao.BoardDAO;
import com.golforyou.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;

	@Override
	public void insertBoard(BoardVO b) {
		this.boardDAO.insertBoard(b);		
	}
	@Override
	public List<BoardVO> getboardList(BoardVO b) {
		
		return this.boardDAO.getboardList(b);
	}

	@Override
	public int getRowCount(BoardVO b) {
		return boardDAO.getRowCount(b);
	
	}
	@Transactional
	@Override
	public BoardVO getBoardCont(int b_no) {
		this.boardDAO.updateHit(b_no); //조회수 증가
		return this.boardDAO.getBoard_cont(b_no);
	}
	@Override
	public BoardVO getBoardCont2(int b_no) {
		return this.boardDAO.getBoard_cont(b_no);
	}
	
	@Transactional
	@Override
	public void replyboard(BoardVO rb) {
		this.boardDAO.updateLevel(rb);
		this.boardDAO.replyboard(rb);
		
	}
	@Override
	public void editboard(BoardVO eb) {
		this.boardDAO.editboard(eb);
		
	}
	@Override
	public void delboard(int b_no) {
		this.boardDAO.delboard(b_no);	
		
	}


}
