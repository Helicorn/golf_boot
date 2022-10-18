package com.golforyou.service;

import java.util.List;

import com.golforyou.vo.ScboardVO;
import com.golforyou.vo.ScorecardVO;

public interface ScBoardService {

	int getListCount(ScboardVO sb);

	List<ScboardVO> getBoardList(ScboardVO sb);

	ScboardVO getScBoardCont(int sc_no);

	ScboardVO getScBoardCont2(int sc_no);

	void insertBoard(ScboardVO sb);

	void replyBoard(ScboardVO sb);

	void updateBoard(ScboardVO sb);

	void delBoard(ScboardVO sb);

	int getUpdated(ScboardVO info);

	ScboardVO getScBoardCont(ScboardVO info);

	void updateCard(ScorecardVO sc);

	void delCard(ScorecardVO sv);

}
