package com.golforyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.golforyou.service.CustomerService;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/customer_main", method = RequestMethod.GET)
	public String Customer_main() {
		return "customer/main"; 
		
		
	} //고객센터 메인 페이지 

	
	@RequestMapping(value = "/customer_fnq", method = RequestMethod.GET)
	public String customer_onebyoneFnq() {
		return "customer/onebyoneFnq"; 
	} //환불정책
	
	@RequestMapping(value = "/customer_sitepolicy", method = RequestMethod.GET)
	public String customer_SitePolicy() {
		return "customer/SitePolicy"; 
	} //환불정책
	
	@RequestMapping(value = "/customer_holeinone", method = RequestMethod.GET)
	public String customer_holeInOne() {
		return "customer/holeInOne"; 
	} //홀인원 보험
	
	
}
