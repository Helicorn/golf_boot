package com.golforyou.vo;

import lombok.Data;

@Data
public class Yeyag_listVO {
	private int y_no; 
	private String y_name;
	private int y_list;
	private int y_price;
	private int y_usepeople;
	private String y_area;
	private String y_img;
	private String y_info;
	private String y_url;
	
	//검색기능
    private String find_field;
    private String find_name;
}
