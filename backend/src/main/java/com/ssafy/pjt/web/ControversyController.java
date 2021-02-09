package com.ssafy.pjt.web;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.ssafy.pjt.provider.service.ControversyService;
import com.ssafy.pjt.web.dto.ControversyDTO;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/controversy")
//@CrossOrigin(origins = { "http://localhost:8080" })
@RequiredArgsConstructor
public class ControversyController {

	@Autowired
	private ControversyService controversyService;

	@ApiOperation(value = "찬반논쟁 글 전체")
	@GetMapping("/search/all")
	private ResponseEntity<List<ControversyDTO>> all() {
		List<ControversyDTO> list;
		try {
			list = controversyService.all();
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "찬반논쟁 글 조회")
	@GetMapping("/search/detail")
	private ResponseEntity<ControversyDTO> detail(@RequestParam(required = true) final int controversyId) {
		ControversyDTO controversy;
		try {
			controversy = controversyService.detail(controversyId);
			return new ResponseEntity<>(controversy, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}		
	}
	
	@ApiOperation(value = "찬성 증가 controversyId, uuid ")
	@PutMapping("/agree")
	private ResponseEntity<String> agree (@Valid @RequestBody ControversyDTO controversy) {		
		System.out.println(controversy);
		try {
			controversyService.overlab(controversy.getUuid(), controversy.getControversyId());
		}catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
		}		
		System.out.println("안넘어갔다");
		try {
			controversyService.hit(controversy.getControversyId(),"Y");
			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "반대 증가")
	@PutMapping("/opposition")
	private ResponseEntity<String> opposition(@Valid @RequestBody ControversyDTO controversy) {
		try {
			controversyService.overlab(controversy.getUuid(), controversy.getControversyId());
		}catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
		}	
		
		try {
			controversyService.hit(controversy.getControversyId(),"N");
			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "찬반논쟁 글 생성(ADMIN만)")
	@PostMapping("/create")
	private ResponseEntity<String> create(@Valid @RequestBody ControversyDTO controversy) {
		try {
			controversyService.insert(controversy);
			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "찬반논쟁 글 삭제")
	@DeleteMapping("/delete")
	private ResponseEntity<String> delete(@RequestParam(required = true) final int controversyId) {
		try {
			controversyService.delete(controversyId);
			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
		}
	}
	@ApiOperation(value = "찬반논쟁 글 수정")
	@PutMapping("/update")
	private ResponseEntity<String> update(@Valid @RequestBody ControversyDTO controversy) {
		try {
			System.out.println(controversy);
			controversyService.update(controversy);
			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
		}
	}

}
