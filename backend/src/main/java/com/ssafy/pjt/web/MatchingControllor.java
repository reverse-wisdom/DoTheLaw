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
import com.ssafy.pjt.provider.service.MatchingService;
import com.ssafy.pjt.web.dto.MatchingRequestDTO;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/matching")
//@CrossOrigin(origins = { "http://localhost:8080" })
@RequiredArgsConstructor
public class MatchingControllor {

	@Autowired
	private MatchingService matchingService;

	@ApiOperation(value = "해당 변호사 자문 전체")
	@GetMapping("/search")
	private ResponseEntity<List<MatchingRequestDTO>> search(@RequestParam(required = true) final int lawyerUuid) {
		List<MatchingRequestDTO> list;
		try {
			System.out.println("??");
			list = matchingService.search(lawyerUuid);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "자문 조회")
	@GetMapping("/search/detail")
	private ResponseEntity<MatchingRequestDTO> detail(@RequestParam(required = true) final int matchingId) {
		MatchingRequestDTO matching;
		try {
			//boardService.hit(boardId);
			matching = matchingService.detail(matchingId);
			return new ResponseEntity<>(matching, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}


	@ApiOperation(value = "자문 생성")
	@PostMapping("/create")
	private ResponseEntity<String> create(@Valid @RequestBody MatchingRequestDTO matching) {
		try {
			System.out.println(matching);
			matchingService.insert(matching);
			System.out.println(matching);
			//matchingService.insert(matching);
			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("FAIL", HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "자문 삭제")
	@DeleteMapping("/delete")
	private ResponseEntity<String> delete(@RequestParam(required = true) final int matchingId,
			@RequestParam(required = true) final int uuid, @RequestParam(required = true) final Role role) {
		try {
			if (matchingService.check(matchingId, uuid, role)) {				
				matchingService.delete(matchingId);
				return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
			} else
				return new ResponseEntity<>("FAIL", HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			return new ResponseEntity<>("FAIL", HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "자문 상태 수정")
	@PutMapping("/update")
	private ResponseEntity<String> update(@Valid @RequestBody MatchingRequestDTO matching, @RequestParam(required = true) final Role role) {
		System.out.println("?");
		try {
			if(matchingService.check(matching.getMatchingId(), matching.getUuid(), role)) {
				System.out.println("??");
				matchingService.update(matching);
				return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
			}else {
				return new ResponseEntity<>("FAIL", HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("FAIL", HttpStatus.NO_CONTENT);
		}
	}



}
