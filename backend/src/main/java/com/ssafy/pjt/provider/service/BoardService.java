package com.ssafy.pjt.provider.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.core.entity.Member;
import com.ssafy.pjt.core.repository.BoardRepository;
import com.ssafy.pjt.core.repository.MemberRepository;
import com.ssafy.pjt.core.service.BoardUseCase;
import com.ssafy.pjt.core.service.SignupUseCase;
import com.ssafy.pjt.core.service.dto.MemberDTO;
import com.ssafy.pjt.provider.security.JwtAuthTokenProvider;
import com.ssafy.pjt.web.dto.BoardRequestDTO;
import com.ssafy.pjt.web.dto.SignupRequestDTO;

import lombok.RequiredArgsConstructor;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class BoardService implements BoardUseCase{
	@Autowired
	private SqlSession sqlSession;	
	
	@Override
	public List<BoardRequestDTO> all() throws SQLException {		  
		return sqlSession.getMapper(BoardRepository.class).all();
	}

	@Override
	public BoardRequestDTO detail(int board_id) throws Exception {
		return sqlSession.getMapper(BoardRepository.class).detail(board_id);
	}
	
	@Override
	public boolean hit(int board_id) throws Exception {
		return sqlSession.getMapper(BoardRepository.class).hit(board_id);
	}
	
	@Override
	public boolean insert(BoardRequestDTO board) throws Exception {
		return sqlSession.getMapper(BoardRepository.class).insert(board);
	}

	@Override
	public boolean delete(int board_id) throws Exception {	
		return sqlSession.getMapper(BoardRepository.class).delete(board_id);
	}

	@Override
	public boolean update(BoardRequestDTO board) throws Exception {
		return sqlSession.getMapper(BoardRepository.class).update(board);
	}




}
