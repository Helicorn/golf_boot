package com.golforyou.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.golforyou.service.IndivService;
import com.golforyou.service.RankingService;
import com.golforyou.vo.ScorecardVO;

@Controller
public class IndivrankController {
	@Autowired
	private IndivService indivService;
	
	@Autowired
	private RankingService rankingService;
	
	//개인랭크 페이지
	@GetMapping("/tier/indivrank")
	public ModelAndView indivrank(ScorecardVO sv, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView im = new ModelAndView();
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("rId");
		if(id == null) {
			HttpSession session = request.getSession();
			if((String)session.getAttribute("id") == null) {
				out.println("<script>");
				out.println("alert('로그인부터 하세요')");
				out.println("history.back();");
				out.println("</script>");
			}else {
				id = (String)session.getAttribute("id");
			}			
		}
		sv.setS_id(id);
		
		String rPoint = request.getParameter("rPoint_");
		if(rPoint == null) {
			rPoint = indivService.getPoint(id);
		}
		String rankno = request.getParameter("rankno");
		
		int getCount = rankingService.playCount(id);
		String s_handicap = indivService.getHandicap(id);
		if(rankno == null) {
			int num = 1;
			
			List<String> row_id = indivService.getRowNum(id);
			
			while(num == getCount) {
				if(row_id.get(num).equals(id)) {
					break;
				}
				++num;
			}			
			rankno = num+"";
		}
		List<Integer> put = new ArrayList<>();
		put = indivService.getPutting(id);
		int sumPutting = 0;
		for(int i=0 ; i<getCount ; ++i) {						
			sumPutting += put.get(i);
		}
		double avgPutting = (double)sumPutting/getCount;
		String strPutting = String.format("%.2f", avgPutting);
		
		Calendar cal = Calendar.getInstance();
		int intyear = cal.get(Calendar.YEAR);
		String year = Integer.toString(intyear);
		
		int jan = 0;int feb = 0;int mar = 0;		
		int apr = 0;int may = 0;int jun = 0;
		int jul = 0;int aug = 0;int sep = 0;
		int oct = 0;int nov = 0;int dec = 0;
				
		for(int i=1 ; i<=12 ; i++) {
			if(i >= 1 && i <= 9) {
				sv.setGraph_date(year+"_0"+i+"%");
			}else if(i >= 10 && i <= 12) {
				sv.setGraph_date(year+"_1"+i+"%");
			}
			
			if(i == 1) {
				jan = indivService.monthCount(sv);
			}else if(i == 2) {
				feb = indivService.monthCount(sv);
			}else if(i == 3) {
				mar = indivService.monthCount(sv);
			}else if(i == 4) {
				apr = indivService.monthCount(sv);
			}else if(i == 5) {
				may = indivService.monthCount(sv);
			}else if(i == 6) {
				jun = indivService.monthCount(sv);
			}else if(i == 7) {
				jul = indivService.monthCount(sv);
			}else if(i == 8) {
				aug = indivService.monthCount(sv);
			}else if(i == 9) {
				sep = indivService.monthCount(sv);
			}else if(i == 10) {
				oct = indivService.monthCount(sv);
			}else if(i == 11) {
				nov = indivService.monthCount(sv);
			}else if(i == 12) {
				dec = indivService.monthCount(sv);
			}
		}
		
		List<String> viewDate = new ArrayList<>();
		List<String> viewLocation = new ArrayList<>();
		List<Integer> viewBestScore = new ArrayList<>();
		List<Integer> viewRange = new ArrayList<>();
		for(int i=0 ; i<getCount ; ++i) {
			viewDate = indivService.getDate(id);
			viewLocation = indivService.getLocation(id);
			viewBestScore = indivService.getBestScore(id);
			viewRange = indivService.getRange(id);
			
			im.addObject("viewDate"+i, viewDate.get(i));
			im.addObject("viewLocation"+i, viewLocation.get(i));
			im.addObject("viewBestScore"+i, viewBestScore.get(i));
			im.addObject("viewRange"+i, viewRange.get(i));
		}
		
		int point = indivService.getSumPoint(id);
		String tierURL = null;
		String tierStr = null;
		
		if(point < -15){
			tierURL = "/resources/images/t_d.png";
			tierStr = "다이아몬드";
		}else if(point >= -15 && point < -10){
			tierURL = "/resources/images/t_p.png";
			tierStr = "플레티넘";
		}else if(point >= -10 && point < -5){
			tierURL = "/resources/images/t_g.png";
			tierStr = "골드";
		}else if(point >= -5 && point < 5){
			tierURL = "/resources/images/t_s.png";
			tierStr = "실버";
		}else{
			tierURL = "/resources/images/t_b.png";
			tierStr = "브론즈";
		}
		
		im.addObject("id", id);
		im.addObject("rPoint", rPoint);
		im.addObject("rankno", rankno);
		im.addObject("getCount", getCount);
		im.addObject("s_handicap", s_handicap);
		im.addObject("strPutting", strPutting);
		im.addObject("jan", jan);
		im.addObject("feb", feb);
		im.addObject("mar", mar);
		im.addObject("apr", apr);
		im.addObject("may", may);
		im.addObject("jun", jun);
		im.addObject("jul", jul);
		im.addObject("aug", aug);
		im.addObject("sep", sep);
		im.addObject("oct", oct);
		im.addObject("nov", nov);
		im.addObject("dec", dec);
		im.addObject("dec", dec);
		im.addObject("tierURL", tierURL);
		im.addObject("tierStr", tierStr);
		
		return im;
	}
	
	@RequestMapping(value="/tier/scorecardImg")
	public void scorecardImg() {
		
	}
}
