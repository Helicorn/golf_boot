package com.golforyou.vo;

import lombok.Data;

@Data
public class ScorecardVO {
	private String s_id;
	private String s_location;
	private double s_putting;
	private int s_range;
	private int s_handicap;
	private String s_date;
	private int s_sort;
	private int s_bestscore;
	private double s_avgscore;
	private int s_updated;
	
	private String graph_date;
	
}
