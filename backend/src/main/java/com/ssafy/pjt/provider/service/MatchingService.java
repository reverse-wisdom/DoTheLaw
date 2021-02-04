package com.ssafy.pjt.provider.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.core.repository.mapper.MatchingMapper;
import com.ssafy.pjt.core.security.Role;
import com.ssafy.pjt.core.service.MatchingUseCase;
import com.ssafy.pjt.web.dto.MatchingRequestDTO;

@Service
public class MatchingService implements MatchingUseCase {
	@Autowired
	private MatchingMapper matchingMapper;

	@Override
	public List<MatchingRequestDTO> search(int lawyerUuid) throws SQLException {
		System.out.println("??");
		return matchingMapper.search(lawyerUuid);
	}

	@Override
	public MatchingRequestDTO detail(int matchingId) throws Exception {
		return matchingMapper.detail(matchingId);
	}

//	@Override
//	public boolean hit(int boardId) throws Exception {
//		return matchingMapper.hit(boardId);
//	}

	@Override
	public boolean insert(MatchingRequestDTO matching) throws Exception {
		System.out.println("???????????");
		return matchingMapper.insert(matching);
	}

	@Override
	public boolean delete(int matchingId) throws Exception {
		return matchingMapper.delete(matchingId);
	}

	@Override
	public boolean update(MatchingRequestDTO matching) throws Exception {
		System.out.println("??");
		System.out.println(matching);
		return matchingMapper.update(matching);
	}

	@Override
	public boolean check(int matchingId, int uuid, Role role) throws Exception {		
		if (role.getCode().equals("ROLE_ADMIN")) {
			return true;
		} else if(role.getCode().equals("ROLE_USER")) {
			if (matchingMapper.checkUser(matchingId) == uuid) {
				return true;
			}
		} else if(role.getCode().equals("ROLE_LAWYER")) {
			if (matchingMapper.checkLawyer(matchingId) == uuid) {
				return true;
			}
		}
		return false;
	}

}
