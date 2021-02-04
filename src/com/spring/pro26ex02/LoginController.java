package com.spring.pro26ex02;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("loginController") // 컨트롤러 빈을 자동생성함
public class LoginController {
	@RequestMapping(value= {"/test/loginForm.do", "/test/loginForm2.do"}, 
			method= {RequestMethod.GET})
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginForm");
		return mav;
	}
	
	// get방식과 post방식 모두 처리하는 방법
	@RequestMapping(value="/test/login.do", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		
		String userID = request.getParameter("userID");
		String userName = request.getParameter("userName");
		
		mav.addObject("userID", userID);
		mav.addObject("userName",userName);
		return mav;
	}
	
// 메서드에 @RequestParam 적용하기
	// @RequestParam을 이용해 매개변수가 userID이면 그 값을 변수 userID에 자동으로 설정함
//	@RequestMapping(value="/test/login2.do", method= {RequestMethod.GET, RequestMethod.POST})
//	public ModelAndView login2(@RequestParam("userID") String userID, 
//			@RequestParam("userName") String userName, 
//			HttpServletResponse response, HttpServletRequest request)throws Exception {
//		request.setCharacterEncoding("utf-8");
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("result");
//		
//		/* String userID = request.getParameter("userID");
//		 * String userName = request.getParameter("userName"); */
//			// getParameter() 메서드를 사용할 필요가 없음.
//		
//		System.out.println("userID: "+userID);
//		System.out.println("userName: "+userName);
//		
//		mav.addObject("userID", userID);
//		mav.addObject("userName", userName);
//
//		return mav;
//	}
	
// @RequestParam의 required 속성 사용하기
	@RequestMapping(value="/test/login2.do", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login2(@RequestParam("userID") String userID, 
			@RequestParam(value="userName", required = true) String userName, 
			@RequestParam(value="email", required = false) String email, 
			HttpServletResponse response, HttpServletRequest request)throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		
		System.out.println("userID: "+userID);
		System.out.println("userName: "+userName);
		System.out.println("email: "+email);
		
		mav.addObject("userID", userID);
		mav.addObject("userName", userName);

		return mav;
	}
	
// @RequestParam 이용해 Map에 매개변수 값 설정하기
	@RequestMapping(value="/test/login3.do", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login3(@RequestParam Map<String, String> info, 
			HttpServletResponse response, HttpServletRequest request)throws Exception {
		/* @RequestParam을 이용해 Map에 전송된 매개변수 이름을 key, 값을 value로 지정함 */
		
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		
		// Map에 저장된 매개변수의 이름으로 전달된 값을 가져옴
		String userID = info.get("userID");
		String userName = info.get("userName");
		System.out.println("userID: "+userID);
		System.out.println("userName: "+userName);
	
		// @RequestParam에서 설정한 Map 이름으로 바인딩함
		mav.addObject("info", info);
		mav.setViewName("result");
		return mav;
	}
	
// @ModelAttribute 이용해 VO에 매개변수 값 설정하기
	@RequestMapping(value="/test/login4.do", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login4(@ModelAttribute("info") LoginVO loginVO, 
			HttpServletResponse response, HttpServletRequest request)throws Exception {
		/* @ModelAttribute를 이용해 전달되는 매개변수 값을 LoginVO클래스와 이름이
		 * 같은 속성에 자동으로 설정함.
		 * addObject()를 이용할 것 없이 info를 이용해 바로 JSP에서 LoginVO 속성에 접근 가능 */
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		
		System.out.println("userID: "+ loginVO.getUserID());
		System.out.println("userName: "+ loginVO.getUserName());
		
		mav.setViewName("result");
		
		return mav;
	}
	
// Model클래스 이용해 값 전달하기
	@RequestMapping(value="/test/login5.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String login5(Model model, HttpServletResponse response, HttpServletRequest request)throws Exception {
		// 메서드 호출 시 Model 클래스 객체를 생성함
		
		request.setCharacterEncoding("utf-8");
		
		// JSP에 전달할 데이터를 model에 addAttribute()메서드를 이용해서 바인딩 함
		model.addAttribute("userID", "hong");
		model.addAttribute("userName", "홍길동");
		
		return "result"; // result는 jsp이름임
	}
	
	
	
	
	
	
	
	
	
}
