package com.ssafy.pjt.core.repository.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.pjt.web.dto.MatchingRequestDTO;
import com.ssafy.pjt.web.dto.MemberRequestDTO;
import com.ssafy.pjt.web.dto.ProposalRequestDTO;

public interface MatchingMapper {
	public List<MatchingRequestDTO> search(int lawyerUuid) throws SQLException;
	public List<MatchingRequestDTO> selectByUUID(int uuid) throws SQLException;
	public MatchingRequestDTO detail(int matchingId) throws SQLException;
	public boolean insert(MatchingRequestDTO matching) throws SQLException;
	public boolean delete(int matchingId) throws SQLException;
	public boolean update(MatchingRequestDTO matching) throws SQLException;
	public int checkUser(int matchingId) throws SQLException;
	public int checkLawyer(int matchingId) throws SQLException;
	
	public MemberRequestDTO searchMember (int uuid) throws SQLException;
}
