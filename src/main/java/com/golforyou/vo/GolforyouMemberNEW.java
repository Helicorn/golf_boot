package com.golforyou.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
@SequenceGenerator(
		name = "MEMBER_SEQ_GENERATOR",
		sequenceName = "MEMBER_SEQ",	//매핑할 DB 시퀀스 이름
		initialValue = 1, allocationSize = 1)
@Data
public class GolforyouMemberNEW implements Serializable{
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MEMBER_SEQ_GENERATOR")
	private int  mNo;
	@Id
	private String username;
	private String password;
	private String mPhone;
	private String mAddr;
	private String mEmail;
	private String mGender;
	private String mFile;
	private int mState;
	private String mRole; //ROLE_USER,ROLE_ADMIN,ROLE_MANAGER
	@CreationTimestamp
	private Timestamp mCreateDate;
	
	private String mDelcont;
	private String mDeldate;
	
	

}
