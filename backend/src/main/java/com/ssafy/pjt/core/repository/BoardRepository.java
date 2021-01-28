package com.ssafy.pjt.core.repository;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.pjt.web.dto.BoardRequestDTO;

public interface BoardRepository {
	public List<BoardRequestDTO> all() throws SQLException;
	public BoardRequestDTO detail(int board_id) throws SQLException;
	public boolean hit(int board_id) throws SQLException;
	public boolean insert(BoardRequestDTO board) throws SQLException;
	public boolean delete(int board_id) throws SQLException;
	public boolean update(BoardRequestDTO board) throws SQLException;
}
