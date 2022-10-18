package com.golforyou.vo;

import lombok.Data;

@Data
public class MemberVO {

	private int  m_no;
	private String m_name;
	private String m_id;
	private String m_pw;
	private String m_phone;
	private String m_addr;
	private String m_email;
	private String m_gender;
	private String m_file;
	private String m_date;
	private int m_state;
	private String m_delcont;
	private String m_deldate;
	private String salt;
	

}
