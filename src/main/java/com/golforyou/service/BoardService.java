package com.golforyou.service;

import java.util.List;

import com.golforyou.vo.BoardVO;

public interface BoardService {
	
	void insertBoard(BoardVO b);
	
	List<BoardVO> getboardList(BoardVO b);

	int getRowCount(BoardVO b);

	BoardVO getBoardCont(int b_no);

	BoardVO getBoardCont2(int b_no);

	void replyboard(BoardVO rb);

	void editboard(BoardVO eb);

	void delboard(int b_no);

	

}
