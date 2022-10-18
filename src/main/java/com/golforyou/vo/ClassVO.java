package com.golforyou.vo;

import lombok.Data;

@Data
public class ClassVO {

	private int c_no; //클래스 고유번호
	private String c_kind; //필드 or 온라인 구분
	private String c_title; //클래스명
	private String c_teacher; //강사명(xxx프로)
	private String c_gender; //강사 성별
	private String c_phone; //클래스 전화번호
	private String c_career; //강사 소개 또는 경력사항
	private String c_sprice; //필드 클래스 STANDARD 가격
	private String c_srounding; //STANDARD 라운딩 횟수
	private String c_sdesc; //STANDARD 상세 설명
	private String c_stime; //STANDARD 1회당 레슨 시간(분)
	private String c_dprice; //필드 클래스 DELUXE 가격
	private String c_drounding; //DELUXE 라운딩 횟수
	private String c_ddesc; //DELUXE 상세 설명
	private String c_dtime; //DELUXE 1회당 레슨 시간(분)
	private String c_pprice; //필드 클래스 PREMIUM 가격
	private String c_prounding; //PREMIUM 라운딩 횟수
	private String c_pdesc; //PREMIUM 상세 설명
	private String c_ptime; //PREMIUM 1회당 레슨 시간(분)
	private String c_online; //온라인 클래스 가격
	private String c_odesc; //온라인 클래스 상세 설명
	private String c_otime; //온라인 클래스 수강기간
	private String c_addr; //클래스 지역
	private String c_image; //이미지 경로
	

}
