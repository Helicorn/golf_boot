package com.golforyou.dao;

import java.util.List;

import com.golforyou.vo.ClassVO;

public interface ClassDAO {

	int getRowCount(ClassVO cv);
	List<ClassVO> getClassList(ClassVO cv);

}
