package com.golforyou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golforyou.dao.ClassDAO;
import com.golforyou.vo.ClassVO;

@Service
public class ClassServiceImpl implements ClassService {

	@Autowired
	private ClassDAO classDAO;

	

	@Override
	public int getRowCount(ClassVO cv) {
		// TODO Auto-generated method stub
		return this.classDAO.getRowCount(cv);
	}



	@Override
	public List<ClassVO> getClassList(ClassVO cv) {
		// TODO Auto-generated method stub
		return this.classDAO.getClassList(cv);
	}


}
