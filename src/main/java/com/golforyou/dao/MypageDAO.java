package com.golforyou.dao;

import com.golforyou.vo.MemberVO;
import com.golforyou.vo.RankingVO;

public interface MypageDAO {

	void updateMember(MemberVO id);

	void updateProvince(RankingVO id);

	void withdrawal(String m_pw);

}
