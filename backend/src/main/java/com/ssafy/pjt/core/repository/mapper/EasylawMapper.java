package com.ssafy.pjt.core.repository.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.pjt.web.dto.EasylawDTO;

public interface EasylawMapper {
	EasylawDTO selectByKeyword(String keyword) throws SQLException;
	List<EasylawDTO> searchBykeyword(String keyword) throws SQLException;
	EasylawDTO selectById(int id) throws SQLException;
}
