package com.ssafy.pjt.core.repository.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.pjt.web.dto.ControversyDTO;

public interface ControversyMapper {
	public List<ControversyDTO> all() throws SQLException;
	public ControversyDTO detail(int controversyId) throws SQLException;
	public boolean hit(int controversyId) throws SQLException;
	public boolean insert(ControversyDTO board) throws SQLException;
	public boolean delete(int controversyId) throws SQLException;
	public boolean update(ControversyDTO board) throws SQLException;
}
