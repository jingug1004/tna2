package net.balgre.controller;

import net.balgre.service.MainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by user on 2017-04-10 오후 5:44
 * PrjBalgre / net.balgre.controller
 * No pain, No gain!
 * What : 테스트 클래스
 * Why :
 * How : 테스트 하고 지울꺼임. 신경쓰지 마셈.
 *
 * @author  김진국
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017/04/10  김진국          최초 생성
 *  </pre>
 * @since ex) 2017/04/10
 */
@Controller
//@RestController
//@RequestMapping("/api/*")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private MainService mainService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String test(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        System.out.println("main test!");

        logger.info("lll~~~ test Index ................. lll~~~");

        return "/myPage/test/";

    }

//    @RequestMapping(value = "/api/product/detail", method = RequestMethod.GET)
//    public void mainGET(Model model, Long a) throws Exception {
//
//        logger.info("lll~~~ detail ................. lll~~~");
//
////        model.addAttribute("pId", mainService.id());
//
//    }

}
