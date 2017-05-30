package net.balgre.controller;

import net.balgre.domain.BasketResponse;
import net.balgre.domain.CategoryLvResult;
import net.balgre.domain.CategoryResponse;
import net.balgre.domain.WishListResponse;
import net.balgre.dto.LoginDTO02;
import net.balgre.service.BasketService;
import net.balgre.service.MainService;
import net.balgre.service.ProductService;
import net.balgre.service.WishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017-04-11 오전 10:06
 * Prac / net.balgre.controller
 * No pain, No gain!
 * What :
 * Why :
 * How :
 *
 * @author  숨 크리에이티브 개발팀 김진국
 * @version 1.0
 * @see
 * @since   2017/04/10
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2017/04/11  김진국          최초 생성
 *  </pre>
 *
 */

@Controller
//@RestController
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private MainService service;

	// 추가영역 시작
	@Autowired
	private ProductService productService;
	// 추가영역 종료

	@Autowired
	private BasketService basketService;

	@Autowired
	private WishService wishService;

	// 메인으로
	// 카테고리 때문에 코드 추가함 by minho
	@RequestMapping(value = {"/main", "/"}, method = RequestMethod.GET)
	public String mainGET(Model model) throws Exception {

		logger.info("lll~~~ 홈페이지 어디서 메인으로 . lll~~~");

		// 추가영역 시작
		CategoryResponse res = productService.categoryList2();

		List<CategoryLvResult> CL = res.getCategoryList();

		model.addAttribute("category", CL);
		// 추가영역 종료
		model.addAttribute("showMain", service.showMain());

		return "main/main";

	}


	@RequestMapping(value="header_info", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getHeaderInfo(HttpSession session) throws Exception {
		LoginDTO02 login = (LoginDTO02)session.getAttribute("login");
		BasketResponse basketList =  basketService.basketListGET(login.getToken());
		WishListResponse wishList = wishService.wishList2(login.getToken());
		Map<String, Object> headerInfo = new HashMap<>();
		headerInfo.put("basketList", basketList);
		headerInfo.put("wishlist", wishList);
		return headerInfo;

	}
}
