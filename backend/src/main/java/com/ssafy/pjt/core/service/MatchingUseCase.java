package com.ssafy.pjt.core.service;

import java.util.List;

import com.ssafy.pjt.core.security.Role;
import com.ssafy.pjt.web.dto.BoardRequestDTO;
import com.ssafy.pjt.web.dto.MatchingRequestDTO;


public interface MatchingUseCase {
	public List<MatchingRequestDTO> search(int lawyerUuid) throws Exception;
	public MatchingRequestDTO detail(int matchingId) throws Exception;
	//public boolean hit(int boardId) throws Exception;
	public boolean insert(MatchingRequestDTO matching) throws Exception;
	public boolean delete(int matchingId) throws Exception;
	public boolean update(MatchingRequestDTO matching) throws Exception;
	public boolean check(int matchingId,int uuid, Role role) throws Exception;
}
