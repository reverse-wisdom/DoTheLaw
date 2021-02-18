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
import com.ssafy.pjt.web.dto.BoardRequestDTO;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/board")
//@CrossOrigin(origins = { "http://localhost:8080" })
@RequiredArgsConstructor
public class BoardControllor {

	@Autowired
	private BoardService boardService;

	@ApiOperation(value = "커뮤니티 글 전체")
	@GetMapping("/search/all")
	private ResponseEntity<List<BoardRequestDTO>> all() {
		List<BoardRequestDTO> list;
		try {
			list = boardService.all();
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "글 조회")
	@GetMapping("/search/detail")
	private ResponseEntity<BoardRequestDTO> detail(@RequestParam(required = true) final int boardId) {
		BoardRequestDTO board;
		try {
			boardService.hit(boardId);
			board = boardService.detail(boardId);
			return new ResponseEntity<>(board, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "조회수 증가(boardId만 있으면 가능)")
	@PutMapping("/hit")
	private ResponseEntity<String> hit(@Valid @RequestBody BoardRequestDTO board) {
		try {
			boardService.hit(board.getBoardId());
			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("FAIL", HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "글 생성")
	@PostMapping("/create")
	private ResponseEntity<String> create(@Valid @RequestBody BoardRequestDTO board) {
		try {
			boardService.insert(board);
			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "글 삭제")
	@DeleteMapping("/delete")
	private ResponseEntity<String> delete(@RequestParam(required = true) final int boardId,
			@RequestParam(required = true) final int uuid) {
		try {
			if (boardService.check(boardId, uuid)) {				
				boardService.delete(boardId);
				return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
			} else
				return new ResponseEntity<>("FAIL", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "글 수정")
	@PutMapping("/update")
	private ResponseEntity<String> update(@Valid @RequestBody BoardRequestDTO board) {
		try {
			if(boardService.check(board.getBoardId(), board.getUuid())) {
				boardService.update(board);
				return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
			}else {
				return new ResponseEntity<>("FAIL", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
		}
	}

//  @ApiOperation(value = "글 검색")
//  @GetMapping("/search/keyword")
//	private ResponseEntity<String> keyword(@RequestParam(required = true) final String key,) {    	
//  	if(service.checkEmail(email)) {   
//  		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
//  	}		
//		return new ResponseEntity<>("FAIL", HttpStatus.NO_CONTENT);
//	}
	
//    @PostMapping("/image")
//    @ResponseBody
//    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
//        try {
//            UploadFile uploadedFile = imageService.store(file);
//            return ResponseEntity.ok().body("/image/" + uploadedFile.getId());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().build();
//        }
//    }


}
