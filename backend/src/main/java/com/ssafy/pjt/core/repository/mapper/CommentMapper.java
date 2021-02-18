package com.ssafy.pjt.core.repository.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.pjt.core.service.dto.MemberDTO;
import com.ssafy.pjt.web.dto.BoardRequestDTO;
import com.ssafy.pjt.web.dto.CommentRequestDTO;
import com.ssafy.pjt.web.dto.MemberRequestDTO;
import com.ssafy.pjt.web.dto.SignupRequestDTO;

public interface CommentMapper {
	public List<CommentRequestDTO> search(int boardId) throws SQLException;
	public boolean insert(CommentRequestDTO comment) throws SQLException;
	public boolean delete(int commentId) throws SQLException;
	public boolean update(CommentRequestDTO comment) throws SQLException;
	
	public MemberRequestDTO searchMember (int uuid) throws SQLException;
	public CommentRequestDTO check(int commentId) throws SQLException;
}
