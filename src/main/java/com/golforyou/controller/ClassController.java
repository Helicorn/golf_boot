package com.golforyou.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.golforyou.service.ClassService;
import com.golforyou.vo.BoardVO;
import com.golforyou.vo.ClassVO;


@Controller
public class ClassController {

	@Autowired
	private ClassService classService;

	//클래스 메인 페이지
	@RequestMapping(value="/class_main",method=RequestMethod.GET)
	public String class_main() {
		return "/class/class_main";
	}//class_main

	//필드 클래스 페이지
	@RequestMapping(value="/class_field",method=RequestMethod.GET)
	public String class_field() {
		return "/class/class_field";
	}//class_field

	//온라인 클래스 페이지
	@RequestMapping(value="/class_online",method=RequestMethod.GET)
	public String class_online() {
		return "/class/class_online";
	}//class_online

	//클래스 상세 페이지
	@RequestMapping(value="/class_detail",method=RequestMethod.GET)
	public String class_detail() {
		return "/class/class_detail";
	}//class_detail

	//클래스 장바구니 페이지
	@RequestMapping(value="/class_cart",method=RequestMethod.GET)
	public String class_cart() {
		return "/class/class_cart";
	}//class_cart

	//클래스 결제하기 페이지
	@RequestMapping(value="/class_pay",method=RequestMethod.GET)
	public String class_pay() {
		return "/class/class_pay";
	}//class_pay

	//클래스 결제완료 페이지
	@RequestMapping(value="/class_pay_ok",method=RequestMethod.GET)
	public String class_pay_ok() {
		return "/class/class_pay_ok";
	}//class_pay_ok


}
