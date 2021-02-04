package com.spring.pro26ex01;
	/* 어노테이션이 적용되게 하려면 해당클래스가 반드시 <component-scan>에서 설정한 
	 * 패키지나 하위 패키지에 존재해야 함 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("mainController") 
	/* @Controller 어노테이션을 이용해 MainController클래스를 빈으로 자동변환함
	 * 빈 id는 mainController */
@RequestMapping("/test")
	/* @RequestMapping을 이용해 첫번째 단계의 URL요청이 /test이면 mainController빈을 요청함 */
public class MainController {
	@RequestMapping(value="/main1.do", method=RequestMethod.GET)
		/* @RequestMapping을 이용해 두번째 단계의 URL요청이 main1.do이면 mainController빈의
		 * main1() 메서드에게 요청함.
		 * method=RequestMethod.GET으로 지정하면 GET방식으로 요청 시 해당 메서드가 호출됨 */
	public ModelAndView main1(HttpServletResponse response, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "main1");
		mav.setViewName("main");
		return mav;
	}

	@RequestMapping(value="/main2.do", method=RequestMethod.GET)
	public ModelAndView main2(HttpServletResponse response, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "main2");
		mav.setViewName("main");
		return mav;
	}
}
