/**
 * Created by user on 2017-04-17 오후 12:47
 * Prac / net.balgre.controller
 * No pain, No gain!
 * What :
 * Why :
 * How :
 *
 * @author 숨 크리에이티브 김진국
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017/04/21  김진국          최초 생성
 *  </pre>
 * @since 2017/04/11
 */

package net.balgre.controller;

import net.balgre.domain.UserResponse;
import net.balgre.dto.LoginDTO;
import net.balgre.dto.LoginDTO02;
import net.balgre.service.LoginService;
import net.balgre.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by user on 2017-04-17 오후 12:47
 * Prac / net.balgre.controller
 * No pain, No gain!
 * What :
 * Why :
 * How :
 * ID : text@test.com
 * P/W : 12345
 * @author  숨 크리에이티브 개발팀 김진국
 * @since   2017/04/10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017/04/21  김진국          최초 생성 
 *  </pre>
 */

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService service;


    @Autowired
    private UserInfoService userInfoService;
    
    // 로그인 접속
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET() throws Exception {

        logger.info("lll~~~ 로그인 페이지 접속 . lll~~~");

        return "/auth/login";

    }






    @RequestMapping(value = "/loginPost", method = RequestMethod.POST)
    public String loginPOST(LoginDTO dto,
                          HttpSession session,
                          Model model) throws Exception {

        LoginDTO02 loginDTO02 = service.login(dto);

        logger.info("lll~~~ 로그인 데이터 전달 .. lll~~~");


        if (loginDTO02 == null) {

            logger.info("lll~~~ 등록되어 있지 않은 회원, 로그인 실패! ... lll~~~");

            return "/auth/login";
        }

        model.addAttribute("userVO", loginDTO02);

        UserResponse userRes = userInfoService.getUserInfo2(loginDTO02.getToken());
        session.setAttribute("user", userRes.getUser());
      
//        logger.info("lll~~~ 토큰 : " + loginDTO02.getToken() + " lll~~~");

//        session.setAttribute("login", loginDTO02);

        logger.info("lll~~~ 마지막 세션 테스트 " + session.getAttribute("login"));

//        if (loginDTO02 != null) {
//
//            logger.info("lll~~~ 세션 접속 성공 .....  lll~~~");
//
////            int amount = 60 * 60 * 24 * 7;
//            int amount = 60 * 60 * 24 * 1;
//
//            Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));
//
//            service.keepLogin(loginDTO02.getToken(), session.getId(), sessionLimit);
//
//            logger.info("lll~~~ 로그인 데이터 전달 성공, 세션 유지 ......  lll~~~");
//
//
//        }
        
        // 기존 main/main 으로 리턴을 줄 시 url이 loginPost가 되어서
        // main 페이지에 기본적으로 뿌려주는 카테고리가 깨지게 되므로 redirect로 변경함. by minho(2017-05-25)
        return "redirect:/main";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request,
                         HttpServletResponse response,
                         HttpSession session) throws Exception {

        logger.info("lll~~~ logout..................1 lll~~~");

        Object obj = session.getAttribute("login");

        if (obj != null) {

            LoginDTO02 vo = (LoginDTO02) obj;

            logger.info("lll~~~ logout.......................2 lll~~~");

            session.removeAttribute("login");
            session.invalidate();

            logger.info("lll~~~ logout............................3 lll~~~");

//            Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");

//            if (loginCookie != null) {
//
//                logger.info("lll~~~ logout.................................4 lll~~~");
//
//                loginCookie.setPath("/");
//                loginCookie.setMaxAge(0);
//                response.addCookie(loginCookie);
//                service.keepLogin(vo.getUid(), session.getId(), new Date());
//            }
        }

        return "redirect:/main";

    }

}