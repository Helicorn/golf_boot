package com.golforyou.service;

import java.util.List;

import com.golforyou.vo.ClassVO;

public interface ClassService {

	int getRowCount(ClassVO cv);
	List<ClassVO> getClassList(ClassVO cv);

}
