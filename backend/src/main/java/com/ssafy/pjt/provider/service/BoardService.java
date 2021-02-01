package com.ssafy.pjt.provider.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.core.repository.mapper.BoardMapper;
import com.ssafy.pjt.core.security.Role;
import com.ssafy.pjt.core.service.BoardUseCase;
import com.ssafy.pjt.web.dto.BoardRequestDTO;

@Service
public class BoardService implements BoardUseCase {
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<BoardRequestDTO> all() throws SQLException {
		return boardMapper.all();
	}

	@Override
	public BoardRequestDTO detail(int boardId) throws Exception {
		return boardMapper.detail(boardId);
	}

	@Override
	public boolean hit(int boardId) throws Exception {
		return boardMapper.hit(boardId);
	}

	@Override
	public boolean insert(BoardRequestDTO board) throws Exception {
		return boardMapper.insert(board);
	}

	@Override
	public boolean delete(int boardId) throws Exception {
		return boardMapper.delete(boardId);
	}

	@Override
	public boolean update(BoardRequestDTO board) throws Exception {
		return boardMapper.update(board);
	}

	@Override
	public boolean check(int boardId, int uuid, Role role) throws Exception {
		if (role.getCode().equals("ROLE_ADMIN")) {
			return true;
		} else {
			if (boardMapper.check(boardId) == uuid) {
				return true;
			}
		}
		return false;
	}

}
