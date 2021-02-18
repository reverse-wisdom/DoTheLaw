package com.ssafy.pjt.provider.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.core.repository.mapper.MatchingMapper;
import com.ssafy.pjt.core.security.Role;
import com.ssafy.pjt.core.service.MatchingUseCase;
import com.ssafy.pjt.web.dto.MatchingRequestDTO;
import com.ssafy.pjt.web.dto.MemberRequestDTO;

@Service
public class MatchingService implements MatchingUseCase {
	@Autowired
	private MatchingMapper matchingMapper;

	@Override
	public List<MatchingRequestDTO> search(int lawyerUuid) throws SQLException {
		return matchingMapper.search(lawyerUuid);
	}

	@Override
	public Map findByUUID(int uuid) throws SQLException {
		Map result = new HashMap();
		List<MatchingRequestDTO> list = matchingMapper.selectByUUID(uuid);
		List lawyer = new ArrayList(), client = new ArrayList(), others = new ArrayList();
		for (MatchingRequestDTO matchingDTO : list) {
			if (matchingDTO.getUuid() == uuid) {
				client.add(matchingDTO);
			} else if (matchingDTO.getLawyerUuid() == uuid) {
				lawyer.add(matchingDTO);
			} else
				others.add(matchingDTO);
		}
		result.put("lawyer", lawyer);
		result.put("client", client);
		result.put("others", others);
		return result;
	}

	@Override
	public MatchingRequestDTO detail(int matchingId) throws Exception {
		return matchingMapper.detail(matchingId);
	}

	@Override
	public boolean insert(MatchingRequestDTO matching) throws Exception {
		return matchingMapper.insert(matching);
	}

	@Override
	public boolean delete(int matchingId) throws Exception {
		return matchingMapper.delete(matchingId);
	}

	@Override
	public boolean update(MatchingRequestDTO matching) throws Exception {
		return matchingMapper.update(matching);
	}

	@Override
	public boolean check(int matchingId, int uuid) throws Exception {
		MemberRequestDTO data = matchingMapper.searchMember(uuid);
		if (data.getRole().equals("ROLE_ADMIN")) {
			return true;
		} else if (data.getRole().equals("ROLE_USER")) {
			if (matchingMapper.checkUser(matchingId) == uuid) {
				return true;
			}
		} else if (data.getRole().equals("ROLE_LAWYER")) {
			if (matchingMapper.checkLawyer(matchingId) == uuid) {
				return true;
			}
		}
		return false;
	}

}
