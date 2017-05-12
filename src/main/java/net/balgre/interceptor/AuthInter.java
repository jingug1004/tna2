/**
 * Created by user on 2017-04-18 오후 2:25
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

import net.balgre.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by user on 2017-04-18 오후 2:25
 * Prac / net.balgre.interceptor
 * No pain, No gain!
 * What :
 * Why :
 * How :
 *
 * @author  숨 크리에이티브 개발팀 김진국
 * @version 1.0
 * @since   2017/04/10
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017/04/18  김진국          최초 생성
 *  </pre>
 *
 */

@Component
public class AuthInter extends HandlerInterceptorAdapter {

    public static final Logger logger =
            LoggerFactory.getLogger(AuthInter.class);

    @Inject
    private LoginService service;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession();

        if (session.getAttribute("login") == null) {

            logger.info("lll~~~ 세션!!! current user is not logined... lll~~~");

            saveDest(request);

            response.sendRedirect("/login");
            return false;
        }
        return true;


    }

    public void saveDest(HttpServletRequest req) {

        String uri = req.getRequestURI();

        String query = req.getQueryString();

        if (query == null || query.equals("null")) {
            query = "";
        } else {
            query = "?" + query;
        }

        if (req.getMethod().equals("GET")) {

            logger.info("lll~~~ dest : " + (uri + query) + " lll~~~");

            req.getSession().setAttribute("dest", uri + query);

            logger.info("lll~~~ AuthInter !!! 여기까지?... lll~~~");

        }
    }
}