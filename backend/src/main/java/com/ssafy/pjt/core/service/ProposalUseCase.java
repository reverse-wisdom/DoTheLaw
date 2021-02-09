package com.ssafy.pjt.core.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.pjt.core.security.Role;
import com.ssafy.pjt.core.service.dto.MemberDTO;
import com.ssafy.pjt.web.dto.BoardRequestDTO;
import com.ssafy.pjt.web.dto.CommentRequestDTO;
import com.ssafy.pjt.web.dto.MemberRequestDTO;
import com.ssafy.pjt.web.dto.ProposalRequestDTO;
import com.ssafy.pjt.web.dto.SignupRequestDTO;

public interface ProposalUseCase {
	public List<ProposalRequestDTO> search(int controversyId) throws Exception;
	public boolean insert(ProposalRequestDTO proposal) throws Exception;
	public boolean delete(int opinionId,int uuid) throws Exception;
	public boolean update(ProposalRequestDTO proposal) throws Exception;
	public MemberRequestDTO searchMember (int uuid) throws Exception;
}
