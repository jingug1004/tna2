package net.balgre.controller;

import net.balgre.domain.CommonResponse;
import net.balgre.domain.Qna;
import net.balgre.domain.QnaListResponse;
import net.balgre.dto.LoginDTO02;
import net.balgre.service.QnaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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


    @RequestMapping("/goQnaList")
    public String goQnaList() throws Exception {

        logger.info("Qna list form success");

        return "qna/qnaListForm";
    }


    /*qna post controller*/
    @RequestMapping(value = "/input_form", method = RequestMethod.POST)
    public String qnaInsert(HttpSession session, Qna qna, String token) throws Exception {

        logger.info("qna : " + qna);

        LoginDTO02 login = (LoginDTO02)session.getAttribute("login");

        logger.info("token 값 : " + login);

        CommonResponse res = qnaService.qnaInsert1(qna, login.getToken());
        // 1. service qnaInsert1 메서드 호출하면서 파라미터 전달

        System.out.println("CTRL : " + res);

        return "redirect:my/qnaList";
    }

    /*qna list response controller*/
    @RequestMapping("/my/qnaList")
    @ResponseBody
    public ModelAndView qnaListResponse(HttpSession session, String token) throws Exception {

        LoginDTO02 login = (LoginDTO02)session.getAttribute("login");

        logger.info("token 값 : " + login);

        QnaListResponse res = qnaService.qnaListResponse(login.getToken());

        logger.info("CTRL : " + res);

        List<Qna> qnaList = res.getQnaList();
        Qna qna = new Qna();
        qna = res.getQnaList().get(5);

        logger.info("Time : " + qna.getRegDate());

        ModelAndView mav = new ModelAndView();

        for(int i=0; i<qnaList.size(); i++) {

            qna = res.getQnaList().get(i);

            logger.info("Time!!!!! : " + qna.getRegDate());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date date = new Date(Long.parseLong(qna.getRegDate()));

            logger.info("date success = " + sdf.format(date));

            String regDate = sdf.format(date);

            logger.info("String regDate = " + regDate);

            qna.setRegDate(regDate);

            mav.setViewName("/qna/qnaListForm");
            mav.addObject("list", qnaList);

        }
        mav.setViewName("/qna/qnaListForm");
        mav.addObject("list", qnaList);

        return mav;

    }


	/*qna detail controller*/

}
