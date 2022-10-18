package com.golforyou.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.golforyou.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insertBoard(BoardVO b) {
		this.sqlSession.insert("board_write",b);		
	}//게시물 저장 
	
	@Override
	public List<BoardVO> getboardList(BoardVO b) {		
		return sqlSession.selectList("board_list",b);
	}//게시물 목록

	@Override
	public int getRowCount(BoardVO b) {
		return sqlSession.selectOne("board_count",b);
	}//게시물 글 수

	@Override
	public void replyboard(BoardVO rb) {
		this.sqlSession.insert("reply_in2",rb);
	}

	@Override
	public void editboard(BoardVO eb) {
		this.sqlSession.update("board_update",eb);
		
	}

	@Override
	public void delboard(int b_no) {
		this.sqlSession.delete("board_del",b_no);
		
	}

	@Override
	public BoardVO getBoardCont(int b_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateHit(int b_no) {
		this.sqlSession.update("board_hit", b_no);
		
	}

	@Override
	public BoardVO getBoard_cont(int b_no) {
		return sqlSession.selectOne("board_co",b_no);
		
	}

	@Override
	public void updateLevel(BoardVO rb) {
		this.sqlSession.update("LevelUp",rb);
		
	}

}
