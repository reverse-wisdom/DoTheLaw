package com.ssafy.pjt.provider.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.pjt.core.repository.mapper.MessageMapper;
import com.ssafy.pjt.web.dto.MessageDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service

public class MessageService {
	private final MessageMapper messageMapper;

//	@Override
	public int insertMessage(MessageDTO chatMessage) {
		return messageMapper.insertMessage(chatMessage);
	}

//	@Override
	public List<MessageDTO> getMessagesByMatchingId(int matchingId) {
		return messageMapper.getMessagesByMatchingId(matchingId);
	}

}
