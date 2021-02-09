package com.ssafy.pjt.core.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.pjt.web.dto.MessageDTO;


@Mapper
public interface MessageMapper {

	int insertMessage(MessageDTO message);
	List<MessageDTO> getMessagesByMatchingId(int matchingId);

}
