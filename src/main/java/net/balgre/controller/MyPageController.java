package net.balgre.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyPageController {
	
	@RequestMapping("/my/index")
	public String myPage() {
		
		return "/myPage/myPage";
	}
	
	/*@RequestMapping("/my/qna")
	public String qna() {
		
		return "/myPage/qna/qnaForm2";
	}*/
	
	/*@RequestMapping("/my/qnaList")
	public String qnaList() {
		return "/qna/qnaListForm";
	}*/
	

}
