/**
 * Created by user on 2017-04-20 오전 10:25
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

import net.balgre.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by user on 2017-04-20 오전 10:25
 * Prac / net.balgre.controller
 * No pain, No gain!
 * What :
 * Why :
 * How :
 *
 * @author 숨 크리에이티브 개발팀 김진국
 * @since 2017/04/10
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
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private ProductService service;

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String productGET () throws Exception {

        logger.info("lll~~~ 전체 상품 메뉴로 1 . lll~~~");

        return "/product/product";

    }

    @RequestMapping(value = "/bestProduct", method = RequestMethod.GET)
    public String bestProductGET (Model model) throws Exception {

        logger.info("lll~~~ 베스트 상품 메뉴로 2 .. lll~~~");

        model.addAttribute("showBestProduct", service.bestResponseGET());

        logger.info("lll~~~ " + model.toString() + " lll~~~");

        logger.info("lll~~~ 모델 베스트 상품 메뉴로 2 .. lll~~~");

        return "/product/bestProduct";

    }

    // 웹에서 박스 기능은 아직 없음.
    @RequestMapping(value = "/boxProduct", method = RequestMethod.GET)
    public String boxProductGET (Model model) throws Exception {

        logger.info("lll~~~ 박스 상품 메뉴로 3 ... lll~~~");

        model.addAttribute("showBoxProduct", service.boxGET());

        logger.info(model.toString());

        logger.info("lll~~~ 박스 상품 메뉴로 3 ... lll~~~");

        return "/product/boxProduct";

    }
    // 웹에서 박스 기능은 아직 없음.

    @RequestMapping(value = "/categoryProduct", method = RequestMethod.GET)
    public void categoryProductGET (Model model) throws Exception {

        logger.info("lll~~~ 카테고리 정렬 기능으로 4 .... lll~~~");

        model.addAttribute("showCategoryProduct", service.boxGET());

        logger.info(model.toString());

        logger.info("lll~~~ 카테고리 정렬 기능으로 4 .... lll~~~");

//        return "/product/categoryProduct";
    }

    @RequestMapping(value = "/category2Product", method = RequestMethod.GET)
    public void category2ProductGET (Model model) throws Exception {

        logger.info("lll~~~ 카테고리2 정렬 기능으로 5 ..... lll~~~");

        model.addAttribute("showCategory2Product", service.boxGET());

        logger.info(model.toString());

        logger.info("lll~~~ 카테고리2 정렬 기능으로 5 ..... lll~~~");

//        return "/product/categoryProduct";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detailProductGET (@RequestParam("product_id") long product_id1,
                                  Model model) throws Exception {

        logger.info("lll~~~ 상품 상세 페이지로 6 ...... lll~~~");

        model.addAttribute("choiceDetailProduct", service.productDetailGET(product_id1));

        logger.info(model.toString());

        logger.info("lll~~~ 상품 상세 페이지로 6 ...... lll~~~");

        return "/product/productDetail";

    }

}