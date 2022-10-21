package com.golforyou.vo;

import lombok.Data;

@Data
public class BoardVO {
	private int b_no;
	private String username;
	private String b_title;
	private String b_pwd;
	private String b_cont;

	private String b_file;	

	private int b_hit;
	private int b_ref;
	private int b_step;
	private int b_level;
	private String b_date;
	private int b_like;
	
	//쪽나누기용 변수
	private int startrow; //시작행 번호
	private int endrow; //끝행 번호
	
	//검색기능
	private String find_field;
	private String find_name;
	
	public void setB_date(String b_date) {
		this.b_date = b_date.substring(0,10);
	}
}
