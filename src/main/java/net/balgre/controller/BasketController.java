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
 * @see
 * @since 2017/04/10
 * <p>
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017/04/19  김진국          최초 생성
 * </pre>
 */

@Controller
public class BasketController {

    private static final Logger logger = LoggerFactory.getLogger(BasketController.class);

    @Autowired
    BasketService basketService;

    @RequestMapping(value = "/basketList", method = RequestMethod.GET)
    public String basketListGET(HttpSession session,
                                HttpServletRequest request,
                                Model model,
                                BasketResponse basketResponse) throws Exception {

        logger.debug("lll~~~ Connect basketList GET 1 . lll~~~");

        LoginDTO02 login = (LoginDTO02) session.getAttribute("login");

        model.addAttribute("basketListGET", basketService.basketListGET(login.getToken()));

        logger.debug("lll~~~ model : " + model + " 2 .. lll~~~");

        return "/basket/basket";

    }

    @RequestMapping(value = "/basketAdd", method = RequestMethod.POST)
    @ResponseBody
    public String basketAddPOST(HttpSession session,
                                HttpServletRequest request,
                                Model model,
                                BasketResponse basketResponse) throws Exception {

        LoginDTO02 login = (LoginDTO02) session.getAttribute("login");

        Map<String, String[]> param = request.getParameterMap();
        String[] itemsVm = request.getParameterValues("items[]");

        for (String item : itemsVm) {
            int itemEach = Integer.parseInt(param.get("upDown[" + item + "].cnt")[0].toString());
            long itemId = Long.parseLong(item.toString());

            basketService.basketAddPOST(login.getToken(), /*itemPrice, */ itemId, itemEach);
            
            logger.debug("ITEM ID : " + itemId);
            logger.debug("ITEM COUNT : " + itemEach);
        }

        return "basketSucc";
    }

    @RequestMapping(value = "/basketDirectPayPage", method = {RequestMethod.GET, RequestMethod.POST})
    public String basketPayPageGETPOST() throws Exception {

        return "/basket/basketPay";

    }

    @RequestMapping(value = "/basketDelete", method = RequestMethod.POST)
    @ResponseBody
    public String basketDelete(HttpSession session,
                             HttpServletRequest request,
                             Model model,
                             BasketResponse basketResponse,
                             @RequestParam("basketId") String basket_id) throws Exception {

        LoginDTO02 login = (LoginDTO02) session.getAttribute("login");

//        long basket_id = 111;

        long basketid = (long) Float.parseFloat(basket_id);

        logger.info("lll~~~ basket_id Delete 0 . " + basketid + " lll~~~");
//        long basket_id = request.getParameter(basketResponse.getBasketList());

        logger.info("lll~~~ basket_id parameter Delete 1 . " + basket_id + " lll~~~");
        logger.info("lll~~~ basket login Delete 2 .. " + login + " lll~~~");

        basketService.basketDeleteDELETE(login.getToken(), basketid);

        return "delSucc";

    }

    @RequestMapping(value = "/basketUpdate", method = RequestMethod.POST)
    @ResponseBody
    public void basketUpdatePUT(HttpSession session,
                                HttpServletRequest request,
                                Model model,
                                BasketResponse basketResponse,
                                @RequestParam("basketId") String basket_id,
                                @RequestParam("itemCount") String item_count) throws Exception {

        LoginDTO02 login = (LoginDTO02) session.getAttribute("login");

        long basketid = (long) Float.parseFloat(basket_id);
        int itemcount = (int) Float.parseFloat(item_count);

        logger.info("basketid : " + basketid + " ");
        logger.info("itemcount : " + itemcount + " ");

        basketService.basketUpdatePUT(login.getToken(), basketid, itemcount);

//        return null;

    }

    
    
    @RequestMapping(value = "/deleteBsk", method = RequestMethod.POST)
    @ResponseBody
    public String basketDelete(HttpSession session,
                             HttpServletRequest request,
                             Model model,
                             BasketResponse basketResponse,
                             @RequestParam("basketId") String basket_id[]) throws Exception {

        LoginDTO02 login = (LoginDTO02) session.getAttribute("login");
        
        
        for(String basketId:basket_id) {
        	basketService.basketDeleteDELETE(login.getToken(), Long.parseLong(basketId));
        }

        return "delSucc";

    }
}