package com.golforyou.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.golforyou.config.auth.PrincipalDetails;

@Repository("userAuthDAO")
public class UserAuthDAO {
	
	  @Autowired
	    private SqlSessionTemplate sqlSession;
	  
	  
	  public PrincipalDetails getUserById(String username) {
	        return sqlSession.selectOne("member.selectUserById", username);
	    }
}
