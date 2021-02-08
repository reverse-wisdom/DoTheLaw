package com.ssafy.pjt.web;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt.provider.service.MessageService;
import com.ssafy.pjt.web.dto.MessageDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@CrossOrigin(origins = "*")
@Slf4j
@RequiredArgsConstructor
@RestController
public class MessageController {
	private final MessageService messageService;
	private final SimpMessagingTemplate template;

	@MessageMapping("/message")
	public void sendMessage(@Payload MessageDTO chatMessage) {
		log.info("전달 메세지 : " + chatMessage);
		
		messageService.insertMessage(chatMessage);
		template.convertAndSend("/sub/" + chatMessage.getMatchingId(), chatMessage);
	}
}
