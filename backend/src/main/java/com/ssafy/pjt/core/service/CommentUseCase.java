package com.ssafy.pjt.core.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.pjt.core.security.Role;
import com.ssafy.pjt.core.service.dto.MemberDTO;
import com.ssafy.pjt.web.dto.BoardRequestDTO;
import com.ssafy.pjt.web.dto.CommentRequestDTO;
import com.ssafy.pjt.web.dto.MemberRequestDTO;
import com.ssafy.pjt.web.dto.SignupRequestDTO;

public interface CommentUseCase {
	public List<CommentRequestDTO> search(int boardId) throws Exception;
	public boolean insert(CommentRequestDTO comment) throws Exception;
	public boolean delete(int commentId,int uuid) throws Exception;
	public boolean update(CommentRequestDTO comment) throws Exception;
	public MemberRequestDTO searchMember (int uuid) throws Exception;
}
