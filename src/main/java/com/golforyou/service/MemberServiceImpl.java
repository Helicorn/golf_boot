package com.golforyou.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golforyou.dao.MemberDAO;
import com.golforyou.util.SHA256Util;
import com.golforyou.vo.MemberVO;


@Service
public class MemberServiceImpl implements MemberService {
		@Autowired
		private MemberDAO memberDAO;

		@Override
		public MemberVO Login(String m_id) {
			
			return this.memberDAO.Login(m_id);
		}

		@Override
		public void insertMember(MemberVO m) {			
			this.memberDAO.insertMember(m);
			
		}

		@Override
		public MemberVO idCheck(String m_id) {
			return this.memberDAO.idCheck(m_id);
			
		}

		@Override
		public String getSaltById(String m_id) {
		
			return memberDAO.getSaltById(m_id);
		}

		@Override
		public MemberVO getMember(String id) {
		
			return memberDAO.getMember(id);
		}
		
		
}
