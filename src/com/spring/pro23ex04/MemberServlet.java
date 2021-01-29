package com.spring.pro23ex04;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.pro23ex01.MemberVO;


@WebServlet("/mem4.do")
public class MemberServlet extends HttpServlet {
	private void doHandle(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		MemberDAO dao = new MemberDAO();
		MemberVO memberVO = new MemberVO();
		
		String action = request.getParameter("action");
		String nextPage = "";
		
		// 전체 회원 정보 조회하기
		if(action==null || action.equals("listMembers")) {
			List<MemberVO> membersList = dao.selectAllMemberList();
			request.setAttribute("membersList", membersList);
			
			nextPage = "pro23ex04/listMembers.jsp";
			
		// 선택창(id)으로 회원 정보 찾기
		} else if(action.equals("selectMemberById")) {
			/* 검색조건이 selectMemberById이면 전송된 값을 getParameter()로 가져온 후
			 * SQL문의 조건식에서 id의 조건 값으로 전달함. */
			String id = request.getParameter("value");
			memberVO = dao.selectMemberById(id);
			request.setAttribute("member", memberVO);
			
			nextPage = "pro23ex04/memberInfo.jsp";
			
		// 선택창(pwd)으로 회원 정보 찾기
		} else if (action.equals("selectMemberByPwd")) {
			int pwd = Integer.parseInt(request.getParameter("value"));
			List<MemberVO> membersList = dao.selectMemberByPwd(pwd);
			request.setAttribute("membersList", membersList);
			
			nextPage = "pro23ex04/listMembers.jsp";
			
		// 회원 가입하기
		} else if (action.equals("insertMember")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			memberVO.setId(id);
			memberVO.setPwd(pwd);
			memberVO.setName(name);
			memberVO.setEmail(email);
			
			// 회원 가입창에서 전송된 회원 정보를 MemberVO에 설정한 후 insertMember()로 전달
			dao.insertMember(memberVO);
			
			nextPage = "/mem4.do?action=listMembers";
		
		// 회원 가입하기 HashMap 버전
		} else if (action.equals("insertMember2")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			Map memberMap = new HashMap();
			memberMap.put("id", id);
			memberMap.put("pwd", pwd);
			memberMap.put("name", name);
			memberMap.put("email", email);
			
			/* 회원 가입창에서 전송된 회원 정보를 HashMap에 key/value로 저장한 후
			 * MemberDAO의 insertMember2() 인자로 전달한다. */
			dao.insertMember2(memberMap);
			
			nextPage = "/mem4.do?action=listMembers";
		
		// 회원 정보 수정하기
		} else if (action.equals("updateMember")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			memberVO.setId(id);
			memberVO.setPwd(pwd);
			memberVO.setName(name);
			memberVO.setEmail(email);
			
			dao.updateMember(memberVO);
				/* 회원 수정창에서 전송된 회원 정보를 MemberVO의 속성에 설정한 후,
				 * updateMember()를 호출하면서 MemberVO 객체를 전달한다. */
			
			nextPage = "/mem4.do?action=listMembers";
		
		// 회원 정보 삭제하기
		} else if(action.equals("deleteMember")) {
			String id = request.getParameter("id"); 
				// 전달된 ID를 조건 값으로 해당 회원 정보를 삭제함.
			dao.deleteMember(id);
			
			nextPage = "mem4.do?action=listMembers";
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
}
