package com.golforyou.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golforyou.dao.CustomerDAO;

@Service
public class CustomerServiceImpl implements CustomerService {

	//@Autowired
	private CustomerDAO customerDAO;
}
