package com.golforyou.service;

import java.util.List;

import com.golforyou.vo.ScorecardVO;

public interface RankingService {

	int memberCount();

	int memberCount(String prov);

	List<String> getId();

	int playCount(String r_id);

	List<Integer> getRankPoint();

	List<Integer> getBestRange();

	List<Integer> getProvince();

	void updateAvgScore(ScorecardVO sc);
	
}
