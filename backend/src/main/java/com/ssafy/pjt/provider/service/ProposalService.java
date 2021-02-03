package com.ssafy.pjt.provider.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.core.repository.mapper.BoardMapper;
import com.ssafy.pjt.core.repository.mapper.CommentMapper;
import com.ssafy.pjt.core.repository.mapper.ProposalMapper;
import com.ssafy.pjt.core.security.Role;
import com.ssafy.pjt.core.service.BoardUseCase;
import com.ssafy.pjt.core.service.CommentUseCase;
import com.ssafy.pjt.core.service.ProposalUseCase;
import com.ssafy.pjt.core.service.dto.MemberDTO;
import com.ssafy.pjt.web.dto.BoardRequestDTO;
import com.ssafy.pjt.web.dto.CommentRequestDTO;
import com.ssafy.pjt.web.dto.MemberRequestDTO;
import com.ssafy.pjt.web.dto.ProposalRequestDTO;
import com.ssafy.pjt.web.dto.SignupRequestDTO;

@Service
public class ProposalService implements ProposalUseCase {
	
	@Autowired
	private ProposalMapper proposalMapper;
	
	@Override
	public List<ProposalRequestDTO> search(int controversyId) throws SQLException {
		return proposalMapper.search(controversyId);
	}
	
	@Override
	public List<ProposalRequestDTO> searchAgree(int controversyId) throws SQLException {
		return proposalMapper.searchAgree(controversyId);
	}
	
	@Override
	public List<ProposalRequestDTO> searchOpposition(int controversyId) throws SQLException {
		return proposalMapper.searchOpposition(controversyId);
	}
	
	
	@Override
	public boolean insert(ProposalRequestDTO proposal) throws SQLException {
		return proposalMapper.insert(proposal);
	}

	@Override
	public boolean delete(int opinionId,int uuid) throws SQLException {
		MemberRequestDTO data = proposalMapper.searchMember(uuid);
		if(data == null)return false;
		if(data.getRole().equals("ROLE_ADMIN")) {
			return proposalMapper.delete(opinionId);
		}
		else{//사용자 uuid와 지우려는 댓글을 작성한 uuid 같으면 삭제
			ProposalRequestDTO proposalDate = proposalMapper.check(opinionId);
			if(proposalDate == null) {
				return false;
			}
			else if(proposalDate.getUuid()==uuid) {
				return proposalMapper.delete(opinionId);
			}
		}
		return false;
	}

	@Override
	public boolean update(ProposalRequestDTO proposal) throws SQLException {
		MemberRequestDTO data = proposalMapper.searchMember(proposal.getUuid());
		if(data == null)return false;
		if(data.getRole().equals("ROLE_ADMIN")) {
			return proposalMapper.update(proposal);
		}else {
			ProposalRequestDTO proposalDate = proposalMapper.check(proposal.getOpinionId());
			if(proposalDate==null) {
				return false;
			}
			else if(proposalDate.getUuid()== proposal.getUuid()) {
				return proposalMapper.update(proposal);
			}
		}
		return false;
	}

	@Override
	public MemberRequestDTO searchMember(int uuid) throws Exception {		
		return proposalMapper.searchMember(uuid);
	}


}
