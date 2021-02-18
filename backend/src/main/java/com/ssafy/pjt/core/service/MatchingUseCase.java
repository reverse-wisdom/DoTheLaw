package com.ssafy.pjt.core.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.pjt.core.security.Role;
import com.ssafy.pjt.web.dto.MatchingRequestDTO;


public interface MatchingUseCase {
	public List<MatchingRequestDTO> search(int lawyerUuid) throws Exception;
	public Map findByUUID(int uuid) throws SQLException;
	public MatchingRequestDTO detail(int matchingId) throws Exception;
	public boolean insert(MatchingRequestDTO matching) throws Exception;
	public boolean delete(int matchingId) throws Exception;
	public boolean update(MatchingRequestDTO matching) throws Exception;
	public boolean check(int matchingId,int uuid) throws Exception;
}
