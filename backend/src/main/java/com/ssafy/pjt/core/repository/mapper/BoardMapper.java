package com.ssafy.pjt.core.repository.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.pjt.web.dto.BoardRequestDTO;
import com.ssafy.pjt.web.dto.MemberRequestDTO;

public interface BoardMapper {
	public List<BoardRequestDTO> all() throws SQLException;
	public BoardRequestDTO detail(int boardId) throws SQLException;
	public boolean hit(int boardId) throws SQLException;
	public boolean insert(BoardRequestDTO board) throws SQLException;
	public boolean delete(int boardId) throws SQLException;
	public boolean update(BoardRequestDTO board) throws SQLException;
	
	public int check(int boardId) throws SQLException;	
	public MemberRequestDTO searchMember (int uuid) throws SQLException;
}
