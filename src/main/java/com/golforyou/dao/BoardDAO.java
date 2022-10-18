package com.golforyou.dao;

import java.util.List;

import com.golforyou.vo.BoardVO;

public interface BoardDAO {
	
	void insertBoard(BoardVO b);
	
	int getRowCount(BoardVO b);

	List<BoardVO> getboardList(BoardVO b);

	BoardVO getBoardCont(int b_no);

	void replyboard(BoardVO rb);

	void editboard(BoardVO eb);

	void delboard(int b_no);

	void updateHit(int b_no);

	BoardVO getBoard_cont(int b_no);

	void updateLevel(BoardVO rb);

}
