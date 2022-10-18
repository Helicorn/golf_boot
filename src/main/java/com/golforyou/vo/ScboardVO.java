package com.golforyou.vo;

import lombok.Data;

@Data
public class ScboardVO {
	private int sc_no;
	private String sc_id;
	private String sc_name;
	private String sc_title;
	private String sc_playdate;
	private String sc_cont;
	private String sc_file;
	private int sc_hit;
	private int sc_ref;
	private int sc_step;
	private int sc_level;
	private String sc_date;
	
	//쪽나누기용 변수
	private int startrow; //시작행 번호
	private int endrow; //끝행 번호
	
	//검색기능
	private String find_field;
	private String find_name;
	
	private int numdate;
	
	public void setSc_date(String sc_date) {
		this.sc_date = sc_date.substring(0,10);
	}
	
	
}
