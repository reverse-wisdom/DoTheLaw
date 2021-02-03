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

import com.ssafy.pjt.provider.service.ProposalService;
import com.ssafy.pjt.web.dto.ProposalRequestDTO;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/proposal")
//@CrossOrigin(origins = { "http://localhost:8080" })
@RequiredArgsConstructor
public class ProposalControllor {

	@Autowired
	private ProposalService proposalService;

	@ApiOperation(value = "찬반 댓글 조회")
	@GetMapping("/search")
	private ResponseEntity<List<ProposalRequestDTO>> search(@RequestParam(required = true) final int controversyId) {
		List<ProposalRequestDTO> list;
		try {
			list = proposalService.search(controversyId);
			for (ProposalRequestDTO proposal : list) {
				proposal.setName(proposalService.searchMember(proposal.getUuid()).getName());
			}
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "찬성 댓글 조회")
	@GetMapping("/search/agree")
	private ResponseEntity<List<ProposalRequestDTO>> searchAgree(@RequestParam(required = true) final int controversyId) {
		List<ProposalRequestDTO> list;
		try {
			list = proposalService.searchAgree(controversyId);
			for (ProposalRequestDTO proposal : list) {
				proposal.setName(proposalService.searchMember(proposal.getUuid()).getName());
			}
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	@ApiOperation(value = "반대 댓글 조회")
	@GetMapping("/search/opposition")
	private ResponseEntity<List<ProposalRequestDTO>> searchOpposition(@RequestParam(required = true) final int controversyId) {
		List<ProposalRequestDTO> list;
		try {
			list = proposalService.searchOpposition(controversyId);
			for (ProposalRequestDTO proposal : list) {
				proposal.setName(proposalService.searchMember(proposal.getUuid()).getName());
			}
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "찬반 댓글 생성")
	@PostMapping("/create")
	private ResponseEntity<String> create(@Valid @RequestBody ProposalRequestDTO proposal) {
		try {
			proposalService.insert(proposal);
			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("FAIL", HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "찬반 댓글 삭제")
	@DeleteMapping("/delete")
	private ResponseEntity<String> delete(@RequestParam(required = true) final int opinionId,
			@RequestParam(required = true) final int uuid) {
		try {
			if (proposalService.delete(opinionId, uuid))
				return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<>("FAIL", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>("FAIL", HttpStatus.OK);
	}

	@ApiOperation(value = "찬반 댓글 수정")
	@PutMapping("/update")
	private ResponseEntity<String> update(@Valid @RequestBody ProposalRequestDTO proposal) {
		try {
			if (proposalService.update(proposal))
				return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<>("FAIL", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>("FAIL", HttpStatus.OK);
	}

}
