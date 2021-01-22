package com.spring.pro21ex02;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

// 설정파일의 userMethodNameResolver프로퍼티를 사용하려면 반드시 MultiActionController를 상속받아야함
public class UserController extends MultiActionController {
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userID="";
		String passwd="";
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("utf-8");
		userID=request.getParameter("userID");
		passwd=request.getParameter("passwd");
		
		mav.addObject("userID", userID); // ModelAndView에 로그인 정보를 바인딩함
		mav.addObject("passwd", passwd);
		
		mav.setViewName("result"); // ModelAndView객체에 포워딩 할 JSP이름을 설정함
		return mav;
	}
}
