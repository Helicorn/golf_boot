package com.golforyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.golforyou.service.YeYagService;

@Controller
@RequestMapping("/yeyag/*")
public class YeYagController {

		//서비스추가
		@Autowired
		private YeYagService yeyagService;
		
		
		//골프장 검색페이지
		@RequestMapping(value="/field_search",method=RequestMethod.GET)
			public String field_search() {
			return "yeyag/field_search";
		}//field_search()
	
	
		//골프장 목록페이지
		@RequestMapping(value="/yeyag_list",method=RequestMethod.GET)
			public String yeyag_list() {
			return "/yeyag/YeYag_list";			
		}// yeyag_list()
		
		//골프장 목록검색완료페이지(임시)
		@RequestMapping(value="/yeyag_list_ok",method=RequestMethod.GET)
		public String yeyag_list_ok() {
		return "/yeyag/YeYag_list_ok";			
	   }// yeyag_list()
		
		
		//골프장 상세페이지
		
		@RequestMapping(value="/yeyag_main",method=RequestMethod.GET)
			public String yeyag_main() {
			return "/yeyag/YeYag_Main";
		}//yeyag_Miam
		
		@RequestMapping(value="/yeyag_main1",method=RequestMethod.GET)
		public String yeyag_main1() {
		return "/yeyag/YeYag_Main1";
	    }//yeyag_Miam1
		
		@RequestMapping(value="/yeyag_main2",method=RequestMethod.GET)
		public String yeyag_main2() {
		return "/yeyag/YeYag_Main2";
	    }//yeyag_Miam2
		
		@RequestMapping(value="/yeyag_main3",method=RequestMethod.GET)
		public String yeyag_main3() {
		return "/yeyag/YeYag_Main3";
	    }//yeyag_Miam3
		
		
		//애약하기페이지(달력)
		@RequestMapping(value="/planlist",method=RequestMethod.GET)
		public String planlist() {
		return "/yeyag/planList";
	    }//planList();
		
		//예약일정등록페이지
		@RequestMapping(value="/planwrite",method=RequestMethod.GET)
		public String planwrite() {
		return "/yeyag/planWrite";
	    }//planwrinte();
		
		//예약완료창페이지
		@RequestMapping(value="/planwriteresult",method=RequestMethod.GET)
		public String planwriteresult() {
		return "/yeyag/planWriteresult";
	    }//planwrinte();
}
