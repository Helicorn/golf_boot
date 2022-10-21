package com.golforyou.dao;

import com.golforyou.vo.GolforyouMemberNEW;
import com.golforyou.vo.RankingVO;

public interface MypageDAO {

	void updateMember(GolforyouMemberNEW id);

	void updateProvince(RankingVO id);

	void withdrawal(String m_pw);

}
