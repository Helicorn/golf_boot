package com.golforyou.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class YeYagDAOImpl implements YeYagDAO {

	//@Autowired
	private SqlSession sqlSession;

}
