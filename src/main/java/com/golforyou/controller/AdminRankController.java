package com.golforyou.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.ModelAndView;

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
	
	@EnableWebSecurity
	public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.cors().and().csrf().disable();
		}
		
		@Bean
	    CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("*"));
	        configuration.setAllowedMethods(Arrays.asList("*"));
	        configuration.setAllowedHeaders(Arrays.asList("*"));
	        configuration.setAllowCredentials(true);
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }

	}
	
	//????????? ??????????????? ????????????
	@RequestMapping("admin/admin_insertCard")
	public void admin_insertCard(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int needCount = scBoardService.getNeedUpdateScorecardCount(); //????????????????????? ??????????????? ??????
		List<ScorecardVO> needList = scBoardService.getNeedUpdateScorecardList(); //????????????????????? ??????????????? ??????
		
		request.setAttribute("page", page);
		request.setAttribute("needCount", needCount);
		request.setAttribute("needList", needList);
		
	}
	
	//???????????? ??? ??????????????? ?????? ????????????
	@RequestMapping("/admin_insertCard_Check")
	public ModelAndView admin_InsertCard_Check(HttpServletRequest request, HttpServletResponse response, ScboardVO sb) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		
		ModelAndView aicm = new ModelAndView();
		
		request.setCharacterEncoding("utf-8");

		String sc_id = request.getParameter("admin_id");
		String sc_playdate = request.getParameter("admin_playdate");
		
				
		sc_playdate = sc_playdate.replace("-", "_");
	
		ScboardVO info = new ScboardVO();
		info.setSc_id(sc_id);
		info.setSc_playdate(sc_playdate);
		
		sb = scBoardService.getScBoardCont(info);
		
		if(sb != null) {
			if(scBoardService.getUpdated(info) == 0) {
				//String sc_fileName = sb.getSc_file();
				
				//String folder = sc_fileName.substring(1,11);
				//String file = sc_fileName.substring(12,22);
				
				request.setAttribute("date", sc_playdate);
				//request.setAttribute("folder", folder);
				//request.setAttribute("file", file);
				request.setAttribute("id", sc_id);
				request.setAttribute("sb", sb);
				
				aicm.setViewName("/admin/admin_insertCard2");
			}else {
				aicm.setViewName("admin/admin_insertCard_null");
			}
		}else {
			aicm.setViewName("admin/admin_insertCard_null");
		}
		return aicm;
	}
	

	
	//??????????????? ???????????????
	@RequestMapping("admin/admin_insertCard_null")
	public void admin_insertCard_null() {
		
	}
	
	//??????????????? ?????? ?????? ??????
	@RequestMapping("admin_insertCard_ok")
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
		sc.setS_sumscore(point_avg); //avgscore??? sumscore??? ????????? ??? ???????????????.
		
		scBoardService.updateCard(sc);
		
		rankingService.updateAvgScore(sc); //point_avg,s_id
		
		return "redirect:/admin/admin_insertCard";
	}
	
	//??????????????? ??????
	@RequestMapping("admin/admin_insertCard_del")
	public void admin_InsertCard_del(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		String s_id = request.getParameter("s_id");
		String s_date = request.getParameter("s_date");
		
		request.setAttribute("s_id", s_id);
		request.setAttribute("s_date", s_date);
	}
	
	//??????????????? ?????? ??????
	@RequestMapping("/admin_insertCard_del_ok")
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
		out.println("alert('?????? ??????')");
		out.println("<script>");
		
		return "redirect:/admin/admin_insertCard";
	}
}
