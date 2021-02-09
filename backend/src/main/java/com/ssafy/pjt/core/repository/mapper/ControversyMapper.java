package com.ssafy.pjt.core.repository.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataIntegrityViolationException;

import com.ssafy.pjt.web.dto.ControversyDTO;

public interface ControversyMapper {
	public List<ControversyDTO> all() throws SQLException;
	public ControversyDTO detail(int controversyId) throws SQLException;
	public boolean hit(@Param("controversyId")int controversyId, @Param("check")String check) throws SQLException;
	public boolean insert(ControversyDTO board) throws SQLException;
	public boolean delete(int controversyId) throws SQLException;
	public boolean update(ControversyDTO board) throws SQLException;
	public void overlab(@Param("uuid") int uuid, @Param("controversyId")int controversyId) throws DataIntegrityViolationException;
}
