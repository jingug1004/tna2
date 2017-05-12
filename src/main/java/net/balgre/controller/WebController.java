package net.balgre.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * What : 테스트 클래스
 * Why :
 * How : 테스트 하고 지울꺼임. 신경쓰지 마셈.
 *
 * @author  김진국
 * @since   2017/04/10
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
 */
@Controller
public class WebController {
	/**
	 * Home string.
	 *
	 * @return the string
	 */
	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "Hello World, Balgre Test 02 03";
//		return "/index";
	}

}
