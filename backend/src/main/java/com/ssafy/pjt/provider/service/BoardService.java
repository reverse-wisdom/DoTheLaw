package com.ssafy.pjt.provider.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.core.repository.mapper.BoardMapper;
import com.ssafy.pjt.core.service.BoardUseCase;
import com.ssafy.pjt.web.dto.BoardRequestDTO;

@Service
public class BoardService implements BoardUseCase{
	@Autowired
	private BoardMapper mapper;
	
	@Override
	public List<BoardRequestDTO> all() throws SQLException {		  
		return mapper.all();
	}

	@Override
	public BoardRequestDTO detail(int board_id) throws Exception {
		return mapper.detail(board_id);
	}
	
	@Override
	public boolean hit(int board_id) throws Exception {
		return mapper.hit(board_id);
	}
	
	@Override
	public boolean insert(BoardRequestDTO board) throws Exception {
		return mapper.insert(board);
	}

	@Override
	public boolean delete(int board_id) throws Exception {	
		return mapper.delete(board_id);
	}

	@Override
	public boolean update(BoardRequestDTO board) throws Exception {
		return mapper.update(board);
	}




}
