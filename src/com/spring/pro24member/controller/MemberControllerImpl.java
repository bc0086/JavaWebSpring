package com.spring.pro24member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.pro24member.service.MemberService;
import com.spring.pro24member.service.MemberServiceImpl;
import com.spring.pro24member.vo.MemberVO;

public class MemberControllerImpl extends MultiActionController implements MemberController {
	private MemberService memberService;

	// memberService 빈을 주입하기 위해 setter를 구현해야 함
	public void setMemberService(MemberServiceImpl memberService) {
		this.memberService = memberService;
	}
	
	// /member/listmembers.do로 요청 시 호출됨.
	@Override
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		List membersList = memberService.listMembers();
		
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList", membersList); 
			// 조회한 회원 정보를 ModelAndView의 addObject() 메서드를 이용해 바인딩함. 
		return mav;
	}
	
	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String fileName = uri.substring(begin, end);
		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		if (fileName.lastIndexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/"), fileName.length());
		}
		return fileName;
	}

	@Override
	public ModelAndView addMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		MemberVO memberVO = new MemberVO();
		/*
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String name=request.getParameter("name");
		String email = request.getParameter("email");
		memberVO.setId(id);
		memberVO.setPwd(pwd);
		memberVO.setName(name);
		memberVO.setEmail(email);
		 */
		
		/* MultiActionController클래스에서 제공하는 bind()메서드를 이용해 회원가입창에서
		 * 전송된 파라미터들을 memberVO 해당 속성에 자동으로 설정함. */
		bind(request, memberVO);
		
		int result = 0;
		result = memberService.addMember(memberVO);
		
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
			/* 회원정보 추가 후 ModelAndView클래스의 redirect 속성을 이용해 
			 * /member.listMembers.do로 리다이렉트함 */
		return mav;
	}
	
	@Override
	public ModelAndView removeMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		memberService.removeMember(id);
		
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	// 데이터베이스 연동작업이 없는 입력창 요청 시 뷰이름만 ModelAndView로 반환함
	// 수정창 요청시 if문으로 분기를 해줘서 구별함.
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request); // modMemberForm
		
		ModelAndView mav = new ModelAndView();
		if(viewName.equals("/modMemberForm")) {
			String id = request.getParameter("id");
			MemberVO memberVO = memberService.findMember(id);
			mav.addObject("member", memberVO);
		}
		
		mav.setViewName(viewName);
		return mav;
	}
}