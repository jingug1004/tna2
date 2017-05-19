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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.balgre.domain.BestResponse;
import net.balgre.domain.CategoryResponse2;
import net.balgre.service.ProductService;

/**
 * Created by user on 2017-04-20 오전 10:25
 * Prac / net.balgre.controller
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
 *  2017/04/21  김진국          최초 생성 
 *  2017/05/18  김진국          "민호씨! 남의 소스를 함부로 지워주지 말아주세요. 주석 처리해서 댓글남겨주시기 바래요."
 *                             "읽었다면 여기 밑에 글 남겨주시기 바래요. ^^"
 *  </pre>
 */

@Controller
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private ProductService service;

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String productGET () throws Exception {

        logger.info("lll~~~ To all products menu 1 . lll~~~");

        return "/product/product";

    }

    @RequestMapping(value = "/bestProduct", method = RequestMethod.GET)
    public String bestProductGET (Model model) throws Exception {

        logger.info("lll~~~ To best products menu 2 .. lll~~~");

        model.addAttribute("showBestProduct", service.bestResponseGET());

        logger.info("lll~~~ " + model.toString() + " lll~~~");

        logger.info("lll~~~ To model products menu 2 .. lll~~~");

        return "/product/bestProduct";

    }

    // 웹에서 박스 기능은 아직 없음.
//    @RequestMapping(value = "/boxProduct", method = RequestMethod.GET)
//    public String boxProductGET (Model model) throws Exception {
//
//        logger.info("lll~~~ To all products menu 3 ... lll~~~");
//
//        model.addAttribute("showBoxProduct", service.boxGET());
//
//        logger.info(model.toString());
//
//        logger.info("lll~~~ To all products menu 3 ... lll~~~");
//
//        return "/product/boxProduct";
//
//    }
    // 웹에서 박스 기능은 아직 없음.


    @RequestMapping(value = "/categoryProduct", method = RequestMethod.GET)
    public void categoryProductGET (Model model) throws Exception {

        logger.info("lll~~~ To category sort function 4 .... lll~~~");

        /*model.addAttribute("showCategoryProduct", service.boxGET());*/

        logger.info(model.toString());

        logger.info("lll~~~ To category sort function 4 .... lll~~~");

//        return "/product/categoryProduct";
    }

//    @RequestMapping(value = "/category2Product", method = RequestMethod.GET)
//    public void category2ProductGET (Model model) throws Exception {
//
//        logger.info("lll~~~ To category sort2 function 5 ..... lll~~~");
//
//        model.addAttribute("showCategory2Product", service.boxGET());
//
//        logger.info(model.toString());
//
//        logger.info("lll~~~ To category sort2 function 5 ..... lll~~~");
//
////        return "/product/categoryProduct";
//    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detailProductGET (@RequestParam("product_id") long product_id1,
                                  Model model) throws Exception {

        logger.info("lll~~~ To product detail page 6 ...... lll~~~");

        model.addAttribute("choiceDetailProduct", service.productDetailGET(product_id1));

        logger.info(model.toString());

        logger.info("lll~~~ To product detail page 6 ...... lll~~~");

        return "/product/productDetail";

    }
    
    
    /*time sale list by minho*/
    @RequestMapping(value = "/bg/timeSale", method = RequestMethod.GET)
    public String timeSaleList(Model model) {
    	
    	model.addAttribute("Res", service.timeSaleList2());
    	
    	logger.info("[Controller] model : " + model.toString());
    	
    	return "/timeSale/timeSale";
    }
    
    
    /*balgeure box by minho*/
    @RequestMapping(value = "/bg/box", method = RequestMethod.GET)
    public String balgeureBox(Model model) {
    	
    	model.addAttribute("Res", service.balgeureBox2());
    	
    	logger.info("[Controller] model : " + model.toString());
    	
    	return "/product/balgeureBox";
    }
    
    
    /*new product by minho*/
    @RequestMapping(value = "/bg/new", method = RequestMethod.GET)
    public String newProduct(Model model) {
    	
    	BestResponse res = service.newProduct2();
    	
    	model.addAttribute("best", res);
    	//model.addAttribute("Res", res.getProductList());
    	
    	logger.info("[Controller] model : " + model.toString());
    	
    	return "/product/new";
    }
    
    
    /*category list by minho*/
    @RequestMapping(value = "/bg/category", method = RequestMethod.GET)
    public String categoryList(Model model) {
    	
    	CategoryResponse2 res = service.categoryList2();
    	
    	model.addAttribute("category", res);
    	
    	return "/main/header";
    }

}