package net.balgre.controller;

import net.balgre.domain.*;
import net.balgre.dto.LoginDTO02;
import net.balgre.service.QnaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class QnaController {
	
	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	
	@Autowired
	private QnaService qnaService;
	
	
	/*qna move controller*/
	@RequestMapping("/goQna")
	public String goQna() throws Exception {
		
		logger.info("Qna insert form success");
		
		return "qna/qnaForm";
	}
	
	
	/*@RequestMapping("/goQnaList")
	public String goQnaList() throws Exception {
		
		logger.info("Qna list form success");
		
		return "qna/qnaListForm";
	}*/
	
	
	/*qna post controller*/
	@RequestMapping(value = "/input_form", method = RequestMethod.POST)
	public String qnaInsert(Model model, HttpSession session, Qna qna, String token) throws Exception {
		
		logger.info("qna : " + qna);
		
		LoginDTO02 login = (LoginDTO02)session.getAttribute("login");
		
		logger.info("token 값 : " + login);
		
		CommonResponse res = qnaService.qnaInsert1(qna, login.getToken());
		
		logger.info("CTRL : " + res);
		
		return "redirect:/my/qnaList";
	}
	
	
	@RequestMapping("/my/qna")
	public String goQnaForm(Model model, HttpSession session, String token) {
		
		LoginDTO02 login = (LoginDTO02)session.getAttribute("login");
		
		UserResponse res = qnaService.getUserInfo(login.getToken());
		
		User user = res.getUser();
		
		model.addAttribute("User", user);
		
		logger.info("getUser : " + user);
				
		return "myPage/qna/qnaForm2";
	}
	
	
	@RequestMapping("/detail")
	public String detailAjax() {
		return "myPage/qna/qnaReplyForm";
	}
	
	
	@RequestMapping("/my/qnaDetail2")
	public String qnaDetail2(Model model, HttpSession session, String token, @RequestParam(value="qna", required=false) String qna) {
		
		LoginDTO02 login = (LoginDTO02)session.getAttribute("login");
		logger.info("login : " + login);
		
		model.addAttribute("qna", qna);
		logger.info("qna : " + qna);
		
		return "myPage/qna/qnaReplyForm";
	}
	
	
	/*qna list response controller*/
	@RequestMapping("/my/qnaList")
	public String qnaListResponse(Model model, HttpSession session, String token) throws Exception {
		
		LoginDTO02 login = (LoginDTO02)session.getAttribute("login");
		
		logger.info("token 값 : " + login);
		
		QnaListResponse res = qnaService.qnaListResponse(login.getToken());
		
		logger.info("CTRL : " + res);
		
		List<Qna> qnaList = res.getQnaList();
		Qna qna = new Qna();
		
        for(int i=0; i<qnaList.size(); i++) {
			
			qna = res.getQnaList().get(i);
			
			logger.info("Time!!!!! : " + qna.getRegDate());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        
	        Date date = new Date(Long.parseLong(qna.getRegDate()));
	        
	        logger.info("date success = " + sdf.format(date));
	         
	        String regDate = sdf.format(date);
	        
	        logger.info("String regDate = " + regDate);
	        
	        qna.setRegDate(regDate);
	        
			model.addAttribute("list", qnaList);
				
		}


        model.addAttribute("list", qnaList);
        
        return "myPage/qna/qnaListForm";

    }
	
	
	/*qna delete*/
	@RequestMapping(value = "/qna/delete", method = RequestMethod.POST)
	public String qnaDelete(Model model, HttpSession session, String token, int id) {
		
		LoginDTO02 login = (LoginDTO02)session.getAttribute("login");
		
		logger.info("token 값 : " + login);
		
		QnaListResponse res = qnaService.qnaDelete(login.getToken(), id);
		
		logger.info("CTRL : " + res);
		
		return "redirect:/my/qnaList";
	}
	

}
