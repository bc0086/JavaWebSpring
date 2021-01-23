package com.spring.pro21ex02;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

// 설정파일의 userMethodNameResolver프로퍼티를 사용하려면 반드시 MultiActionController를 상속받아야함
public class UserController extends MultiActionController {
	/*
	 * public ModelAndView login(HttpServletRequest request, HttpServletResponse
	 * response) throws Exception { String userID=""; String passwd=""; ModelAndView
	 * mav = new ModelAndView(); request.setCharacterEncoding("utf-8");
	 * userID=request.getParameter("userID"); passwd=request.getParameter("passwd");
	 * 
	 * mav.addObject("userID", userID); // ModelAndView에 로그인 정보를 바인딩함
	 * mav.addObject("passwd", passwd);
	 * 
	 * mav.setViewName("result"); // ModelAndView객체에 포워딩 할 JSP이름을 설정함 return mav; }
	 */

	public ModelAndView memberInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		mav.addObject("id", id);
		mav.addObject("pwd", pwd);
		mav.addObject("name", name);
		mav.addObject("email", email);

		mav.setViewName("memberInfo"); // memberInfo.jsp로 포워딩
		return mav;
	}

	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		String userID=""; 
		String passwd=""; 
		String viewName=getViewName(request); 
			//getViewName()메서드를 호출해 요청명에서 확장명.do를 제외한 뷰이름을 가져옴
		ModelAndView mav = new ModelAndView(); 
		
		request.setCharacterEncoding("utf-8");
		userID=request.getParameter("userID"); 
		passwd=request.getParameter("passwd");
	 
		mav.addObject("userID", userID); // ModelAndView에 로그인 정보를 바인딩함
		mav.addObject("passwd", passwd);
	 
		mav.setViewName(viewName);
		System.out.println("ViewName:" + viewName); // 뷰이름을 지정
		return mav; 
	} 
	
	// request객체에서 URL요청명을 가져와 do를 제외한 요청명을 구함
	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		
		if(uri==null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}
		
		int begin = 0;
		if(!(contextPath==null && "".equals(contextPath))) {
			begin = contextPath.length();
		}
		
		int end = 0;
		if(uri.indexOf(";")!= -1) {
			end = uri.indexOf(";");
		} else if(uri.indexOf("?")!= -1) {
			end=uri.indexOf("?");
		} else {
			end = uri.length();
		}
		
		String fileName = uri.substring(begin, end);
		if(fileName.indexOf(".") != -1) {
			fileName=fileName.substring(0,fileName.lastIndexOf("."));
		}
		if(fileName.lastIndexOf("/") != -1) {
			fileName=fileName.substring(fileName.lastIndexOf("/"),fileName.length());
		}
		return fileName;
	}
}
