/**
 * Created by user on 2017-04-14 오후 6:05
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

import net.balgre.service.PlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by user on 2017-04-14 오후 6:05
 * Prac / net.balgre.controller
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
 *  2017/04/14  김진국          최초 생성
 *  </pre>
 *
 */

@Controller
public class PlanController {

    private static final Logger logger = LoggerFactory.getLogger(PlanController.class);

    @Autowired
    private PlanService service;

    // 기획전
    @RequestMapping(value = "/plan", method = RequestMethod.GET)
    public String planGET(Model model) throws Exception {

        logger.info("lll~~~ 기획전으로 . lll~~~");

        model.addAttribute("showPlan", service.showPlan());

        return "plan/plan";

    }

}