/**
 * Created by user on 2017-04-18 오후 2:24
 * Prac / net.balgre.interceptor
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

package net.balgre.interceptor;

import net.balgre.dto.LoginDTO02;
import retrofit2.http.Header;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by user on 2017-04-18 오후 2:24
 * Prac / net.balgre.interceptor
 * No pain, No gain!
 * What :
 * Why :
 * How :
 *
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
 *  2017/04/18  김진국          최초 생성
 *  </pre>
 */

@Component
public class LoginInter extends HandlerInterceptorAdapter {

    public static final String LOGIN = "login";

    public static final Logger logger =
            LoggerFactory.getLogger(LoginInter.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession();

        if (session.getAttribute(LOGIN) != null) {
            logger.info("lll~~~ 세션! clear login data before. lll~~~");
            session.removeAttribute(LOGIN);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {

        HttpSession session = request.getSession();

        /*
        ModelMap modelMap = modelAndView.getModelMap();
        Object userVO = modelMap.get("userVO");

        if (userVO != null) {

            logger.info("lll~~~ 세션!! new login success.. lll~~~");

            session.setAttribute(LOGIN, userVO);

            logger.info("lll~~~ 세션! 여기까지 먹히나? . 1 lll~~~" + LOGIN.toString());
            */

        ModelMap modelMap = modelAndView.getModelMap();
        LoginDTO02 userVO = (LoginDTO02)modelMap.get("userVO");

        if (userVO != null) {

            logger.info("lll~~~ 세션!! new login success.. lll~~~");

            session.setAttribute(LOGIN, userVO);
        

            logger.info("lll~~~ 세션! 여기까지 먹히나? . 1 lll~~~" + ((LoginDTO02)session.getAttribute(LOGIN)).getToken());

//            Object dest = session.getAttribute("dest");
//
//            logger.info("lll~~~ 세션!! 여기까지 먹히나? .. 2 lll~~~" + LOGIN);
//
//            response.sendRedirect(dest != null ? (String) dest : "/");
//
//            logger.info("lll~~~ 세션!!! 여기까지 먹히나? ... 3 lll~~~" + LOGIN);

        }
    }
}