package com.ssafy.pjt.core.repository.mapper;

import java.util.List;

import com.ssafy.pjt.web.dto.SearchDictDTO;
import com.ssafy.pjt.web.dto.SearchRequestDTO;

public interface SearchMapper {

	void hit(String word);
	List<SearchRequestDTO> selectHitTop();
	List<SearchDictDTO> selectDictAll();
}
