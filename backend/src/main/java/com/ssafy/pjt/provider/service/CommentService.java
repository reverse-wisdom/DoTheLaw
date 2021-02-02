package com.ssafy.pjt.provider.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.core.repository.mapper.BoardMapper;
import com.ssafy.pjt.core.repository.mapper.CommentMapper;
import com.ssafy.pjt.core.security.Role;
import com.ssafy.pjt.core.service.BoardUseCase;
import com.ssafy.pjt.core.service.CommentUseCase;
import com.ssafy.pjt.core.service.dto.MemberDTO;
import com.ssafy.pjt.web.dto.BoardRequestDTO;
import com.ssafy.pjt.web.dto.CommentRequestDTO;
import com.ssafy.pjt.web.dto.MemberRequestDTO;
import com.ssafy.pjt.web.dto.SignupRequestDTO;

@Service
public class CommentService implements CommentUseCase {
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public List<CommentRequestDTO> search(int boardId) throws SQLException {
		return commentMapper.search(boardId);
	}

	@Override
	public boolean insert(CommentRequestDTO comment) throws SQLException {
		return commentMapper.insert(comment);
	}

	@Override
	public boolean delete(int commentId,int uuid) throws SQLException {
		MemberRequestDTO data = commentMapper.searchMember(uuid);
		if(data == null)return false;
		if(data.getRole().equals("ROLE_ADMIN")) {
			return commentMapper.delete(commentId);
		}
		else{//사용자 uuid와 지우려는 댓글을 작성한 uuid 같으면 삭제
			CommentRequestDTO commentDate = commentMapper.check(commentId);
			if(commentDate == null) {
				return false;
			}
			else if(commentDate.getUuid()==uuid) {
				return commentMapper.delete(commentId);
			}
		}
		return false;
	}

	@Override
	public boolean update(CommentRequestDTO comment) throws SQLException {
		MemberRequestDTO data = commentMapper.searchMember(comment.getUuid());
		if(data == null)return false;
		if(data.getRole().equals("ROLE_ADMIN")) {
			return commentMapper.update(comment);
		}else {
			CommentRequestDTO commentDate = commentMapper.check(comment.getCommentId());
			if(commentDate==null) {
				return false;
			}
			else if(commentDate.getUuid()== comment.getUuid()) {
				return commentMapper.update(comment);
			}
		}
		return false;
	}

	@Override
	public MemberRequestDTO searchMember(int uuid) throws Exception {		
		return commentMapper.searchMember(uuid);
	}


}
