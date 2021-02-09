package com.ssafy.pjt.core.service;

import java.util.List;

import com.ssafy.pjt.core.security.Role;
import com.ssafy.pjt.web.dto.ControversyDTO;


public interface ControversyUseCase {
	public List<ControversyDTO> all() throws Exception;
	public ControversyDTO detail(int controversyId) throws Exception;
	public boolean hit(int controversyId) throws Exception;
	public boolean insert(ControversyDTO controversy) throws Exception;
	public boolean delete(int controversyId) throws Exception;
	public boolean update(ControversyDTO controversy) throws Exception;
}
