package com.ssafy.pjt.core.repository.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.pjt.core.service.dto.MemberDTO;
import com.ssafy.pjt.web.dto.BoardRequestDTO;
import com.ssafy.pjt.web.dto.CommentRequestDTO;
import com.ssafy.pjt.web.dto.MemberRequestDTO;
import com.ssafy.pjt.web.dto.ProposalRequestDTO;
import com.ssafy.pjt.web.dto.SignupRequestDTO;

public interface ProposalMapper {
	public List<ProposalRequestDTO> search(int controversyId) throws SQLException;
	public List<ProposalRequestDTO> searchAgree(int controversyId) throws SQLException;
	public List<ProposalRequestDTO> searchOpposition(int controversyId) throws SQLException;
	public boolean insert(ProposalRequestDTO proposal) throws SQLException;
	public boolean delete(int controversyId) throws SQLException;
	public boolean update(ProposalRequestDTO proposal) throws SQLException;
	
	public MemberRequestDTO searchMember (int uuid) throws SQLException;
	public ProposalRequestDTO check(int opinionId) throws SQLException;
}
