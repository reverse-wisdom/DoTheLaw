package com.ssafy.pjt.web;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt.core.security.Role;
import com.ssafy.pjt.provider.service.BoardService;
import com.ssafy.pjt.provider.service.CommentService;
import com.ssafy.pjt.web.dto.BoardRequestDTO;
import com.ssafy.pjt.web.dto.CommentRequestDTO;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/comment")
//@CrossOrigin(origins = { "http://localhost:8080" })
@RequiredArgsConstructor
public class CommentControllor {

	@Autowired
	private CommentService commentService;

	@ApiOperation(value = "댓글 조회")
	@GetMapping("/search")
	private ResponseEntity<List<CommentRequestDTO>> search(@RequestParam(required = true) final int boardId) {
		List<CommentRequestDTO> list;
		try {
			list = commentService.search(boardId);
			for (CommentRequestDTO comment : list) {
				comment.setName(commentService.searchMember(comment.getUuid()).getName());
			}
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "댓글 생성")
	@PostMapping("/create")
	private ResponseEntity<String> create(@Valid @RequestBody CommentRequestDTO comment) {
		try {
			commentService.insert(comment);
			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("FAIL", HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "댓글 삭제")
	@DeleteMapping("/delete")
	private ResponseEntity<String> delete(@RequestParam(required = true) final int commentId,
			@RequestParam(required = true) final int uuid) {
		try {
			if (commentService.delete(commentId, uuid))
				return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<>("FAIL", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>("FAIL", HttpStatus.OK);
	}

	@ApiOperation(value = "댓글 수정")
	@PutMapping("/update")
	private ResponseEntity<String> update(@Valid @RequestBody CommentRequestDTO comment) {
		try {
			if (commentService.update(comment))
				return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<>("FAIL", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>("FAIL", HttpStatus.OK);
	}

}
