package com.golforyou.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.golforyou.service.RankingService;
import com.golforyou.vo.RankingVO;

@Controller
public class RankingController {
	
	@Autowired
	private RankingService rankingService;
	
	//랭킹 메인페이지
	@GetMapping("/tier/ranking")
	public ModelAndView ranking(RankingVO rv, HttpServletRequest request) {
		ModelAndView rm = new ModelAndView();
		String prov = request.getParameter("prov");
		
		int mem=0;
		
		mem = rankingService.memberCount();
		
		List<String> rankid = new ArrayList<>();
		//List<Integer> count = new ArrayList<Integer>();
		List<Integer> rankpoint = new ArrayList<Integer>();
		List<Integer> bestrange = new ArrayList<Integer>();
		List<Integer> province = new ArrayList<Integer>();
		rankid = rankingService.getId();
		for(int i=0 ; i<mem ; ++i) {
			rm.addObject("rankid"+i,rankid.get(i));
		}
		
		for(int i=0 ; i<mem ; ++i) {
			int count = rankingService.playCount(rankid.get(i)); //플레이횟수
			rm.addObject("count"+i, count);				
		}
		
		rankpoint = rankingService.getRankPoint(); //점수합계
		bestrange = rankingService.getBestRange(); //최대비거리
		province = rankingService.getProvince(); //주소지
		for(int i=0 ; i<mem ; ++i) {				
			rm.addObject("rankpoint"+i, rankpoint.get(i));
			rm.addObject("bestrange"+i, bestrange.get(i));
			rm.addObject("province"+i, province.get(i));
		}
		
		String[] fileaddr = new String[mem];
								
		rm.addObject("prov", prov);
		rm.addObject("mem",mem);
		for(int i=0 ; i<mem ; ++i) {
			rm.addObject("fileaddr"+i, fileaddr[i]);
		}
		
		return rm;
		
		
		
	}
}
