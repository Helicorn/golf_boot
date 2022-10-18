package com.golforyou.service;

import java.util.List;

import com.golforyou.vo.ScboardVO;
import com.golforyou.vo.ScorecardVO;

public interface IndivService {

	String getPoint(String id);

	String getHandicap(String id);

	List<Integer> getPutting(String id);

	int monthCount(ScorecardVO sv);

	List<String> getRowNum(String id);

	List<String> getDate(String id);

	List<Integer> getBestScore(String id);

	List<String> getLocation(String id);

	List<Integer> getRange(String id);

	int getSumPoint(String id);

	void autoInsert(ScboardVO sb);

	String makeDate(ScboardVO sb);

	void sortDate(ScboardVO sb);

	
}
