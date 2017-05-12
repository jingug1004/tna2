/**
 * Created by user on 2017-04-19 오후 1:57
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

import net.balgre.domain.BasketResponse;
import net.balgre.dto.LoginDTO02;
import net.balgre.service.BasketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by user on 2017-04-19 오후 1:57
 * Prac / net.balgre.controller
 * No pain, No gain!
 * What :
 * Why :
 * How :
 *
 * @author 숨 크리에이티브 개발팀 김진국
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017/04/19  김진국          최초 생성
 *  </pre>
 * @since 2017/04/10
 */

@Controller
public class BasketController {

    private static final Logger logger = LoggerFactory.getLogger(BasketController.class);

    @Autowired
    BasketService basketService;

    @RequestMapping(value = "/basketList", method = RequestMethod.GET)
    public String basketGET() throws Exception {

        logger.info("lll~~~ 장바구니 리스트 접속 GET 1 . lll~~~");

        return "/basket/basket";

    }

    //    @RequestMapping(value = "/basketAdd", method = RequestMethod.POST)
//    @ResponseBody
//    public BasketResponse basketPOST(HttpSession session,
//                                     ProductItem productItem,
//                                     Model model) throws Exception {
//
//        LoginDTO02 loginDTO02 = (LoginDTO02)session.getAttribute("login");
//
//        BasketResponse response = basketService.basketPOST(loginDTO02.getToken(), productItem);
//
//        logger.info("lll~~~ 장바구니 상품 등록 POST 2 .. token : " + loginDTO02.getToken() + " + response : " + response.toString() + " lll~~~");
//
//        return response;
//
//    }

    @RequestMapping(value = "/basketAdd", method = RequestMethod.POST)
    @ResponseBody
    public String basketPOST(HttpSession session,
                             HttpServletRequest request,
                             @RequestParam("items[]")  String[] itemsVm,
                             Model model,
                             BasketResponse basketResponse) throws Exception {

        LoginDTO02 login = (LoginDTO02)session.getAttribute("login");

        logger.info("lll~~~ " + itemsVm[0] + " 1 . lll~~~");

        Map<String, String[]> param = request.getParameterMap();

        System.out.println("lll~~~ Request? " + param + " 2 .. lll~~~");

//        logger.info(param);
        System.out.println("lll~~~ " + param + " 2 .. lll~~~");

        for (String item:itemsVm) {

            param.get("items[" + item + "].price");

            logger.info("lll~~~ " + param + " 3-a ... lll~~~");

            logger.info("lll~~~ " + item + " 3 ... lll~~~");
            logger.info("lll~~~ " + param.get("items[" + item + "].price")[0] + " 4 .... lll~~~");
            logger.info("lll~~~ " + param.get("upDown[" + item + "].cnt")[0] + " 4 카운트 .... lll~~~");

            int curPage = Integer.parseInt(param.get("items[" + item + "].price")[0].toString());
            long nextPage = Long.parseLong(param.get("upDown[" + item + "].cnt")[0].toString());

//            model.addAttribute("basketListModel", basketService.basketPOST(login.getToken(), curPage, nextPage));

            basketService.basketPOST(login.getToken(), curPage, nextPage);

//            basketService.basketPOST();

            logger.info("lll~~~ " + curPage + " 5  커 페이지 변환 ..... lll~~~");
            logger.info("lll~~~ " + nextPage + " 5 넥스트 페이지 변환 ..... lll~~~");

        }

        System.out.println("lll~~~ " + request.getParameterMap() + " 5 ..... lll~~~" );

//        BasketResponse res = basketService.basketPOST(login.getToken(), items.length);

//        return "redirect:/basket/basket";
        return "basketSucc";
    }

}