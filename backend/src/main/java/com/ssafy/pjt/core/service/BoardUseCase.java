package com.ssafy.pjt.core.service;

import java.util.List;

import com.ssafy.pjt.web.dto.BoardRequestDTO;


public interface BoardUseCase {
	public List<BoardRequestDTO> all() throws Exception;
	public BoardRequestDTO detail(int board_id) throws Exception;
	public boolean hit(int board_id) throws Exception;
	public boolean insert(BoardRequestDTO board) throws Exception;
	public boolean delete(int board_id) throws Exception;
	public boolean update(BoardRequestDTO board) throws Exception;
}
