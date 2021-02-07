package com.ssafy.pjt.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt.provider.service.MatchingService;
import com.ssafy.pjt.provider.service.MessageService;
import com.ssafy.pjt.web.dto.MessageDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping("/api/chat")
@RestController
@RequiredArgsConstructor
public class ChatRoomController {

	private final MatchingService matchingService;
	private final MessageService messageService;

	// 특정 채팅방 타이틀 가져오기
	@GetMapping("/room/{id}")
	public ResponseEntity<String> roomTitle(@PathVariable int id) {
		String roomTitle;
		try {
			roomTitle = matchingService.detail(id).getTitle();
			if (roomTitle != null)
				return ResponseEntity.status(HttpStatus.OK).body(roomTitle);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	// 특정 채팅방 의 메세지 가져오기
	@GetMapping("/room/message/{id}")
	public ResponseEntity<List<MessageDTO>> roomInfo(@PathVariable int id) {
//		long idx = page.equals("0") ? 0 : Integer.parseInt(page) * PAGE + 1;
		List<MessageDTO> msgList = messageService.getMessagesByMatchingId(id);
		return ResponseEntity.status(HttpStatus.OK).body(msgList);
	}

}