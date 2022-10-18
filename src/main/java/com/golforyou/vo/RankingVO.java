package com.golforyou.vo;

import lombok.Data;

@Data
public class RankingVO {
	private int r_no;
	private String r_id;
	private int r_sum;
	private double r_avg;
	private double r_putting;
	private int r_maxrange;
	private double r_avgrange;
	private String r_province;
}
