package com.golforyou.service;

import com.golforyou.vo.MemberVO;
import com.golforyou.vo.RankingVO;

public interface MypageService {

	void updateProvince(RankingVO id);

	void updateMember(MemberVO id);

	void withdrawal(String m_pw);




}
