package com.spring.pro22member.controller;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.pro22member.service.MemberService;

public class MemberControllerImpl extends MultiActionController implements MemberController {
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
}
