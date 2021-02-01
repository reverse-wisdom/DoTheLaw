package com.ssafy.pjt.core;

import com.ssafy.pjt.core.entity.Member;
import com.ssafy.pjt.core.service.dto.MemberDTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommonResponse {

    private String message;
    private int status;
    private String code;
    private MemberDTO member;
}
