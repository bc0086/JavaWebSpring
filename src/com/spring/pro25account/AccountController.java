package com.spring.pro25account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class AccountController extends MultiActionController {
	private AccountService accService;
	
	// accService 빈을 주입하기 위해 setter를 구현해야 함
	public void setAccService(AccountService accService) {
		this.accService = accService;
	}
	
	// /account/sendMoney.do로 요청 시 sendMoney()를 호출해 계좌이체함
	public ModelAndView sendMoney(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		accService.sendMoney(); // 금액을 이체하게됨
		mav.setViewName("result");
		return mav;
	}
}
