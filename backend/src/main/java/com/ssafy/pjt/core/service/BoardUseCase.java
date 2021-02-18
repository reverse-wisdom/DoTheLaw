package com.ssafy.pjt.core.service;

import java.util.List;

import com.ssafy.pjt.core.security.Role;
import com.ssafy.pjt.web.dto.BoardRequestDTO;


public interface BoardUseCase {
	public List<BoardRequestDTO> all() throws Exception;
	public BoardRequestDTO detail(int boardId) throws Exception;
	public boolean hit(int boardId) throws Exception;
	public boolean insert(BoardRequestDTO board) throws Exception;
	public boolean delete(int boardId) throws Exception;
	public boolean update(BoardRequestDTO board) throws Exception;
	public boolean check(int boardId,int uuid) throws Exception;
}
