package com.golforyou.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.golforyou.service.RankingService;
import com.golforyou.service.ScBoardService;
import com.golforyou.vo.ScboardVO;
import com.golforyou.vo.ScorecardVO;

@Controller
public class AdminRankController {
	@Autowired
	private ScBoardService scBoardService;
	@Autowired
	private RankingService rankingService;
	
	//관리자 스코어카드 첫페이지
	@RequestMapping
	public void admin_insertCard() {
		
	}
	
	//입력해야 할 스코어카드 존재 확인여부
	@RequestMapping(value="/admin_insertCard2")
	public String admin_InsertCard_Check(HttpServletRequest request, HttpServletResponse response, ScboardVO sb) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		
		String sc_id = request.getParameter("id");
		String sc_playdate = request.getParameter("playdate");
		sc_playdate = sc_playdate.replace("-", "_");
		
		ScboardVO info = new ScboardVO();
		info.setSc_id(sc_id);
		info.setSc_playdate(sc_playdate);
		
		sb = scBoardService.getScBoardCont(info);
		
		if(sb != null) {
			if(scBoardService.getUpdated(info) == 0) {
				String sc_fileName = sb.getSc_file();
				
				String folder = sc_fileName.substring(1,11);
				String file = sc_fileName.substring(12,22);
				
				request.setAttribute("date", sc_playdate);
				request.setAttribute("folder", folder);
				request.setAttribute("file", file);
				request.setAttribute("id", sc_id);
				
				return "redirect:/admin/admin_inserCard2";
			}else {
				return "redirect:/admin/admin_insertCard_null";
			}
		}else {
			return "redirect:/admin/admin_insertCard_null";
		}
	}
	
	//스코어카드 정보 입력 확인
	@RequestMapping(value="/admin_insertCard_ok")
	public String admin_InsertCard_Ok(HttpServletRequest request, HttpServletResponse response, ScorecardVO sc) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		String s_id = request.getParameter("s_id");
		String s_date = request.getParameter("s_date");
		double point_avg = Double.parseDouble(request.getParameter("point_avg"));
		int bestPoint = Integer.parseInt(request.getParameter("bestPoint"));
		double put_avg = Double.parseDouble(request.getParameter("put_avg"));
		int range = Integer.parseInt(request.getParameter("range"));
		String location = request.getParameter("location");
		int handicap = Integer.parseInt(request.getParameter("handicap"));
		
		sc.setS_id(s_id);
		sc.setS_date(s_date);
		sc.setS_bestscore(bestPoint);
		sc.setS_range(range);
		sc.setS_location(location);
		sc.setS_handicap(handicap);
		sc.setS_putting(put_avg);
		sc.setS_avgscore(point_avg); //avgscore를 sumscore로 있다가 꼭 바꿔줘야함.
		
		scBoardService.updateCard(sc);
		
		rankingService.updateAvgScore(sc); //point_avg,s_id
		
		return "redirect:/admin/admin_insertCard";
	}
	
	//스코어카드 삭제
	@RequestMapping(value="/admin_insertCard_del")
	public void admin_InsertCard_del(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		String s_id = request.getParameter("s_id");
		String s_date = request.getParameter("s_date");
		
		request.setAttribute("s_id", s_id);
		request.setAttribute("s_date", s_date);
	}
	
	//스코어카드 삭제 확인
	@RequestMapping(value="/admin_insertCard_del_ok")
	public String admin_InsertCard_del_ok(HttpServletRequest request, HttpServletResponse response, ScorecardVO sv) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		String s_id = request.getParameter("s_id");
		String s_date = request.getParameter("s_date");
		
		sv.setS_id(s_id);
		sv.setS_date(s_date);
		
		scBoardService.delCard(sv);
		
		out.println("<script>");
		out.println("alert('삭제 완료')");
		out.println("<script>");
		
		return "redirect:/admin/admin_insertCard";
	}
}
