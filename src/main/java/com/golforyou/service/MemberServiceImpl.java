package com.golforyou.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golforyou.dao.MemberDAO;
import com.golforyou.vo.GolforyouMemberNEW;


@Service
public class MemberServiceImpl implements MemberService {
		@Autowired
		private MemberDAO memberDAO;

		@Override
		public GolforyouMemberNEW Login(String m_id) {
			
			return this.memberDAO.Login(m_id);
		}

		@Override
		public void insertMember(GolforyouMemberNEW m) {			
			this.memberDAO.insertMember(m);
			
		}

		@Override
		public GolforyouMemberNEW idCheck(String m_id) {
			return this.memberDAO.idCheck(m_id);
			
		}

		@Override
		public String getSaltById(String m_id) {
		
			return memberDAO.getSaltById(m_id);
		}

		@Override
		public GolforyouMemberNEW getMember(String id) {
		
			return memberDAO.getMember(id);
		}
		
		
}
